package Business.Role;

import Business.Player;

import java.util.List;

public class Enginyer extends Player {

    public Enginyer(){
        this.setName("Pepe");
        this.setInvestigationPoints(5);
    }

    public Enginyer(String name, int ip) {
        this.setName(name);
        this.setInvestigationPoints(ip);
    }

    //Publication
    @Override
    public void getRewardPublication(String quartil) {
        switch (quartil){
            case "Q1":
                this.setInvestigationPoints( this.getInvestigationPoints() + 4);

            case "Q2":
                this.setInvestigationPoints(this.getInvestigationPoints() + 3);

            case "Q3":
                this.setInvestigationPoints(this.getInvestigationPoints() + 2);

            case "Q4":
                this.setInvestigationPoints(this.getInvestigationPoints() + 1);

        }
        super.getRewardPublication(quartil);
    }
    @Override
    public void getPenalizationPublication(String quartil) {
        switch (quartil){
            case "Q1":
                this.setInvestigationPoints(this.getInvestigationPoints() - 5);

            case "Q2":
                this.setInvestigationPoints(this.getInvestigationPoints() - 4);

            case "Q3":
                this.setInvestigationPoints(this.getInvestigationPoints() - 3);

            case "Q4":
                this.setInvestigationPoints(this.getInvestigationPoints() - 2);
        }
        super.getPenalizationPublication(quartil);
    }

    //Defense
    @Override
    public void getRewardDefense() {
        super.getRewardDefense();
        this.setInvestigationPoints(this.getInvestigationPoints() + 5);
    }
    @Override
    public void getPenalizationDefense() {
        super.getPenalizationDefense();
        this.setInvestigationPoints(this.getInvestigationPoints() - 5);
    }

    //Master
    @Override
    public void getRewardMaster() {
        super.getRewardMaster();
        this.setInvestigationPoints(10);
    }
    @Override
    public void getPenalizationMaster() {
        super.getPenalizationMaster();
        this.setInvestigationPoints(this.getInvestigationPoints() -3);
    }

    //Budget
    @Override
    public void getPenalizationBudget() {
        super.getPenalizationBudget();
        this.setInvestigationPoints(this.getInvestigationPoints() - 2);
    }

    @Override
    public void checkRole(List<Player> players, int playerIteration) {
        Master player = new Master(this.getName(), 5);
        players.remove(playerIteration);
        players.add(playerIteration, player);
    }
}
