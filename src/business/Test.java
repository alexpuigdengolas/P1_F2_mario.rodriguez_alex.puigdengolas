package business;

import presentation.ViewController;

import java.util.List;

/**
 * This class will be used to represent all the tests
 */
public class Test {
    private String name;
    private String type;

    /**
     * This constructor will allow us to create a test with a name
     * @param name the name we want the test to have
     */
    public Test(String name) {
        this.name = name;
    }

    /**
     * This is a getter of the name
     * @return the name of the test
     */
    public String getName() {
        return name;
    }

    /**
     * This is the setter of the name
     * @param name the name we want the test to have
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This will allow us to show the test's information
     * @param viewController the view controller that will show all the info
     */
    public void showInfo(ViewController viewController) {
    }

    /**
     * This method will allow us to execute the test
     * @param test the test we want to execute
     * @param edition the edition that has this test
     */
    public void execute(Test test, Edition edition){
    }

    /**
     * This will calculate the reward if the player wins
     * @param test the test we want to get the reward from
     * @param players the players of the edition
     * @param playerIteration the id of the player selected
     */
    public void getReward(Test test, List<Player> players, int playerIteration) {

    }

    /**
     * This will calculate the reward if the player wins
     * @param test the test we want to get the reward from
     * @param edition the edition that is being executed
     * @param playerIteration the id of the player selected
     */
    public void getPenalitation(Test test, Edition edition, int playerIteration) {

    }

    /**
     * Setter of the type of test
     * @param type the type of the test
     */
    public void setType(String type) {
        this.type = type;
    }
}
