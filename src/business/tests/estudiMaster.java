package business.tests;

import business.Edition;
import business.Player;
import business.Test;
import presentation.ViewController;

import java.util.List;

/**
 * This class extends from Test and will be used to represent all the estudi masters
 */
public class estudiMaster extends Test {
    private String master;
    private int credits;
    private int probability;

    /**
     * This constructor will allow us to create a study master
     * @param name is the name of the test
     * @param master is the name of the master theme
     * @param credits the amount of credits that the study has
     * @param probability the probability to succeed for every credit
     */
    public estudiMaster(String name, String master, int credits, int probability) {
        super(name);
        setType("estudiMaster");
        this.master = master;
        this.credits = credits;
        this.probability = probability;
    }

    /**
     * The getter of the master theme
     * @return the master theme
     */
    public String getMaster() {
        return master;
    }

    /**
     * The setter of the master theme
     * @param master the master theme
     */
    public void setMaster(String master) {
        this.master = master;
    }

    /**
     * The getter of the amount of credits
     * @return the amount of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * The getter of the probability per credit to succeed
     * @return the probability per credit to succeed
     */
    public int getProbability() {
        return probability;
    }

    /**
     * This method allows to show the information of the master
     * @param viewController the view controller that will show all the info
     */
    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showEstudyMaster(this);
    }

    /**
     * This method will execute the test
     * @param test the test we want to execute
     * @param edition the edition that has this test
     */
    @Override
    public void execute(Test test, Edition edition) {

        super.execute(test, edition);
        for(int i = 0; i < edition.getPlayers().size(); i++){
            int pass = 0, fail = 0;
            for(int j = 0; j < credits; j++){
                double newProb = Math.random() * 100;
                if(newProb > Math.abs(100 - probability)){
                    pass++;
                }else{
                    fail++;
                }
            }
            if(pass > fail){
                getReward(test, edition.getPlayers(), i);
                if(edition.getPlayers().size() > i)
                edition.getBusinessController().getViewController().masterPassed(edition.getPlayers().get(i), credits, pass);
            }else{
                getPenalitation(test, edition, i);
                if(edition.getPlayers().size() > i)
                edition.getBusinessController().getViewController().masterNotPassed(edition.getPlayers().get(i), credits, pass);
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
        players.get(playerIteration).getRewardDefense();
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
        edition.getPlayers().get(playerIteration).getPenalizationMaster();
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            edition.getBusinessController().getViewController().playerEliminated(edition.getPlayers().get(playerIteration));
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }
}
