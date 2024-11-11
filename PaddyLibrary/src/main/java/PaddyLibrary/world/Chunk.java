package PaddyLibrary.world;

import java.util.Random;

public class Chunk {
    private int chunkX, chunkY;  // Chunk-Koordinaten
    private static final int SIZE = 50;  // Größe des Chunks in Pixel
    private Random random;  // Pseudo-Zufallsgenerator für prozedurale Inhalte

    public Chunk(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.random = new Random(chunkX * 31 + chunkY * 17);  // Seed für Konsistenz
        generateContent();
    }

    // Generiert prozedurale Inhalte für den Chunk
    private void generateContent() {
        // Beispiel: zufällige Platzierung von Objekten (wie Bäume) basierend auf Seed
        // Implementierung für prozedurale Inhalte je nach Anforderungen
    }

    // Zeichnet den Chunk relativ zur Spielerposition
    public void render(int playerX, int playerY) {
        int renderX = chunkX * SIZE - playerX;
        int renderY = chunkY * SIZE - playerY;

        // Renderlogik hier, z. B. Renderer.Rect(renderX, renderY, SIZE, SIZE);
    }

    // Getter für die Chunk-Größe
    public static int getSize() {
        return SIZE;
    }
}
