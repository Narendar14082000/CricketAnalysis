package com.cricket.fetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Fetches data from a specified API.
 */
public class ApiFetcher {
    private final String apiUrl;
    private final String apiKey;

    /**
     * Constructs an ApiFetcher object.
     *
     * @param apiUrl the URL of the API
     * @param apiKey the API key
     */
    public ApiFetcher(String apiUrl, String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    /**
     * Fetches data from the API.
     *
     * @return the fetched data as a string
     * @throws IOException if an I/O error occurs
     */
    public String fetchData() throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("apiKey", apiKey);

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }
}
