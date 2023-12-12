import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class LeaderBoards extends JFrame {
    public JPanel leaderBoardPanel;
    private JTextArea ranking;

    public LeaderBoards(List<SortUserData.UserData> userDataList) {
        // Initialize components, set layout, and add UI elements
        leaderBoardPanel = new JPanel(new BorderLayout());
        leaderBoardPanel.setBackground(Color.black);
        leaderBoardPanel.setForeground(Color.white);
        ranking = new JTextArea();
        ranking.setEditable(false);
        ranking.setBackground(Color.black);
        ranking.setForeground(Color.white);
        ranking.setFont(new Font("Verdana", Font.PLAIN, 32));

        // Display leaderboards information
        displayLeaderboards(userDataList);

        JScrollPane scrollPane = new JScrollPane(ranking);
        add(scrollPane);

        setTitle("Leaderboards");
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add window listener to handle the last leaderboard window
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Check if this is the last leaderboard window
                if (JFrame.getFrames().length == 0) {
                    // If it is the last window, exit the program
                    System.exit(0);
                }
            }
        });
    }

    private void displayLeaderboards(List<SortUserData.UserData> userDataList) {
        StringBuilder leaderboardText = new StringBuilder("Leaderboards:\n");
        int rank = 1;

        // Sort user data based on WPM in descending order
        userDataList.sort(Comparator.comparingInt(SortUserData.UserData::wpm).reversed());

        // Display only the top 10 users
        for (SortUserData.UserData userData : userDataList.subList(0, Math.min(userDataList.size(), 10))) {
            leaderboardText.append(rank).append(". ").append(userData.userName()).append(" - ").append(userData.wpm()).append("\n");
            leaderboardText.append("--------------------------------------------------------------------\n");
            rank++;
        }
        ranking.setText(leaderboardText.toString());
    }


    public static void main(String[] args) {
        // Example usage of LeaderBoards (for testing purposes)
        List<SortUserData.UserData> userDataList = SortUserData.readUserDataFromFile();
        LeaderBoards leaderBoards = new LeaderBoards(userDataList);
        leaderBoards.setVisible(true);
    }
}
