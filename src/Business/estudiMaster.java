package Business;

import Presentation.ViewController;

import java.util.List;

public class estudiMaster extends Test{
    private String master;
    private int credits;
    private int probability;

    public estudiMaster(String name, String master, int credits, int probability) {
        super(name);
        setType("estudiMaster");
        this.master = master;
        this.credits = credits;
        this.probability = probability;
    }


    public String getMaster() {
        return master;
    }
    public String getMasterName(){
        return getName();
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showEstudyMaster(this);
    }

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
                edition.getBusinessController().getViewController().masterPassed(edition.getPlayers().get(i));
            }else{
                getPenalitation(test, edition, i);
                edition.getBusinessController().getViewController().masterNotPassed(edition.getPlayers().get(i));
            }
        }
    }

    @Override
    void getReward(Test test, List<Player> players, int playerIteration) {
        super.getReward(test, players, playerIteration);
        players.get(playerIteration).getRewardDefense();
        if(players.get(playerIteration).getInvestigationPoints() >= 10 && !players.get(playerIteration).getClass().getSimpleName().equals("Doctor")){
            players.get(playerIteration).checkRole(players, playerIteration);
        }
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIteration) {
        super.getPenalitation(test, edition, playerIteration);
        edition.getPlayers().get(playerIteration).getPenalizationMaster();
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            System.out.println(edition.getPlayers().get(playerIteration).getName() + " was eliminated!");
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }


}
