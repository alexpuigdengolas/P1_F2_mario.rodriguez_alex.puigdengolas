package Business;

public class Player {
    private String name;
    private int investigationPoints;

    public Player() {
        this.name = "Pepe";
        this.investigationPoints = 5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInvestigationPoints() {
        return investigationPoints;
    }

    public void setInvestigationPoints(int investigationPoints) {
        this.investigationPoints = investigationPoints;
    }
}