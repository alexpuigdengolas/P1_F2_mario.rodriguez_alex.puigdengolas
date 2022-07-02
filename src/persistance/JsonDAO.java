package persistance;

import business.Edition;
import business.Player;
import business.Test;

import java.util.LinkedList;
import java.util.List;



/**
 * this interface have the methods to save and write in the JSON database
 */
public interface JsonDAO {
    /**
     * This method will allow us to write the edition in a file .json
     * @param editions the list of the editions that we have in the code
     * @param address the address were we will save the information
     */
    void writeEditionsJSON(List<Edition> editions, String address);


    /**
     * This method will allow us to write the tests in a file .json
     * @param editions the list of the editions that we have in the code
     * @param testPubliAddress the address of the publication tests
     * @param testDefAddress the address of the defense tests
     * @param testMasterAddress the address of the master tests
     * @param testReqAddress the address of the budget request tests
     */
    void writeTestsJSON(List<Edition> editions, String testPubliAddress, String testDefAddress, String testMasterAddress, String testReqAddress);

    /**
     * This method will allow us to write the players in a file .json
     * @param editions the list of the editions that we have in the code
     * @param enginyerAddress the address where we will save the enginyers
     * @param masterAddress the address where we will save the masters
     * @param doctorAddress the address where we will save the doctors
     */
    void writePlayersJSON(List<Edition> editions, String enginyerAddress, String masterAddress, String doctorAddress);

    /**
     * This method will allow us to read the edition in a file .json
     * @param address the address where we have the .json file
     * @return the list of editions contained in the .json
     */
    List<Edition> readJSON(String address);


    /**
     * This method will allow us to read the tests in a file .json
     * @param testPubliAddress the address of the publication tests
     * @param testDefAddress the address of the defense tests
     * @param testMasterAddress the address of the master tests
     * @param testReqAddress the address of the budget request tests
     * @return a list of the tests that are inside de .json
     */
    LinkedList<Test> readTestJSON(String testPubliAddress, String testDefAddress, String testMasterAddress, String testReqAddress);


    /**
     * This method will allow us to read the players in a file .json
     * @param enginyerAddress the address where we will save the enginyers
     * @param masterAddress the address where we will save the masters
     * @param doctorAddress the address where we will save the doctors
     * @return a list of all the players of the .json
     */
    LinkedList<Player> readPlayersJSON(String enginyerAddress, String masterAddress, String doctorAddress);


}
