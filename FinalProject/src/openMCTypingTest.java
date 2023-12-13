import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
public class openMCTypingTest extends JFrame implements ActionListener, KeyListener {





    static String passage = ""; //Passage we get
    String typedPass = ""; //Passage the user types
    String message = ""; //Message to display at the end of the TypingTest

    int typed = 0; //typed stores till which character the user has typed
    int count = 0;
    int WPM;

    double start;
    double end;
    double elapsed;
    double seconds;

    static boolean running; //If the person is typing
    boolean ended; //Whether the typing test has ended or not

    final int SCREEN_WIDTH;
    final int SCREEN_HEIGHT;
    final int DELAY = 100;

    String userName;
    Color Brown = new Color(146, 104, 10);
    Color royalBlue = new Color(35,78,112);

    JButton button;
    JButton backButton;
    Timer timer;
    JLabel label;
    JPanel typingTestPanel;


    JButton leaderboardsButton;
    public openMCTypingTest(String userName) {
        this.userName = userName;
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SCREEN_WIDTH = 1000;
        SCREEN_HEIGHT = 700;
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        leaderboardsButton = new JButton("Leaderboards");
        leaderboardsButton.setFont(new Font("Verdana", Font.BOLD, 30));
        leaderboardsButton.setForeground(Color.LIGHT_GRAY);
        leaderboardsButton.setBackground(Color.DARK_GRAY);
        leaderboardsButton.setVisible(false);
        leaderboardsButton.addActionListener(this);
        leaderboardsButton.setFocusable(false);

        label = new JLabel();
        label.setText("User: " + userName);
        label.setFont(new Font("Verdana", Font.BOLD, 30));
        label.setVisible(true);
        label.setOpaque(true);
        label.setForeground(Brown);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(Color.BLACK);


        button = new JButton("Start");
        button.setFont(new Font("Verdana", Font.BOLD, 30));
        button.setForeground(Color.GREEN);
        button.setVisible(true);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setBackground(Color.BLACK);

        backButton = new JButton("NEW USER");
        backButton.setFont(new Font("Verdana", Font.BOLD, 30));
        backButton.setForeground(Color.RED);
        backButton.setBackground(Color.black);
        backButton.setVisible(false);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button);
        buttonPanel.setBackground(Color.black);
        buttonPanel.add(leaderboardsButton);
        buttonPanel.add(backButton);




        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(label, BorderLayout.NORTH);


        label = new JLabel();
        label.setText("User: " + userName);
        label.setFont(new Font("Verdana", Font.BOLD, 30));
        label.setVisible(true);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(royalBlue);

