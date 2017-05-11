import javax.swing.*;
import java.awt.*;

/**
 * Created by Oscar on 5/10/17.
 */
public class JavaChess extends JFrame {
    public static final int height = 700;
    public static final int width = 1100;

    public static JavaChess MainScreen;
    private JPanel board = new JPanel(new GridLayout(8, 8));

    private static String move;

    public static void main(String[] args) {
        MainScreen = new JavaChess();
        MainScreen.setVisible(true);
        MainScreen.setResizable(false);
    }

    public JavaChess() {
        move = "White";
        board = new JPanel(new GridLayout(8, 8));
        board.setMinimumSize(new Dimension(800, 700));


    }

}
