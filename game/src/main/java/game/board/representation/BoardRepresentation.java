package game.board.representation;

import game.board.interfaces.IBoardStateObserver;
import game.board.interfaces.IBoardTileValueObserver;
import game.board.raw.*;
import game.util.Position;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;

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
public class BoardRepresentation extends StackPane implements IBoardStateObserver {
    public BoardRepresentation(int pxlWindowWidth, int pxlWindowHeight, Board board) {

        boardMap = new LinkedHashMap<>();
        // for (int i = 0; i < Board.N_TILES_VER; ++i) Arrays.fill(boardRepresentation[i], null);

        this.pxlTileWidth = pxlWindowWidth / Board.N_TILES_HOR;
        // this.pxlTileWidth -= 2 * this.pxlTileWidth / 16;

        this.pxlTileHeight = pxlWindowHeight / Board.N_TILES_VER;
        // this.pxlTileHeight -= 2 * this.pxlTileHeight / 16;

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
                    countX(j),
                    countY(i),
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

        this.valueObservers = new HashSet<>();
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
    
    private int countX(int col) {
        return col * pxlTileWidth + countXoffset();
    }
    
    private int countY(int row) {
        return row * pxlTileHeight + countYoffset();
    }

    private int countTileWidth() {
        return pxlTileWidth - 2 * countXoffset();
    }

    private int countTileHeight() {
        return pxlTileHeight - 2 * countYoffset();
    }

    private void place(BoardTile tile) {
        BoardTileRepresentation newTile = new BoardTileRepresentation(
            countX(tile.getPosition().col),
            countY(tile.getPosition().row),
            pxlTileWidth - 2 * countXoffset(),
            pxlTileHeight - 2 * countYoffset(),
            tile.getValue()
        );
       
        this.addValueObserver(newTile);
        
        boardMap.put(tile, newTile);
        tilesLayer.getChildren().add(newTile);
    }

    private void remove(BoardTile tile) {
        tilesLayer.getChildren().remove(boardMap.get(tile));
        boardMap.remove(tile);
    }

    private void updatePosition(BoardTile tile) {
        BoardTileRepresentation repr = boardMap.get(tile);
        repr.setLayoutX(countX(tile.getPosition().col));
        repr.setLayoutY(countY(tile.getPosition().row));
    }

    public void addValueObserver(IBoardTileValueObserver obs) {
        valueObservers.add(obs);
    }

    public void removeValueObserver(IBoardTileValueObserver obs) {
        valueObservers.remove(obs);
    }

    @Override
    public void onTileMoved(BoardTile tile, Position oldPosition) {
        updatePosition(tile);
    }
    
    @Override
    public void onTilePlaced(BoardTile tile) {
        place(tile);
    }

    @Override
    public void onTileRemoved(BoardTile tile) {
        remove(tile);
    }

    @Override
    public void onTileMerged(BoardTile tile, BoardTile mergedWith, Position oldTilePosition) {
        System.out.print("BoardRepresentation: notified on tile merged...");

        updatePosition(tile);
        remove(mergedWith);
        
        boardMap.get(tile).onTileValueChange(tile.getValue());
    }


    
    private int pxlTileWidth;
    private int pxlTileHeight;

    private final Board board;

    private final Pane tilesLayer; 

    private final LinkedHashMap<BoardTile, BoardTileRepresentation> boardMap;

    private final HashSet<IBoardTileValueObserver> valueObservers;

    private final Background background; 
    private final BackgroundFill backgroundFill;

    private final Pane backgroundTiles;
}
