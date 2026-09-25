package tests;

import clients.CaseApi;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;

public class CaseApiTest {
    CaseApi caseApi = new CaseApi();

    @Test
    @DisplayName("API-005 Получение конкретного Test Case")
    void getTestCaseTest(){
        int caseId = 67;
        Response response = caseApi.getTestCase(caseId);
        response.then()
                .statusCode(200)
                .body("id", equalTo(caseId))
                .body("title", not(emptyString()));
    }

    @Test
    @DisplayName("API-006 Получение несуществующего Test Case")
    void getNonExistentTestCaseTest() {
        int caseId = 999_999_999;
        Response response = caseApi.getTestCase(caseId);
        response.then()
                .statusCode(400)
                .body("error", equalTo("Field :case_id is not a valid test case."));
    }

    @Test
    @DisplayName("API-007 Создание Test Case")
    void addTestCaseTest(){
        Faker faker = new Faker();
        int sectionId = 1;
        String title = "Created autotest " + faker.number().digits(6);
        Response response = caseApi.addTestCase(sectionId, title);
        response.then()
                .statusCode(200)
                .body("id",notNullValue())
                .body("section_id",equalTo(sectionId))
                .body("title", equalTo(title));

        int caseId = response.path("id");

        Response getResponse = caseApi.getTestCase(caseId);

        getResponse.then()
                .statusCode(200)
                .body("id", equalTo(caseId))
                .body("section_id", equalTo(sectionId))
                .body("title", equalTo(title));

        Response deleteResponce = caseApi.deleteTestCase(caseId);
        deleteResponce.then()
                        .statusCode(200);
        Response checkCorrectDelete = caseApi.getTestCase(caseId);
        checkCorrectDelete.then()
                .statusCode(400);
    }

    @Test
    @DisplayName("API-008 Обновление Test Case")
    void updateTestCaseTest(){
        Faker faker = new Faker();
        String update_title = "Update title for case 81: " + faker.pokemon().name();
        int caseId = 81;
        Response response = caseApi.updateTestCase(81, update_title);
        response.then()
                .statusCode(200)
                .body("title",equalTo(update_title))
                .body("id", equalTo(caseId));
    }
}
