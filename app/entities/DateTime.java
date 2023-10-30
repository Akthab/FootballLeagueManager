package entities;

import java.io.Serializable;
import java.util.Objects;

public class DateTime implements Serializable,Comparable<DateTime> {

    private int year;
    private int month;
    private int day;

    public DateTime(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    @Override
    public String toString() {
        return "["+ day +"/"+ month+"/"+ year +"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DateTime dateTimeObj = (DateTime) obj;
        return year == dateTimeObj.year &&
                month == dateTimeObj.month &&
                day == dateTimeObj.day ;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > 2021){
            throw new IllegalArgumentException("Invalid Year!!! Select any year below 2021");
        } else {
            this.year = year;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month > 12 || month < 1){
            throw new IllegalArgumentException("Invalid Month!!!");
        } else {
            this.month = month;
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if(day > 31 || day < 1){
            throw new IllegalArgumentException("Invalid day!!!");
        } else {
            this.day = day;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    public int toDays(){
        return this.year*365 + this.month*12 + this.day;
    }

    @Override
    public int compareTo(DateTime o) {
        return toDays() - o.toDays();
    }
}
