package Business;

import Presentation.ViewController;

public class Test {
    private String name;
    private String type;

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showInfo(ViewController viewController) {
    }

    public void execute(Test test, Edition edition){

    }

    void getReward(Test test, Player player) {

    }

    void getPenalitation(Test test, Edition edition, int playerIterarion) {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
