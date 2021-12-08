package Business;

import java.util.LinkedList;

public class Edition {
    private int year;
    private int initialPlayers;
    private int numTest;
    private int rounds;
    private LinkedList<Test> tests;
    private LinkedList<Player> players;

    public Edition(int year, int initialPlayers, int numTest, int rounds, LinkedList<Test> tests, LinkedList<Player> players) {
        this.year = year;
        this.initialPlayers = initialPlayers;
        this.numTest = numTest;
        this.rounds = rounds;
        this.tests = tests;
        this.players = players;
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

    public LinkedList<Test> getTests() {
        return tests;
    }

    public void setTests(LinkedList<Test> tests) {
        this.tests = tests;
    }

    public LinkedList<Player> getPlayers() {
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
            //TODO: Descubrir como sacar el tipo de test
            System.out.println("    " + (i + 1) + "- The Trials " + tests.get(i).getName() +" (Paper publication)");
        }
    }
}
