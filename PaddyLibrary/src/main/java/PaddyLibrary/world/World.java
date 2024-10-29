package PaddyLibrary.world;

import PaddyLibrary.entities.Player;
import java.util.List;

public class World {
    private LevelGenerator levelGenerator;

    public World(LevelGenerator levelGenerator) {
        this.levelGenerator = levelGenerator;
    }

    public void checkCollisions(Player player) {
        for (Platform platform : levelGenerator.getPlatforms()) {
            if (platform.isColliding(player.getX(), player.getY(), 50, 50)) {
                player.setY(platform.getY() - 50);  // Spieler auf der Plattform positionieren
                break;
            }
        }
    }
}
