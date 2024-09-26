package PaddyLibrary.ninjalla.entities;

public class Player {
    private String name;
    private int health;
    private int xPosition;
    private int yPosition;
    private int coins;

    // Konstruktor
    public Player() {
        this.name = "Ninja";
        this.health = 100;  // Maximal Gesundheit
        this.xPosition = 0;  // Startposition auf der X-Achse
        this.yPosition = 0;  // Startposition auf der Y-Achse
        this.coins = 0;      // Startmünzen
    }

    // Spawn den Spieler am Startpunkt
    public void spawn() {
        System.out.println(name + " wurde gespawnt bei Position (" + xPosition + ", " + yPosition + ").");
    }

    // Bewege den Spieler nach links, rechts, oben oder unten
    public void move() {
        // Beispielhafte einfache Bewegung
        System.out.println(name + " bewegt sich...");

        // Beispiel einer Bewegung (links/rechts/up/down)
        xPosition += 1;  // Bewege den Spieler nach rechts
        yPosition += 1;  // Bewege den Spieler nach oben (in einem realen Spiel wären das Sprünge)

        System.out.println(name + " Position: (" + xPosition + ", " + yPosition + ")");
    }

    // Spieler nimmt Schaden
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println(name + " hat " + damage + " Schaden genommen. Gesundheit: " + health);
    }

    // Spieler sammelt Münzen
    public void collectCoin() {
        this.coins += 1;
        System.out.println(name + " hat eine Münze gesammelt! Gesamtmünzen: " + coins);
    }

    // Getter für die Gesundheit
    public int getHealth() {
        return health;
    }

    // Getter für die aktuelle Anzahl der Münzen
    public int getCoins() {
        return coins;
    }
}
