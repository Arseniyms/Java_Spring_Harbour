package service2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Ship;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    public static void writeToJson(String ships) {

        try (FileWriter file = new FileWriter("ships.json")) {
            //Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //String json = gson.toJson(ships);
            file.write(ships);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
