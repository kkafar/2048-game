package game.board.raw;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import game.board.interfaces.IBoardStateObserver;
import game.board.interfaces.IBoardTileMoveObserver;
import game.util.Position;

public class Board implements IBoardTileMoveObserver {
    public Board() {
        board = new BoardTile[N_TILES_VER][N_TILES_HOR];

        emptyPositions = new LinkedHashSet<>(N_TILES_HOR * N_TILES_VER);

        for (int i = 0; i < N_TILES_VER; ++i) {
            Arrays.fill(board[i], null);
            for (int j = 0; j < N_TILES_HOR; ++j) {
                emptyPositions.add(new Position(j, i));
            }
        }

        observers = new HashSet<>();
    }

    /**
     * @brief access to tile at given position
     * 
     * @return
     *      {@link BoardTile} at given position or null if there is no tile at give position
     */
    public BoardTile at(Position position) {
        return board[position.row][position.col];
    }

    /**
     * @brief access to tile at given position
     *
     * @return
     *      {@link BoardTile} at given position or null if there is no tile at given position
     */
    public BoardTile at(int row, int col) {
        return board[row][col];
    }

    public boolean isEmpty(Position position) {
        return isEmpty(position.row, position.col);
    }

    public boolean isEmpty(int row, int col) {
        return (board[row][col] == null);
    }

    // public void place(BoardTile tile, Position position) {
    //     if (!isEmpty(position))
    //         throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + position.toString());

    //     board[position.row][position.col] = tile;
    //     emptyPositions.remove(position);
    // } 
    
    public void place(BoardTile tile) {
        if (!isEmpty(tile.getPosition()))
            throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + tile.getPosition().toString());
        
        board[tile.getPosition().row][tile.getPosition().col] = tile;


        emptyPositions.remove(tile.getPosition());

        tile.addMoveObserver(this);

        observers.forEach(obs -> { obs.onTilePlaced(tile); });
    }
    
    // public void place(BoardTile tile, int row, int col) {
    //     if (!isEmpty(row, col))
    //         throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + new Position(row, col).toString());
        
    //     board[row][col] = tile;
    //     emptyPositions.remove(tile.getPosition());

    // }
    
    // public void remove(BoardTile tile, int row, int col) {
    //     if (isEmpty(row, col))
    //         throw new IllegalArgumentException("Board: remove: Removing from empty position. Position: " + new Position(row, col).toString());
        
    //     board[row][col] = null;
    //     emptyPositions.add(new Position(row, col));
    //     observers.forEach(obs -> {obs.onTileRemoved(tile);});
    // }

    public void remove(BoardTile tile) {
        if (isEmpty(tile.getPosition()))
            throw new IllegalArgumentException("Board: remove: Removing from empty position. Position: " + tile.getPosition().toString());

        board[tile.getPosition().row][tile.getPosition().col] = null;
        emptyPositions.add(tile.getPosition());

        observers.forEach(obs -> { obs.onTileRemoved(tile); });
    }

    public static boolean isValidPosition(Position position) {
        return isValidPosition(position.row, position.col);
    }

    public static boolean isValidPosition(int row, int col) {
        return (row >= 0 && row < N_TILES_VER && col >= 0 && col < N_TILES_HOR);
    }


    public void addObserver(IBoardStateObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(IBoardStateObserver obs) {
        observers.remove(obs);
    }

    @Override
    public void onTileMoved(BoardTile tile, Position oldPosition) {
        if (isEmpty(oldPosition)) 
            throw new IllegalArgumentException("Board: onTileMoved: no tile to move from " + oldPosition.toString());

        if (!isEmpty(tile.getPosition()))
            throw new IllegalArgumentException("Board: onTileMOved: cant move on occupied position " + tile.getPosition().toString());

        System.out.println("Board: notified on tile move, from: " + oldPosition.toString() + " to " + tile.getPosition().toString());

        board[oldPosition.row][oldPosition.col] = null;
        board[tile.getPosition().row][tile.getPosition().col] = tile;

        observers.forEach(obs -> { obs.onTileMoved(tile, oldPosition); });
    }

    @Override
    public void onTileMerged(BoardTile tile, BoardTile mergedWith, Position oldTilePosition) {
        if (isEmpty(mergedWith.getPosition()))
            throw new IllegalArgumentException("Board: can't merge. There is no tile at: " + mergedWith.getPosition().toString());
        
        if (isEmpty(oldTilePosition)) 
            throw new IllegalArgumentException("Board: can't merge. There was no tile at: " + oldTilePosition.toString());

        if (!tile.getPosition().equals(mergedWith.getPosition())) 
            throw new IllegalArgumentException("Board: merged tiles are on different positions: " + tile.getPosition().toString() + " " + mergedWith.getPosition().toString());
        
        System.out.println("Board: notified on tile merge."); 
        
        board[oldTilePosition.row][oldTilePosition.col] = null;
        board[tile.getPosition().row][tile.getPosition().col] = tile;
        
        observers.forEach(obs -> {obs.onTileMerged(tile, mergedWith, oldTilePosition);});
    }

    private final BoardTile[][] board;    

    private final LinkedHashSet<Position> emptyPositions;

    private final HashSet<IBoardStateObserver> observers;

    public static final int N_TILES_VER = 4;
    public static final int N_TILES_HOR = 4;
}
