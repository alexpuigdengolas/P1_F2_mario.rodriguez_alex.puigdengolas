package Persistance;

import Business.Edition;
import Business.Player;
import Business.Publication;
import Business.Test;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CsvCotroller {
    private BufferedReader lector;//lee el archivo
    private String liniea; // lee cada linea
    private String partes[] = null; //Guarda las lineas

    public List<Edition> readCSV(String nameCSV){
        try{
            List<Edition> data = new ArrayList<Edition>();
            //Aqui va el salseo
            lector = new BufferedReader(new FileReader(nameCSV));
            while ((liniea = lector.readLine()) != null){
                partes = liniea.split(",");
                Edition edition = new Edition(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2]) ,Integer.parseInt(partes[3]), generateTests(partes[4]), generatePlayers(partes[5]));
                data.add(edition);
            }
            lector.close();
            liniea = null;
            partes = null;
            return data;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

    private List<Player> generatePlayers(String parte) {

        List<Player> players = new ArrayList<Player>();
        parte = parte.replace("[", "");
        parte = parte.replace("]", "");
        String playerData [] = parte.split(";");
        int j = 0;
        while (j < playerData.length){
            String info [] = playerData[j].split("/");
            Player player = new Player(info[0], Integer.parseInt(info[1]));
            players.add(player);
            j++;
        }
        return players;
    }

    private List<Test> generateTests(String parte) {
        List<Test> tests = new ArrayList<Test>();
        parte = parte.replace("[", "");
        parte = parte.replace("]", "");
        String testData [] = parte.split(";");
        int j = 0;
        while (j < testData.length){
            String info [] = testData[j].split("/");
            Publication test = new Publication(info[0], info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
            tests.add(test);
            j++;
        }
        return tests;
    }

    public void writeCSV(){

    }

    public void printLinea(){
        for (int i = 0 ; i < partes.length; i++ ){
            System.out.print(partes[i]+"  |  ");

        }
    }

}
