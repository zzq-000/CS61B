package byog.Core;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    private int width;
    private int height;

    private static final int MINSIZE = 4;
    private static final int MAXSIZE = 10;
    private Location loc;
    public Room(Location l, int width, int height) {
        this.loc = l;
        this.width = width;
        this.height = height;
    }

    public static Room generateRoom(Random random, int mapWidth, int mapHeight) {

        int width = RandomUtils.uniform(random, MINSIZE, MAXSIZE);
        int height = RandomUtils.uniform(random, MINSIZE, MAXSIZE);
        Location l = Location.generateLoc(random, mapWidth, mapHeight);
        while (l.getX() + width > mapWidth || l.getY() + height > mapHeight) {
            width = RandomUtils.uniform(random, MINSIZE, MAXSIZE);
            height = RandomUtils.uniform(random, MINSIZE, MAXSIZE);
            l = Location.generateLoc(random, mapWidth, mapHeight);
        }
        return new Room(l, width, height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public Location getLeftDown() {
        return this.loc;
    }
    public Location getLeftUp() {
        return new Location(this.loc.getX(), this.loc.getY() + this.height - 1);
    }
    public Location getRightDown() {
        return new Location(this.loc.getX() + this.width - 1, this.loc.getY());
    }
    public Location getRightUp() {
        return new Location(this.loc.getX() + this.width - 1, this.loc.getY() + this.height - 1);
    }

    public Location getUp() {
        return new Location(loc.getX() + width / 2, loc.getY() + height - 1);
    }
    public Location getDown() {
        return new Location(loc.getX() + width / 2, loc.getY());
    }
    public Location getLeft() {
        return new Location(loc.getX(), loc.getY() + height / 2);
    }
    public Location getRight() {
        return new Location(loc.getX() + width - 1, loc.getY());
    }

    public Location getCenter() {
        return new Location(loc.getX() + width / 2, loc.getY() + height / 2);
    }

    public ArrayList<Location> getAllExits() {
        ArrayList<Location> loc = new ArrayList<>();
        loc.add(getUp());
        loc.add(getLeft());
        loc.add(getRight());
        loc.add(getDown());
        return loc;
    }

    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> loc = new ArrayList<>();
        loc.add(getLeftDown());
        loc.add(getLeftUp());
        loc.add(getRightUp());
        loc.add(getRightDown());
        return loc;
    }

    private boolean isPointInRec(Location point, Location leftDown, Location rightUp) {
        return point.getX() <= rightUp.getX() + 1 && point.getX() >= leftDown.getX() - 1 &&
                point.getY() <= rightUp.getY() + 1 && point.getY() >= leftDown.getY() - 1;
    }


    private boolean isOverlapHelper(Room another) {
        ArrayList<Location> loc = another.getAllLocations();
        for(Location l:loc) {
            if (isPointInRec(l, getLeftDown(), getRightUp())) {
                return true;
            }
        }
        return false;
    }
    public boolean isOverlap(Room another) {
        return this.isOverlapHelper(another) || another.isOverlapHelper(this);
    }

    public int getMHD(Room another) {
        return getCenter().getMHD(another.getCenter());
    }

    @Override
    public String toString() {
        return loc.toString() + "|| width: " + width +"; height: " + height;
    }
}
