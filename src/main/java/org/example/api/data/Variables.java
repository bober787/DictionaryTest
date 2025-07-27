package org.example.api.data;

public class Variables {
    public static final String BASE_URL = "http://localhost:8000";

    // Form Param names
    public static final String PARAM_GRANT_TYPE = "grant_type";
    public static final String PARAM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String PARAM_GRANT_TYPE_PASSWORD = "password";
    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String PARAM_CLIENT_SECRET = "client_secret";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String ACCESS_TOKEN = "access_token";

    // JSON Response fields
    public static final String JSON_ACCESS_TOKEN = "access_token";
    public static final String JSON_REFRESH_TOKEN = "refresh_token";

    // JSON Field names
    public static final String JSON_FIELD_ID = "id";
    public static final String JSON_FIELD_USERNAME = "username";
    public static final String JSON_FIELD_EMAIL = "email";
    public static final String JSON_FIELD_FULLNAME = "fullname";
    public static final String JSON_FIELD_BIO = "bio";
    public static final String JSON_FIELD_PHOTO_PATH = "photo_path";

    // Data
    public static String LOGIN = "admintest";
    public static String PASSWORD = "admintest";
    public static String ID = "";
    public static String USERNAME = "";
    public static String EMAIL = "";
    public static String FULLNAME = "";
    public static String BIO = "";
    public static String PHOTO_PATH = "";
    public static String CLIENT_ID = "";
    public static String CLIENT_SECRET = "";

    // Header
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_TYPE = "Bearer ";
}