        this.getContentPane().setBackground(Brown);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setResizable(true);
        this.setTitle("Typing Test");
        this.revalidate();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setFont(new Font("Verdana", Font.BOLD, 20));
        g.setColor(Color.black);
        if (running) {
            // This will put our passage on the screen
            if (passage.length() > 1) {
                int xOffset = g.getFont().getSize();
                int yOffset = g.getFont().getSize() * 5;
                drawWrappedText(g, passage, xOffset, yOffset, SCREEN_WIDTH - xOffset);
            }

            // Displaying correctly typed passage in GREEN
            g.setColor(Color.GREEN);
            if (!typedPass.isEmpty()) {
                int xOffset = g.getFont().getSize();
                int yOffset = g.getFont().getSize() * 5;
                drawWrappedText(g, typedPass, xOffset, yOffset, SCREEN_WIDTH - xOffset);
            }

            running = false;
        }
        if (ended) {
            if (WPM <= 40)
                message = "You are an Average Typist";
            else if (WPM <= 60)
                message = "You are a Good Typist";
            else if (WPM <= 100)
                message = "You are an Excellent Typist";
            else
                message = "You are an Elite Typist";

            FontMetrics metrics = getFontMetrics(g.getFont());
            g.setColor(Color.red);
            g.drawString("Typing Test Completed!", (SCREEN_WIDTH - metrics.stringWidth("Typing Test Completed!")) / 2, g.getFont().getSize() * 6);

            g.setColor(Color.black);
            g.drawString("Typing Speed: " + WPM + " Words Per Minute", (SCREEN_WIDTH - metrics.stringWidth("Typing Speed: " + WPM + " Words Per Minute")) / 2, g.getFont().getSize() * 9);
            g.drawString(message, (SCREEN_WIDTH - metrics.stringWidth(message)) / 2, g.getFont().getSize() * 11);

            timer.stop();
            ended = false;
        }
    }

    private void drawWrappedText(Graphics g, String text, int xOffset, int yOffset, int maxWidth) {
        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight();

        String[] words = text.split("\\s+");
        StringBuilder line = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            String testLine = line + " " + words[i];
            if (metrics.stringWidth(testLine) < maxWidth) {
                line = new StringBuilder(testLine);
            } else {
                g.drawString(line.toString(), xOffset, yOffset);
                yOffset += lineHeight;
                line = new StringBuilder(words[i]);
            }
        }

        g.drawString(line.toString(), xOffset, yOffset);
    }

    @Override
    public void keyTyped(KeyEvent e) {//keyTyped uses the key Character which can identify capital and lowercase difference in keyPressed it takes unicode so it also considers shift which creates a problem
        if (passage.length() > 1) {
            if (count == 0)
                start = LocalTime.now().toNanoOfDay();

            else if (count == passage.length()) { //Once all 200 characters are typed we will end the time and calculate time elapsed
                end = LocalTime.now().toNanoOfDay();
                elapsed = end - start;
                seconds = elapsed / 1000000000.0; //nano/1000000000.0 is seconds
                WPM = (int) (((200.0 / 5) / seconds) * 60); //number of character by 5 is one word by seconds is words per second * 60 WPM
                ended = true;
                running = false;
                count++;
            }
            char[] pass = passage.toCharArray();
            if (typed < passage.length()) {
                running = true;
                if (e.getKeyChar() == pass[typed]) {
                    typedPass = typedPass + pass[typed]; //To the typed Passage we are adding what is currently typed
                    typed++;
                    count++;
                } //If the typed character is not equal to the current position it will not add it to the typedPassage, so the user needs to type the right thing
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override

    public void actionPerformed(ActionEvent e) {



        if (e.getSource() == button) {
            passage = MCPassageGenerator.getPassage();
            timer = new Timer(DELAY, this);
            timer.start();
            running = true;
            ended = false;

            typedPass = "";
            message = "";

            typed = 0;
            count = 0;

            backButton.setVisible(true);
            button.setText("RETRY");
            leaderboardsButton.setVisible(true);  // Hide the Leaderboards button when the game starts
        }
        if(e.getSource() == backButton){
            String newUserName = JOptionPane.showInputDialog(this, "Enter a new username:");

            if (newUserName != null && !newUserName.isEmpty()) {
                // Create a new instance of TypingTest with the new username
                TypingTest newTypingTest = new TypingTest(newUserName);
                newTypingTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                newTypingTest.setSize(1000, 700);
                newTypingTest.setVisible(true);
                newTypingTest.setTitle("Typing Game");

                setVisible(false); // Hide the current TypingTest
            }
        }
        if (e.getSource() == leaderboardsButton) {
            // Display leaderboards (you can replace this with your actual leaderboard display logic)
            showLeaderboards();
        }
        if (running || ended) {
            repaint();
            backButton.setVisible(ended);
            leaderboardsButton.setVisible(ended);  // Show the Leaderboards button after the game has ended
            // Write user data to the file before reading
            if (WPM != 0 && !running) {
                // Write user data to the file only if WPM is not 0 and it's not a retry
                UserData.writeToFile(userName, WPM);
            }

            // Read the final WPM from the file
            int previousWPM = UserData.readFromFile(userName);
            if (previousWPM != -1) {
                System.out.println("Previous WPM for " + userName + ": " + previousWPM);
                // Do something with the previous WPM value if needed
                SortUserData.sortUserWPM();
            }
        }
    }

    private void showLeaderboards() {
        // Call the function to sort user data
        SortUserData.sortUserWPM();
        // Example usage of LeaderBoards (for testing purposes)
        java.util.List<SortUserData.UserData> userDataList = SortUserData.readUserDataFromFile();
        LeaderBoards leaderBoards = new LeaderBoards(userDataList);
        leaderBoards.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        revalidate();
        leaderBoards.setVisible(true);
        leaderBoards.setTitle("LEADERBOARD");
    }
}