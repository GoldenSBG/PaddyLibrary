package PaddyLibrary.roguelikedungeoncrawler;

import javax.swing.JFrame;

public class Main extends JFrame {

    private GamePanel gamePanel;

    public Main() {
        // Fenster-Eigenschaften
        setTitle("Dungeon Crawler");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Spielfeld initialisieren und zum Fenster hinzufügen
        gamePanel = new GamePanel();
        add(gamePanel);
    }

    public static void main(String[] args) {
        // Spiel starten
        java.awt.EventQueue.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
