import Business.BusinessController;
import Business.Edition;
import Persistance.CsvCotroller;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main (String[] args){

        //Esto solo es la prueba para ver si lee no hacer caso
        //---------------------------------------------------------
        CsvCotroller csv = new CsvCotroller(); //creamos objeto
        List<Edition> editions = csv.readCSV("CSV/Edicions.csv");
        //-----------------------------------------------------
        BusinessController businessController = new BusinessController();
    }
}
