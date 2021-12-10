package Persistance;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CsvCotroller {
    private BufferedReader lector;//lee el archivo
    private String liniea; // lee cada linea
    private String partes[] = null; //Guarda las lineas

    public void readCSV(String nameCSV){
        try{
            //Aqui va el salseo
            lector = new BufferedReader(new FileReader(nameCSV));
            while ((liniea = lector.readLine()) != null){
                partes = liniea.split(";");
                printLinea();
                System.out.println();
            }
            System.out.println(partes.length);
            lector.close();
            liniea = null;
            partes = null;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void printLinea(){
        for (int i = 0 ; i < partes.length; i++ ){
            System.out.print(partes[i]+"  |  ");

        }
    }

}
