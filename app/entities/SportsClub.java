package entities;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {

    private String name;
    private String location;
    private String managerName;

    public SportsClub(String name, String managerName, String location) {
        this.name = name;
        this.location = location;
        this.managerName = managerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return this.name.equals(((SportsClub) obj).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return "SportsClub{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
