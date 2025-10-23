package org.lidblanc.leaderboard_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.lidblanc.leaderboard_league.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository {
}
