package clients;

import io.restassured.response.Response;

import java.util.Map;

import static config.RequestSpec.requestSpec;
import static clients.ProjectApi.midPath;

public class CaseApi {
    public Response getTestCase(int caseId){
        return requestSpec()
                .when()
                .get(midPath + "/get_case/" + caseId);
    }

    public Response addTestCase(int sectionId, String title) {
        return requestSpec()
                .body(Map.of("title", title))
                .when()
                .post(midPath + "/add_case/" + sectionId);
    }

    public Response deleteTestCase(int caseId){
        return requestSpec()
                .when()
                .post(midPath + "/delete_case/" + caseId);
    }

    public Response updateTestCase(int caseId, String title){
        return requestSpec()
                .body(Map.of("title", title))
                .when()
                .post(midPath + "/update_case/" + caseId);
    }
}
