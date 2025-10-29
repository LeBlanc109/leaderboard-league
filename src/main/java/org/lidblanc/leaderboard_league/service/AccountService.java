package org.lidblanc.leaderboard_league.service;

import org.lidblanc.leaderboard_league.entity.RiotAccount;
import org.lidblanc.leaderboard_league.repository.RiotAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {
    RiotAccountRepository riotAccountRepository;

    @Autowired
    public AccountService(RiotAccountRepository riotAccountRepository){
        this.riotAccountRepository = riotAccountRepository;
    }

    //BASIC Check's to start tracking an account (adding it to the database)
    public RiotAccount trackAccount(RiotAccount riotAccount){

        if(riotAccount.getGameName() == null
            || riotAccount.getGameName().isEmpty()
            || riotAccount.getTagLine() == null
            || riotAccount.getTagLine().isEmpty()){
            return null;
        }

        RiotAccount existingAccount = riotAccountRepository.findByGameNameAndTagLine(riotAccount.getGameName(), riotAccount.getTagLine());

        if(existingAccount != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return riotAccountRepository.save(riotAccount);
    }
}
