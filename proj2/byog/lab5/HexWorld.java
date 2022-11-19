package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static final long SEED = 2873123;

    private static final Random RANDOM = new Random(SEED);

    private boolean checkPoint(TETile[][] map, int row, int col) {
        return row >= 0 && row < map.length && col >= 0
                && col < map[0].length && map[row][col] == Tileset.NOTHING;
    }
    public boolean checkValid(TETile[][] map, int row, int col, int size) {
        int ySum = 2 * (row + size) - 1;
        for (int i = row; i < row + size; i++) {
            for (int j = col + (row - i); j < col + size + i - row; j++) {
                if (!checkPoint(map, j, i) || !checkPoint(map, j, ySum - i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setHexagon(TETile[][] map, TETile target, int row, int col, int size) {

        int ySum = 2 * (row + size) - 1;
        for (int i = row; i < row + size; i++) {
            for (int j = col + (row - i); j < col + row - i + size + 2 * (i - row); j++) {
                //屏幕上显示的行和列和通常意义上的行和列是反的， 需要一个转换的过程
                map[j][i] = target;
                map[j][ySum - i] = target;
            }
        }
    }
    public void addHexagon(TETile[][] map, TETile target, int i, int j, int size) {
        if (checkValid(map, i, j, size)) {
            target = randomTile();
            setHexagon(map, target, i, j, size);
        }
    }
    private int up(int row, int size) {
        return row + size * 2;
    }
    private int halfUp(int row, int size) {
        return row + size;
    }
    private int down(int row, int size) {
        return row - size * 2;
    }
    private int halfDown(int row, int size) {
        return row - size;
    }
    private int left(int col, int size) {
        return col - (2 * size - 1);
    }

    private int right(int col, int size) {
        return col + (2 * size - 1);
    }

    private TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.PLAYER;
            case 3: return Tileset.SAND;
            case 4: return Tileset.MOUNTAIN;
            case 5: return Tileset.TREE;
            case 6: return Tileset.WATER;

            default: return Tileset.NOTHING;
        }
    }

    public void allHexagon(TETile[][] map, TETile target, int row, int col, int size) {
        int tRow = row;
        int tCol = col;
        for (int i = 0; i < 5; i++) {
            addHexagon(map, target, tRow, tCol, size);
            tRow = up(tRow, size);
        }
        tRow = halfUp(row, size);
        tCol = left(col, size);
        int rightCol = right(col, size);
        for (int i = 0; i < 4; i++) {
            addHexagon(map, target, tRow, tCol, size);
            addHexagon(map, target, tRow, rightCol, size);
            tRow = up(tRow, size);
        }
        tRow = halfUp(halfUp(row, size), size);
        tCol = left(left(col, size), size);
        rightCol = right(right(col, size), size);
        for (int i = 0; i < 3; i++) {
            addHexagon(map, target, tRow, tCol, size);
            addHexagon(map, target, tRow, rightCol, size);
            tRow = up(tRow, size);
        }

    }
    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] map = new TETile[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                map[i][j] = Tileset.NOTHING;
            }
        }
        HexWorld test = new HexWorld();
        test.allHexagon(map, Tileset.FLOWER, 0, 15, 3);
        test.allHexagon(map, Tileset.FLOWER, 0, 42, 3);
        ter.renderFrame(map);

    }

}
