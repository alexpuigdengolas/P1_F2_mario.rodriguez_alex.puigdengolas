package persistance;

import business.Edition;

import java.util.List;

/**
 * this interface have the methods to save and write in the CSV database
 */
public interface CsvDAO {


    /**
     * This method will allow us to read the file .csv
     * @param nameCSV the name of the file
     * @return the list of editions that the file contains
     */
    List<Edition> readCSV(String nameCSV);

    /**
     * This method will allow us to write the .csv with all the information of the edition
     * @param editions the list of editions that we have in the program
     * @param nameCSV the name of where we want to save the information
     */
    void writeCSV(List<Edition> editions, String nameCSV);
}
