package game.board;

import java.util.AbstractMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardTileRepresentation extends Rectangle {
    public BoardTileRepresentation(int x, int y, int width, int height, int value) {
        super(x, y, width, height);
        
        if (x < 0 || y < 0 || width <= 0 || height <= 0) 
            throw new IllegalArgumentException("BoardTile: Invalid arguments passed to constructor. Args: x=" 
            + x + " y=" + y + " width=" + width + " height=" + height);
        
        if (!BoardTile.isValidValue(value)) 
            throw new IllegalArgumentException("BoardTileRepresentation: Invalid value for tile. Value: " + value);
        
        this.valueLabel = new Label();
        this.valueLabel.setLabelFor(this);
        this.valueLabel.setAlignment(Pos.CENTER);
        
        this.setValue(value);
    }
    
    public BoardTileRepresentation(int x, int y, int width, int height) {
        this(x, y, width, height, BoardTile.VALID_VALUES.get(0));
    }

    private void adjustColor() {
        this.color = VALUE_COLOR_MAP.get(this.value);
        this.setFill(this.color);
    }

    private void adjustLabel() {
        this.valueLabel.setText(Integer.toString(this.value));
    }

    public void setValue(int value) {
        if (!BoardTile.isValidValue(value)) {
            throw new IllegalArgumentException("BoardTileRepresentation: " + value + " is an invalid value.");
        }
        this.value = value;
        this.adjustColor();
        this.adjustLabel();
    }

    public int getValue() {
        return value;
    }

    /**
     * Current color of tile. Modifiable only by changing tile's value. 
     */
    private Color color;

    /**
     * Current value of tile. (Number displayed on a tile)
     */
    private int value;

    private Label valueLabel; 

    /**
     * Maping tile.value -> tile.color.
     */
    private static final Map<Integer, Color> VALUE_COLOR_MAP = Map.ofEntries(
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(0), Color.rgb(238, 228, 218)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(1), Color.rgb(237, 224, 201)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(2), Color.rgb(242, 177, 123)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(3), Color.rgb(245, 149, 101)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(4), Color.rgb(246, 124, 96)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(5), Color.rgb(247, 93, 60)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(6), Color.rgb(237, 208, 119)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(7), Color.rgb(237, 205, 103)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(8), Color.rgb(0, 0, 255)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(9), Color.rgb(255, 0, 0)),
        new AbstractMap.SimpleImmutableEntry<>(BoardTile.VALID_VALUES.get(10), Color.rgb(0, 255, 0))
    );
}
