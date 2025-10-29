package org.lidblanc.leaderboard_league.repository;

import org.lidblanc.leaderboard_league.entity.RiotAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiotAccountRepository extends JpaRepository<RiotAccount, Long> {

    RiotAccount findByGameNameAndTagLine(String gameName, String tagLine);
}
