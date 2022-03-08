package Business;

import Presentation.ViewController;

import java.util.List;

public class doctoralDefense extends Test{
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
        try {
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
        }catch (Exception ignored){

        }
    }

    /**
     * This will calculate the reward if the player wins
     * @param test the test we want to get the reward from
     * @param players the players of the edition
     * @param playerIteration the id of the player selected
     */
    @Override
    void getReward(Test test, List<Player> players, int playerIteration) {
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
    void getPenalitation(Test test, Edition edition, int playerIteration) {
        super.getPenalitation(test, edition, playerIteration);
        edition.getPlayers().get(playerIteration).getPenalizationDefense();
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            edition.getBusinessController().viewController.playerEliminated(edition.getPlayers().get(playerIteration));
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
