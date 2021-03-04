package game.board;


public interface IBoardObserver {
    public void onTilePlaced(BoardTile tile);

    public void onTileRemoved(BoardTile tile);
}
