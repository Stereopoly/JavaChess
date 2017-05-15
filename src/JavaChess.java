import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Oscar on 5/10/17.
 */
public class JavaChess extends JFrame implements MouseListener {
    public static final int height = 800;
    public static final int width = 800;

    // pieces
    private static Pawn whitePawns[];
    private static Pawn blackPawns[];
    private static Rook whiteRook1, whiteRook2, blackRook1, blackRook2;
    private static King whiteKing, blackKing;
    private static Bishop whiteBishop1, whiteBishop2, blackBishop1, blackBishop2;
    private static Knight whiteKnight1, whiteKnight2, blackKnight1, blackKnight2;
    private static Queen whiteQueen, blackQueen;

    // ui
    private Container content;
    private Cell chessBoardState[][];

    // game logic
    private Cell cellPressed, previousCellPressed;
    private int currentPlayer = 0;   // 0 for white, 1 for black
    private ArrayList<Cell> possibleDestinations = new ArrayList<Cell>();


    public static JavaChess MainScreen;
    private JPanel board = new JPanel(new GridLayout(8, 8));


    public static void main(String[] args) {
        // generate pieces
        // pawns
        whitePawns = new Pawn[8];
        blackPawns = new Pawn[8];
        for (int i = 0; i < whitePawns.length; i++) {
            whitePawns[i] = new Pawn("WhitePawn0" + (i + 1), "whitepawn.png", 0);
            blackPawns[i] = new Pawn("BlackPawn0" + (i + 1), "blackpawn.png", 1);
        }
        // rooks
        whiteRook1 = new Rook("WhiteRook1", "whiterook.png", 0);
        whiteRook2 = new Rook("WhiteRook2", "whiterook.png", 0);
        blackRook1 = new Rook("BlackRook1", "blackrook.png", 1);
        blackRook2 = new Rook("blackRook2", "blackrook.png", 1);
        // kings
        whiteKing = new King("WhiteKing", "whiteking.png", 0, 7, 3);
        blackKing = new King("BlackKing", "blackking.png", 1, 0, 3);
        // bishops
        whiteBishop1 = new Bishop("WhiteBishop1", "whitebishop.png", 0);
        whiteBishop2 = new Bishop("WhiteBishop2", "whitebishop.png", 0);
        blackBishop1 = new Bishop("BlackBishop1", "blackbishop.png", 1);
        blackBishop2 = new Bishop("BlackBishop2", "blackbishop.png", 1);
        // knights
        whiteKnight1 = new Knight("WhiteKnight1", "whiteknight.png", 0);
        whiteKnight2 = new Knight("WhiteKnight2", "whiteknight.png", 0);
        blackKnight1 = new Knight("BlackKnight1", "blackknight.png", 1);
        blackKnight2 = new Knight("BlacKKnight2", "blackknight.png", 1);
        // queens
        whiteQueen = new Queen("WhiteQueen", "whitequeen.png", 0);
        blackQueen = new Queen("BlackQueen", "blackqueen.png", 1);

        MainScreen = new JavaChess();
        MainScreen.setVisible(true);
        MainScreen.setResizable(false);
    }

