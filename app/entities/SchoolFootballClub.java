package entities;

public class SchoolFootballClub extends FootballClub {

    private String schoolName;

    public SchoolFootballClub(String name, String managerName, String location, String schoolName) {
        super(name, managerName, location);
        this.schoolName = schoolName;
    }



    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                "schoolName='" + schoolName + '\'' +
                "} " + super.toString();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
