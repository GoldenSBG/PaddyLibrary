package PaddyLibraryTest.brawlhallaGame.gameserver;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public Socket getSocket() {
        return socket;
    }
    public void start() {
        try {
            Scanner scanner = new Scanner(System.in);
            String userInput;
            while ((userInput = scanner.nextLine()) != null) {
                out.println(userInput);
                System.out.println("Server: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            GameClient client = new GameClient("localhost", 12345);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
