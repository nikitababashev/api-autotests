package clients;

import io.restassured.response.Response;

import static config.RequestSpec.requestSpec;

public class ProjectApi {
    public static final String midPath = "/index.php?/api/v2";

    public Response getProjects(){
        return requestSpec()
                .when()
                .get(midPath + "/get_projects");
    }

    public Response getProject(int projectId){
        return requestSpec()
                .when()
                .get(midPath + "/get_project/" + projectId);
    }

    public Response getListTestCasesProject(int projectId, int suiteId) {
        return requestSpec()
                .when()
                .get(midPath + "/get_cases/" + projectId + "&suite_id=" + suiteId);

    }

    public Response getListTestCasesProject(int projectId) {
        return requestSpec()
                .when()
                .get(midPath + "/get_cases/" + projectId);
    }
}
