package Persistance;

import Business.Edition;
import Business.Publication;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class jsonController {
    public void writeJSON(List<Edition> editions, String address) {
        // creating JSONObject
        JSONObject jo = new JSONObject();
        try {

            // putting data to JSONObject
            for (int i = 0; i < editions.size(); i++) {
                jo.put("year", editions.get(i).getYear());
                jo.put("initialPlayers", editions.get(i).getInitialPlayers());
                jo.put("numTests", editions.get(i).getNumTest());
                jo.put("rounds", editions.get(i).getRounds());

                Map tests = new LinkedHashMap();
                for (int j = 0; j < editions.get(i).getNumTest(); j++) {
                    if (editions.get(i).getTests().get(j).getClass().getSimpleName().equals("Publication")) {
                        Publication publication = (Publication) editions.get(i).getTests().get(j);
                        tests.put("name", publication.getName());
                        tests.put("nameMag", publication.getNameMag());
                        tests.put("quartil", publication.getQuartil());
                        tests.put("acceptanceProblability", publication.getAcceptanceProbability());
                        tests.put("revisionProbability", publication.getRevisionProbability());
                        tests.put("notAcceptedProbability", publication.getNotAcceptedProbability());
                    }
                    //TODO Poder escribir el resto de Tests
                }
                jo.put("tests", tests);

                // for address data, first create LinkedHashMap
                Map players = new LinkedHashMap();
                for (int j = 0; j < editions.get(i).getInitialPlayers(); j++) {
                    players.put("name", editions.get(i).getPlayers().get(j).getName());
                    players.put("investigationPoints", editions.get(i).getPlayers().get(j).getInvestigationPoints());
                }
                jo.put("players", players);

            }

            // writing JSON to file:"JSONExample.json" in cwd
            PrintWriter pw = new PrintWriter(address);
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public List<Edition> readJSON(String address) {
        List<Edition> editions = new ArrayList<Edition>();
        try {
            // parsing file "JSONExample.json"
            Object obj = new JSONParser().parse(new FileReader(address));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            for(int i = 0; jo.isEmpty(); i++) {
                // getting firstName and lastName
                editions.get(i).setYear((Integer) jo.get("year"));
                editions.get(i).setInitialPlayers((int) jo.get("initialPlayers"));
                editions.get(i).setNumTest((Integer) jo.get("numTest"));
                editions.get(i).setRounds((int) jo.get("rounds"));

                //Ejemplo de como hacer para leer Players y Tests
                Iterator<Map.Entry> itr1 = address.entrySet().iterator();
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }
            }
            return editions;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
