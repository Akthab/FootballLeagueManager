package entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class FootballClubTest {


    private final FootballClub club = new FootballClub("Manchester Utd","Van Gaal","Manchester",
            5,2,2,8,3);


    @Test
    public void testInitialization(){
        FootballClub clubTest = new FootballClub("Name","ManagerName","Location");
        assertEquals("Name",clubTest.getName());
        assertEquals("ManagerName",clubTest.getManagerName());
        assertEquals("Location",clubTest.getLocation());
    }

    @Test
    public void testSetName() {
        club.setName("Liverpool");
        assertEquals("Should return club name",
                "Liverpool",club.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("Manchester Utd",club.getName());
    }

    @Test
    public void testGetManagerName() {
        assertEquals("Van Gaal",club.getManagerName());
    }

    @Test
    public void testSetManagerName() {
        club.setManagerName("Pep");
        assertEquals("Pep",club.getManagerName());
    }

    @Test
    public void testGetLocation() {
        assertEquals("Manchester",club.getLocation());
    }

    @Test
    public void testSetLocation() {
        club.setLocation("Liverpool");
        assertEquals("Liverpool",club.getLocation());
    }

    @Test
    public void testEquals(){
        FootballClub testClub1 = new FootballClub("Manchester Utd","Van Gaal","Manchester",
                5,2,2,8,3);
        FootballClub testClub2 = new FootballClub("Manchester","Van Gaal","Manchester",
                5,2,2,8,3);
        assertTrue(club.equals(testClub1));
        assertFalse(club.equals(testClub2));
        assertFalse(club.equals(null));
    }

    @Test
    public void testGetWins(){
        assertEquals(5,club.getWins());
    }

    @Test
    public void testGetLoses(){
        assertEquals(2,club.getLoses());
    }

    @Test
    public void testGetDraws(){
        assertEquals(2,club.getDraws());
    }

    @Test
    public void testGoalsScored(){
        assertEquals(8,club.getGoalsScored());
    }

    @Test
    public void testGoalsConceded(){
        assertEquals(3,club.getGoalsConceded());
    }

    @Test
    public void testGoalDifference(){
        assertEquals(5,club.getGoalDifference());
    }

    @Test
    public void testMatchesPlayed(){
        assertEquals(9,club.getMatchesPlayed());
    }

    @Test
    public void testGetPoints(){
        assertEquals(17,club.getPoints());
    }

    @Test
    public void test1UpdateStats(){
        club.updateStats(2,1);
        assertEquals(6,club.getWins());
        assertEquals(10,club.getMatchesPlayed());
        assertEquals(20,club.getPoints());
    }

    @Test
    public void test2UpdateStats(){
        club.updateStats(2,4);
        assertEquals(3,club.getLoses());
        assertEquals(10,club.getMatchesPlayed());
        assertEquals(17,club.getPoints());
    }

    @Test
    public void test3UpdateStats(){
        club.updateStats(4,4);
        assertEquals(10,club.getMatchesPlayed());
        assertEquals(18,club.getPoints());
    }

    @Test
    public void test1CompareTo(){
        FootballClub testClub1 = new FootballClub("Liverpool","JurgonKlopp","liverpool",
                1,2,2,8,3);
        FootballClub testClub2 = new FootballClub("Chelsea","Lampard","chelsea",
                5,2,2,8,4);
        assertEquals("Should return 1 since testClub1 has lesser points",
                1,club.compareTo(testClub1));
        assertEquals("points are same but should return 1 since gd of testClub2 is lesser",
                1,club.compareTo(testClub2));
    }

    @Test
    public void test2CompareTo(){
        FootballClub testClub1 = new FootballClub("Liverpool","JurgonKlopp","liverpool",
                10,2,2,8,3);
        FootballClub testClub2 = new FootballClub("Chelsea","Lampard","chelsea",
                5,2,2,8,2);
        assertEquals("Should return -1 since testClub1 has more points",
                -1,club.compareTo(testClub1));
        assertEquals("points are same but should return -1 since gd of testClub2 is higher",
                -1,club.compareTo(testClub2));
    }



}