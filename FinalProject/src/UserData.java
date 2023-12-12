import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class UserData {

    public static void writeToFile(String userName, int WPM) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt", true))) {
            writer.write(userName + "," + WPM);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readFromFile(String userName) {
        int finalWPM = -1; // Default value if user data is not found

        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUserName = parts[0];
                    int storedWPM = Integer.parseInt(parts[1]);

                    if (storedUserName.equals(userName) && storedWPM != 0) {
                        // Match found for the user with non-zero WPM, update the finalWPM value
                        finalWPM = storedWPM;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return finalWPM;
    }
}
