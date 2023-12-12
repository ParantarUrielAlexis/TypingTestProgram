import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.io.File;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class startPage extends JFrame {
    JPanel startPagePanel;
    private JButton startBtn;
    private JTextField inputUserName;
    private JLabel nameInput;
    private JSeparator horizontalLine;
    private JSeparator horizontalLine2;
    private JLabel typingGameTitle;
    private JSeparator transparentSepartor;
    private JButton leaderBoardsBtn;
    private boolean musicPlaying;
    private Clip clip; // Add a Clip field to control music playback

    public startPage() {
        leaderBoardsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the function to sort and display leaderboards
                showLeaderboards();
            }
        });

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUserName = null;
                boolean valid = true;
                try {
                    enteredUserName = inputUserName.getText();
                    containsAlphanumericCharacters(enteredUserName);
                    if(enteredUserName.isEmpty()){
                        throw new InputMismatchException("Input Username");
                    }
                } catch (InputMismatchException ime) {
                    JOptionPane.showMessageDialog(startPage.this, ime.getMessage());
                    valid = false;
                }
                if (valid) {
                    openTypingTest(enteredUserName);
                }
            }
        });

        // Initialize the Clip for music
        clip = getClip("Official Opening Credits_ Game of Thrones (HBO).wav");
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
            musicPlaying = true;
        }
    }

    public startPage(String newUserName) {
    }

    private void showLeaderboards() {
        // Call the function to sort user data
        SortUserData.sortUserWPM();
        // Example usage of LeaderBoards (for testing purposes)
        List<SortUserData.UserData> userDataList = SortUserData.readUserDataFromFile();
        LeaderBoards leaderBoards = new LeaderBoards(userDataList);
        leaderBoards.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        revalidate();
        leaderBoards.setVisible(true);
        leaderBoards.setTitle("LEADERBOARD");

    }

    private void containsAlphanumericCharacters(String input) throws InputMismatchException {
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                throw new InputMismatchException("Alphanumeric and numeric characters are not allowed!");
            }
        }
    }


    private void openTypingTest(String userName) {
        stopMusic();
        // Close the current startPage GUI
        setVisible(false);
        // Open the new TypingTest GUI
        TypingTest typingTest = new TypingTest(userName);
        typingTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        revalidate();
        typingTest.setVisible(true);
        typingTest.setTitle("Typing Game");

        clip = getClip("The Pointy End.wav");
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
            musicPlaying = true;
        }
    }

    private void stopMusic() {
        if (musicPlaying) {
            clip.stop();
            musicPlaying = false;
        }
    }

    private Clip getClip(String filePath) {
        try {
            File audioFile = new File(filePath);

            if (audioFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();  // Update the clip field here
                clip.open(audioInput);
                return clip;
            } else {
                System.out.println("Can't find file");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        startPage app = new startPage();
        app.setContentPane(app.startPagePanel);
        app.setSize(1000, 700);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setTitle("Typing Game");
    }
}
