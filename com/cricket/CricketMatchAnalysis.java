package com.cricket;

import com.cricket.analysis.MatchAnalyzer;
import com.cricket.config.Config;
import com.cricket.fetch.ApiFetcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Main class for cricket match analysis.
 */
public class CricketMatchAnalysis {
    /**
     * Main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Config config = new Config("config.properties");
            ApiFetcher apiFetcher = new ApiFetcher(config.getApiUrl(), config.getApiKey());
            String jsonData = apiFetcher.fetchData();

            if (jsonData != null) {
                JSONObject jsonObject = new JSONObject(jsonData);
                if (jsonObject.has("data")) {
                    JSONArray matches = jsonObject.getJSONArray("data");
                    MatchAnalyzer matchAnalyzer = new MatchAnalyzer();
                    String highestScoreDetails = matchAnalyzer.getHighestScore(matches);
                    int matchesWith300PlusScore = matchAnalyzer.getMatchesWith300PlusScore(matches);

                    System.out.println(highestScoreDetails);
                    System.out.println("Number Of Matches with total 300 Plus Score : " + matchesWith300PlusScore);
                } else {
                    System.out.println("API response does not contain expected data.");
                }
            } else {
                System.out.println("Failed to fetch data from the API.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("Error parsing JSON data: " + e.getMessage());
        }
    }
}
