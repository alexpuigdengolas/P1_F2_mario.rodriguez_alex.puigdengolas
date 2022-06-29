package business;

import java.util.List;

/**
 * This class will be used to represent any kind of player
 * that will play in a edition
 */
public class Player {
    private String name;
    private int investigationPoints;

    /**
     * This empty constructor is going to be used to create a default player
     */
    public Player() {
        this.name = "Pepe";
        this.investigationPoints = 5;
    }

    /**
     * This constructor will be used to create a new player and add the
     * desired information
     * @param name is the name of the player
     * @param investigationPoints is the amount of investigation points that te player has earned
     */
    public Player(String name, int investigationPoints) {
        this.name = name;
        this.investigationPoints = investigationPoints;
    }

    /**
     * This method is the getter of the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * This method is the setter of the name of the player
     * @param name is the name that we want to give to the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is the getter of the investigation points of the player
     * @return the investigation points of the player
     */
    public int getInvestigationPoints() {
        return investigationPoints;
    }

    /**
     * This method is the setter of the investigation points of the player
     * @param investigationPoints the investigation points that the player has gotten
     */
    public void setInvestigationPoints(int investigationPoints) {
        this.investigationPoints = investigationPoints;
    }

    /**
     * This method will check that the role of the player is appropriate and that we doesn't need
     * to get a promotion
     * @param players is the list of players of the edition
     * @param playerIteration is the id of the player that we want to check into
     */
    public void checkRole(List<Player> players, int playerIteration){}
}
