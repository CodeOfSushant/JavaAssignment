import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialize {

    static String fileName = "studentdetails.txt";
    static List<StudentDetails> localList = new ArrayList<>();

    //Method using Serialization process to save data to file in local disk
    public static void saveDataToDisk(List<StudentDetails> savedList){
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(savedList);

        } catch (IOException e) {
            System.err.println(" Unable to Save Data due to:" + e.getMessage());
        }
    }

    // Method using Serialization process to fetch data from local disk file.
    public static List<StudentDetails> readDataFromDisc(){
        try {
            // List<StudentDetails> currentList = new ArrayList<>();
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            localList = (ArrayList<StudentDetails>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" File Name/Class Name Not found ");
        }
        return localList;
    }
}
