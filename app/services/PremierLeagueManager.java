package services;
import entities.DateTime;
import entities.FootballClub;
import entities.MatchPlayed;

import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    private static PremierLeagueManager premierLeagueManager = null;

    private List<FootballClub> clubList = new ArrayList<>();
    private List<MatchPlayed> matchesPlayedList = new ArrayList<>();
    private final int MAX_CLUBS = 20;

    private PremierLeagueManager(){}

    public static PremierLeagueManager getInstance(){
        if (premierLeagueManager == null){
            synchronized (PremierLeagueManager.class){
                if (premierLeagueManager == null){
                    premierLeagueManager = new PremierLeagueManager();
                }
            }
        }
        return premierLeagueManager;
    }

    @Override
    public void addFootballClub(FootballClub footballClub) throws IllegalArgumentException {
        // Check if club already exists in list
        if (clubList.contains(footballClub)){
            throw new IllegalArgumentException("club already exists!");
            //Maximum number of clubs which can compete in the league is 20
            //reference : https://en.wikipedia.org/wiki/Premier_League
        } else if (clubList.size() < MAX_CLUBS){
            clubList.add(footballClub);
        } else {
            throw new IllegalArgumentException("maximum clubs in league reached");
        }
    }

    @Override
    public void deleteFootballClub(String clubName) throws IllegalArgumentException {
        boolean clubAvailable = false;
        Iterator<FootballClub> iterator = clubList.iterator();
        while (iterator.hasNext()){
            FootballClub club = iterator.next();
            if (club.getName().equals(clubName)){
                clubAvailable = true;
                iterator.remove();
            }
        }
        if (!clubAvailable){
            throw new IllegalArgumentException("club does not exist!");
        }
    }

    @Override
    public void addMatch(MatchPlayed matchPlayed) throws IllegalArgumentException {
        if (matchesPlayedList.contains(matchPlayed))
            throw new IllegalArgumentException("match already exists!");
        else {
            matchesPlayedList.add(matchPlayed);
            matchPlayed.updateStats();
        }
    }

    //Only to display club stats on console
    @Override
    public void displayStatistics(String clubName) throws IllegalArgumentException {
        boolean clubAvailable = false;
        for (FootballClub footballClub: clubList){
            if (clubName.equals(footballClub.getName())){
                clubAvailable = true;
                System.out.println("Club Name : " + footballClub.getName());
                System.out.println("Location : " + footballClub.getLocation());
                System.out.println("Manager Name : " + footballClub.getManagerName());

                //Displays all matches played by club
                //Matches played by club sorted by date
                Collections.sort(matchesPlayedList);
                System.out.println("\n---- Season Stats ---- ");
                for (MatchPlayed matchPlayed: matchesPlayedList){
                    if (footballClub.equals(matchPlayed.getTeam1()) || footballClub.equals(matchPlayed.getTeam2()))
                        System.out.println("\n" + matchPlayed.displayMatchStats());
                }
            }
        }
        //If club not available displays error message
        if (!clubAvailable){
            throw new IllegalArgumentException("Club does not exist");
        }
    }

    // Only to display table on console
    @Override
    public void displayLeagueTable() {
        clubList.sort(Collections.reverseOrder());
        System.out.printf("%-20s | %3s | %3s | %3s | %3s | %3s | %3s | %3s | %3s |"
                ,"Name","MP","W","D","L","GF","GA","GD","Pts");
        System.out.println("\n----------------------------------------------------------------------");
        int i = 1;
        for (FootballClub club: clubList){
            System.out.printf("%-20s | %3s | %3s | %3s | %3s | %3s | %3s | %3s | %3s |\n",
                    i + "  " +club.getName(),
                    club.getMatchesPlayed(),
                    club.getWins(),
                    club.getDraws(),
                    club.getLoses(),
                    club.getGoalsScored(),
                    club.getGoalsConceded(),
                    club.getGoalDifference(),
                    club.getPoints());
            i += 1;
        }
    }

    //displays matches on console
    @Override
    public void displayMatches() {
        Collections.sort(matchesPlayedList);
        for (MatchPlayed matchPlayed: matchesPlayedList){
            System.out.println("\n" + matchPlayed.displayMatchStats());
        }
    }

    @Override
    public void saveToFile() throws IOException {
        ObjectOutputStream writeClubData = new ObjectOutputStream(new FileOutputStream("club-data.txt"));
        for (FootballClub club: clubList){
            writeClubData.writeObject(club);
        }
        writeClubData.close();
        ObjectOutputStream writeMatchData = new ObjectOutputStream(new FileOutputStream("match-data.txt"));
        for (MatchPlayed match: matchesPlayedList){
            writeMatchData.writeObject(match);
        }
        writeMatchData.close();
    }

    @Override
    public void readFile() throws IOException, ClassNotFoundException {
        ObjectInputStream readClubData = new ObjectInputStream(new FileInputStream("club-data.txt"));
        ObjectInputStream readMatchData = new ObjectInputStream(new FileInputStream("match-data.txt"));
        this.clubList.clear();
        this.matchesPlayedList.clear();
        for(;;){
            try {
                Object obj = readClubData.readObject();
                clubList.add((FootballClub) obj);
            } catch (EOFException e){
                break;
            }

        }
        readClubData.close();
        for(;;){
            try {
                Object obj = readMatchData.readObject();
                matchesPlayedList.add((MatchPlayed) obj);
            } catch (EOFException e){
                break;
            }
        }
        readMatchData.close();
    }

    @Override
    public MatchPlayed createRandomMatch() throws IllegalArgumentException {
        Random random = new Random();
        FootballClub team1;
        FootballClub team2;

        if (!(clubList.size() < 2)){
            while (true){
                team1 = clubList.get(random.nextInt(clubList.size()));
                team2 = clubList.get(random.nextInt(clubList.size()));
                if (!team1.equals(team2))
                    break;
            }
            int team1score = random.nextInt(10);
            int team2score = random.nextInt(10);
            DateTime dateTime = new DateTime(random.nextInt(30)+1,random.nextInt(12)+1,2020);

            MatchPlayed matchPlayed = new MatchPlayed(team1, team2, team1score, team2score, dateTime);
            matchesPlayedList.add(matchPlayed);
            matchPlayed.updateStats();
            return matchPlayed;
        } else
            throw new IllegalArgumentException("Cannot generate match since less than 2 clubs in league");

    }

    public List<FootballClub> getClubList() {
        clubList.sort(Collections.reverseOrder());
        return clubList;
    }

    public List<MatchPlayed> getMatchesPlayedList() {
        Collections.sort(matchesPlayedList);
        return matchesPlayedList;
    }

    public int getMAX_CLUBS() {
        return MAX_CLUBS;
    }
}
