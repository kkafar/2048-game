package game.board;

import game.util.Position;

public interface IBoardTileObserver {
    public void onTileMoved(BoardTile tile, Position oldPosition);
    // public void onTileMerge5
}
