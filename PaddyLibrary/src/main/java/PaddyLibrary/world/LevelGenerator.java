package PaddyLibrary.world;

import PaddyLibrary.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelGenerator {
    private List<Platform> platforms;
    private int lastPlatformX = 0; // Position der letzten generierten Plattform
    private Random random;

    public LevelGenerator() {
        platforms = new ArrayList<>();
        lastPlatformX = 0;
        random = new Random();
        generateInitialPlatforms();
    }

    private void generateInitialPlatforms() {
        /*// Startplattform
        platforms.add(new Platform(50, 400, 100, 20));
        lastPlatformX = 150;

        for (int i = 0; i < 5; i++) {
            generatePlatform();
        }
        */
        for (int i = 0; i < 5; i++) {
            platforms.add(new Platform(100 + i * 200, 400 - i * 20, 100, 10));
        }
        lastPlatformX = 100 + (5 * 200);
    }

    private void generatePlatform() {
        /*int x = lastPlatformX + random.nextInt(100) + 50; // Distanz zur letzten Plattform
        int y = 300 + random.nextInt(200) - 100; // Höhe zufällig wählen
        platforms.add(new Platform(x, y, 100, 20));
        lastPlatformX = x + 100;*/
        Random rand = new Random();
        int x = lastPlatformX + rand.nextInt(200) + 100;
        int y = 300 + rand.nextInt(200) - 100;
        platforms.add(new Platform(x, y, 100, 10));
        lastPlatformX = x;
    }

    public void updatePlatforms(int playerX) {
        /*// Neue Plattformen generieren, wenn der Spieler sich weiter nach rechts bewegt
        if (playerX + 400 > lastPlatformX) {
            generatePlatform();
        }

        // Entfernen von Plattformen, die weit links liegen
        platforms.removeIf(platform -> platform.getTop() < playerX - 400);*/

        // Entfernen von Plattformen, die zu weit links vom Spieler sind
        platforms.removeIf(platform -> platform.getX() < playerX - 400);

        // Neue Plattformen generieren
        if (platforms.size() < 10) {
            generatePlatform();
        }
    }

    public void renderPlatforms() {
        /*for (Platform platform : platforms) {
            platform.render();
        }*/
        Renderer.Color3f(0.5f, 0.25f, 0.0f);
        for (Platform platform : platforms) {
            Renderer.Rect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
        }
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }
}
