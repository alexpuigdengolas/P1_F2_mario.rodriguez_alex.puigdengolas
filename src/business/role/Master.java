package business.role;

import business.Player;

import java.util.List;

/**
 * This class extends from Player and will be used to represent all the master
 */
public class Master extends Player {

    /**
     * This constructor will be used to create a new Master and add the
     * desired information
     * @param name is the name of the Master
     * @param ip is the amount of investigation points that hte Master has earned
     */
    public Master(String name, int ip) {
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
    /**
     * This method will give use the penalization if we lose in a Publication
     * @param quartil the quartil of the paper
     */
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
        //Aseguramos que el jugador asciende de rango al meterke 10 puntos
        this.setInvestigationPoints(10);
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
    /**
     * This method will give use the reward if we win in a MasterStudies
     */
    @Override
    public void getRewardMaster() {
        super.getRewardMaster();
        this.setInvestigationPoints(this.getInvestigationPoints() + 3);
    }
    /**
     * This method will give use the penalization if we lose in a MasterStudies
     */
    @Override
    public void getPenalizationMaster() {
        super.getPenalizationMaster();
        this.setInvestigationPoints(this.getInvestigationPoints() - 3);
    }

    //Budget
    /**
     * This method will give use the penalization if we lose in a BudgetRequest
     */
    @Override
    public void getPenalizationBudget() {
        super.getPenalizationBudget();
        this.setInvestigationPoints(this.getInvestigationPoints() - 2);
    }

    /**
     * This method will check that the role of the player is appropriate and that we doesn't need
     * to get a promotion
     * @param players is the list of players of the edition
     * @param playerIteration is the id of the player that we want to check into
     */
    @Override
    public void checkRole(List<Player> players, int playerIteration) {
        Doctor player = new Doctor(this.getName(), 5);
        players.remove(playerIteration);
        players.add(playerIteration, player);
        System.out.println(this.getName() + " has become a Enginyer!");
    }
}
