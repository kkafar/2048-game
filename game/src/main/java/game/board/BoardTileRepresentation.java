package game.board;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardTileRepresentation extends Rectangle {
    public BoardTileRepresentation(int x, int y, int width, int height) {
        super(x, y, width, height);

        if (x < 0 || y < 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("BoardTile: Invalid arguments passed to constructor. Args: x=" 
            + x + " y=" + y + " width=" + width + " height=" + height);
        }

        this.valueLabel = new Label();
        this.valueLabel.setLabelFor(this);
        this.valueLabel.setAlignment(Pos.CENTER);

        this.setValue(VALID_VALUES.get(0));
    }

    private void adjustColor() {
        this.color = VALUE_COLOR_MAP.get(this.value);
        this.setFill(this.color);
    }

    private void adjustLabel() {
        this.valueLabel.setText(Integer.toString(this.value));
    }

    public void setValue(int value) {
        if (!VALID_VALUES.contains(value)) {
            throw new IllegalArgumentException("BoardTile: " + value + " is an invalid value.");
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
     * List of valid values for tile to have 
     */
    private static final ArrayList<Integer> VALID_VALUES = new ArrayList<>(
        List.of(2, 4, 8, 16, 32, 64, 128, 256, 1024, 2048)
    );

    /**
     * Maping tile.value -> tile.color.
     */
    private static final Map<Integer, Color> VALUE_COLOR_MAP = Map.ofEntries(
        new AbstractMap.SimpleImmutableEntry<>(2, Color.rgb(238, 228, 218)),
        new AbstractMap.SimpleImmutableEntry<>(4, Color.rgb(237, 224, 201)),
        new AbstractMap.SimpleImmutableEntry<>(8, Color.rgb(242, 177, 123)),
        new AbstractMap.SimpleImmutableEntry<>(16, Color.rgb(245, 149, 101)),
        new AbstractMap.SimpleImmutableEntry<>(32, Color.rgb(246, 124, 96)),
        new AbstractMap.SimpleImmutableEntry<>(64, Color.rgb(247, 93, 60)),
        new AbstractMap.SimpleImmutableEntry<>(128, Color.rgb(237, 208, 119)),
        new AbstractMap.SimpleImmutableEntry<>(256, Color.rgb(237, 205, 103)),
        new AbstractMap.SimpleImmutableEntry<>(512, Color.rgb(0, 0, 255)),
        new AbstractMap.SimpleImmutableEntry<>(1024, Color.rgb(255, 0, 0)),
        new AbstractMap.SimpleImmutableEntry<>(2048, Color.rgb(0, 255, 0))
    );
}
