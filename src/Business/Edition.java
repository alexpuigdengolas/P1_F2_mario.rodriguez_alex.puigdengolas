package Business;

import com.google.gson.annotations.Expose;

import java.util.LinkedList;
import java.util.List;

public class Edition {
    private int year;
    private int initialPlayers;
    private int numTest;
    private int rounds;
    private List<Test> tests;
    private List<Player> players;

    private transient BusinessController businessController;

    public Edition(int year, int initialPlayers, int numTest, int rounds, List<Test> tests, List<Player> players) {
        this.year = year;
        this.initialPlayers = initialPlayers;
        this.numTest = numTest;
        this.rounds = rounds;
        this.tests = tests;
        this.players = players;
    }

    public BusinessController getBusinessController() {
        return businessController;
    }

    public void setBusinessController(BusinessController businessController) {
        this.businessController = businessController;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getInitialPlayers() {
        return initialPlayers;
    }

    public void setInitialPlayers(int initialPlayers) {
        this.initialPlayers = initialPlayers;
    }

    public int getNumTest() {
        return numTest;
    }

    public void setNumTest(int numTest) {
        this.numTest = numTest;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(LinkedList<Test> tests) {
        this.tests = tests;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public void showInfo() {
        System.out.println("Year: "+ getYear());
        System.out.println("Players: "+ getInitialPlayers());
        System.out.println("Trials: ");
        for (int i = 0; i < tests.size(); i++) {
            System.out.println("    " + (i + 1) + "- The Trials " + tests.get(i).getName() +" ("+tests.get(i).getClass().getSimpleName()+")");
        }
    }

    public void removePlayer(Edition edition, int playerIterarion) {
        if(edition.getPlayers().get(playerIterarion).getInvestigationPoints() <= 0){
            edition.getPlayers().remove(playerIterarion);
            edition.setInitialPlayers(edition.getInitialPlayers()-1);
        }
    }


}
