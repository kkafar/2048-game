package game.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import game.util.Position;

public class BoardTest {
    @Test
    public void initializationTest() {
        Board board = new Board();

        for (int i = 0; i < Board.N_TILES_VER; ++i)
            for (int j = 0; j < Board.N_TILES_HOR; ++j)
                assertEquals("Error in initialization (Board constructor)", null, board.at(i, j)); 
    } 

    @Test
    public void atTest() {
        BoardTile boardTile = new BoardTile(BoardTile.VALID_VALUES.get(0), new Position(0, 0));

        Board board = new Board();

        board.place(boardTile);

        assertEquals("Bad tile at 0,0", boardTile, board.at(0, 0));
        assertEquals("Bad tile at 0,0", boardTile, board.at(new Position(0, 0)));

        for (int i = 1; i < Board.N_TILES_HOR; ++i) {
            if (i < Board.N_TILES_VER) {
                board.place(boardTile, i, i);
                assertEquals("at returned bad tile! 1", boardTile, board.at(i, i));
                assertEquals("at returned bad tile! 2", boardTile, board.at(new Position(i, i)));
            }
        }

        for (int i = 0; i < Board.N_TILES_HOR; ++i) {
            for (int j = 0; j < Board.N_TILES_VER; ++j) {
                if (i != j) { 
                    assertNull("Non empty field 1", board.at(j, i));
                    assertNull("Non empty field 2", board.at(new Position(j, i)));
                }
            }
        }
    }
}
