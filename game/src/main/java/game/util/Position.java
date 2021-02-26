package game.util;

import java.util.Objects;

public class Position {
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
   
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) 
            return true;
        
        if (!(other instanceof Position))
            return false;
        
        Position that = (Position) other;

        return (this.x == that.x && this.y == that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private final int x;
    private final int y;
}
