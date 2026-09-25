package tests;

import clients.ProjectApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class ProjectApiTest {
    ProjectApi projectApi = new ProjectApi();

    @Test
    @DisplayName("API-001 Получение всех проектов")
    void getProjectsTest(){
        Response response = projectApi.getProjects();
        response.then()
                .statusCode(200)
                .body("projects", not(empty()))
                .body("projects.id", everyItem(notNullValue()))
                .body("projects.name", everyItem(not(emptyString())));
    }

    @Test
    @DisplayName("API-002 Получение конкретного проекта по ID")
    void getProjectTest(){
        int projectId = 1;

        Response response = projectApi.getProject(projectId);
        response.then()
                .statusCode(200)
                .body("id", equalTo(projectId))
                .body("name", equalTo("Sample Project"));
    }

    @Test
    @DisplayName("API-003 Получение несуществующего проекта")
    void getNonExistentProjectTest(){
        int projectId = 999_999_999;

        Response response = projectApi.getProject(projectId);
        response.then()
                .statusCode(400)
                .body("error", equalTo("Field :project_id is not a valid or accessible project."));
    }

    @Test
    @DisplayName("API-004 Получение списка Test Cases проекта")
    void getListTestCasesProjectTest() {
        int projectId = 1;
        int suiteId = 1;

        Response responseTwoParam = projectApi.getListTestCasesProject(projectId, suiteId);
        responseTwoParam.then()
                .statusCode(200)
                .body("cases", not(empty()))
                .body("cases.id", everyItem(notNullValue()))
                .body("cases.title", everyItem(not(emptyString())));

        Response responseOneParam = projectApi.getListTestCasesProject(projectId);
        responseOneParam.then()
                .statusCode(200)
                .body("cases", not(empty()))
                .body("cases.id", everyItem(notNullValue()))
                .body("cases.title", everyItem(not(emptyString())));
    }
}
