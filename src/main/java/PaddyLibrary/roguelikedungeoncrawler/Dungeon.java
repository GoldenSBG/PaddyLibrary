package PaddyLibrary.roguelikedungeoncrawler;

public class Dungeon {
    private int width;
    private int height;
    private char[][] map;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        map = new char[width][height];
        generateDungeon();
    }

    private void generateDungeon() {
        // Einfaches Dungeon-Layout generieren (hier nur ein Platzhalter)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = '.';
            }
        }
        // Wände hinzufügen (Platzhalter)
        for (int i = 0; i < width; i++) {
            map[i][0] = '#';
            map[i][height - 1] = '#';
        }
        for (int j = 0; j < height; j++) {
            map[0][j] = '#';
            map[width - 1][j] = '#';
        }
    }

    public char[][] getMap() {
        return map;
    }
}
