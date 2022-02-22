package Business;

import Presentation.ViewController;

import java.util.List;

public class doctoralDefense extends Test{
    private String field;
    private int diff;

    public doctoralDefense(String name, String field, int diff) {
        super(name);
        setType("doctoralDefense");
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
                    getReward(test, edition.getPlayers(), i);
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
    void getReward(Test test, List<Player> players, int playerIteration) {
        super.getReward(test, players, playerIteration);
        players.get(playerIteration).getRewardDefense();
        if(players.get(playerIteration).getInvestigationPoints() >= 10 && !players.get(playerIteration).getClass().getSimpleName().equals("Doctor")){
            players.set(playerIteration, players.get(playerIteration).checkRole());
        }
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIteration) {
        super.getPenalitation(test, edition, playerIteration);
        edition.getPlayers().get(playerIteration).getPenalizationDefense();
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            System.out.println(edition.getPlayers().get(playerIteration).getName() + " was eliminated!");
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }

    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showDoctoralDefense(this);
    }
}
