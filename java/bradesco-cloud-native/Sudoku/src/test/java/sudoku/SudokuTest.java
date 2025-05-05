package sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuTest {

    public static final String SUDOKU_PUZZLE_PATH = "/sudoku";
    public static final String SUDOKU_INVALID_PUZZLE_PATH = "/sudoku/invalid";
    public static final String SUDOKU_1 = "/1.txt";
    private static final String SUDOKU_COL = "/col.txt";
    private static final String SUDOKU_ROW = "/row.txt";
    private static final String SUDOKU_BLOCK = "/block.txt";
    private static final String SUDOKU_COMPLETE = "/complete.txt";

    private Sudoku sudoku;
    private SudokuParser<InputStream> parser;

    @BeforeEach
    public void setup() {
        sudoku = new Sudoku();
        parser = new TextMatrixSudokuParser();
    }

    @Test
    void testMark() {
        BiConsumer<Integer, Integer> actionForEachCell = (row, col) -> {
            sudoku.mark(row, col, (row % 10));
        };
        BiConsumer<Integer, Integer> assertForEachCell = (row, col) -> {
            Optional<Cell> cell = sudoku.get(row, col);
            assertTrue(cell.isPresent());
            if (row % 10 < 1) {
                assertFalse(cell.get().isMark(row % 10));
                return;
            }
            assertTrue(cell.get().isMark(row % 10));
        };
        forEachCell(actionForEachCell, assertForEachCell);
    }

    @Test
    void testUnmark() {
        BiConsumer<Integer, Integer> actionForEachCell = (row, col) -> {
            sudoku.mark(row, col, (row % 10));
            sudoku.unmark(row, col, (row % 10));
        };
        BiConsumer<Integer, Integer> assertForEachCell = (row, col) -> {
            Optional<Cell> cell = sudoku.get(row, col);
            assertTrue(cell.isPresent());
            assertFalse(cell.get().isMark(row % 10));
        };
        forEachCell(actionForEachCell, assertForEachCell);
    }

    @Test
    void testToggleMark() {
        BiConsumer<Integer, Integer> toggleMarkForEachCell = (row, col) -> {
            sudoku.toggleMark(row, col, (row % 10));
        };
        BiConsumer<Integer, Integer> assertMarkForEachCell = (row, col) -> {
            Optional<Cell> cell = sudoku.get(row, col);
            assertTrue(cell.isPresent());
            if (row % 10 < 1) {
                assertFalse(cell.get().isMark(row % 10));
                return;
            }
            assertTrue(cell.get().isMark(row % 10));
        };
        BiConsumer<Integer, Integer> assertUnmarkForEachCell = (row, col) -> {
            Optional<Cell> cell = sudoku.get(row, col);
            assertTrue(cell.isPresent());
            assertFalse(cell.get().isMark(row % 10));
        };
        forEachCell(toggleMarkForEachCell, assertMarkForEachCell);
        forEachCell(toggleMarkForEachCell, assertUnmarkForEachCell);
    }

    @Test
    void testSet() {
        BiConsumer<Integer, Integer> setForEachCell = (row, col) -> {
            sudoku.set(row, col, (row % 10));
        };
        BiConsumer<Integer, Integer> assertGetForEachCell = (row, col) -> {
            Optional<Cell> cell = sudoku.get(row, col);
            assertTrue(cell.isPresent());
            assertEquals(cell.get().getNumber(), (row % 10));
        };
        forEachCell(setForEachCell, assertGetForEachCell);
    }

    @Test
    void testWhenCompleteShouldReturnTrue() {
        assertTrue(parse(SUDOKU_COMPLETE).isComplete());
    }

    @Test
    void testWhenCompleteShouldReturnFalse() {
        String[] puzzles = { SUDOKU_COL, SUDOKU_ROW, SUDOKU_BLOCK };
        for (int i = 0; i < puzzles.length; i++) {
            assertFalse(parse(puzzles[i]).isComplete());
            assertFalse(parseInvalid(puzzles[i]).isComplete());
        }
        assertFalse(parse(SUDOKU_1).isComplete());
    }

    @Test
    void testWhenCheckColumnShouldReturnFalse() {
        Sudoku col = parseInvalid(SUDOKU_COL);
        assertFalse(col.isColumnValid(5));
    }

    @Test
    void testWhenCheckLineShouldReturnFalse() {
        Sudoku row = parseInvalid(SUDOKU_ROW);
        assertFalse(row.isLineValid(1));
    }

    @Test
    void testWhenCheckBlockShouldReturnFalse() {
        Sudoku block = parseInvalid(SUDOKU_BLOCK);
        assertFalse(block.isBlockValid(6, 3));
        assertFalse(block.isBlockValid(7, 4));
        assertFalse(block.isBlockValid(8, 5));
    }

    @Test
    void testWhenCheckColumnShouldReturnTrue() {
        sudoku = parse(SUDOKU_1);
        Sudoku col = parse(SUDOKU_COL);
        for (int i = 0; i < Sudoku.BOARD_WIDTH; i++) {
            assertTrue(sudoku.isColumnValid(i));
            assertTrue(col.isColumnValid(i));
        }
    }

    @Test
    void testWhenCheckLineShouldReturnTrue() {
        sudoku = parse(SUDOKU_1);
        Sudoku row = parse(SUDOKU_ROW);
        for (int i = 0; i < Sudoku.BOARD_HEIGHT; i++) {
            assertTrue(sudoku.isLineValid(i));
            assertTrue(row.isLineValid(i));
        }
    }

    @Test
    void testWhenCheckBlockShouldReturnTrue() {
        sudoku = parse(SUDOKU_1);
        Sudoku block = parse(SUDOKU_BLOCK);
        for (int i = 0; i < Sudoku.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Sudoku.BOARD_WIDTH; j++) {
                assertTrue(sudoku.isBlockValid(i, j));
                assertTrue(block.isBlockValid(i, j));
            }
        }
    }

    private Sudoku parseInvalid(String file) {
        return parser.parse(SudokuTest.class.getResourceAsStream(SUDOKU_INVALID_PUZZLE_PATH + file));
    }

    private Sudoku parse(String file) {
        return parser.parse(SudokuTest.class.getResourceAsStream(SUDOKU_PUZZLE_PATH + file));
    }

    private void forEachCell(BiConsumer<Integer, Integer> action,
            BiConsumer<Integer, Integer> asserts) {
        for (int i = 0; i < Sudoku.BOARD_WIDTH; i++) {
            for (int j = 0; j < Sudoku.BOARD_HEIGHT; j++) {
                action.accept(i, j);
            }
        }
        for (int i = 0; i < Sudoku.BOARD_WIDTH; i++) {
            for (int j = 0; j < Sudoku.BOARD_HEIGHT; j++) {
                asserts.accept(i, j);
            }
        }
    }

}
