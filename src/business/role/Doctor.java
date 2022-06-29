package business.role;

import business.Player;

/**
 * This class extends from Player and will be used to represent all the Doctors
 */
public class Doctor extends Player {

    /**
     * This constructor will be used to create a new Doctor and add the
     * desired information
     * @param name is the name of the Doctor
     * @param ip is the amount of investigation points that hte Doctor has earned
     */
    public Doctor(String name, int ip) {
        this.setName(name);
        this.setInvestigationPoints(ip);
    }
}
