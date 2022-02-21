package Persistance;

import Business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
            /*
            gson = new GsonBuilder().setPrettyPrinting().create();
            gson.fromJson(gson.newJsonReader(new FileReader(address)), Test[].class);
            testsArray = gson.fromJson(gson.newJsonReader(new FileReader(address)), Test[].class);
            if (testsArray == null) {
                tests = new LinkedList<>();
            } else {
                List<Test> list = Arrays.asList(testsArray);
                tests = new LinkedList<>(list);
            }*/

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
}
