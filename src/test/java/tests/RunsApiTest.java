package tests;

import clients.RunsApi;
import clients.RunsApi.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@Epic("RunsApiTest")
public class RunsApiTest {

    RunsApi runsApi = new RunsApi();

    @Test
    @Story("Получение списка Test Runs")
    @DisplayName("API-009 Получение списка Test Runs")
    void getListTestRunsTest(){
        int projectId = 1;

        Response response = runsApi.getListTestRuns(projectId);
        response.then()
                .statusCode(200)
                .body("runs", not(empty()))
                .body("runs.project_id", everyItem(equalTo(projectId)));
    }

    @Test
    @Story("Создание Test Run")
    @DisplayName("API-010 Создание Test Run")
    void createTestRunTest(){
        Faker faker = new Faker();

        int projectId = 1;
        int suiteId = 1;
        String name = "Created autorun " + faker.number().digits(6);

        Response response = runsApi.addTestRun(projectId, suiteId, name);
        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("project_id",equalTo(projectId))
                .body("suite_id", equalTo(suiteId))
                .body("name", equalTo(name));

        int idCreatedTestRun = response.path("id");

        Response getResponse = runsApi.getTestRun(idCreatedTestRun);

        getResponse.then()
                .statusCode(200)
                .body("id", equalTo(idCreatedTestRun))
                .body("project_id", equalTo(projectId))
                .body("suite_id", equalTo(suiteId))
                .body("name", equalTo(name));

        Response deleteResponse = runsApi.deleteTestRun(idCreatedTestRun);
        deleteResponse.then()
                .statusCode(200);

        Response checkResponse = runsApi.getTestRun(idCreatedTestRun);
        checkResponse.then()
                .statusCode(400);
    }
}
