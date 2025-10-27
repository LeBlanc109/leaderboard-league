package org.lidblanc.leaderboard_league.controller;

import org.lidblanc.leaderboard_league.boundary.RiotApiComponent;
import org.lidblanc.leaderboard_league.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/riot")
public class PlayerController {

    private final RiotApiComponent riot;

    public PlayerController(RiotApiComponent riot) {
        this.riot = riot;
    }

    @GetMapping("/account")
    public UserDto account(@RequestParam String gameName, @RequestParam String tagLine) {
        return riot.getAccountByRiotId(gameName, tagLine);
    }
}
