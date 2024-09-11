package PaddyLibrary.tictactoe;

import java.io.*;
import java.net.*;
import java.util.*;

public class TicTacToeServer {
    private static final int PORT = 5000;
    private static final int BOARD_SIZE = 3;
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static char currentPlayer = 'X';
    private static PrintWriter player1Out, player2Out;
    private static Socket player1Socket, player2Socket;

    public static void main(String[] args) {
        // Initialisiere das Spielfeld
        for (char[] row : board) {
            Arrays.fill(row, '-');
        }

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Tic Tac Toe Server läuft auf Port " + PORT);

            // Warte auf zwei Clients
            player1Socket = serverSocket.accept();
            player2Socket = serverSocket.accept();

            player1Out = new PrintWriter(player1Socket.getOutputStream(), true);
            player2Out = new PrintWriter(player2Socket.getOutputStream(), true);

            BufferedReader player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            BufferedReader player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));

            new Thread(() -> handlePlayer(player1In, player1Out, 'X')).start();
            new Thread(() -> handlePlayer(player2In, player2Out, 'O')).start();

            // Sende das Initialboard an beide Spieler
            sendBoard(player1Out);
            sendBoard(player2Out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handlePlayer(BufferedReader in, PrintWriter out, char symbol) {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                int row = line.charAt(0) - '0';
                int col = line.charAt(1) - '0';

                synchronized (board) {
                    if (board[row][col] == '-' && symbol == currentPlayer) {
                        board[row][col] = symbol;
                        currentPlayer = (symbol == 'X') ? 'O' : 'X';
                        sendBoard(player1Out);
                        sendBoard(player2Out);
                        checkGameState();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendBoard(PrintWriter out) {
        synchronized (board) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                out.println(new String(board[i]));
            }
            out.flush();
        }
    }

    private static void checkGameState() {
        String pattern = "";
        for (int i = 0; i < BOARD_SIZE; i++) {
            pattern += new String(board[i]) + ",";
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                pattern += board[j][i];
            }
            pattern += ",";
        }
        pattern += new String(new char[] { board[0][0], board[1][1], board[2][2] }) + ",";
        pattern += new String(new char[] { board[0][2], board[1][1], board[2][0] });

        if (pattern.contains("XXX")) {
            sendMessageToPlayers("X won");
        } else if (pattern.contains("OOO")) {
            sendMessageToPlayers("O won");
        } else if (!pattern.contains("-")) {
            sendMessageToPlayers("Board full");
        }
    }

    private static void sendMessageToPlayers(String message) {
        player1Out.println(message);
        player2Out.println(message);
    }
}
