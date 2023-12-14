import javax.swing.*;

public class Main
{
    public static void main(String[] args) {
        startPage app = new startPage();
        app.setContentPane(app.startPagePanel);
        app.setSize(1000, 700);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setTitle("Typing Game");
    }
}