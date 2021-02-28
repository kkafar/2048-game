package game.board;

import java.util.Arrays;
import game.util.Position;

public class Board {
    public Board() {
        board = new BoardTile[N_TILES_VER][N_TILES_HOR];

        for (int i = 0; i < N_TILES_VER; ++i) Arrays.fill(board[i], null);
    }

    public BoardTile at(Position position) {
        return board[position.row][position.col];
    }

    public BoardTile at(int row, int col) {
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

        board[position.row][position.col] = tile;
        tile.setPosition(position);
    } 
    
    public void place(BoardTile tile) {
        if (!isEmpty(tile.getPosition()))
            throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + tile.getPosition().toString());
        
        board[tile.getPosition().row][tile.getPosition().col] = tile;
    }
    
    public void place(BoardTile tile, int row, int col) {
        if (!isEmpty(row, col))
            throw new IllegalArgumentException("Board: Placing tile on not empty position. Position: " + new Position(row, col).toString());
        
        board[row][col] = tile;
        tile.setPosition(new Position(row, col));
    }
    
    public void remove(BoardTile tile, int row, int col) {
        if (isEmpty(row, col))
            throw new IllegalArgumentException("Board: remove: Removing from empty position. Position: " + new Position(row, col).toString());
        
        board[row][col] = null;
    }

    private final BoardTile[][] board;    

    public static final int N_TILES_VER = 4;
    public static final int N_TILES_HOR = 4;
}
