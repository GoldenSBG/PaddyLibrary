package PaddyLibrary;

import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Labyrinth {
    private final int nbHorzCells = 31;  // must be odd
    private final int nbVertCells = 31;  // ditto
    private final int cellSize = 18;

    public Labyrinth() {
        GameGrid gg = new GameGrid(nbHorzCells, nbVertCells, cellSize, false);
        GGMaze maze = drawMaze(gg);
        Bug sbug = new Bug(gg);
        gg.addActor(sbug, maze.getStartLocation());
        gg.addKeyListener(sbug);
        gg.show();
    }

    private GGMaze drawMaze(GameGrid gg) {
        GGBackground bg = gg.getBg();
        GGMaze maze = new GGMaze(nbHorzCells, nbVertCells);
        for (int x = 0; x < nbHorzCells; x++)
            for (int y = 0; y < nbVertCells; y++)
            {
                Location location = new Location(x, y);
                if (maze.isWall(location))
                    bg.fillCell(location, Color.black);
                else
                    bg.fillCell(location, Color.white);
            }
        return maze;
    }

    public static void main(String[] args) {
        new Labyrinth();
    }
}

// ------------------class Bug ------------------------------------
class Bug extends Actor implements GGKeyListener {
    private final Location startLocation = new Location(0, 1);
    private final Location exitLocation;
    private boolean isGameOver = false;

    public Bug(GameGrid gg) {
        super(true, "sprites/smallbug.gif");  // Rotatable
        exitLocation = new Location(gg.getNbHorzCells() - 1, gg.getNbVertCells() - 2);
    }

    public boolean keyPressed(KeyEvent evt) {
        if (isGameOver)
            return true;

        Location next = null;

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (getLocation().equals(startLocation))
                    return true;
                next = getLocation().getNeighbourLocation(Location.WEST);
                setDirection(Location.WEST);
                break;
            case KeyEvent.VK_UP:
                next = getLocation().getNeighbourLocation(Location.NORTH);
                setDirection(Location.NORTH);
                break;
            case KeyEvent.VK_RIGHT:
                if (getLocation().equals(exitLocation))
                    return true;
                next = getLocation().getNeighbourLocation(Location.EAST);
                setDirection(Location.EAST);
                break;
            case KeyEvent.VK_DOWN:
                next = getLocation().getNeighbourLocation(Location.SOUTH);
                setDirection(Location.SOUTH);
                break;
        }

        if (next != null && canMove(next)) {
            setLocation(next);
            gameGrid.refresh();
        }
        if (next != null && next.equals(exitLocation)) {
            GGBackground bg = getBackground();
            gameGrid.addActor(new Actor("sprites/you_win.gif"), new Location(15, 15));
            isGameOver = true;
            isGameOver = true;
            gameGrid.refresh();
        }
        return true;
    }

    public boolean keyReleased(KeyEvent evt) {
        return false;
    }

    private boolean canMove(Location location) {
        Color c = getBackground().getColor(location);
        return (!c.equals(Color.black));
    }
}