package com.example.base;

import java.util.Properties;

public class Config {

    private static Properties properties = new Properties();

    public static String get(String key, String defaultValue) {
        // Priority order: System property > Env var > config.properties > default
        String value = System.getProperty(key);

        if (value == null || value.isEmpty()) {
            value = System.getenv(key);
        }

        if (value == null || value.isEmpty()) {
            value = properties.getProperty(key);
        }

        return (value == null || value.isEmpty()) ? defaultValue : value;
    }

    public static String getExecutionEnv() {
        return get("execution", "local");
    }

    public static String getHubHost() {
        return get("HUB_HOST", "selenium-hub");
    }

    public static String getBaseUrl() {
        return get("baseUrl", "http://localhost:8080");
    }
}
