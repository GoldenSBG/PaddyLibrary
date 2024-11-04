package PaddyLibraryTest.tictactoe;

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TicTacToeClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 5000;
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static boolean isMyMove = true;
    private static boolean isOver = false;
    private static JFrame frame;
    private static JPanel gridPanel;
    private static JButton[][] buttons = new JButton[3][3];
    private static JLabel statusLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeClient::createAndShowGUI);
        connectToServer();
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Tic Tac Toe Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));
        frame.add(gridPanel, BorderLayout.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                gridPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Connecting...", SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(TicTacToeClient::handleServerMessages).start();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Connection failed");
        }
    }

    private static void handleServerMessages() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("X won") || line.equals("O won") || line.equals("Board full")) {
                    statusLabel.setText(line);
                    isOver = true;
                } else {
                    updateBoard(line);
                    statusLabel.setText("Make a move!");
                    isMyMove = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Server disconnected");
            isOver = true;
        }
    }

    private static void updateBoard(String boardState) {
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char cell = boardState.charAt(index++);
                board[i][j] = cell;
                buttons[i][j].setText(String.valueOf(cell == '-' ? ' ' : cell));
            }
        }
    }

    private static class ButtonClickListener implements ActionListener {
        private final int x, y;

        ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isMyMove && !isOver && board[x][y] == '-') {
                out.println(x + "" + y);
                buttons[x][y].setText("X");
                isMyMove = false;
                statusLabel.setText("Wait!");
            }
        }
    }
}
