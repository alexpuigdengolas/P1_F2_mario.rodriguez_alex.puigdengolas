package business;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to create editions during the execution of the code
 */
public class Edition {
    private int year;
    private int initialPlayers;
    private int numTest;
    private int rounds;
    private List<Test> tests;
    private List<Player> players;

    private transient BusinessController businessController;

    /**
     * This is the constructor of the editions, this will allow us to create editions filled with info
     * @param year the year of the edition
     * @param initialPlayers the number of initial players
     * @param numTest the number of tests
     * @param rounds the current round
     * @param tests the tests that are contained this edition
     * @param players the players that play a role in this edition
     */
    public Edition(int year, int initialPlayers, int numTest, int rounds, List<Test> tests, List<Player> players) {
        this.year = year;
        this.initialPlayers = initialPlayers;
        this.numTest = numTest;
        this.rounds = rounds;
        this.tests = tests;
        this.players = players;
    }

    public Edition(BusinessController businessController, int year, int initialPlayers, int numTest, int rounds, List<Test> tests, List<Player> players) {
        this.businessController = businessController;
        this.year = year;
        this.initialPlayers = initialPlayers;
        this.numTest = numTest;
        this.rounds = rounds;
        this.tests = tests;
        this.players = players;
    }

    /**
     * This is a getter for the business controller
     * @return the business controller
     */
    public BusinessController getBusinessController() {
        return businessController;
    }

    /**
     * This is a setter for the business controller
     * @param businessController the bussines controller we want it to have
     */
    public void setBusinessController(BusinessController businessController) {
        this.businessController = businessController;
    }

    /**
     * This is the getter of the year of the edition
     * @return the year of the edition
     */
    public int getYear() {
        return year;
    }

    /**
     * This is the getter of the initial number of players that the edition
     * will have
     * @return the initial number of players of the edition
     */
    public int getInitialPlayers() {
        return initialPlayers;
    }

    /**
     * This is the setter of the initial number of players that the edition
     * will have
     * @param initialPlayers the initial number of players that this edition will have
     */
    public void setInitialPlayers(int initialPlayers) {
        this.initialPlayers = initialPlayers;
    }

    /**
     * Getter of the number of tests that the edition contains
     * @return the number of tests that the edition contains
     */
    public int getNumTest() {
        return numTest;
    }

    /**
     * Getter of the current round that the edition is in
     * @return the current round that the edition is in
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Getter of all the tests that are inside of the edition
     * @return the test list that are contained in the edition
     */
    public List<Test> getTests() {
        return tests;
    }

    /**
     * Setter of all the tests that are inside of the edition
     * @param tests the tests that we want the edition to have
     */
    public void setTests(LinkedList<Test> tests) {
        this.tests = tests;
    }

    /**
     * Getter of all the players that are inside of the edition
     * @return the players that are inside of the edition
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Setter of all the players that are inside of the edition
     * @param players the players that we want the edition to have
     */
    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    /**
     * This method will show the information of the edition
     */
    public void showInfo() {
        this.getBusinessController().getViewController().showEditionInformation(this);
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}
