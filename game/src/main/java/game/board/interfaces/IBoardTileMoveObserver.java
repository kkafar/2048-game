package game.board.interfaces;

import game.board.raw.BoardTile;
import game.util.Position;

public interface IBoardTileMoveObserver {
    /**
     * @param tile - board tile that changed position
     * 
     * @param oldPosition - position of tile before move
     */
    public void onTileMoved(BoardTile tile, Position oldPosition);
    
    /**
     * @param tile - board tile merged with mergedWith
     * 
     * @param mergedWith
     * 
     * @param oldTilePosition - position of 'tile' (param) before merge operation
     */
    public void onTileMerged(BoardTile tile, BoardTile mergedWith, Position oldTilePosition);
}
