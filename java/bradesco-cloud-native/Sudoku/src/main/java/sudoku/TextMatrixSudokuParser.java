package sudoku;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TextMatrixSudokuParser implements SudokuParser<InputStream> {

    @Override
    public Sudoku parse(InputStream toParse) {
        BufferedReader reader = bufferedReaderFrom(toParse);
        List<Integer> reduce = reader.lines()
                .map(line -> line.split(","))
                .map(values -> {
                    if (values.length > Sudoku.BOARD_WIDTH)
                        throw new IllegalArgumentException("O tabuleiro não deve ter mais de 9 colunas");
                    List<Integer> numbers = new ArrayList<>();
                    for (int i = 0; i < values.length; i++) {
                        if (!isInt(values[i]))
                            throw new IllegalArgumentException("O valor %s não é um número".formatted(values[i]));
                        numbers.add(Integer.parseInt(values[i]));
                    }
                    return numbers;
                })
                .reduce(new ArrayList<>(), (first, second) -> {
                    first.addAll(second);
                    return first;
                });
        if (reduce.size() > Sudoku.BOARD_SIZE)
            throw new IllegalArgumentException("O tabuleiro não deve ter mais de 9 linhas");
        return new Sudoku(reduce);
    }
}
