package sudoku;

import java.util.*;

import static java.util.Objects.isNull;

public class Sudoku {

    public static final int BOARD_WIDTH = 9;
    public static final int BOARD_HEIGHT = 9;
    public static final int BOARD_SIZE = BOARD_WIDTH * BOARD_HEIGHT;

    private final List<Cell> board;

    public Sudoku() {
        this.board = new ArrayList<>(BOARD_SIZE);
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board.add(i, new Cell(0));
        }
    }

    public Sudoku(List<Integer> board) {
        this();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Integer number = board.get(i);
            this.board.add(i, new Cell(number, number != 0));
        }
    }

    public boolean set(int row, int col, int value) {
        return get(row, col)
                .map(cell -> cell.setNumber(value))
                .orElse(false);
    }

    public Optional<Cell> get(int row, int col) {
        return Optional.ofNullable(get(indexForCoord(row, col)));
    }

    public void toggleMark(int row, int col, int... positions) {
        final Cell cell = get(indexForCoord(row, col));
        if (isNull(cell))
            return;
        cell.toggleMark(positions);
    }

    public void mark(int row, int col, int... positions) {
        final Cell cell = get(indexForCoord(row, col));
        if (isNull(cell))
            return;
        cell.mark(positions);
    }

    public void unmark(int row, int col, int... positions) {
        final Cell cell = get(indexForCoord(row, col));
        if (isNull(cell))
            return;
        cell.unmark(positions);
    }

    private Cell get(int index) {
        if (isOutBounds(index))
            return null;
        return board.get(index);
    }

    private boolean isOutBounds(int indexForCoord) {
        return indexForCoord < 0 || indexForCoord >= board.size();
    }

    private int indexForCoord(int row, int col) {
        return row * BOARD_WIDTH + col;
    }

    public int totalSize() {
        return getBoard().size();
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public static int getBoardSize() {
        return BOARD_SIZE;
    }

    public List<Cell> getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                Cell cell = get(indexForCoord(i, j));
                int number = isNull(cell) ? -1 : cell.getNumber();
                boardString.append(String.format("[%s]", number == 0 ? " " : number));
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}