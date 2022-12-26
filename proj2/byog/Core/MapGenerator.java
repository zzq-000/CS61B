package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class MapGenerator {



    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static long SEED;
    private static Random random;
    private TETile[][] map;
    private ArrayList<Room> rooms;

    public MapGenerator(long seed) {
        SEED = seed;
        random = new Random();
        map = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                map[i][j] = Tileset.NOTHING;
            }
        }
        rooms = new ArrayList<>();
        generateMap();
    }

    public TETile[][] getMap() {
        return map;
    }

    public void generateMap() {
        for (int i = 0; i < 10; i++) {
            addRoom();
        }
        ;
        drawAllRooms();
    }
    private boolean isConflict(Room r) {
        for (Room room: this.rooms) {
            if (room.isOverlap(r)) {
                return true;
            }
        }
        return false;
    }

    public void addRoom() {
        Room r = Room.generateRoom(random, WIDTH, HEIGHT);
        while (isConflict(r)) {
            r = Room.generateRoom(random, WIDTH, HEIGHT);
        }
        this.rooms.add(r);
    }
    public boolean isWall(Location l) {
        return map[l.getX()][l.getY()] == Tileset.WALL;
    }
    public Location chooseOneExit(Room r1) {
        ArrayList<Location> exits = r1.getAllExits();
        for (Location loc: exits) {
            if (!isWall(loc)) {
                return loc;
            }
        }
        return null;
    }
    public boolean connectRooms(Room r1, Room r2) {
        Location l1 = chooseOneExit(r1);
        Location l2 = chooseOneExit(r2);
        if (l1 == null || l2 == null) {
            return false;
        } else {
            ;;;
        }
        return false;
    }
    public void drawRoom(Room r) {
        int x = r.getLoc().getX();
        int y = r.getLoc().getY();
        for (int i = x; i < x + r.getWidth(); i++) {
            for (int j = y; j < y + r.getHeight(); j++) {
                if (i == x || i == x + r.getWidth() - 1 || j == y || j == y + r.getHeight() - 1) {
                    map[i][j] = Tileset.WALL;
                } else {
                    map[i][j] = Tileset.FLOOR;
                }
            }
        }
    }
    public void drawAllRooms() {
        for (Room r: rooms) {
            System.out.println(r);
            drawRoom(r);
        }
    }
}
