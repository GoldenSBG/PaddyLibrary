package PaddyLibrary.snakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameBoard extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final int UNIT_SIZE = 20;
    private final int ALL_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private final int DELAY = 140;

    private Image snakeHead;
    private Image snakeBody;


    private final int[] x = new int[ALL_UNITS];
    private final int[] y = new int[ALL_UNITS];

    private int bodyParts = 3;
    private int foodEaten;
    private int foodX;
    private int foodY;

    private char direction = 'R';
    private boolean running = false;
    private Timer timer;

    private JButton retryButton;
    private JPanel gamePanel;

    public GameBoard() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        // Bilder laden
        snakeHead = new ImageIcon("images/snakeHead.png").getImage();
        snakeBody = new ImageIcon("images/snakeBody.png").getImage();



        // Setup retry button
        retryButton = new JButton("Retry");
        retryButton.setFont(new Font("Arial", Font.BOLD, 30));
        retryButton.setFocusable(false);
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                retryButton.setVisible(false);
            }
        });

        // Setup panel to hold retry button
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(retryButton, BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.SOUTH);

        startGame();
    }

    public void startGame() {
        placeFood();
        bodyParts = 3;
        foodEaten = 0;
        direction = 'R';
        running = true;
        retryButton.setVisible(false);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            // Schlange zeichnen
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.drawImage(snakeHead, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this); // Kopf der Schlange
                } else {
                    g.drawImage(snakeBody, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this); // Körper der Schlange
                }
            }
            /*for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }*/
        } else {
            gameOver(g);
        }
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkFoodCollision() {
        if ((x[0] == foodX) && (y[0] == foodY)) {
            bodyParts++;
            foodEaten++;
            placeFood();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
            retryButton.setVisible(true);
        }
    }

    public void placeFood() {
        foodX = (int) (Math.random() * (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = (int) (Math.random() * (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void gameOver(Graphics g) {
        String msg = "Game Over";
        g.setColor(Color.RED);
        g.setFont(new Font("Helvetica", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(msg, (WIDTH - metrics.stringWidth(msg)) / 2, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFoodCollision();
            checkCollisions();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }
}
