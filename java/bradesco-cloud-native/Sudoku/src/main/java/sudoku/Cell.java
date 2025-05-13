package sudoku;

import java.util.Objects;

public class Cell {

    public static final Cell EMPTY = new Cell(0, true);

    private int number;
    private final boolean[] marks;
    private final boolean locked;

    public Cell() {
        this(0);
    }

    public Cell(int number) {
        this(number, false);
    }

    public Cell(int number, boolean locked) {
        this.number = number;
        this.marks = new boolean[10];
        this.locked = locked;
    }

    public void toggleMark(int[] position) {
        for (int i = 0; i < position.length; i++) {
            if (isOutOfBounds(position[i]))
                continue;
            if (this.marks[position[i]]) {
                unmark(position[i]);
                return;
            }
            mark(position[i]);
        }
    }

    public void unmark(int[] position) {
        for (int i = 0; i < position.length; i++) {
            unmark(position[i]);
        }
    }

    public void mark(int[] position) {
        for (int i = 0; i < position.length; i++) {
            mark(position[i]);
        }
    }

    public void unmark(int position) {
        setMark(position, false);
    }

    public void mark(int position) {
        setMark(position, true);
    }

    private void setMark(int position, boolean value) {
        if (isOutOfBounds(position))
            return;
        this.marks[position] = value;
    }

    private boolean isOutOfBounds(int i) {
        return i < 1 || i >= getMarks().length;
    }

    public int getNumber() {
        return number;
    }

    public boolean[] getMarks() {
        return marks;
    }

    public boolean isMark(int i) {
        if (isOutOfBounds(i))
            return false;

        return getMarks()[i];
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean setNumber(int number) {
        if (isLocked()) return false;
        this.number = number;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%d] [", this.number));
        for (int i = 0; i < marks.length; i++) {
            if (marks[i])
                sb.append(i + ",");
        }
        if (sb.lastIndexOf(",") == sb.length() - 1)
            sb.setLength(sb.length() - 1);

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return number == cell.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
