package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextMatrixSudokuParserTest {

    public static final String SUDOKU_PUZZLE_PATH = "/sudoku/1.txt";
    public static final String TOO_MANY_COLUMNS_PATH = "/sudoku/invalid/tooManyColumns.txt";
    public static final String TOO_MANY_LINES_PATH = "/sudoku/invalid/tooManyLines.txt";
    public static final String NON_NUMERIC_CHARACTERS_PATH = "/sudoku/invalid/nonDigit.txt";

    private TextMatrixSudokuParser parser;

    @BeforeEach
    void setup() {
        parser = new TextMatrixSudokuParser();
    }

    @Test
    void testWhenParseShouldSucceed() {
        Sudoku parse = parser.parse(TextMatrixSudokuParserTest.class.getResourceAsStream(SUDOKU_PUZZLE_PATH));
        Integer[][] expected = {
                {0, 7, 2, 0, 0, 4, 9, 0, 0},
                {3, 0, 4, 0, 8, 9, 1, 0, 0},
                {8, 1, 9, 0, 0, 6, 2, 5, 4},
                {7, 0, 1, 0, 0, 0, 0, 9, 5},
                {9, 0, 0, 0, 0, 2, 0, 7, 0},
                {0, 0, 0, 8, 0, 7, 0, 1, 2},
                {4, 0, 5, 0, 0, 1, 6, 2, 0},
                {2, 3, 7, 0, 0, 0, 5, 0, 1},
                {0, 0, 0, 0, 2, 5, 7, 0, 0},
        };
        System.out.println(parse);
        for (int i = 0; i < Sudoku.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Sudoku.BOARD_WIDTH; j++) {
                Optional<Cell> cell = parse.get(i, j);
                assertTrue(cell.isPresent());
                assertEquals(cell.get().getNumber(), expected[i][j]);
            }
        }
    }

    @Test
    void testWhenParseShouldThrowNonDigit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(TextMatrixSudokuParserTest.class.getResourceAsStream(NON_NUMERIC_CHARACTERS_PATH));
        });
    }

    @Test
    void testWhenParseShouldThrowTooManyColumns() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(TextMatrixSudokuParserTest.class.getResourceAsStream(TOO_MANY_COLUMNS_PATH));
        });
    }

    @Test
    void testWhenParseShouldThrowTooManyLines() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(TextMatrixSudokuParserTest.class.getResourceAsStream(TOO_MANY_LINES_PATH));
        });
    }

}