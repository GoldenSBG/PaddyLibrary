package PaddyLibraryTest.ninjalla.menus;

import PaddyLibraryTest.ninjalla.entities.Enemy;
import PaddyLibraryTest.ninjalla.world.Platform;
import PaddyLibraryTest.ninjalla.world.World;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {

    private int playerX = 50;
    private int playerY = 50;
    private int playerSpeed = 5;
    private int playerWidth = 30;
    private int playerHeight = 30;
    private int gravity = 1;
    private int height = 600; // Set height of the panel
    private int playerVelocityY = 0; // Speed in Y direction
    private boolean onGround = false; // Checks if player is on the ground
    private ArrayList<Platform> platforms;
    private ArrayList<Enemy> enemies; // List of enemies
    private World world; // The world with platforms
    BufferedImage idle;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);

        // Initialize the world and platforms
        world = new World(800, height); // Set width and height of the world

        platforms = new ArrayList<>();
        initializePlatforms(); // Generiere Plattformen beim Start

        // Positioniere den Spieler auf der ersten Plattform
        if (!platforms.isEmpty()) {
            playerY = platforms.get(0).getY() - playerHeight; // Spieler auf der Plattform positionieren
            onGround = true; // Spieler ist jetzt auf dem Boden
        }

        // Initialize the list of enemies and add some enemies
        enemies = new ArrayList<>();
        enemies.add(new Enemy(200, 100));
        enemies.add(new Enemy(400, 150));
        enemies.add(new Enemy(600, 200));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw platforms
        for (Platform platform : platforms) {
            platform.draw(g); // Ensure draw method is defined in Platform class
        }

        // Draw the player
        //g.setColor(Color.RED);
        //g.fillRect(playerX, playerY, playerWidth, playerHeight);
        loadImage();
        g.drawImage(idle, playerX, playerY, null);

        // Draw enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    private void loadImage() {
        try {
            idle = ImageIO.read(getClass().getResourceAsStream("/ninjalla/player/idle1.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/ninjalla/player/idle2.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/ninjalla/player/idle3.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/ninjalla/player/idle4.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/ninjalla/player/idle5.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        // Apply gravity
        playerVelocityY += gravity;
        playerY += playerVelocityY;

        // Check for collisions
        checkCollisions();

        // Plattformen generieren, wenn der Spieler sich in einem bestimmten Bereich bewegt
        if (playerY < height / 2) { // Wenn der Spieler sich oben im Panel befindet
            generatePlatforms();
        }

        // Update and repaint the panel
        repaint();
    }

    private void checkCollisions() {
        onGround = false; // Reset onGround
        for (Platform platform : platforms) {
            // Check collision with platform
            if (playerX + playerWidth > platform.getX() && playerX < platform.getX() + platform.getWidth() &&
                    playerY + playerHeight >= platform.getY() && playerY + playerHeight <= platform.getY() + playerVelocityY) {
                // Collision detected, set player on top of the platform
                playerY = platform.getY() - playerHeight; // Position player directly above the platform
                playerVelocityY = 0; // Reset vertical velocity
                onGround = true; // Player is now on the ground
                break; // Stop checking for further collisions
            }
        }

        // Check if the player falls below the ground level
        if (playerY > getHeight()) {
            playerY = getHeight(); // Position player at the bottom
            onGround = true; // Player is now on the ground
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Movement up, down, left, and right
        if (key == KeyEvent.VK_UP && onGround) {
            playerVelocityY = -15; // Jump strength
            onGround = false; // Player is no longer on the ground
        }
        if (key == KeyEvent.VK_LEFT) {
            playerX -= playerSpeed; // Move player left
        }
        if (key == KeyEvent.VK_RIGHT) {
            playerX += playerSpeed; // Move player right
        }

        // Optionally move player down (not common in platformers)
        if (key == KeyEvent.VK_DOWN) {
            playerY += playerSpeed; // Move player down
        }

        // Redraw panel after movement
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No action required on keyReleased
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action required on keyTyped
    }

    //wenn das gewählt, kein spielfenster
    /*private void generatePlatforms() {
        // Generate platforms until we reach the maximum number
        while (platforms.size() < 50) { // Maximum 50 platforms
            int x = (int) (Math.random() * (getWidth() - 50)); // Zufällige X-Position
            int y = (int) (Math.random() * (getHeight() - 200) + 100); // Zufällige Y-Position

            // Überprüfen, ob die neue Plattform nicht mit bestehenden Plattformen überlappt
            boolean overlap = false;
            for (Platform platform : platforms) {
                if (x < platform.getX() + platform.getWidth() && x + 50 > platform.getX() &&
                        y < platform.getY() + platform.getHeight() && y + 10 > platform.getY()) {
                    overlap = true; // Überlappt mit einer bestehenden Plattform
                    break;
                }
            }

            if (!overlap) {
                platforms.add(new Platform(x, y, 50, 10)); // Plattform mit Breite 50 und Höhe 10
            }
        }
    }*/

    private void generatePlatforms() {
        // Add logic to generate platforms, e.g., random platforms
        if (platforms.size() < 50) { // Maximum 50 platforms
            int x = (int) (Math.random() * (getWidth() - 50)); // Random X position
            int y = (int) (Math.random() * (getHeight() - 200) + 100); // Random Y position
            platforms.add(new Platform(x, y, 50, 10)); // Platform with width 50 and height 10
        }
    }

    private void initializePlatforms() {
        // Generiere initiale Plattformen beim Start
        for (int i = 0; i < 5; i++) { // Erstelle 5 initiale Plattformen
            generatePlatforms();
        }
    }
}
