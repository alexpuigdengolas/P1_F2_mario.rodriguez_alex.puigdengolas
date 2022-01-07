package Business;

public class Player {
    private String name;
    private int investigationPoints;
    private String role;

    public Player() {
        this.name = "Pepe";
        this.investigationPoints = 5;
        this.role = "Enginyer";
    }

    public Player(String name, int investigationPoints) {
        this.name = name;
        this.investigationPoints = investigationPoints;
        this.role = "Enginyer";
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void checkRole(){
        if(investigationPoints >= 10 && role.equals("Enginyer")){
            this.role = "Màster";
            this.investigationPoints = 5;
        }else if(investigationPoints >= 10 && role.equals("Màster")){
            this.role = "Doctor";
            this.investigationPoints = 5;
        }
    }
}
