package service2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Ship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {
    public static List<Ship> readFromJson(String nameOfFile) throws FileNotFoundException {
        Gson gson = new Gson();
        Type itemsListType = new TypeToken<List<Ship>>() {}.getType();
        try (Reader file = new FileReader(nameOfFile)) {
            List<Ship> ships= gson.fromJson(file, itemsListType);
            return ships;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File was not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
