import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;

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


    private static JavaChess MainScreen;
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
                } else if (i == 1) {
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        System.out.println("Length of array to clear: " + cells.size());
        System.out.println("In clear function");
        printArrayList(cells);
        System.out.println("Clear destinations");
        for (int i = 0; i < cells.size(); i++) {
            System.out.println("per cell");
            cells.get(i).removeHighlightDestinations();
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
                        possibleDestinations = filterKingInDangerMoves(possibleDestinations, cellPressed);
                    } else {
                        // find moves to get king out of check
                        if (chessBoardState[getKing(currentPlayer).getX()][getKing(currentPlayer).getY()].isCheck()) {
                            possibleDestinations = new ArrayList<Cell>(filterKingInDangerMoves(possibleDestinations, cellPressed));
                        } else if (!possibleDestinations.isEmpty() && willKingBeInCheck(cellPressed, possibleDestinations.get(0))) {
                            possibleDestinations.clear();
                        }
                    }
                    highlightDestinations(possibleDestinations);
                    System.out.println("Populated");
                    printArrayList(possibleDestinations);
                } else {
                    return;
                }
            }
        } else {
            if (cellPressed.x == previousCellPressed.x && cellPressed.y == previousCellPressed.y) {
                // deselect current clicked location
                System.out.println("Clicked same place");
                printArrayList(possibleDestinations);
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
                    if (previousCellPressed.isCheck()) {
                        previousCellPressed.removeCheck();
                    }
                    previousCellPressed.removePiece();
                    if (getKing(1 - currentPlayer).isKingInDanger(chessBoardState, 1 - currentPlayer)) {
                        // check if its checkmate
                        chessBoardState[getKing(1 - currentPlayer).getX()][getKing(1 - currentPlayer).getY()].setCheck();
                        // TODO: Checkmate logic
                    }
                    if (!getKing(currentPlayer).isKingInDanger(chessBoardState, currentPlayer)) {
                        // not in check anymore
                        chessBoardState[getKing(currentPlayer).getX()][getKing(currentPlayer).getY()].removeCheck();
                    }
                    if (cellPressed.getPiece() instanceof King) {
                        ((King) cellPressed.getPiece()).setX(cellPressed.x);
                        ((King) cellPressed.getPiece()).setY(cellPressed.y);
                    }
                    changePlayerTurn();
                }
                if (previousCellPressed != null) {
                    previousCellPressed.deselectPiece();
                    previousCellPressed = null;
                }
                System.out.println("Empty spot or opponent piece");
                printArrayList(possibleDestinations);
                clearDestinations(possibleDestinations);
                possibleDestinations.clear();

            } else if (previousCellPressed.getPiece().getColor() == cellPressed.getPiece().getColor()) {
                previousCellPressed.deselectPiece();
                System.out.println("Clear clicked on different piece of same color");
                printArrayList(possibleDestinations);
                clearDestinations(possibleDestinations);
                possibleDestinations.clear();

                cellPressed.selectPiece();
                previousCellPressed = cellPressed;
                possibleDestinations = cellPressed.getPiece().move(chessBoardState, cellPressed.x, cellPressed.y);
                if (cellPressed.getPiece() instanceof King) {
                    // same as above logic, remove possible danger moves
                    possibleDestinations = filterKingInDangerMoves(possibleDestinations, cellPressed);
                } else {
                    if (chessBoardState[getKing(currentPlayer).getX()][getKing(currentPlayer).getY()].isCheck()) {
                        System.out.println("Is check");
                        possibleDestinations = new ArrayList<Cell>(filterKingInDangerMoves(possibleDestinations, cellPressed));
                    } else if (!possibleDestinations.isEmpty() && willKingBeInCheck(cellPressed, possibleDestinations.get(0))) {
                        possibleDestinations.clear();
                    }
                }
                highlightDestinations(possibleDestinations);
                System.out.println("Repopulated");
                printArrayList(possibleDestinations);
            }
        }
        if (cellPressed.getPiece() != null && cellPressed.getPiece() instanceof King) {
            ((King) cellPressed.getPiece()).setX(cellPressed.x);
            ((King) cellPressed.getPiece()).setY(cellPressed.y);
        }
    }

    // filter moves that put king in danger
    private ArrayList<Cell> filterKingInDangerMoves(ArrayList<Cell> possibleDestinations, Cell fromcell) {
        ArrayList<Cell> filteredDestinations = new ArrayList<Cell>();
        Cell tempChessBoardState[][] = new Cell[8][8];
        ListIterator<Cell> it = possibleDestinations.listIterator();
        int x,y;
        while (it.hasNext()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    try {
                        tempChessBoardState[i][j] = new Cell(chessBoardState[i][j]);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Cell tempCell = it.next();
            if (tempChessBoardState[tempCell.x][tempCell.y].getPiece() != null) {
                tempChessBoardState[tempCell.x][tempCell.y].removePiece();
            }
            tempChessBoardState[tempCell.x][tempCell.y].setPiece(tempChessBoardState[fromcell.x][fromcell.y].getPiece());
            x = getKing(currentPlayer).getX();
            y = getKing(currentPlayer).getY();
            if (tempChessBoardState[fromcell.x][fromcell.y].getPiece() instanceof King) {
                ((King) (tempChessBoardState[tempCell.x][tempCell.y].getPiece())).setX(tempCell.x);
                ((King) (tempChessBoardState[tempCell.x][tempCell.y].getPiece())).setY(tempCell.y);
                x = tempCell.x;
                y = tempCell.y;
            }
            tempChessBoardState[fromcell.x][fromcell.y].removePiece();
            if (!(((King) (tempChessBoardState[x][y].getPiece())).isKingInDanger(tempChessBoardState, currentPlayer))) {
                filteredDestinations.add(tempCell);
            }

        }

        System.out.println("Filtered: ");
        printArrayList(filteredDestinations);
        return filteredDestinations;
    }

    // check if king in check if move is made
    private boolean willKingBeInCheck(Cell from, Cell to) {
        Cell tempChessBoardState[][] = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    tempChessBoardState[i][j] = new Cell(chessBoardState[i][j]);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (tempChessBoardState[to.x][to.y].getPiece()!=null) {
            tempChessBoardState[to.x][to.y].removePiece();
        }
        tempChessBoardState[to.x][to.y].setPiece(tempChessBoardState[from.x][from.y].getPiece());

        if (tempChessBoardState[to.x][to.y].getPiece() instanceof King) {
            ((King) (tempChessBoardState[to.x][to.y].getPiece())).setX(to.x);
            ((King) (tempChessBoardState[to.x][to.y].getPiece())).setY(to.y);
        }
        tempChessBoardState[from.x][from.y].removePiece();
        if (((King) (tempChessBoardState[getKing(currentPlayer).getX()][getKing(currentPlayer).getY()].getPiece())).isKingInDanger(tempChessBoardState, currentPlayer)) {
            return true;
        } else {
            return false;
        }

        //return false;
    }

    private void changePlayerTurn() {
//        TODO: Check if a player is in checkmate

        System.out.println("Change turn");
        printArrayList(possibleDestinations);

        if (!possibleDestinations.isEmpty()) {
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

    private void printArrayList(ArrayList<Cell> cells) {
        for (Cell cell : cells) {
            System.out.println("X: " + cell.getX() + " Y: " + cell.getY());
        }
    }
}
