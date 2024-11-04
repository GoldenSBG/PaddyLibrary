package PaddyLibraryTest.brawlFight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameBoard extends JPanel implements ActionListener, KeyListener {
    private Player player1;
    private Player player2;
    private Timer timer;

    public GameBoard() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        player1 = new Player(100, 400);
        player2 = new Player(600, 400);

        timer = new Timer(16, this); // ca. 60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        player1.draw(g);
        player2.draw(g);

        // Beispiel-Plattformen zeichnen
        g.setColor(Color.GRAY);
        g.fillRect(300, 450, 200, 20);
    }

    public void update() {
        player1.update();
        player2.update();

        // Angriffs- und Treffererkennung
        if (player1.getAttackBox().intersects(player2.getHitbox())) {
            // Player1 trifft Player2
            System.out.println("Player1 hits Player2");
        }

        if (player2.getAttackBox().intersects(player1.getHitbox())) {
            // Player2 trifft Player1
            System.out.println("Player2 hits Player1");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            player1.moveLeft();
        }

        if (key == KeyEvent.VK_D) {
            player1.moveRight();
        }

        if (key == KeyEvent.VK_W) {
            player1.jump();
        }

        if (key == KeyEvent.VK_S) {
            player1.attack();
        }

        if (key == KeyEvent.VK_LEFT) {
            player2.moveLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
            player2.moveRight();
        }

        if (key == KeyEvent.VK_UP) {
            player2.jump();
        }

        if (key == KeyEvent.VK_DOWN) {
            player2.attack();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            player1.stopMoving();
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            player2.stopMoving();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }
}
