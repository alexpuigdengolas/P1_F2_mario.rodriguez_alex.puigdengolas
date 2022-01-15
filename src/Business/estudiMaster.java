package Business;

import Presentation.ViewController;

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
                getReward(test, edition.getPlayers().get(i));
                edition.getBusinessController().getViewController().masterPassed(edition.getPlayers().get(i));
            }else{
                getPenalitation(test, edition, i);
                edition.getBusinessController().getViewController().masterNotPassed(edition.getPlayers().get(i));
            }
        }
    }

    @Override
    void getReward(Test test, Player player) {
        super.getReward(test, player);
        if(player.getRole().equals("Enginyer")){
            player.setInvestigationPoints(10);
            player.checkRole();
        }else{
            if (player.getRole().equals("Doctor")) {
                player.setInvestigationPoints(player.getInvestigationPoints() + 6);
            }else {
                player.setInvestigationPoints(player.getInvestigationPoints() + 3);
            }
            player.checkRole();
        }
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIterarion) {
        super.getPenalitation(test, edition, playerIterarion);
        if (edition.getPlayers().get(playerIterarion).getRole().equals("Doctor")) {
            edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 1);
        } else{
            edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 3);
        }
        edition.removePlayer(edition, playerIterarion);
    }


}
