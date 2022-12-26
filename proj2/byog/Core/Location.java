package byog.Core;

import java.util.Random;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static Location generateLoc(Random random, int width, int height) {
        int x = RandomUtils.uniform(random, width);
        int y = RandomUtils.uniform(random, height);
        return new Location(x, y);
    }

    public int getMHD(Location l) {
        return Math.abs(x - l.getX()) + Math.abs(y - l.getY());
    }

    @Override
    public String toString() {
        return "x:" + x + "; y: " + y;
    }
}
