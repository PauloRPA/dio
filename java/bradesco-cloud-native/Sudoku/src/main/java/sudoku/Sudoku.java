package sudoku;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.*;

public class Sudoku {

    private static final int BLANK = 0;
    public static final int BOARD_WIDTH = 9;
    public static final int BOARD_HEIGHT = 9;
    public static final int BOARD_SIZE = BOARD_WIDTH * BOARD_HEIGHT;

    private final List<Cell> board;
    private boolean lock = false;

    public Sudoku() {
        this.board = new ArrayList<>(BOARD_SIZE);
        this.lock = false;
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board.add(i, new Cell(BLANK));
        }
    }

    public Sudoku(List<Integer> board) {
        this();
        for (int i = 0; i < board.size(); i++) {
            Integer number = board.get(i);
            this.board.set(i, new Cell(number, number != BLANK));
        }
    }

    public boolean set(int row, int col, int value) {
        if (lock)
            return false;
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

    public boolean isColumnValid(int col) {
        if (col < 0 || col >= BOARD_WIDTH) {
            return false;
        }
        Set<Integer> values = new HashSet<>();
        int i = -1;
        while (++i < BOARD_WIDTH) {
            Cell cell = get(indexForCoord(i, col));
            if (isNull(cell))
                return false;
            if (cell.getNumber() == 0)
                continue;
            if (!values.add(cell.getNumber()))
                return false;
        }
        return true;
    }

    public boolean isLineValid(int row) {
        if (row < 0 || row >= BOARD_HEIGHT) {
            return false;
        }
        Set<Integer> values = new HashSet<>();
        int i = -1;
        while (++i < BOARD_HEIGHT) {
            Cell cell = get(indexForCoord(row, i));
            if (isNull(cell))
                return false;
            if (cell.getNumber() == 0)
                continue;
            if (!values.add(cell.getNumber()))
                return false;
        }
        return true;
    }

    public boolean isBlockValid(int row, int col) {
        if (isOutBounds(indexForCoord(row, col)))
            return false;
        final int blockRow = (row / 3) * 3;
        final int blockCol = (col / 3) * 3;
        Set<Integer> values = new HashSet<>();
        for (int i = blockRow; i < blockRow + 3; i++) {
            for (int j = blockCol; j < blockCol + 3; j++) {
                Cell cell = get(indexForCoord(i, j));
                if (isNull(cell))
                    return false;
                if (cell.getNumber() == 0)
                    continue;
                if (!values.add(cell.getNumber()))
                    return false;
            }
        }
        return true;
    }

    // Brute force solution
    public Sudoku solve() {
        List<Integer> initialCells = this.board.stream()
                .map(cell -> cell.isLocked() ? cell.getNumber() : 0)
                .toList();

        Sudoku solution = new Sudoku(initialCells);
        int i = findNextNonLockedCell(solution, -1);
        while (!solution.isComplete() && i >= 0) {
            Cell currentCell = solution.board.get(i);
            int numberToTry = currentCell.getNumber() + 1;
            currentCell.setNumber(numberToTry);
            while (isCellNotValid(solution, i) && numberToTry < 9)
                currentCell.setNumber(++numberToTry);

            if (isCellNotValid(solution, i) || numberToTry > 9) {
                currentCell.setNumber(0);
                i = findPreviousNonLockedCell(solution, i);
                continue;
            }
            i = findNextNonLockedCell(solution, i);
        }

        return solution;
    }

    private static boolean isCellNotValid(Sudoku solution, int i) {
        final int row = i / BOARD_WIDTH;
        final int col = i % BOARD_WIDTH;
        final boolean rowValid = solution.isLineValid(row);
        final boolean columnValid = solution.isColumnValid(col);
        final boolean blockValid = solution.isBlockValid(row, col);
        return !rowValid || !columnValid || !blockValid;
    }

    private int findPreviousNonLockedCell(Sudoku solution, int i) {
        i--;
        while (solution.get(i).isLocked())
            i--;
        return i;
    }

    private int findNextNonLockedCell(Sudoku solution, int i) {
        i++;
        while (solution.get(i).isLocked())
            i++;
        return i;
    }

    private Cell get(int index) {
        if (isOutBounds(index))
            return Cell.EMPTY;
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

    public void complete() {
        if (isComplete())
            this.lock = true;
    }

    public boolean isComplete() {
        return isValid() && this.board.stream()
                .allMatch(cell -> nonNull(cell) && cell.getNumber() != BLANK);
    }

    public boolean isValid() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            if (!(isLineValid(i) && isColumnValid(i) && isBlockValid(i, i)))
                return false;
        }
        return true;
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
                boardString.append(String.format("[%s]", number == BLANK ? " " : number));
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sudoku sudoku = (Sudoku) o;
        return Objects.equals(board, sudoku.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }
}
