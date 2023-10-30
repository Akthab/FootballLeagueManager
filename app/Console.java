import entities.DateTime;
import entities.FootballClub;
import entities.MatchPlayed;
import services.LeagueManager;
import services.PremierLeagueManager;

import java.io.IOException;
import java.util.*;

public class Console {

    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner sc2 = new Scanner(System.in);
    private static final LeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    private static final List<FootballClub> clubList = PremierLeagueManager.getInstance().getClubList();

    public static void main(String[] args) {
        readData();
        mainMenu:
        while (true){
            try {
                System.out.println("\nPremier League Manager - Main Menu");
                System.out.println("1. Add Club");
                System.out.println("2. Delete Club");
                System.out.println("3. Display League Table");
                System.out.println("4. Add New Match");
                System.out.println("5. Display Statistics");
                System.out.println("0. Quit");
                System.out.print("Select Option : ");
                int mainSelection = sc2.nextInt();

                switch (mainSelection){
                    case 1:
                        addClub();
                        saveChanges();
                        break;
                    case 2:
                        deleteClub();
                        saveChanges();
                        break;
                    case 3:
                        readData();
                        displayTable();
                        break;
                    case 4:
                        readData();
                        addMatch();
                        saveChanges();
                        break;
                    case 5:
                        readData();
                        displayStatistics();
                        break;
                    case 0:
                        break mainMenu;
                    default:
                        System.out.println("Invalid Selection!!! please enter valid integer(0-5)\n");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid Input!!! Please enter an integer...");
                sc2.next();
            }
        }
    }

    private static void displayStatistics() {
        System.out.print("Enter club name : ");
        String clubName = sc.nextLine();
        try{
            premierLeagueManager.displayStatistics(clubName);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addClub() {
        System.out.print("Enter club name : ");
        String clubName = sc.nextLine();
        System.out.print("Enter club location : ");
        String location = sc.nextLine();
        System.out.print("Enter manager name : ");
        String managerName = sc.nextLine();

        try {
            premierLeagueManager.addFootballClub(new FootballClub(clubName, managerName, location));
            System.out.println("Club successfully added!!!");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private static void deleteClub() {
        System.out.print("Enter club name : ");
        String clubName = sc.nextLine();
        try {
            premierLeagueManager.deleteFootballClub(clubName);
            System.out.println("club was deleted successfully!");
        } catch (Exception e){
            System.out.println("club does not exist!!! Please enter again...");
        }
    }

    private static void addMatch() {
        FootballClub team1;
        FootballClub team2;

        try {
            mainLoop1:
            while (true){
                System.out.print("Enter team 1 name : ");
                String team1Name = sc.nextLine();
                for (FootballClub club: clubList){
                    if(team1Name.equals(club.getName())){
                        team1 = club;
                        break mainLoop1;
                    }
                }
                System.out.println("Invalid team!!! Please enter again...");
            }

            mainLoop2:
            while (true){
                System.out.print("Enter team 2 name : ");
                String team2Name = sc.nextLine();
                for (FootballClub club: clubList){
                    if(team2Name.equals(club.getName())){
                        team2 = club;
                        break mainLoop2;
                    }
                }
                System.out.println("Invalid team!!! Please enter again...");
            }

            System.out.print("Enter team 1 score : ");
            int team1Score = sc2.nextInt();
            System.out.print("Enter team 2 score : ");
            int team2Score = sc2.nextInt();

            System.out.println("Enter Match Date");
            System.out.print("Day : ");
            int day = sc2.nextInt();
            System.out.print("Month : ");
            int month = sc2.nextInt();
            System.out.print("Year : ");
            int year = sc2.nextInt();

            premierLeagueManager.addMatch(
                    new MatchPlayed(team1,team2,team1Score,team2Score,
                            new DateTime(day,month,year)
                    )
            );
            
        } catch (InputMismatchException e){
            System.out.println("Invalid entry!!! Please enter again...");
        } catch (IllegalArgumentException x){
            System.out.println(x.getMessage());
        } catch (Exception e) {
            System.out.println("A team can have only one match per day!!! \n" +
                    "Please try a different date or change team with the match on this date...");
        }
    }

    private static void displayTable() {
        premierLeagueManager.displayLeagueTable();
    }

    private static void saveChanges(){
        try {
            premierLeagueManager.saveToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong!!! unable to save data...");
        }
    }

    private static void readData(){
        try {
            premierLeagueManager.readFile();
            System.out.println("Loading saved data...");
            System.out.println("Data has been loaded successfully!");
        } catch (IOException e) {
            System.out.println("Oops!!! Something went wrong...");
            System.out.println("Unable to read saved data");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
