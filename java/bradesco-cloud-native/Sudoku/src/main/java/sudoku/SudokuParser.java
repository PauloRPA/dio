package sudoku;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public interface SudokuParser<T> {

    Sudoku parse(T toParse);

    default BufferedReader bufferedReaderFrom(InputStream is) {
        return new BufferedReader(new InputStreamReader(is));
    }

    default boolean isInt(String str) {
        return str.chars()
                .mapToObj(ch -> (char) ch)
                .allMatch(Character::isDigit);
    }

}
