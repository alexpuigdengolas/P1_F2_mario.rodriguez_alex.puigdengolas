import Business.BusinessController;
import Business.Edition;
import Persistance.CsvCotroller;

import java.util.LinkedList;
import java.util.List;

/**
 * This is the main class that will allow the code to execute correctly
 */
public class Main {
    /**
     * The main only executes the BusinessController constructor that contains all the logic of
     * the program
     * @param args This are the arguments, but for this code we don't use them
     */
    public static void main (String[] args){
        new BusinessController();
    }
}
