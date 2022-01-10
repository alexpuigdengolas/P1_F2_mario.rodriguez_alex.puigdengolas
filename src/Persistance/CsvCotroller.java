package Persistance;

import Business.*;

import javax.swing.*;
import java.io.*;
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
                List<Test> tests = generateTests(partes[4]);
                List<Player> players = generatePlayers(partes[5]);
                Edition edition = new Edition(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2]) ,Integer.parseInt(partes[3]), tests, players);
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
            if(info[0].equals("Publication")) {
                Publication test = new Publication(info[1], info[2], info[3], Integer.parseInt(info[4]), Integer.parseInt(info[5]), Integer.parseInt(info[6]));
                tests.add(test);
            }else if(info[0].equals("doctoralDefense")){
                doctoralDefense test = new doctoralDefense(info[1], info[2], Integer.parseInt(info[3]));
                tests.add(test);
            }else if(info[0].equals("estudiMaster")){
                estudiMaster test = new estudiMaster(info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4]));
                tests.add(test);
            }else if(info[0].equals("budgetRequest")){
                budgetRequest test = new budgetRequest(info[1], info[2], Double.parseDouble(info[3]));
                tests.add(test);
            }
            j++;
        }
        return tests;
    }

    public void writeCSV(List<Edition> editions, String nameCSV){
        try {
            FileWriter csvWriter = new FileWriter(nameCSV);
            for (int i = 0; i < editions.size(); i++) {
                csvWriter.append (String.valueOf(editions.get(i).getYear()));
                csvWriter.append(",");
                csvWriter.append (String.valueOf(editions.get(i).getInitialPlayers()));
                csvWriter.append(",");
                csvWriter.append (String.valueOf(editions.get(i).getNumTest()));
                csvWriter.append(",");
                csvWriter.append (String.valueOf(editions.get(i).getRounds()));
                csvWriter.append(",");
                csvWriter.append("[");
                csvWriter.append (getTests(editions.get(i).getTests()));
                csvWriter.append("]");
                csvWriter.append(",");
                csvWriter.append("[");
                csvWriter.append (getPlayers(editions.get(i).getPlayers()));
                csvWriter.append("]");
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    private String getTests(List<Test> tests) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i <tests.size();i++) {
            result.append(tests.get(i).getClass().getSimpleName());
            result.append("/");
            if(tests.get(i).getClass().getSimpleName().equals("Publication")) {
                result.append(tests.get(i).getName());
                result.append("/");
                result.append(((Publication) tests.get(i)).getNameMag());
                result.append("/");
                result.append(((Publication) tests.get(i)).getQuartil());
                result.append("/");
                result.append(((Publication) tests.get(i)).getAcceptanceProbability());
                result.append("/");
                result.append(((Publication) tests.get(i)).getRevisionProbability());
                result.append("/");
                result.append(((Publication) tests.get(i)).getNotAcceptedProbability());
                result.append(";");
            }else if(tests.get(i).getClass().getSimpleName().equals("doctoralDefense")){
                result.append(tests.get(i).getName());
                result.append("/");
                result.append(((doctoralDefense) tests.get(i)).getField());
                result.append("/");
                result.append(((doctoralDefense) tests.get(i)).getDiff());
                result.append(";");
            }else if(tests.get(i).getClass().getSimpleName().equals("budgetRequest")){
                result.append(tests.get(i).getName());
                result.append("/");
                result.append(((budgetRequest) tests.get(i)).getEntity());
                result.append("/");
                result.append(((budgetRequest) tests.get(i)).getQuantity());
                result.append(";");
            }else if(tests.get(i).getClass().getSimpleName().equals("estudiMaster")){
                result.append(tests.get(i).getName());
                result.append("/");
                result.append(((estudiMaster) tests.get(i)).getMaster());
                result.append("/");
                result.append(((estudiMaster) tests.get(i)).getCredits());
                result.append("/");
                result.append(((estudiMaster) tests.get(i)).getProbability());
                result.append(";");
            }

        }
        return result.toString();
    }

    private String getPlayers(List<Player> players) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i <players.size();i++) {
            result.append(players.get(i).getName());
            result.append("/");
            result.append(players.get(i).getInvestigationPoints());
            result.append(";");
        }
        return result.toString();
    }

    public void printLinea(){
        for (int i = 0 ; i < partes.length; i++ ){
            System.out.print(partes[i]+"  |  ");

        }
    }

}
