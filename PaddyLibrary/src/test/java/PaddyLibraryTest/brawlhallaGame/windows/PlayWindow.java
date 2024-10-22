package PaddyLibraryTest.brawlhallaGame.windows;

import PaddyLibraryTest.brawlhallaGame.gameserver.GameClient;
import PaddyLibraryTest.brawlhallaGame.gameserver.GameServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PlayWindow extends JFrame {
    private JTextField serverAddressField;
    private JButton connectButton;
    private JButton startServerButton;
    private JButton backButton;
    private JButton cancelButton;
    private JTextArea statusArea;

    private GameClient client;
    private Thread clientThread;

    public PlayWindow() {
        setTitle("Play");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel für die Buttons und Textfelder
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        backButton = new JButton("Back");
        topPanel.add(backButton);

        startServerButton = new JButton("Start Server");
        topPanel.add(startServerButton);

        // Verbindung Panel
        JPanel connectPanel = new JPanel();
        connectPanel.setLayout(new FlowLayout());

        serverAddressField = new JTextField("localhost", 15);
        connectPanel.add(serverAddressField);

        connectButton = new JButton("Connect");
        connectPanel.add(connectButton);

        cancelButton = new JButton("Cancel");
        connectPanel.add(cancelButton);

        // Statusbereich
        statusArea = new JTextArea(10, 30);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        add(topPanel, BorderLayout.NORTH);
        add(connectPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // ActionListener für Server starten
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        // ActionListener für Verbinden
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer(serverAddressField.getText());
            }
        });

        // ActionListener für Abbrechen
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelConnection();
            }
        });

        // ActionListener für Zurück
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Schließt das PlayWindow und kehrt zum Hauptmenü zurück
            }
        });
    }

    private void startServer() {
        new Thread(() -> {
            try {
                GameServer server = new GameServer(12345);
                server.start();
                statusArea.append("Server gestartet.\n");
            } catch (IOException e) {
                e.printStackTrace();
                statusArea.append("Fehler beim Starten des Servers.\n");
            }
        }).start();
    }

    private void connectToServer(String serverAddress) {
        if (client != null) {
            statusArea.append("Bereits mit einem Server verbunden.\n");
            return;
        }

        statusArea.append("Versuche Verbindung zu " + serverAddress + "\n");

        clientThread = new Thread(() -> {
            try {
                client = new GameClient(serverAddress, 12345);
                client.start();
            } catch (IOException e) {
                e.printStackTrace();
                statusArea.append("Fehler beim Verbinden zum Server.\n");
            }
        });
        clientThread.start();
    }

    private void cancelConnection() {
        if (client != null) {
            try {
                client.getSocket().close(); // Schließt die Verbindung
                statusArea.append("Verbindung abgebrochen.\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                client = null;
                clientThread = null;
            }
        } else {
            statusArea.append("Keine Verbindung zum Abbrechen.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayWindow().setVisible(true));
    }
}
