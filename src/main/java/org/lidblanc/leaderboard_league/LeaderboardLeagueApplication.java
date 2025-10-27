package org.lidblanc.leaderboard_league;

//Spring Dependencies
import org.lidblanc.leaderboard_league.config.ApiKeys;
import org.lidblanc.leaderboard_league.entity.Player;
import org.lidblanc.leaderboard_league.service.PlayerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class LeaderboardLeagueApplication {

	public static void main(String[] args) throws Exception {
        //SpringApplication.run(LeaderboardLeagueApplication.class, args);

        PlayerService playerService = new PlayerService();
        playerService.getAirBourneTiger();
    }

}
