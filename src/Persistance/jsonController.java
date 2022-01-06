package Persistance;

import Business.Edition;
import Business.Publication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class jsonController {
    public void writeJSON(List<Edition> editions, String address) {
        try {
            FileWriter writer = new FileWriter(address);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            gson.toJson(editions, writer);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Edition> readJSON(String address) {
        List<Edition> editions = new ArrayList<Edition>();

        Edition[] editionsArray;
        Gson gson;

        try {
            /*
            String f = new File("").getAbsolutePath();
            JsonReader fitxer = null;
            fitxer = new JsonReader(new FileReader(f.concat(address)));
            Gson gson = new Gson();
            //Copiamos el fichero Json a teams
            editions = gson.fromJson(fitxer, Edition.class);
            */
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
}
