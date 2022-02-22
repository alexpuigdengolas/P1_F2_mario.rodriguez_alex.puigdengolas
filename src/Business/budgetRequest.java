package Business;

import Presentation.ViewController;

import java.util.List;

public class budgetRequest extends Test{
    private String entity;
    private double quantity;

    public budgetRequest(String name, String entity, double quantity) {
        super(name);
        setType("budgetRequest");
        this.entity = entity;
        this.quantity = quantity;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public void showInfo(ViewController viewController) {
        super.showInfo(viewController);
        viewController.showBudgetRequest(this);
    }

    @Override
    public void execute(Test test, Edition edition) {
        super.execute(test, edition);
        int totalPi = 0;
        for (int i = 0; i < edition.getPlayers().size(); i++){
            totalPi += edition.getPlayers().get(i).getInvestigationPoints();
        }

        if(totalPi > (int)(Math.log(quantity) / Math.log(2))){
            for(int j = 0; j < edition.getPlayers().size(); j++) {
                getReward(test, edition.getPlayers(), j);
                edition.getBusinessController().getViewController().BudgetPassed(edition.getPlayers().get(j));
            }
        }else{
            for(int j = 0; j < edition.getPlayers().size(); j++) {
                getPenalitation(test, edition, j);
                edition.getBusinessController().getViewController().BudgetNotPassed(edition.getPlayers().get(j));

            }
        }
    }

    @Override
    void getReward(Test test, List<Player> players, int playerIteration) {
        super.getReward(test, players, playerIteration);
        players.get(playerIteration).setInvestigationPoints((int) Math.ceil(players.get(playerIteration).getInvestigationPoints()/2));
        if (players.get(playerIteration).getInvestigationPoints() >= 10 && !players.get(playerIteration).getClass().getSimpleName().equals("Doctor")){
            players.get(playerIteration).checkRole();
        }
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIteration) {
        super.getPenalitation(test, edition, playerIteration);
        edition.getPlayers().get(playerIteration).getPenalizationBudget();
        if(edition.getPlayers().get(playerIteration).getInvestigationPoints() <= 0){
            System.out.println(edition.getPlayers().get(playerIteration).getName() + " was eliminated!");
            edition.getPlayers().remove(playerIteration);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }
}
