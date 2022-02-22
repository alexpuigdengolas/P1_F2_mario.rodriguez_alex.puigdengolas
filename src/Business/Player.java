package Business;

import Business.Role.Doctor;
import Business.Role.Master;

public class Player {
    private String name;
    private int investigationPoints;

    public Player() {
        this.name = "Pepe";
        this.investigationPoints = 5;
    }

    public Player(String name, int investigationPoints) {
        this.name = name;
        this.investigationPoints = investigationPoints;
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

    public Player checkRole(){
        return this;
    }


    //Publication
    public void getRewardPublication(String quartil) {
    }
    public void getPenalizationPublication(String quartil){

    }

    //Doctoral Defense
    public void getRewardDefense() {

    }
    public void getPenalizationDefense(){

    }

    //Master
    public void getRewardMaster() {

    }
    public void getPenalizationMaster(){

    }

    //Budget
    public void getRewardBudget() {
        this.setInvestigationPoints(((int) Math.ceil(this.getInvestigationPoints()/2)) + this.getInvestigationPoints());
    }
    public void getPenalizationBudget(){

    }
}
