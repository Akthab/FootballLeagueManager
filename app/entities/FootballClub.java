package entities;

public class FootballClub extends SportsClub implements Comparable {

    private int wins;
    private int loses;
    private int draws;
    private int matchesPlayed;
    private int goalsScored;
    private int goalsConceded;
    private int points;
    private int goalDifference;

    public FootballClub(String name, String managerName, String location){
        super(name, managerName, location);
    }

    public FootballClub(String name,String managerName, String location,
                        int wins, int draws, int loses, int goalsScored,
                        int goalsConceded){
        super(name, managerName, location);
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.matchesPlayed = wins + loses + draws;
    }

    @Override
    public String toString() {
        return "FootballClub{" +
                "wins=" + wins +
                ", loses=" + loses +
                ", draws=" + draws +
                ", matchesPlayed=" + matchesPlayed +
                ", goalsScored=" + goalsScored +
                ", goalsConceded=" + goalsConceded +
                ", points=" + points +
                '}';
    }

    // This method is used to update the stats after a match is played
    // Therefore this method is called on the club after a match is created
    // Takes two parameters goals scored and goals conceded
    public void updateStats(int goalsScored, int goalsConceded){
        this.matchesPlayed += 1;
        this.goalsScored += goalsScored;
        this.goalsConceded += goalsConceded;
        if (goalsScored > goalsConceded){
            this.wins += 1;
        } else if (goalsScored == goalsConceded){
            this.draws += 1;
        } else {
            this.loses += 1;
        }
        this.points = getPoints();
        this.goalDifference = getGoalDifference();
    }

    @Override
    public int compareTo(Object o) {
        int comparePoints = this.getPoints() - ((FootballClub) o).getPoints();
        return (comparePoints > 0) ? 1 : (comparePoints < 0) ? -1
                : this.getGoalDifference() > ((FootballClub) o).getGoalDifference()? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int getPoints() {
        return this.wins*3 + this.draws;
    }

    public int getGoalDifference(){
        this.goalDifference = goalsScored - goalsConceded;
        return this.goalDifference;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLoses() {
        return this.loses;
    }

    public int getDraws() {
        return this.draws;
    }

    public int getMatchesPlayed() {
        return this.wins + this.loses + this.draws;
    }

    public int getGoalsScored() {
        return this.goalsScored;
    }

    public int getGoalsConceded() {
        return this.goalsConceded;
    }
}
