package org.lidblanc.leaderboard_league.dto;

import java.util.List;

public class UserDto {

    private String puuid;
    private String gameName;
    private String tagLine;
    private List<String> matchIds;

    public UserDto(){
    }

    public UserDto(String puuid, String gameName, String tagLine, List<String> matchIds) {
        this.puuid = puuid;
        this.gameName = gameName;
        this.tagLine = tagLine;
        this.matchIds = matchIds;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public List<String> getMatchIds() {
        return matchIds;
    }

    public void setMatchIds(List<String> matchIds) {
        this.matchIds = matchIds;
    }
}
