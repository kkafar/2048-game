package game.board.raw;

import java.util.ArrayList;
import java.util.List;

import game.util.Position;

public class BoardTile {
    public BoardTile(int value, Position position) {
        if (!BoardTile.isValidValue(value))
            throw new IllegalArgumentException("BoardTile: Invalid value for tile. Value: " + value);

        this.value = value;
        this.position = position;
    }
    
    public Position getPosition() {
        return new Position(this.position);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (!isValidValue(value))
            throw new IllegalArgumentException("BoardTile: setValue: Invalid value for tile. Value: " + value);
        
        this.value = value;
    }

    public void moveTo(Position newPosition) {        
        if (newPosition.equals(this.position)) return;

        Position oldPosition = position;
        position = newPosition;
    }

    public void mergeWith(BoardTile other) {
        if (this.value != other.value) 
            throw new IllegalArgumentException("BoardTile: mergeWith: attempt to merge tiles with different values: " + this.value + " && " + other.value);

        other.setValue(other.value + this.value);
    }
    
    public static boolean isValidValue(int value) {
        return VALID_VALUES.contains(value);
    }

    private int value;
    private Position position;

    /**
     * List of valid values for tile to have 
     */
    public static final ArrayList<Integer> VALID_VALUES = new ArrayList<>(
        List.of(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048)
    );
}
