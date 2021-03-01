package game.board;

import java.util.Arrays;
import java.util.HashSet;

import game.util.Position;

public class Board implements IBoardTileObserver {
    public Board() {
        board = new BoardTile[N_TILES_VER][N_TILES_HOR];

        for (int i = 0; i < N_TILES_VER; ++i) Arrays.fill(board[i], null);

        observers = new HashSet<>();
    }

    public BoardTile at(Position position) {
        // if (!isValidPosition(position))
            // throw new IllegalArgumentException("Board: Invalid position. Position: " + position.toString());

        return board[position.row][position.col];
    }

    public BoardTile at(int row, int col) {
        // if (!isValidPosition(row, col))
            // throw new IllegalArgumentException("Board: Invalid position. Position: " + new Position(row, col).toString());

        return board[row][col];
    }

    public boolean isEmpty(Position position) {
        return isEmpty(position.row, position.col);
    }

    public boolean isEmpty(int row, int col) {
        return (board[row][col] == null);
    }

    public void place(BoardTile tile, Position position) {
        if (!isEmpty(position))
            throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + position.toString());

        tile.setPosition(position);
        tile.addObserver(this);
        board[position.row][position.col] = tile;

        observers.forEach(obs -> {obs.onTilePlaced(tile);});
    } 
    
    public void place(BoardTile tile) {
        if (!isEmpty(tile.getPosition()))
            throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + tile.getPosition().toString());
        
        tile.addObserver(this);
        board[tile.getPosition().row][tile.getPosition().col] = tile;

        observers.forEach(obs -> {obs.onTilePlaced(tile);});
    }
    
    public void place(BoardTile tile, int row, int col) {
        if (!isEmpty(row, col))
            throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + new Position(row, col).toString());
        
        tile.setPosition(new Position(row, col));
        tile.addObserver(this);
        board[row][col] = tile;

        observers.forEach(obs -> {obs.onTilePlaced(tile);});
    }
    
    public void remove(BoardTile tile, int row, int col) {
        if (isEmpty(row, col))
            throw new IllegalArgumentException("Board: remove: Removing from empty position. Position: " + new Position(row, col).toString());
        
        board[row][col] = null;

        observers.forEach(obs -> {obs.onTileRemoved(tile);});
    }

    public static boolean isValidPosition(Position position) {
        return isValidPosition(position.row, position.col);
    }

    public static boolean isValidPosition(int row, int col) {
        return (row >= 0 && row < N_TILES_VER && col >= 0 && col < N_TILES_HOR);
    }

    public void addObserver(IBoardObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IBoardObserver observer) {
        observers.remove(observer);
    }


    @Override
    public void onTileMoved(BoardTile tile, Position oldPosition) {
        if (isEmpty(oldPosition))
            throw new IllegalArgumentException("Board: onTileMoved: Moving tile from empty position. Position: " + oldPosition.toString());
        
        // if (!isEmpty(position))
        // what if moving onto not emppy position? 

        board[oldPosition.row][oldPosition.col] = null;
        board[tile.getPosition().row][tile.getPosition().col] = tile;    
            
        observers.forEach(obs -> {obs.onTileMoved(tile, oldPosition);});
    }

    private final BoardTile[][] board;    

    private final HashSet<IBoardObserver> observers;

    public static final int N_TILES_VER = 4;
    public static final int N_TILES_HOR = 4;
}
