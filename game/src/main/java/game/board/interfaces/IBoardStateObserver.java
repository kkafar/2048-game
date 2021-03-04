package game.board.interfaces;

import game.board.raw.BoardTile;
import game.util.Position;


/**
 * 
 */
public interface IBoardStateObserver {
    public void onTilePlaced(BoardTile tile);

    public void onTileRemoved(BoardTile tile);

    public void onTileMoved(BoardTile tile, Position oldPosition);
}
