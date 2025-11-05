package org.lidblanc.leaderboard_league.boundary;
import org.lidblanc.leaderboard_league.config.ApiKeys;
import org.lidblanc.leaderboard_league.dto.UserDto;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

@Component
public class RiotApiComponent {

    private String apiKey = ApiKeys.RIOT_API_KEY_HERE;
    private final RestTemplate restTemplate = new RestTemplate();

    //So in reality, I want this method to persist the RIOT account to the database, so far we have just created a dto
    public UserDto getAccountByRiotId(String gameName, String tagLine) {
        String url = String.format(
                "https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s",
                gameName, tagLine
        );
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDto> response =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), UserDto.class);
        return response.getBody();
    }

    //if i want match id's I need a list, it gets a list
    public UserDto getMatchListByPuuid(UserDto userDto){
        String url = String.format(
                "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids",
                userDto.getPuuid()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Riot returns a JSON array: ["NA1_123", "NA1_456", ...]
        ResponseEntity<String[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String[].class
        );

        // Convert array → List
        List<String> matchIds = Arrays.asList(response.getBody());
        userDto.setMatchIds(matchIds);  // attach it to the existing DTO

        return userDto;
    }

}
