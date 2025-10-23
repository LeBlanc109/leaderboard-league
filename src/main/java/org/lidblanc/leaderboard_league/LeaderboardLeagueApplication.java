package org.lidblanc.leaderboard_league;

import java.net.http.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

//Spring Dependencies
import org.lidblanc.leaderboard_league.config.ApiKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class LeaderboardLeagueApplication {

	public static void main(String[] args) throws Exception {

        //SpringApplication.run(LeaderboardLeagueApplication.class, args);

        //Test from Docs:
        String apiKey = ApiKeys.RIOT_API_KEY_HERE;

        // 🔹 Replace with your own Riot ID
        String gameName = "AirBourneTiger";
        String tagLine  = "Fish";

        // Get PUUID, then Summoner info
        String puuid = getResponse(String.format(
                "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s",
                encode(gameName), encode(tagLine)), apiKey);

        System.out.println("\nPUUID Response:\n" + puuid);

        // Quick way to extract the puuid field (for demo only)
        String extractedPuuid = puuid.split("\"puuid\":\"")[1].split("\"")[0];

        String summoner = getResponse(String.format(
                "https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/%s",
                extractedPuuid), apiKey);

        System.out.println("\nSummoner Response:\n" + summoner);
    }

    private static String getResponse(String url, String apiKey) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Riot-Token", apiKey)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP " + response.statusCode() + ": " + response.body());
        }
        return response.body();
    }

    private static String encode(String input) {
        return URLEncoder.encode(input, StandardCharsets.UTF_8);

	}

}
