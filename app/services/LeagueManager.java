package services;

import entities.FootballClub;
import entities.MatchPlayed;

import java.io.IOException;

public interface LeagueManager {

    void addFootballClub(FootballClub footballClub) throws IllegalArgumentException;
    void deleteFootballClub(String clubName) throws IllegalArgumentException;
    void displayStatistics(String clubName) throws IllegalArgumentException;
    void displayLeagueTable();
    void displayMatches();
    void saveToFile() throws IOException;
    void readFile() throws IOException, ClassNotFoundException;
    void addMatch(MatchPlayed matchPlayed) throws Exception;
    MatchPlayed createRandomMatch() throws IllegalArgumentException;

}
