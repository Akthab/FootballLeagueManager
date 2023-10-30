package entities;

import java.io.Serializable;
import java.util.Objects;

public class MatchPlayed implements Serializable, Comparable<MatchPlayed> {

    private FootballClub team1;
    private FootballClub team2;
    private int team1Score;
    private int team2Score;
    private DateTime dateTime;
    private final String team1Name;
    private final String team2Name;


    public MatchPlayed(FootballClub team1, FootballClub team2, int team1Score, int team2Score, DateTime dateTime) {
        this.team1 = team1;
        this.team2 = team2;
        setTeam1Score(team1Score);
        setTeam2Score(team2Score);
        this.dateTime = dateTime;
        this.team1Name = team1.getName();
        this.team2Name = team2.getName();
    }

    public String displayMatchStats() {
        return "MatchDay --- " + this.dateTime + "\n" +
                this.team1.getName() + " " +  this.team1Score + " : " + this.team2Score + " " + this.team2.getName() ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MatchPlayed matchObj = (MatchPlayed) obj;
        return ( Objects.equals(team1, matchObj.team1) || Objects.equals(team2, matchObj.team2) ) &&
                Objects.equals(dateTime, matchObj.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team1, team2, dateTime);
    }

    public void updateStats() {
        team1.updateStats(team1Score,team2Score);
        team2.updateStats(team2Score,team1Score);
    }

    public FootballClub getTeam1() {
        return team1;
    }

    public void setTeam1(FootballClub team1) {
        this.team1 = team1;
    }

    public FootballClub getTeam2() {
        return team2;
    }

    public void setTeam2(FootballClub team2) {
        this.team2 = team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        if (team1Score >= 0)
            this.team1Score = team1Score;
        else
            throw new IllegalArgumentException("Score cannot be negative");
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        if (team2Score >= 0)
            this.team2Score = team2Score;
        else
            throw new IllegalArgumentException("Score cannot be negative");
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    @Override
    public String toString(){
        return this.team1.getName() + "," + this.team2.getName() + "," + this.team1Score + "," + this.team2Score + "," + this.dateTime;
    }

    @Override
    public int compareTo(MatchPlayed o) {
        return this.dateTime.compareTo(o.getDateTime());
    }
}
