package utils;

import entities.FootballClub;

import java.util.Comparator;

public class SortByGF implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub club1, FootballClub club2) {
        return Integer.compare(club1.getGoalsScored(), club2.getGoalsScored());
    }
}
