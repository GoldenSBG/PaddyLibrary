package PaddyLibrary.ninjalla;

import PaddyLibrary.ninjalla.entities.Player;
import PaddyLibrary.ninjalla.utils.Level;

public class Game {
    private boolean isRunning;
    private boolean isPaused;
    private Player player;
    private Level level;

    // Konstruktor
    public Game() {
        // Initialisiere das Spiel
        this.isRunning = false;
        this.isPaused = false;
        this.player = new Player();
        this.level = new Level();
    }

    // Methode zum Starten des Spiels
    public void start() {
        System.out.println("Spiel wird gestartet...");
        this.isRunning = true;
        this.isPaused = false;

        // Initialisiere Level und Spieler
        level.generate();
        player.spawn();

        // Spielschleife (einfach simuliert)
        gameLoop();
    }

    // Methode zum Pausieren des Spiels
    public void pause() {
        if (isRunning && !isPaused) {
            System.out.println("Spiel wird pausiert...");
            isPaused = true;
        } else if (isPaused) {
            System.out.println("Spiel wird fortgesetzt...");
            isPaused = false;
        }
    }

    // Methode zum Beenden des Spiels
    public void stop() {
        System.out.println("Spiel wird beendet...");
        isRunning = false;
        System.exit(0);  // Spiel beenden
    }

    // Simulierte Spielschleife (Game Loop)
    private void gameLoop() {
        while (isRunning) {
            if (!isPaused) {
                // Hier kommt die Spiellogik hin
                update();
                render();
                checkGameOver();
            }

            // Simulation einer Pause in der Schleife (z.B. Zeit zwischen Frames)
            try {
                Thread.sleep(100);  // 100 Millisekunden Pause (10 FPS)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Update-Methode: Hier wird die Spiellogik aktualisiert (Spieler, Gegner usw.)
    private void update() {
        player.move();  // Bewege den Spieler
        level.update();  // Aktualisiere das Level (Gegner, Hindernisse usw.)

        // Beispiel: Wenn der Spieler eine Plattform verlässt, könnte er Münzen sammeln
        /*if (Überprüfe, ob der Spieler Münzen sammelt ) {
            player.collectCoin();
        }*/
    }

    // Render-Methode: Hier wird das Spiel visuell dargestellt (Textausgabe in der Konsole)
    private void render() {
        System.out.println("Spiel läuft... (Rendering-Logik hier einfügen)");
        // Dies könnte später die Spielfelder, den Spielerstatus usw. anzeigen
    }

    // Überprüfe, ob das Spiel vorbei ist
    private void checkGameOver() {
        if (player.getHealth() <= 0) {
            System.out.println("Spiel vorbei! Der Spieler ist gestorben.");
            stop();  // Beende das Spiel
        }
    }
}
