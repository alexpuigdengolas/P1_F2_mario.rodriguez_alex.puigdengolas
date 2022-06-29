package business.tests;

import business.Edition;
import business.Player;
import business.Test;
import presentation.ViewController;

import java.util.List;

/**
 * This class extends from Test and will be used to represent all the doctoral defenses
 */
public class doctoralDefense extends Test {
    private String field;
    private int diff;

    /**
     * This constructor will allow us to create a test
     * @param name the name we want the test to have
     * @param field the field that is treated
     * @param diff the difficulty of the doctoral defense
     */
    public doctoralDefense(String name, String field, int diff) {
        super(name);
        setType("doctoralDefense");
        this.field = field;
        this.diff = diff;
    }

    /**
     * This is a getter of the field
     * @return the field of the test
     */
    public String getField() {
        return field;
    }

    /**
     * This is the getter of the difficulty
     * @return the difficulty
     */
    public int getDiff() {
        return diff;
    }

    /**
     * This method will allow us to execute the test
     * @param test the test we want to execute
     * @param edition the edition that has this test
     */
    @Override
    public void execute(Test test, Edition edition) {
        super.execute(test, edition);
        double result, noGoodResult = 0;
        for(int i = 0; i < diff; i++){
            noGoodResult = noGoodResult + 2*(i+1) - 1;
        }
        result = Math.sqrt(noGoodResult);
        for (int i = 0; i < edition.getPlayers().size(); i++) {
            if(edition.getPlayers().get(i).getInvestigationPoints() < result){
                getReward(test, edition.getPlayers(), i);
                edition.getBusinessController().getViewController().defensePassed(edition.getPlayers().get(i));
            }else{
                getPenalitation(test, edition, i);
                edition.getBusinessController().getViewController().defenseNotPassed(edition.getPlayers().get(i));
            }
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
        if(players.get(playerIteration).getClass().getSimpleName().equals("Enginyer")){
            players.get(playerIteration).setInvestigationPoints(players.get(playerIteration).getInvestigationPoints() + 5);
        }else if(players.get(playerIteration).getClass().getSimpleName().equals("Master")){
            players.get(playerIteration).setInvestigationPoints(10);
        }else{
            players.get(playerIteration).setInvestigationPoints(players.get(playerIteration).getInvestigationPoints() + 10);
        }

        if(players.get(playerIteration).getInvestigationPoints() >= 10 && !players.get(playerIteration).getClass().getSimpleName().equals("Doctor")){
            players.get(playerIteration).checkRole(players, playerIteration);
        }
    }

    /**
     * This will calculate the reward if the player wins
     * @param test the test we want to get the reward from
     * @param edition the edition that is being executed
     * @param playerIteration the id of the player selected
     */
    @Override
    public void getPenalitation(Test test, Edition edition, int playerIteration) {
        super.getPenalitation(test, edition, playerIteration);
        if(edition.getPlayers().get(playerIteration).getClass().getSimpleName().equals("Enginyer")){
            edition.getPlayers().get(playerIteration).setInvestigationPoints(edition.getPlayers().get(playerIteration).getInvestigationPoints() - 5);
        }else {
            edition.getPlayers().get(playerIteration).setInvestigationPoints(edition.getPlayers().get(playerIteration).getInvestigationPoints() - 2);
        }

        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            edition.getBusinessController().getViewController().playerEliminated(edition.getPlayers().get(playerIteration));
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }

    /**
     * This will allow us to show the test's information
     * @param viewController the view controller that will show all the info
     */
    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showDoctoralDefense(this);
    }
}
