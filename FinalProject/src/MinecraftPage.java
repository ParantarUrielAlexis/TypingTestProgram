import javax.swing.*;

public class MinecraftPage extends JFrame {


    private JPanel MinecraftPagepanel;
    private JButton singleTyperButton;
    private JButton leaderboardsButton;
    private JButton changeThemeButton;
    private JButton quitGameButton;
    private JTextField textField1;

    public JButton getSingleTyperButton() {
        return singleTyperButton;
    }

    public JButton getLeaderboardsButton() {
        return leaderboardsButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JPanel getMinecraftPagepanel() {
        return MinecraftPagepanel;
    }


    public JButton getChangeThemeButton() {
        return changeThemeButton;
    }

    public JButton getQuitGameButton() {
        return quitGameButton;
    }

    public static void main(String[] args) {
        MinecraftPage app = new MinecraftPage();
        app.setContentPane(app.MinecraftPagepanel);
        app.setSize(1000, 700);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setTitle("Typing Game");
    }
}
