package business.role;

import business.Player;

import java.util.List;

/**
 * This class extends from Player and will be used to represent all the master
 */
public class Master extends Player {

    /**
     * This constructor will be used to create a new Master and add the
     * desired information
     * @param name is the name of the Master
     * @param ip is the amount of investigation points that hte Master has earned
     */
    public Master(String name, int ip) {
        this.setName(name);
        this.setInvestigationPoints(ip);
    }

    /**
     * This method will check that the role of the player is appropriate and that we doesn't need
     * to get a promotion
     * @param players is the list of players of the edition
     * @param playerIteration is the id of the player that we want to check into
     */
    @Override
    public void checkRole(List<Player> players, int playerIteration) {
        Doctor player = new Doctor(this.getName(), 5);
        players.remove(playerIteration);
        players.add(playerIteration, player);
        System.out.println(this.getName() + " has become a Enginyer!");
    }
}
