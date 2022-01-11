package Business;

import Presentation.ViewController;

public class budgetRequest extends Test{
    private String entity;
    private double quantity;

    public budgetRequest(String name, String entity, double quantity) {
        super(name);
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
                getReward(test, edition.getPlayers().get(j));
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
    void getReward(Test test, Player player) {
        super.getReward(test, player);
        player.setInvestigationPoints((int) Math.ceil(player.getInvestigationPoints()/2));
    }

    @Override
    void getPenalitation(Test test, Edition edition, int playerIterarion) {
        super.getPenalitation(test, edition, playerIterarion);
        if (edition.getPlayers().get(playerIterarion).getRole().equals("Doctor")) {
            edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 1);
        } else{
            edition.getPlayers().get(playerIterarion).setInvestigationPoints(edition.getPlayers().get(playerIterarion).getInvestigationPoints() - 2);
        }
    }
}
