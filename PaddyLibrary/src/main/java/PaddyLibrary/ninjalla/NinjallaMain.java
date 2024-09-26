package PaddyLibrary.ninjalla;

import PaddyLibrary.ninjalla.menus.GamePanel;
import PaddyLibrary.ninjalla.menus.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NinjallaMain extends JFrame {

    private GamePanel gamePanel;
    private MenuPanel menuPanel;

    public NinjallaMain() {
        // Setze Titel des Fensters
        setTitle("Ninjalla - Hauptfenster");

        // Setze Größe des Fensters
        setSize(800, 600);

        // Beende das Programm, wenn das Fenster geschlossen wird
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fenster in der Mitte des Bildschirms anzeigen
        setLocationRelativeTo(null);

        // Hauptmenü initialisieren und hinzufügen
        menuPanel = new MenuPanel(this);  // Übergabe der Instanz für Steuerung
        add(menuPanel);
        gamePanel = new GamePanel();


        // Sichtbar machen
        setVisible(true);

        // Timer für die regelmäßige Aktualisierung
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.update(); // Aktualisiere den Spielzustand
            }
        });
        timer.start(); // Starte den Timer
    }

    // Wechsel zum Spielbildschirm
    public void startGame() {
        // Entferne das Menü und zeige das Spielpanel
        remove(menuPanel);
        gamePanel = new GamePanel();  // Das Spielpanel initialisieren
        add(gamePanel);
        revalidate();  // Layout aktualisieren
        repaint();     // Neuzeichnen des Fensters
        gamePanel.requestFocus();  // Fokus auf das Spielpanel setzen, damit die Steuerung funktioniert
    }

    public static void main(String[] args) {
        // Erstelle das Hauptfenster
        new NinjallaMain();
    }
}
/* toDO
- Map generierung machen -> soll weitergenerieren
- Spieler und Enemy Texture ersetzen (keine Würfel)
- Platformen texure machen
- allg Visuals hinzufügen
 */