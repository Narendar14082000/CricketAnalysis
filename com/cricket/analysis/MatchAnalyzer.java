package com.cricket.analysis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchAnalyzer {
    public String getHighestScore(JSONArray matches) throws JSONException {
        int highestScore = 0;
        String teamName = "";

        for (int i = 0; i < matches.length(); i++) {
            JSONObject match = matches.getJSONObject(i);
            if (match.has("t1s") && match.has("t2s")) {
                int team1Score = extractScore(match.getString("t1s"));
                int team2Score = extractScore(match.getString("t2s"));

                if (team1Score > highestScore) {
                    highestScore = team1Score;
                    teamName = match.getString("t1");
                }
                if (team2Score > highestScore) {
                    highestScore = team2Score;
                    teamName = match.getString("t2");
                }
            }
        }

        return "Highest Score : " + highestScore + " and Team Name is : " + teamName;
    }

    public int getMatchesWith300PlusScore(JSONArray matches) throws JSONException {
        int matchesCount = 0;

        for (int i = 0; i < matches.length(); i++) {
            JSONObject match = matches.getJSONObject(i);
            if (match.has("t1s") && match.has("t2s")) {
                int team1Score = extractScore(match.getString("t1s"));
                int team2Score = extractScore(match.getString("t2s"));

                if (team1Score + team2Score >= 300) {
                    matchesCount++;
                }
            }
        }

        return matchesCount;
    }

    private int extractScore(String scoreString) {
        String[] parts = scoreString.split("/");
        if (parts.length > 0) {
            try {
                return Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing score: " + scoreString);
            }
        }
        return 0;
    }
}
