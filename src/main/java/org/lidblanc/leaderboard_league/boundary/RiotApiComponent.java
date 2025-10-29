package org.lidblanc.leaderboard_league.boundary;
import org.lidblanc.leaderboard_league.config.ApiKeys;
import org.lidblanc.leaderboard_league.dto.UserDto;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;
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


}
