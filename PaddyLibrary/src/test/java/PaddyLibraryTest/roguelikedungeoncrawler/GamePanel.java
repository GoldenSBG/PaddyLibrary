package PaddyLibraryTest.roguelikedungeoncrawler;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private Dungeon dungeon;
    private Player player;

    public GamePanel() {
        dungeon = new Dungeon(40, 30);
        player = new Player(5, 5);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dungeon zeichnen
        char[][] map = dungeon.getMap();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (map[x][y] == '#') {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x * 20, y * 20, 20, 20);
            }
        }

        // Spieler zeichnen
        g.setColor(Color.GREEN);
        g.fillRect(player.getX() * 20, player.getY() * 20, 20, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                player.move(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                player.move(1, 0);
                break;
            case KeyEvent.VK_UP:
                player.move(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                player.move(0, 1);
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
