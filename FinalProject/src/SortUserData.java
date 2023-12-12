import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortUserData {

    public static void main(String[] args) {
        sortUserWPM();
    }

    public static void sortUserWPM() {
        List<UserData> userDataList = readUserDataFromFile();

        if (!userDataList.isEmpty()) {
            // Sort user data based on WPM in descending order
            userDataList.sort(Comparator.comparingInt(UserData::wpm).reversed());

            // Write the sorted user data back to the file
            writeUserDataToFile(userDataList);

            System.out.println("User data sorted in descending order and written to sorted_user_data.txt");
        } else {
            System.out.println("No user data to sort.");
        }
    }

    static List<UserData> readUserDataFromFile() {
        List<UserData> userDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String userName = parts[0];
                    int wpm = Integer.parseInt(parts[1]);
                    userDataList.add(new UserData(userName, wpm));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return userDataList;
    }

    private static void writeUserDataToFile(List<UserData> userDataList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sorted_user_data.txt"))) {
            writer.write("Leaderboard:\n\n");
            writer.write(String.format("%-5s %-20s %-10s", "Rank", "User", "WPM"));
            writer.newLine();

            int rank = 1;
            for (UserData userData : userDataList.subList(0, Math.min(userDataList.size(), 10))) {
                writer.write(String.format("%-5d %-20s %-10d", rank, userData.userName(), userData.wpm()));
                writer.newLine();
                rank++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    record UserData(String userName, int wpm) {
        @Override
        public String toString() {
            return String.format("%-20s %-10d", userName, wpm);
        }
    }
}
