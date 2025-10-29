package org.example.api.config;

public class ApiConfig {
    public static final String AUTH_TOKEN = "/auth/token";
    public static final String REFRESH_TOKEN = "/auth/token/refresh";

    public static final String USER_PROFILE = "/user/profile";
    public static final String PUBLIC_PROFILE = "/user/profile/{username}";
    public static final String USER_PHOTO = "/user/photo/{username}";

    public static final String DICTIONARIES = "/dictionaries/";
    public static final String USER_DICTIONARIES = "/dictionaries/user/{username}";
    public static final String DICTIONARY_BY_ID = "/dictionaries/{dictionary_id}";
}
