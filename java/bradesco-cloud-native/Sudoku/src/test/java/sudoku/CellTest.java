package sudoku;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {

    private Cell cell;

    @BeforeEach
    public void setup() {
        cell = new Cell();
    }

    @Test
    void testMark() {
        for (int i = -10; i < 20; i++) {
            cell.mark(i);
            if (i < 1 || i > 9) {
                assertFalse(cell.isMark(i));
            } else {
                assertTrue(cell.isMark(i));
            }
        }
    }

    @Test
    void testToggleMark() {
        for (int i = -10; i < 20; i++) {
            cell.toggleMark(new int[] { i });
            if (i < 1 || i > 9) {
                assertFalse(cell.isMark(i));
            } else {
                assertTrue(cell.isMark(i));
                cell.toggleMark(new int[] { i });
                assertFalse(cell.isMark(i));
            }
        }
    }

    @Test
    void testUnmark() {
        for (int i = -10; i < 20; i++)
            cell.mark(i);

        for (int i = -10; i < 20; i++) {
            cell.unmark(i);
            assertFalse(cell.isMark(i));
        }
    }

}
