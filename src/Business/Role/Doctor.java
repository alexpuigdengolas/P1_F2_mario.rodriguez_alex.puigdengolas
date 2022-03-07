package Business.Role;

import Business.Player;

public class Doctor extends Player {

    /**
     * This constructor will be used to create a new Doctor and add the
     * desired information
     * @param name is the name of the Doctor
     * @param ip is the amount of investigation points that hte Doctor has earned
     */
    public Doctor(String name, int ip) {
        this.setName(name);
        this.setInvestigationPoints(ip);
    }

    //Publication
    /**
     * This method will give use the reward if we win in a Publication
     * @param quartil the quartil of the paper
     */
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
    /**
     * This method will give use the penalization if we lose in a Publication
     * @param quartil the quartil of the paper
     */
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
    /**
     * This method will give use the reward if we win in a DoctoralDefense
     */
    @Override
    public void getRewardDefense() {
        super.getRewardDefense();
        this.setInvestigationPoints(this.getInvestigationPoints() + (5*2));
    }
    /**
     * This method will give use the penalization if we lose in a DoctoralDefense
     */
    @Override
    public void getPenalizationDefense() {
        super.getPenalizationDefense();
        this.setInvestigationPoints(this.getInvestigationPoints() - 2);
    }

    //Master
    @Override
    /**
     * This method will give use the reward if we win in a MasterStudies
     */
    public void getRewardMaster() {
        super.getRewardMaster();
        this.setInvestigationPoints(this.getInvestigationPoints() + 6);
    }
    /**
     * This method will give use the penalization if we lose in a MasterStudies
     */
    @Override
    public void getPenalizationMaster() {
        super.getPenalizationMaster();
        this.setInvestigationPoints(this.getInvestigationPoints() - 1);
    }

    //Budget
    @Override
    /**
     * This method will give use the penalization if we lose in a BudgetRequest
     */
    public void getPenalizationBudget() {
        super.getPenalizationBudget();
        this.setInvestigationPoints(this.getInvestigationPoints() - 1);
    }
}
