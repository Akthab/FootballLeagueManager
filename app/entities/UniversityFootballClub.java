package entities;

public class UniversityFootballClub extends FootballClub {

    private String universityName;

    public UniversityFootballClub(String name, String managerName, String location, String universityName) {
        super(name, managerName, location);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
