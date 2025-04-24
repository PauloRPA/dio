package sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuTest {

    private Sudoku sudoku;

    @BeforeEach
    public void setup() {
        sudoku = new Sudoku();
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
