package Business;

public class Test {
    private String name;

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showInfo() {
        System.out.println(getName());
    }

    public void execute(Test test, Edition edition){

    }

    void getReward(Test test, Player player) {

    }

    void getPenalitation(Test test, Edition edition, int playerIterarion) {

    }
}
