package config;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpec {

    public static RequestSpecification requestSpec(){
        return given()
                .config(RestAssured.config()
                        .logConfig(LogConfig.logConfig()
                                .blacklistHeader("Authorization")
                                .enableLoggingOfRequestAndResponseIfValidationFails()))
                .baseUri(ApiConfig.BASE_URL)
                .auth()
                .preemptive()
                .basic(ApiConfig.EMAIL, ApiConfig.API_KEY)
                .contentType("application/json")
                .log().ifValidationFails();
    }
}
