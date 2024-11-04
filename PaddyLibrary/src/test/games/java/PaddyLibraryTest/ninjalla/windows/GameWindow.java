package PaddyLibraryTest.ninjalla.windows;

import PaddyLibraryTest.ninjalla.menus.GamePanel;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    private GamePanel gamePanel;

    public GameWindow() {
        // Setze Titel des Spielfensters
        setTitle("Ninjalla Game");

        // Setze Größe des Fensters
        setSize(800, 600);

        // Beende das Programm, wenn das Fenster geschlossen wird
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fenster in der Mitte des Bildschirms anzeigen
        setLocationRelativeTo(null);

        // Initialisiere das Spielpanel und füge es dem Fenster hinzu
        gamePanel = new GamePanel();
        add(gamePanel);

        // Sichtbar machen
        setVisible(true);
    }

    // Startpunkt für das Spielfenster
    public static void main(String[] args) {
        new GameWindow();
    }
}
