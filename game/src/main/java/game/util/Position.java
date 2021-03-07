package game.util;

import java.util.Objects;

public class Position {
    public Position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public Position(Position position) {
        this.col = position.col;
        this.row = position.row;
    }

    public String toString() {
        return "[" + Integer.toString(row) + ", " + Integer.toString(col) + "]";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) 
            return true;
        
        if (!(other instanceof Position))
            return false;
        
        Position that = (Position) other;

        return (this.col == that.col && this.row == that.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    public final int col;
    public final int row;
}
