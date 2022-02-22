package Business.Role;

import Business.Player;

public class Doctor extends Player {
    //Publication
    @Override
    public void getRewardPublication(String quartil) {
        switch (quartil){
            case "Q1":
                this.setInvestigationPoints(this.getInvestigationPoints() + (4*2));

            case "Q2":
                this.setInvestigationPoints(this.getInvestigationPoints() + (3*2));

            case "Q3":
                this.setInvestigationPoints(this.getInvestigationPoints() + (2*2));

            case "Q4":
                this.setInvestigationPoints(this.getInvestigationPoints() + (1*2));

        }
        super.getRewardPublication(quartil);
    }
    @Override
    public void getPenalizationPublication(String quartil) {
        switch (quartil){
            case "Q1":
                this.setInvestigationPoints(this.getInvestigationPoints() - 2);

            case "Q2":
                this.setInvestigationPoints(this.getInvestigationPoints() - 2);

            case "Q3":
                this.setInvestigationPoints(this.getInvestigationPoints() - 1);

            case "Q4":
                this.setInvestigationPoints(this.getInvestigationPoints() - 1);
        }
        super.getPenalizationPublication(quartil);
    }

    //Defense
    @Override
    public void getRewardDefense() {
        super.getRewardDefense();
        this.setInvestigationPoints(this.getInvestigationPoints() + (5*2));
    }
    @Override
    public void getPenalizationDefense() {
        super.getPenalizationDefense();
        this.setInvestigationPoints(this.getInvestigationPoints() - 2);
    }

    //Master
    @Override
    public void getRewardMaster() {
        super.getRewardMaster();
        this.setInvestigationPoints(this.getInvestigationPoints() + 6);
    }
    @Override
    public void getPenalizationMaster() {
        super.getPenalizationMaster();
        this.setInvestigationPoints(this.getInvestigationPoints() - 1);
    }

    //Budget
    @Override
    public void getPenalizationBudget() {
        super.getPenalizationBudget();
        this.setInvestigationPoints(this.getInvestigationPoints() - 1);
    }
}
