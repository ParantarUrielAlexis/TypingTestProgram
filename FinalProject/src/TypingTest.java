import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;




public class TypingTest extends JFrame implements ActionListener, KeyListener {
    static String passage = "";
    String typedPass = "";
    String message = "";
    int typed = 0;
    int count = 0;
    int WPM;

    double start;
    double end;
    double elapsed;
    double seconds;

    static boolean running;
    boolean ended;

    final int SCREEN_WIDTH;
    final int SCREEN_HEIGHT;
    final int DELAY = 100;

    String userName;
    Color paleYellow = new Color(255, 255, 200);
    Color royalBlue = new Color(35,78,112);

    JButton button;
    JButton backButton;
    Timer timer;
    JLabel label;
    JPanel typingTestPanel;

    JButton leaderboardsButton;
    public TypingTest(String userName) {
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
        leaderboardsButton.setForeground(paleYellow);
        leaderboardsButton.setBackground(Color.black);
        leaderboardsButton.setVisible(false);
        leaderboardsButton.addActionListener(this);
        leaderboardsButton.setFocusable(false);

        label = new JLabel();
        label.setText("User: " + userName);
        label.setFont(new Font("Verdana", Font.BOLD, 30));
        label.setVisible(true);
        label.setOpaque(true);
        label.setForeground(paleYellow);
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

        this.getContentPane().setBackground(paleYellow);
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
            if (passage.length() > 1) {
                int xOffset = g.getFont().getSize();
                int yOffset = g.getFont().getSize() * 5;
                drawWrappedText(g, passage, xOffset, yOffset, SCREEN_WIDTH - xOffset);
            }

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
    public void keyTyped(KeyEvent e) {
        if (passage.length() > 1) {
            if (count == 0)
                start = LocalTime.now().toNanoOfDay();

            else if (count == passage.length()) {
                end = LocalTime.now().toNanoOfDay();
                elapsed = end - start;
                seconds = elapsed / 1000000000.0;
                WPM = (int) (((200.0 / 5) / seconds) * 60);
                ended = true;
                running = false;
                count++;
            }
            char[] pass = passage.toCharArray();
            if (typed < passage.length()) {
                running = true;
                if (e.getKeyChar() == pass[typed]) {
                    typedPass = typedPass + pass[typed];
                    typed++;
                    count++;
                }
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
            passage = PassageGenerator.getPassage();
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
            leaderboardsButton.setVisible(true);
        }
        if(e.getSource() == backButton){
            String newUserName = JOptionPane.showInputDialog(this, "Enter a new username:");

            if (newUserName != null && !newUserName.isEmpty()) {
                TypingTest newTypingTest = new TypingTest(newUserName);
                newTypingTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                newTypingTest.setSize(1000, 700);
                newTypingTest.setVisible(true);
                newTypingTest.setTitle("Typing Game");

                setVisible(false);
            }
        }
        if (e.getSource() == leaderboardsButton) {
            showLeaderboards();
        }
        if (running || ended) {
            repaint();
            backButton.setVisible(ended);
            leaderboardsButton.setVisible(ended);
            if (WPM != 0 && !running) {
                UserData.writeToFile(userName, WPM);
            }

            int previousWPM = UserData.readFromFile(userName);
            if (previousWPM != -1) {
                System.out.println("Previous WPM for " + userName + ": " + previousWPM);

                SortUserData.sortUserWPM();
            }
        }
    }

    private void showLeaderboards() {
        SortUserData.sortUserWPM();
        java.util.List<SortUserData.UserData> userDataList = SortUserData.readUserDataFromFile();
        LeaderBoards leaderBoards = new LeaderBoards(userDataList);
        leaderBoards.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        revalidate();
        leaderBoards.setVisible(true);
        leaderBoards.setTitle("LEADERBOARD");
    }
}