package game.board.raw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import game.board.interfaces.IBoardTileMoveObserver;
import game.board.interfaces.IBoardTileValueObserver;
import game.util.Position;

public class BoardTile {
    public BoardTile(int value, Position position) {
        if (!isValidValue(value))
            throw new IllegalArgumentException("BoardTile: setValue: Invalid value for tile. Value: " + value);

        this.value = value;
        this.position = position;

        this.moveObservers = new HashSet<>();
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

        moveObservers.forEach(obs -> { obs.onTileMoved(this, oldPosition); });
    }

    /**
     * Merging two tiles.
     * 
     * @param other 
     *      tile to-be-merged with
     */
    public void mergeWith(BoardTile other) {
        if (!canMerge(other)) 
            throw new IllegalArgumentException("BoardTile: mergeWith: attempt to merge tiles with different values: " 
            + Integer.toString(value) + " and " + Integer.toString(other.value));

        Position oldPosition = this.position;
        this.position = other.position;
    
        this.setValue(this.value + other.value);


        moveObservers.forEach(obs -> {obs.onTileMerged(this, other, oldPosition);});
    }

    public boolean canMerge(BoardTile other) {
        return this.value == other.value && this.value != BoardTile.VALID_VALUES.get(BoardTile.VALID_VALUES.size() - 1);
    }

    
    public static boolean isValidValue(int value) {
        return VALID_VALUES.contains(value);
    }


    public void addMoveObserver(IBoardTileMoveObserver obs) {
        moveObservers.add(obs);
    }

    public void removeMoveObserver(IBoardTileMoveObserver obs) {
        moveObservers.remove(obs);
    }

    private int value;
    private Position position;

    private final HashSet<IBoardTileMoveObserver> moveObservers;
    
    /**
     * List of valid values for tile to have 
     */
    public static final ArrayList<Integer> VALID_VALUES = new ArrayList<>(
        List.of(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048)
    );
}
