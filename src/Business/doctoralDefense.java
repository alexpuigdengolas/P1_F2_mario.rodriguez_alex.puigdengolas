package Business;

import Presentation.ViewController;

public class doctoralDefense extends Test{
    private String field;
    private int diff;

    public doctoralDefense(String name, String field, int diff) {
        super(name);
        this.field = field;
        this.diff = diff;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

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
                    getReward(test, edition.getPlayers().get(i));
                    edition.getBusinessController().getViewController().deffensePassed(edition.getPlayers().get(i));
                }else{
                    getPenalitation(test, edition, i);
                    edition.getBusinessController().getViewController().deffenseNotPassed(edition.getPlayers().get(i));
                }
            }
        }catch (Exception ignored){

        }
    }

    @Override
    void getReward(Test test, Player player) {
        super.getReward(test, player);
        if(player.getRole().equals("Màster")){
            player.setInvestigationPoints(10);
            player.checkRole();
        }else{
            player.setInvestigationPoints(player.getInvestigationPoints() + 5);
            player.checkRole();
        }
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIterarion) {
        super.getPenalitation(test, edition, playerIterarion);
        edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 5);
        edition.removePlayer(edition, playerIterarion);
    }

    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showDoctoralDefense(this);
    }
}
