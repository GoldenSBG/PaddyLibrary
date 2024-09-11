package PaddyLibrary.brawlFight;

import javax.swing.*;

public class BrawlFightMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Brawlhalla Game");
        GameBoard board = new GameBoard();

        frame.add(board);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

