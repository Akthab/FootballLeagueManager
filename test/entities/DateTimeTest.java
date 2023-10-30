package entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTimeTest{

    @Test
    public void testInitialization(){
        DateTime dateTime = new DateTime(5,2,2020);
        assertEquals(5,dateTime.getDay());
        assertEquals(2,dateTime.getMonth());
        assertEquals(2020,dateTime.getYear());
    }

    @Test
    public void testSetDay(){
        DateTime dateTime = new DateTime(5,2,2020);
        dateTime.setDay(21);
        assertEquals(21,dateTime.getDay());
        assertThrows(IllegalArgumentException.class,() -> {
            dateTime.setDay(36);
        });
    }

    @Test
    public void testMonth(){
        DateTime dateTime = new DateTime(5,2,2020);
        dateTime.setMonth(5);
        assertEquals(5,dateTime.getDay());
        assertThrows(IllegalArgumentException.class, () -> {
            dateTime.setMonth(25);
        });
    }

    @Test
    public void testYear(){
        DateTime dateTime = new DateTime(5,2,2020);
        dateTime.setYear(2019);
        assertEquals(2019,dateTime.getYear());
        assertThrows(IllegalArgumentException.class,() -> {
            dateTime.setYear(2029);
        });
    }

    @Test
    public void testCompareTo(){
        DateTime dateTime1 = new DateTime(5,2,2020);
        DateTime dateTime2 = new DateTime(6,2,2020);

        assertEquals(-1,dateTime1.compareTo(dateTime2));
    }

    @Test
    public void testEquals(){
        DateTime dateTime1 = new DateTime(5,2,2020);
        DateTime dateTime1same = new DateTime(5,2,2020);
        DateTime dateTime2 = new DateTime(6,2,2020);
        assertTrue(dateTime1.equals(dateTime1));
        assertTrue(dateTime1.equals(dateTime1same));
        assertFalse(dateTime1.equals(dateTime2));
    }


}