package clients;

import static clients.ProjectApi.midPath;
import static config.RequestSpec.requestSpec;

import io.restassured.response.Response;

import java.util.Map;

public class RunsApi {
    public Response getListTestRuns(int projectId){
        return requestSpec()
                .when()
                .get(midPath + "/get_runs/" + projectId);
    }

    public Response addTestRun(int projectId, int suiteId, String name) {
        return requestSpec()
                .body(Map.of(
                        "suite_id", suiteId,
                        "name", name
                ))
                .when()
                .post(midPath + "/add_run/" + projectId);
    }

    public Response getTestRun(int runId) {
        return requestSpec()
                .when()
                .get(midPath + "/get_run/" + runId);
    }

    public Response deleteTestRun(int runId) {
        return requestSpec()
                .when()
                .post(midPath + "/delete_run/" + runId);
    }
}
