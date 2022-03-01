package Persistance;

import Business.*;
import Business.Role.Doctor;
import Business.Role.Enginyer;
import Business.Role.Master;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.print.Doc;
import javax.swing.*;
import javax.xml.crypto.KeySelector;
import java.io.*;
import java.util.*;

public class jsonController {
    public void writeEditionsJSON(List<Edition> editions, String address) {
        try {
            FileWriter writer = new FileWriter(address);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            gson.toJson(editions, writer);
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeTestsJSON(List<Edition> editions, String testPubliAddress, String testDefAddress, String testMasterAddress, String testReqAddress) {
        try {
            FileWriter testPubliWriter = new FileWriter(testPubliAddress);
            FileWriter testDefWriter = new FileWriter(testDefAddress);
            FileWriter testMasterWriter = new FileWriter(testMasterAddress);
            FileWriter testReqWriter = new FileWriter(testReqAddress);
            Gson testGson = new GsonBuilder().setPrettyPrinting().create();

            List<Publication> publications = new ArrayList<>();
            List<doctoralDefense> doctoralDefenses = new ArrayList<>();
            List<estudiMaster> estudiMasters = new ArrayList<>();
            List<budgetRequest> budgetRequests = new ArrayList<>();


            for (int i = 0; i < editions.size(); i++) {
                for (int j = 0; j < editions.get(i).getNumTest(); j++) {
                    if (editions.get(i).getTests().get(j).getClass().getSimpleName().equals("Publication")) {
                        publications.add((Publication) editions.get(i).getTests().get(j));
                    } else if (editions.get(i).getTests().get(j).getClass().getSimpleName().equals("doctoralDefense")) {
                        doctoralDefenses.add((doctoralDefense) editions.get(i).getTests().get(j));
                    } else if (editions.get(i).getTests().get(j).getClass().getSimpleName().equals("estudiMaster")) {
                        estudiMasters.add((estudiMaster) editions.get(i).getTests().get(j));
                    } else if (editions.get(i).getTests().get(j).getClass().getSimpleName().equals("budgetRequest")) {
                        budgetRequests.add((budgetRequest) editions.get(i).getTests().get(j));
                    }
                }
                testGson.toJson(publications, testPubliWriter);
                testGson.toJson(doctoralDefenses, testDefWriter);
                testGson.toJson(estudiMasters, testMasterWriter);
                testGson.toJson(budgetRequests, testReqWriter);

            }
            testPubliWriter.close();
            testDefWriter.close();
            testMasterWriter.close();
            testReqWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void writePlayersJSON(List<Edition> editions, String enginyerAddress, String masterAddress, String doctorAddress) {
        try {
            FileWriter enginyerWriter = new FileWriter(enginyerAddress);
            FileWriter masterWriter = new FileWriter(masterAddress);
            FileWriter doctorWriter = new FileWriter(doctorAddress);
            Gson testGson = new GsonBuilder().setPrettyPrinting().create();

            List<Enginyer> enginyers = new ArrayList<>();
            List<Master> masters = new ArrayList<>();
            List<Doctor> doctors = new ArrayList<>();


            for (int i = 0; i < editions.size(); i++) {
                for (int j = 0; j < editions.get(i).getPlayers().size(); j++) {
                    if (editions.get(i).getPlayers().get(j).getClass().getSimpleName().equals("Enginyer")) {
                        enginyers.add((Enginyer) editions.get(i).getPlayers().get(j));
                    } else if (editions.get(i).getPlayers().get(j).getClass().getSimpleName().equals("Master")) {
                        masters.add((Master) editions.get(i).getPlayers().get(j));
                    } else if (editions.get(i).getPlayers().get(j).getClass().getSimpleName().equals("Doctor")) {
                        doctors.add((Doctor) editions.get(i).getPlayers().get(j));
                    }
                }
                testGson.toJson(enginyers, enginyerWriter);
                testGson.toJson(masters, masterWriter);
                testGson.toJson(doctors, doctorWriter);

            }
            enginyerWriter.close();
            masterWriter.close();
            doctorWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Edition> readJSON(String address) {
        List<Edition> editions = new ArrayList<Edition>();

        Edition[] editionsArray;
        Gson gson;

        try {
            gson = new GsonBuilder().setPrettyPrinting().create();
            editionsArray = gson.fromJson(gson.newJsonReader(new FileReader(address)), Edition[].class);
            if (editionsArray == null) {
                editions = new LinkedList<>();
            } else {
                List<Edition> list = Arrays.asList(editionsArray);
                editions = new LinkedList<>(list);
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return editions;
    }

    public LinkedList<Test> readTestJSON(String testPubliAddress, String testDefAddress, String testMasterAddress, String testReqAddress) {
        LinkedList<Test> tests = new LinkedList<>();
        Test[] testsArray;
        Gson gson;

        try {

            gson = new GsonBuilder().setPrettyPrinting().create();
            Publication[] publications = gson.fromJson(gson.newJsonReader(new FileReader(testPubliAddress)), Publication[].class);
            if(publications != null){
                tests.addAll(Arrays.asList(publications));
            }

            estudiMaster[] masters = gson.fromJson(gson.newJsonReader(new FileReader(testMasterAddress)), estudiMaster[].class);
            if(masters != null){
                tests.addAll(Arrays.asList(masters));
            }

            budgetRequest[] budgetRequests = gson.fromJson(gson.newJsonReader(new FileReader(testReqAddress)), budgetRequest[].class);
            if(budgetRequests != null){
                tests.addAll(Arrays.asList(budgetRequests));
            }

            doctoralDefense[] doctoralDefenses = gson.fromJson(gson.newJsonReader(new FileReader(testDefAddress)), doctoralDefense[].class);
            if(doctoralDefenses != null){
                tests.addAll(Arrays.asList(doctoralDefenses));
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return tests;
    }

    public LinkedList<Player> readPlayersJSON(String enginyerAddress, String masterAddress, String doctorAddress) {
        LinkedList<Player> players = new LinkedList<>();
        Player[] playerArray;
        Gson gson;

        try {

            gson = new GsonBuilder().setPrettyPrinting().create();
            Enginyer[] enginyers = gson.fromJson(gson.newJsonReader(new FileReader(enginyerAddress)), Enginyer[].class);
            if(enginyers != null){
                players.addAll(Arrays.asList(enginyers));
            }

            Master[] masters = gson.fromJson(gson.newJsonReader(new FileReader(masterAddress)), Master[].class);
            if(masters != null){
                players.addAll(Arrays.asList(masters));
            }

            Doctor[] doctors = gson.fromJson(gson.newJsonReader(new FileReader(doctorAddress)), Doctor[].class);
            if(doctors != null) {
                players.addAll(Arrays.asList(doctors));
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return players;
    }
}
