import Business.BusinessController;
import Persistance.CsvCotroller;

public class Main {
    public static void main (String[] args){

        //Esto solo es la prueba para ver si lee no hacer caso
        //---------------------------------------------------------
        CsvCotroller csv = new CsvCotroller(); //creamos objeto
        csv.readCSV("CSV/Edicions.csv");
        //-----------------------------------------------------
        BusinessController businessController = new BusinessController();
    }
}
