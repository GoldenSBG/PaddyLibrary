package PaddyLibraryTest.snakeGame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SnakeMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        GameBoard board = new GameBoard();

        frame.add(board);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);

        board.startGame();
    }
}
/*
from PaddyLibrary.snakeGame import SnakeMain

SnakeMain.main([])

 */