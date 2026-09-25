package config;

public class ApiConfig {
    public static final String BASE_URL = System.getProperty("baseUrl",
            "https://workspaceforapiandui.testrail.io");
    public static final String EMAIL = System.getenv("TEST_EMAIL");
    public static final String API_KEY = System.getenv("TEST_API_KEY");
}
