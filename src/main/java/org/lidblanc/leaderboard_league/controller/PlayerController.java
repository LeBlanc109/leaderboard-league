package org.lidblanc.leaderboard_league.controller;

import org.lidblanc.leaderboard_league.boundary.RiotApiComponent;
import org.lidblanc.leaderboard_league.dto.UserDto;
import org.lidblanc.leaderboard_league.entity.RiotAccount;
import org.lidblanc.leaderboard_league.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/riot")
public class
PlayerController {

    @Autowired
    AccountService accountService;

    private final RiotApiComponent riot;

    public PlayerController(RiotApiComponent riot) {
        this.riot = riot;
    }

    @GetMapping("/account")
    public UserDto account(@RequestParam String gameName, @RequestParam String tagLine) {
        return riot.getAccountByRiotId(gameName, tagLine);
    }

    //Starting here:
    @PostMapping("/addAccount")
    public ResponseEntity<?> registerAccount(@RequestBody RiotAccount riotAccount) {
        RiotAccount trackedAccount = accountService.trackAccount(riotAccount);
        return ResponseEntity.ok(trackedAccount);
    }

    // get match list, attach it to dto
    @GetMapping("/matches")
    public UserDto getMatches(@RequestParam String gameName, @RequestParam String tagLine) {
        // First get the account info (for puuid)
        UserDto user = riot.getAccountByRiotId(gameName, tagLine);
        // Then populate its match list
        riot.getMatchListByPuuid(user);
        return user;
    }
}
