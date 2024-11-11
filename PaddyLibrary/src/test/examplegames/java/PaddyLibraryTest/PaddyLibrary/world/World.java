package PaddyLibraryTest.PaddyLibrary.world;

import PaddyLibraryTest.PaddyLibrary.entities.Enemies;
import PaddyLibraryTest.PaddyLibrary.entities.Player;

public class World {
    private LevelGenerator levelGenerator;

    public World(LevelGenerator levelGenerator) {
        this.levelGenerator = levelGenerator;
    }

    public void checkPlayerCollisions(Player player) {
        for (Platform platform : levelGenerator.getPlatforms()) {
            player.checkPlatformCollision(platform);
        }
    }
    public void checkEnemiesCollisions(Enemies enemies) {
        for (Platform platform : levelGenerator.getPlatforms()) {
            enemies.checkPlatformCollision(platform);
        }
    }
}
