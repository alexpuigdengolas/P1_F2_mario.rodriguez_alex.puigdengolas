package persistance;

import business.*;
import business.role.Doctor;
import business.role.Enginyer;
import business.role.Master;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

/**
 * This class will create the the file .json to contain the editions information
 */
public class JsonController {

    /**
     * This method will allow us to write the edition in a file .json
     * @param editions the list of the editions that we have in the code
     * @param address the address were we will save the information
     */
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

    /**
     * This method will allow us to write the tests in a file .json
     * @param editions the list of the editions that we have in the code
     * @param testPubliAddress the address of the publication tests
     * @param testDefAddress the address of the defense tests
     * @param testMasterAddress the address of the master tests
     * @param testReqAddress the address of the budget request tests
     */
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

    /**
     * This method will allow us to write the players in a file .json
     * @param editions the list of the editions that we have in the code
     * @param enginyerAddress the address where we will save the enginyers
     * @param masterAddress the address where we will save the masters
     * @param doctorAddress the address where we will save the doctors
     */
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

    /**
     * This method will allow us to read the edition in a file .json
     * @param address the address where we have the .json file
     * @return the list of editions contained in the .json
     */
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

    /**
     * This method will allow us to read the tests in a file .json
     * @param testPubliAddress the address of the publication tests
     * @param testDefAddress the address of the defense tests
     * @param testMasterAddress the address of the master tests
     * @param testReqAddress the address of the budget request tests
     * @return a list of the tests that are inside de .json
     */
    public LinkedList<Test> readTestJSON(String testPubliAddress, String testDefAddress, String testMasterAddress, String testReqAddress) {
        LinkedList<Test> tests = new LinkedList<>();
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

    /**
     * This method will allow us to read the players in a file .json
     * @param enginyerAddress the address where we will save the enginyers
     * @param masterAddress the address where we will save the masters
     * @param doctorAddress the address where we will save the doctors
     * @return a list of all the players of the .json
     */
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