    public JavaChess() {
        board = new JPanel(new GridLayout(8, 8));
        board.setMinimumSize(new Dimension(480, 480));

        Cell cell;
        Piece piece;

        board.setBorder(BorderFactory.createLoweredBevelBorder());
        content = getContentPane();
        setSize(width, height);
        setTitle("Java Chess");
        content.setBackground(Color.black);
        content.setLayout(new BorderLayout());

        // generate all pieces and put on board
        chessBoardState = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                piece = null;

                if (i == 0 && j == 0) {
                    piece = blackRook1;
                } else if (i == 0 && j == 7) {
                    piece = blackRook2;
                } else if (i == 7 && j == 0) {
                    piece = whiteRook1;
                } else if (i == 7 && j == 7) {
                    piece = whiteRook2;
                } else if (i == 0 && j == 3) {
                    piece = blackKing;
                } else if (i == 7 && j == 3) {
                    piece = whiteKing;
                } else if (i == 7 && j == 2) {
                    piece = whiteBishop1;
                } else if (i == 7 && j == 5) {
                    piece = whiteBishop2;
                } else if (i == 0 && j == 2) {
                    piece = blackBishop1;
                } else if (i == 0 && j == 5) {
                    piece = blackBishop2;
                } else if (i == 7 && j == 1) {
                    piece = whiteKnight1;
                } else if (i == 7 && j == 6) {
                    piece = whiteKnight2;
                } else if (i == 0 && j == 1) {
                    piece = blackKnight1;
                } else if (i == 0 && j == 6) {
                    piece = blackKnight2;
                } else if (i == 7 && j == 4) {
                    piece = whiteQueen;
                } else if (i == 0 && j == 4) {
                    piece = blackQueen;
                }
//                TODO: All other pieces to be generated before pawns
                else if (i == 1) {
                    piece = blackPawns[j];
                } else if (i == 6) {
                    piece = whitePawns[j];
                }
                cell = new Cell(i, j, piece);
                cell.addMouseListener(this);
                board.add(cell);
                chessBoardState[i][j] = cell;
            }
        }
        content.add(board);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private King getKing(int color) {
        if (color == 0) {
            return whiteKing;
        } else {
            return blackKing;
        }
    }

    // Functions for highlighting cells
    private void clearDestinations(ArrayList<Cell> cells) {
        for (Cell cell : cells) {
            cell.removeHighlightDestinations();
        }
    }

    private void highlightDestinations(ArrayList<Cell> cells) {
        for (Cell cell : cells) {
            cell.highlightPossibleDestinations();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cellPressed = (Cell) e.getSource();

        if (previousCellPressed == null) {
            if (cellPressed.getPiece() != null) {
                if (cellPressed.getPiece().getColor() == currentPlayer) {
                    cellPressed.selectPiece();
                    previousCellPressed = cellPressed;

                    possibleDestinations.clear();
                    possibleDestinations = cellPressed.getPiece().move(chessBoardState, cellPressed.x, cellPressed.y);
                    if (cellPressed.getPiece() instanceof King) {
//                        TODO: Filter out moves that don't put king in check
                    } else {
//                        TODO: Find moves to get king out of check if needed
                    }
                    highlightDestinations(possibleDestinations);
                } else {
                    return;
                }
            }
        } else {
            if (cellPressed.x == previousCellPressed.x && cellPressed.y == previousCellPressed.y) {
                // deselect current clicked location
                cellPressed.deselectPiece();
                clearDestinations(possibleDestinations);
                possibleDestinations.clear();
                previousCellPressed = null;
            } else if (cellPressed.getPiece() == null || previousCellPressed.getPiece().getColor() != cellPressed.getPiece().getColor()) {
                // selected empty spot or opponent piece
                if (cellPressed.isPossibleDestination()) {
                    if (cellPressed.getPiece() != null) {
                        cellPressed.removePiece();
                    }
                    cellPressed.setPiece(previousCellPressed.getPiece());
//                    TODO: check logic
                    previousCellPressed.removePiece();

                    changePlayerTurn();
                }
                if (previousCellPressed != null) {
                    previousCellPressed.deselectPiece();
                    previousCellPressed = null;
                }
                clearDestinations(possibleDestinations);
                possibleDestinations.clear();

            } else if (previousCellPressed.getPiece().getColor() == cellPressed.getPiece().getColor()) {
                previousCellPressed.deselectPiece();
                clearDestinations(possibleDestinations);
                possibleDestinations.clear();

                cellPressed.selectPiece();
                previousCellPressed = cellPressed;
                possibleDestinations = cellPressed.getPiece().move(chessBoardState, cellPressed.x, cellPressed.y);
                if (cellPressed.getPiece() instanceof King) {
//                        TODO: Filter out moves that don't put king in check (same as above)
                } else {
//                        TODO: Find moves to get king out of check if needed (same as above)
                }
                highlightDestinations(possibleDestinations);
            }
        }
    }

    private void changePlayerTurn() {
//        TODO: Check if game over

        if (possibleDestinations.isEmpty() == false) {
            clearDestinations(possibleDestinations);
        }
        if (previousCellPressed != null) {
            previousCellPressed.deselectPiece();
        }
        previousCellPressed = null;
        currentPlayer = 1 - currentPlayer;

        switch (currentPlayer) {
            case 0: setTitle("Java Chess - White's Turn to Move"); break;
            case 1: setTitle("Java Chess - Black's Turn to Move"); break;
            default: setTitle("Java Chess"); break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
