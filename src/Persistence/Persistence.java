package src.Persistence;

import java.io.*;
import src.Model.*;

public class Persistence {

    /**
     * Saves the WordTrainer object to a file.
     *
     * @param data The WordTrainer object to save.
     * @return true if the data was successfully saved, false otherwise.
     */
    public static boolean saveData(WordTrainer data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("wordTrainerData.ser"))) {
            oos.writeObject(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Loads the WordTrainer object from a file.
     *
     * @return The loaded WordTrainer object, or null if the loading failed.
     */
    public static WordTrainer loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("wordTrainerData.ser"))) {
            return (WordTrainer) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
