package PaddyLibraryTest.PaddyLibrary.world;

import PaddyLibraryTest.PaddyLibrary.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelGenerator {
    private List<Platform> platforms;
    private Random random;

    private final int PLATFORM_COUNT = 20; // Maximale Anzahl an Plattformen
    private final int REMOVE_DISTANCE = 500; // Entfernung, ab der Plattformen entfernt werden
    private final int PLATFORM_SPACING = 150; // Abstand zwischen Plattformen

    public LevelGenerator() {
        platforms = new ArrayList<>();
        random = new Random();
        generateInitialPlatforms();
    }

    private void generateInitialPlatforms() {
        platforms.add(new Platform(200, 400, 100, 10));

        /*for (int i = 0; i < 10; i++) {
            int width = 100;
            int height = 10;
            int x = random.nextInt(850 - width);
            int y = random.nextInt(300); // Höhengrenze für Plattformen
            platforms.add(new Platform(x, y, width, height));
        }*/
        for (int i = 1; i < PLATFORM_COUNT; i++) {
            generateNewPlatform(random.nextInt(850), random.nextInt(300) + 100);
        }
    }

    /*private void generatePlatform() {
        Random rand = new Random();
        int x = lastPlatformX + rand.nextInt(200) + 100;
        int y = 300 + rand.nextInt(200) - 100;
        platforms.add(new Platform(x, y, 100, 10));
        lastPlatformX = x;
    }*/

    public void updatePlatforms(int playerX, int playerY) {
        /*for (int i = platforms.size() - 1; i >= 0; i--) {
            Platform platform = platforms.get(i);
            // Hier entfernen, wenn der Spieler sich weit weg bewegt
            if (platform.getY() > playerX + 600 || platform.getX() < playerX - 100 || platform.getX() > playerX + 850) {
                platforms.remove(platform);
                // Generiere neue Plattformen in der Nähe des Spielers
                generateNewPlatform(platform.getX(), platform.getY());
            }
        }*/
        for (int i = platforms.size() - 1; i >= 0; i--) {
            Platform platform = platforms.get(i);
            // Entferne Plattformen, die sich weit weg vom Spieler befinden
            if (platform.getY() > playerY + 600 || Math.abs(platform.getX() - playerX) > REMOVE_DISTANCE) {
                platforms.remove(platform);
            }
        }

        // Überprüfen, ob neue Plattformen generiert werden sollen
        regeneratePlatforms(playerX, playerY);

        /*// Entfernen von Plattformen, die zu weit links vom Spieler sind
        platforms.removeIf(platform -> platform.getX() < playerX - 400);

        // Neue Plattformen generieren
        if (platforms.size() < 10) {
            generatePlatform();
        }*/
    }

    private void regeneratePlatforms(int playerX, int playerY) {
        int currentPlatformCount = platforms.size();
        if (currentPlatformCount < PLATFORM_COUNT) {
            // Neue Plattformen in der Nähe des Spielers generieren
            for (int i = 0; i < PLATFORM_COUNT - currentPlatformCount; i++) {
                generateNewPlatform(playerX + random.nextInt(PLATFORM_SPACING) - PLATFORM_SPACING / 2,
                        playerY - random.nextInt(100)); // Plattformen oberhalb
            }
        }
    }

    private void generateNewPlatform(int oldX, int oldY) {
        /*int width = 100;
        int height = 10;
        // Plattformen zufällig in der Nähe generieren
        int newX = oldX + random.nextInt(200) - 100; // Random X in der Nähe
        int newY = oldY + random.nextInt(200) - 100; // Random Y in der Nähe
        platforms.add(new Platform(newX, newY, width, height));*/

        // Stelle sicher, dass die Plattform innerhalb des Fensters bleibt
        oldX = Math.max(0, Math.min(850 - 100, oldX)); // 100 ist die Breite der Plattform
        platforms.add(new Platform(oldX, oldY, 100, 10));
    }

    public void renderPlatforms() {
        for (Platform platform : platforms) {
            platform.render();
        }
    }

    public void renderCameraPlatforms(int cameraX, int cameraY) {
        Renderer.Color3f(0.5f, 0.25f, 0.0f);
        for (Platform platform : platforms) {
            Renderer.Rect(platform.getX() - cameraX, platform.getY() - cameraY, platform.getWidth(), platform.getHeight());
        }
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }
}
