package game.board;

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
    
    public BoardTile(int value) {
        this(value, null);
    }
    
    public Position getPosition() {
        return new Position(this.position);
    }

    public void setPosition(Position newPosition) {
        position = newPosition;
    }

    public void setValue(int value) {
        if (!isValidValue(value))
            throw new IllegalArgumentException("BoardTile: Invalid value for tile. Value: " + value);
        
        this.value = value;
    }
    
    private int value;
    private Position position;
    
    public static boolean isValidValue(int value) {
        return VALID_VALUES.contains(value);
    }
    
    /**
     * List of valid values for tile to have 
     */
    public static final ArrayList<Integer> VALID_VALUES = new ArrayList<>(
        List.of(2, 4, 8, 16, 32, 64, 128, 256, 1024, 2048)
    );
}
