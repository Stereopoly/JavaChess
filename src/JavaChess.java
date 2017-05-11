import javax.swing.*;
import java.awt.*;

/**
 * Created by Oscar on 5/10/17.
 */
public class JavaChess extends JFrame {
    public static final int height = 700;
    public static final int width = 1100;

    // pieces
    private static Pawn whitePawns[];
    public static Pawn blackPawns[];


    public static JavaChess MainScreen;
    private JPanel board = new JPanel(new GridLayout(8, 8));

    private static String move;

    public static void main(String[] args) {
        // generate pieces
        whitePawns = new Pawn[8];
        blackPawns = new Pawn[8];
        for (int i = 0; i < whitePawns.length; i++) {
            whitePawns[i] = new Pawn("WhitePawn0" + (i + 1), "ImageName", 0);  // TODO: Add image name
            blackPawns[i] = new Pawn("BlackPawn0" + (i + 1), "ImageName", 1);
        }


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
