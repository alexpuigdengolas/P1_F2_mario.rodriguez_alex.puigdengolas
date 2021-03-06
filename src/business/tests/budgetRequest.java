package business.tests;

import business.Edition;
import business.Player;
import business.Test;
import presentation.ViewController;

import java.util.List;

/**
 * This class extends from Test and will be used to represent all the doctoral budget requests
 */
public class budgetRequest extends Test {
    private String entity;
    private double quantity;

    /**
     * This is a constructor for a test of the budget request kind
     * @param name the name of the test
     * @param entity the name of the entity
     * @param quantity the quantity demanded
     */
    public budgetRequest(String name, String entity, double quantity) {
        super(name);
        setType("budgetRequest");
        this.entity = entity;
        this.quantity = quantity;
    }

    /**
     * This is the getter of the entity that this budget is going to
     * @return the entity name
     */
    public String getEntity() {
        return entity;
    }

    /**
     * This is the getter of the amount of money that this budget is going to spend
     * @return the amount of money that this budget is going to spend
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * This will allow us to show the test's information
     * @param viewController the view controller that will show all the info
     */
    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showBudgetRequest(this);
    }

    /**
     * This method will allow us to execute the test
     * @param test the test we want to execute
     * @param edition the edition that has this test
     */
    @Override
    public void execute(Test test, Edition edition) {
        super.execute(test, edition);
        int totalPi = 0;
        for (int i = 0; i < edition.getPlayers().size(); i++){
            totalPi += edition.getPlayers().get(i).getInvestigationPoints();
        }

        if(totalPi > (int)(Math.log(quantity) / Math.log(2))){
            edition.getBusinessController().getViewController().BudgetPassed(edition.getPlayers());
        }else{
            edition.getBusinessController().getViewController().BudgetNotPassed(edition.getPlayers());
        }
    }

    /**
     * This will calculate the reward if the player wins
     * @param test the test we want to get the reward from
     * @param players the players of the edition
     * @param playerIteration the id of the player selected
     */
    @Override
    public void getReward(Test test, List<Player> players, int playerIteration) {
        super.getReward(test, players, playerIteration);
        players.get(playerIteration).setInvestigationPoints(players.get(playerIteration).getInvestigationPoints() + (int) Math.ceil(players.get(playerIteration).getInvestigationPoints()/2));
        if (players.get(playerIteration).getInvestigationPoints() >= 10 && !players.get(playerIteration).getClass().getSimpleName().equals("Doctor")){
            players.get(playerIteration).checkRole(players, playerIteration);
        }
    }

    /**
     * This will calculate the penalization if the player looses
     * @param test the test we want to get the reward from
     * @param edition the edition that is being executed
     * @param playerIteration the id of the player selected
     */
    @Override
    public void getPenalitation(Test test, Edition edition, int playerIteration) {
        super.getPenalitation(test, edition, playerIteration);
        if(edition.getPlayers().get(playerIteration).getClass().getSimpleName().equals("Doctor")){
            edition.getPlayers().get(playerIteration).setInvestigationPoints(edition.getPlayers().get(playerIteration).getInvestigationPoints() - 1);
        }else {
            edition.getPlayers().get(playerIteration).setInvestigationPoints(edition.getPlayers().get(playerIteration).getInvestigationPoints() - 2);
        }
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            edition.getBusinessController().getViewController().playerEliminated(edition.getPlayers().get(playerIteration));
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }
}
