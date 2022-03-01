package Business;

import Presentation.ViewController;

import java.util.List;

public class Publication extends Test{
    private String nameMag;
    private String quartil;
    private int acceptanceProbability;
    private int revisionProbability;
    private int notAcceptedProbability;

    public Publication(String name, String nameMag, String quartil, int acceptanceProbability, int revisionProbability, int notAcceptedProbability) {
        super(name);
        setType("Publication");
        this.nameMag = nameMag;
        this.quartil = quartil;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.notAcceptedProbability = notAcceptedProbability;
    }

    public Publication(String name) {
        super(name);
    }

    public String getNameMag() {
        return nameMag;
    }

    public void setNameMag(String nameMag) {
        this.nameMag = nameMag;
    }

    public String getQuartil() {
        return quartil;
    }

    public void setQuartil(String quartil) {
        this.quartil = quartil;
    }

    public int getAcceptanceProbability() {
        return acceptanceProbability;
    }

    public void setAcceptanceProbability(int acceptanceProbability) {
        this.acceptanceProbability = acceptanceProbability;
    }

    public int getRevisionProbability() {
        return revisionProbability;
    }

    public void setRevisionProbability(int revisionProbability) {
        this.revisionProbability = revisionProbability;
    }

    public int getNotAcceptedProbability() {
        return notAcceptedProbability;
    }

    public void setNotAcceptedProbability(int notAcceptedProbability) {
        this.notAcceptedProbability = notAcceptedProbability;
    }

    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showPublication(this);
    }

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

    @Override
    void getReward(Test test, List<Player> players, int playerIteration) {
        super.getReward(test, players, playerIteration);
        Publication publication = (Publication) test;
        quartil = publication.getQuartil();
        players.get(playerIteration).getRewardPublication(quartil);
        if(players.get(playerIteration).getInvestigationPoints() >= 10 && !players.get(playerIteration).getClass().getSimpleName().equals("Doctor")){
            players.get(playerIteration).checkRole(players, playerIteration);
        }
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIteration) {
        super.getPenalitation(test, edition, playerIteration);
        Publication publication = (Publication) test;
        quartil = publication.getQuartil();
        edition.getPlayers().get(playerIteration).getPenalizationPublication(quartil);
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            System.out.println(edition.getPlayers().get(playerIteration).getName() + " was eliminated!");
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }
}
