package game.board;

import game.util.Position;

public interface IBoardObserver {
    public void onTilePlaced(BoardTile tile);

    public void onTileMoved(BoardTile tile, Position oldPosition);

    public void onTileRemoved(BoardTile tile);
}
