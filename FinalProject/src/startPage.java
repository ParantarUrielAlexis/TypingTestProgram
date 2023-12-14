import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.InputMismatchException;
import java.util.List;

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
    private JButton EXITButton;
    private JButton CHANGETHEMEButton;
    private boolean musicPlaying;
    private Clip clip;

    private Clip MCclip;

    public startPage() {

        MinecraftPage MCpage = new MinecraftPage();




        MCpage.getSingleTyperButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String EnteredName = null;
                boolean valid = true;
                try{
                    EnteredName = MCpage.getTextField1().getText();
                    containsAlphanumericCharacters(EnteredName);
                    if(EnteredName.isEmpty()){
                        throw new InputMismatchException("Input Username");
                    }
                } catch (InputMismatchException ime) {
                    JOptionPane.showMessageDialog(startPage.this, ime.getMessage());
                    valid = false;
                }
                if (valid){
                        openMCTypingTest(EnteredName);
                }
            }
        });
        MCpage.getQuitGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
                dispose();
            }
        });
        MCpage.getChangeThemeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startPagePanel.setVisible(true);
                MCpage.getMinecraftPagepanel().setVisible(false);
                setContentPane(startPagePanel);
                stopMusic();
                clip = getClip("Official Opening Credits_ Game of Thrones (HBO).wav");
                if (clip != null) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
                    musicPlaying = true;
                }
            }
        });

        CHANGETHEMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MCpage.getMinecraftPagepanel().setVisible(true);
                startPagePanel.setVisible(false);
                setContentPane(MCpage.getMinecraftPagepanel());
                stopMusic();
                MCclip = getClip("Sweden.wav");
                if (MCclip != null) {
                    MCclip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
                    musicPlaying = true;
                }
            }
        });

        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
                dispose();
            }
        });
        MCpage.getLeaderboardsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLeaderboards();
            }
        });
        leaderBoardsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        clip = getClip("Official Opening Credits_ Game of Thrones (HBO).wav");
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            musicPlaying = true;
        }
    }

    public startPage(String newUserName) {
    }

    private void showLeaderboards() {
        SortUserData.sortUserWPM();
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
        setVisible(false);
        TypingTest typingTest = new TypingTest(userName);
        typingTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        revalidate();
        typingTest.setVisible(true);
        typingTest.setTitle("Typing Game");

    }

    private void openMCTypingTest(String userName) {
        stopMusic();
        // Close the current startPage GUI
        setVisible(false);
        // Open the new TypingTest GUI
        openMCTypingTest MCTest = new openMCTypingTest(userName);
        MCTest .setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        revalidate();
        MCTest.setVisible(true);
        MCTest.setTitle("Typing Game");

        clip = getClip("Lullaby.wav");
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
                clip = AudioSystem.getClip();
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
