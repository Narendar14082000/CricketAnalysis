package com.cricket.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads configuration settings from a file.
 */
public class Config {
    private Properties properties;

    /**
     * Constructs a Config object.
     *
     * @param configFile the path to the configuration file
     * @throws IOException if an I/O error occurs
     */
    public Config(String configFile) throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
        }
    }

    /**
     * Gets the API URL from the configuration.
     *
     * @return the API URL
     */
    public String getApiUrl() {
        return properties.getProperty("apiUrl");
    }

    /**
     * Gets the API key from the configuration.
     *
     * @return the API key
     */
    public String getApiKey() {
        return properties.getProperty("apiKey");
    }
}
