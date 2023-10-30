package entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatchPlayedTest{

    private final FootballClub testClub1 = new FootballClub("Liverpool","JurgonKlopp","liverpool");
    private final FootballClub testClub2 = new FootballClub("Chelsea","Lampard","chelsea");
    private final DateTime dateTime = new DateTime(1,1,2021);
    private final MatchPlayed testMatchPlayed = new MatchPlayed(testClub1,testClub2,5,3,dateTime);

    @Test
    public void testMatchInitialization(){
        MatchPlayed matchPlayed = new MatchPlayed(testClub1,testClub2,5,3,dateTime);
        assertEquals(testClub1,matchPlayed.getTeam1());
        assertEquals(testClub2,matchPlayed.getTeam2());
        assertEquals("Liverpool",matchPlayed.getTeam1Name());
        assertEquals("Chelsea",matchPlayed.getTeam2Name());
        assertEquals(5,matchPlayed.getTeam1Score());
        assertEquals(3,matchPlayed.getTeam2Score());
        assertEquals(dateTime,matchPlayed.getDateTime());
    }

    @Test
    public void testSetMethods(){
        testMatchPlayed.setTeam1(testClub2);
        assertEquals(testClub2,testMatchPlayed.getTeam1());
        testMatchPlayed.setTeam2(testClub1);
        assertEquals(testClub1,testMatchPlayed.getTeam2());
        testMatchPlayed.setTeam1Score(0);
        assertEquals(0,testMatchPlayed.getTeam1Score());
        testMatchPlayed.setTeam2Score(0);
        assertEquals(0,testMatchPlayed.getTeam2Score());

        //set date time
        DateTime testDateTime = new DateTime(1,1,2021);
        testMatchPlayed.setDateTime(testDateTime);
        assertEquals(testDateTime,testMatchPlayed.getDateTime());

        //test throwable errors
        assertThrows(IllegalArgumentException.class,() -> {
            testMatchPlayed.setTeam1Score(-2);
        });
        assertThrows(IllegalArgumentException.class,() -> {
            testMatchPlayed.setTeam2Score(-2);
        });
    }

    @Test
    public void testEquals(){
        MatchPlayed matchPlayed = new MatchPlayed(testClub1,testClub2,5,3,dateTime);
        assertTrue(testMatchPlayed.equals(matchPlayed));
        assertTrue(testMatchPlayed.equals(testMatchPlayed));
        assertFalse(testMatchPlayed.equals(null));
        assertFalse(testMatchPlayed.equals(testClub1));
    }

    @Test
    public void testCompareTo(){
        DateTime dateTime1 = new DateTime(2,1,2021);
        MatchPlayed matchPlayed1 = new MatchPlayed(testClub1,testClub2,5,3,dateTime1);
        assertEquals(-1,testMatchPlayed.compareTo(matchPlayed1));
    }

}