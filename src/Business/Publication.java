package Business;

import Presentation.ViewController;

import java.util.List;

public class Publication extends Test{
    private String nameMag;
    private String quartil;
    private int acceptanceProbability;
    private int revisionProbability;
    private int notAcceptedProbability;

    /**
     * This method will allow us to create a Publication that will be filled with the information
     * that we desire
     * @param name the name that we want the publication to have
     * @param nameMag the name of the mag of the publication
     * @param quartil the quartil of the publication
     * @param acceptanceProbability the probability to get accepted
     * @param revisionProbability the probability to get revised
     * @param notAcceptedProbability the probability to not get accepted
     */
    public Publication(String name, String nameMag, String quartil, int acceptanceProbability, int revisionProbability, int notAcceptedProbability) {
        super(name);
        setType("Publication");
        this.nameMag = nameMag;
        this.quartil = quartil;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.notAcceptedProbability = notAcceptedProbability;
    }

    /**
     * This method will create a publication that only contains the name
     * @param name the name of the publication
     */
    public Publication(String name) {
        super(name);
    }

    /**
     * Getter of the name of the mag
     * @return the name of the mag
     */
    public String getNameMag() {
        return nameMag;
    }

    /**
     * Getter of the quartil of the publication
     * @return the quartil of the publication
     */
    public String getQuartil() {
        return quartil;
    }

    /**
     * The getter of the acceptance probability
     * @returnthe acceptance probability
     */
    public int getAcceptanceProbability() {
        return acceptanceProbability;
    }

    /**
     * The getter of the revision probability
     * @returnthe revision probability
     */
    public int getRevisionProbability() {
        return revisionProbability;
    }

    /**
     * The getter of the rejection probability
     * @returnthe rejection probability
     */
    public int getNotAcceptedProbability() {
        return notAcceptedProbability;
    }

    /**
     * This will allow us to show the test's information
     * @param viewController the view controller that will show all the info
     */
    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showPublication(this);
    }

    /**
     * This method will allow us to execute the test
     * @param test the test we want to execute
     * @param edition the edition that has this test
     */
    @Override
    public void execute(Test test, Edition edition) {
        super.execute(test, edition);
        Publication publication = (Publication) test;
        String quartil = publication.getQuartil();
        try {
            for (int i = 0; i < edition.getPlayers().size(); i++) {
                boolean result = false;
                edition.getBusinessController().getViewController().submitting(edition.getPlayers().get(i));
                while (!result) {
                    double num = Math.random() * 100;

                    if (num < publication.getAcceptanceProbability()) {
                        getReward(test, edition.getPlayers(), i);
                        edition.getBusinessController().getViewController().acceptedPublication(edition.getPlayers().get(i));
                        result = true;
                    } else {
                        if (num > publication.getRevisionProbability() + publication.getAcceptanceProbability()) {
                            getPenalitation(test, edition, i);
                            edition.getBusinessController().getViewController().rejectedPublication(edition.getPlayers().get(i));
                            result = true;
                        } else {
                            edition.getBusinessController().getViewController().revisedPublication();
                        }
                    }
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
    public void getReward(Test test, List<Player> players, int playerIteration) {
        super.getReward(test, players, playerIteration);
        Publication publication = (Publication) test;
        quartil = publication.getQuartil();
        players.get(playerIteration).getRewardPublication(quartil);
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
        Publication publication = (Publication) test;
        quartil = publication.getQuartil();
        edition.getPlayers().get(playerIteration).getPenalizationPublication(quartil);
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            edition.getBusinessController().getViewController().playerEliminated(edition.getPlayers().get(playerIteration));
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }
}
