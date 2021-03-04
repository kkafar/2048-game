package game.board;

import java.util.Arrays;

import game.util.Position;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @brief Representation of the gameboard. 
 */
public class BoardRepresentation extends StackPane implements IBoardObserver, IBoardTileObserver {
    public BoardRepresentation(int pxlWindowWidth, int pxlWindowHeight, Board board) {

        boardRepresentation = new BoardTileRepresentation[Board.N_TILES_VER][Board.N_TILES_VER];
        for (int i = 0; i < Board.N_TILES_VER; ++i) Arrays.fill(boardRepresentation[i], null);

        this.pxlTileWidth = pxlWindowWidth / Board.N_TILES_HOR;
        this.pxlTileHeight = pxlWindowHeight / Board.N_TILES_VER;

        this.board = board;
        this.board.addObserver(this);

        this.setPrefSize(pxlWindowWidth, pxlWindowHeight);

        this.backgroundFill = new BackgroundFill(Color.rgb(187, 173, 160), CornerRadii.EMPTY, Insets.EMPTY);
        this.background = new Background(this.backgroundFill);

        this.setBackground(this.background);

        this.backgroundTiles = new Pane();

        Rectangle rectangle;

        for (int i = 0; i < Board.N_TILES_VER; ++i) {
            for (int j = 0; j < Board.N_TILES_HOR; ++j) {
                rectangle = new Rectangle(
                    j * this.pxlTileWidth + this.countXoffset(),
                    i * this.pxlTileHeight + this.countYoffset(),
                    this.pxlTileWidth - 2 * this.countXoffset(), 
                    this.pxlTileHeight - 2 * this.countYoffset()
                );

                rectangle.setFill(Color.rgb(205, 193, 180));
                rectangle.setArcWidth(this.countArcWidth());
                rectangle.setArcHeight(this.countArcHeight());
                this.backgroundTiles.getChildren().add(rectangle);
            }
        }

        this.tilesLayer = new Pane();

        this.getChildren().addAll(this.backgroundTiles, this.tilesLayer);
    }

    private int countXoffset() {
        return this.pxlTileWidth / 16;
    }

    private int countYoffset() {
        return this.pxlTileHeight / 16;
    }

    private int countArcWidth() {
        return this.pxlTileWidth / 8;
    }

    private int countArcHeight() {
        return this.pxlTileHeight / 8;
    }

    private void place(BoardTile tile) {
        BoardTileRepresentation newTile = new BoardTileRepresentation(
            tile.getPosition().col * pxlTileWidth + countXoffset(),
            tile.getPosition().row * pxlTileHeight + countYoffset(),
            pxlTileWidth - 2 * countXoffset(),
            pxlTileHeight - 2 * countYoffset(),
            tile
        );

        newTile.addObserver(this);

        boardRepresentation[tile.getPosition().row][tile.getPosition().col] = newTile;
        tilesLayer.getChildren().add(newTile);
    }

    @Override
    public void onTilePlaced(BoardTile tile) {
        place(tile);
    }

    @Override
    public void onTileMoved(BoardTile tile, Position oldPosition) {
        boardRepresentation[tile.getPosition().row][tile.getPosition().col] = boardRepresentation[oldPosition.row][oldPosition.col];
        boardRepresentation[oldPosition.row][oldPosition.col] = null;
        // TODO: animations
    }

    @Override
    public void onTileRemoved(BoardTile tile) {
        tilesLayer.getChildren().remove(boardRepresentation[tile.getPosition().row][tile.getPosition().col]);
        boardRepresentation[tile.getPosition().row][tile.getPosition().col] = null;
        // TODO: animations
    }

    
    private int pxlTileWidth;
    private int pxlTileHeight;

    private final Board board;

    private final Pane tilesLayer; 

    private final BoardTileRepresentation[][] boardRepresentation;

    private final Background background; 
    private final BackgroundFill backgroundFill;

    private final Pane backgroundTiles;
}
