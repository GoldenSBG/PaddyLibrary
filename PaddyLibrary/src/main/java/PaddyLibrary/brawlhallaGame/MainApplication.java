package PaddyLibrary.brawlhallaGame;

import paddylibrary.brawlhallaGame.gameserver.GameClient;
import paddylibrary.brawlhallaGame.gameserver.GameServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainApplication extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JLabel previewLabel; // Instanzvariable hinzufügen

    public MainApplication() {
        setTitle("Brawlhalla Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialisiere Panels
        JPanel mainMenuPanel = createMainMenuPanel();
        JPanel selectCharacterPanel = createSelectCharacterPanel();
        JPanel playPanel = createPlayPanel();

        // Füge Panels zum CardLayout hinzu
        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(selectCharacterPanel, "SelectCharacter");
        cardPanel.add(playPanel, "Play");

        // Füge das CardLayout-Panel zum JFrame hinzu
        add(cardPanel);

        // Zeige das Hauptmenü an
        cardLayout.show(cardPanel, "MainMenu");
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JButton playButton = new JButton("Play");
        JButton selectCharacterButton = new JButton("Select Character");
        JButton quitButton = new JButton("Quit");

        gbc.gridy = 0;
        panel.add(playButton, gbc);

        gbc.gridy = 1;
        panel.add(selectCharacterButton, gbc);

        gbc.gridy = 2;
        panel.add(quitButton, gbc);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Play");
            }
        });

        selectCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SelectCharacter");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return panel;
    }


    private JPanel createSelectCharacterPanel() {
        previewLabel = new JLabel("No Character Selected", SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());

        // Panel für die Charakterauswahl auf der linken Seite
        JPanel characterGridPanel = new JPanel();
        characterGridPanel.setLayout(new GridLayout(0, 3, 10, 10)); // Gitterlayout mit 3 Spalten

        // Erstelle eine Liste von Buttons für Charaktere
        String[] characterNames = {"Character 1", "Character 2", "Character 3", "Character 4", "Character 5", "Character 6"};
        for (String name : characterNames) {
            JButton characterButton = new JButton(name);
            characterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateCharacterPreview(name);
                }
            });
            characterGridPanel.add(characterButton);
        }

        // Panel für die Vorschau des ausgewählten Charakters auf der rechten Seite
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new BorderLayout());

        JLabel previewLabel = new JLabel("No Character Selected", SwingConstants.CENTER);
        previewPanel.add(previewLabel, BorderLayout.CENTER);

        // Füge das Gitterpanel und das Vorschau-Panel zum Haupt-Panel hinzu
        panel.add(characterGridPanel, BorderLayout.WEST);
        panel.add(previewPanel, BorderLayout.CENTER);

        // Button für zurück zum Hauptmenü
        JButton backButton = new JButton("Back");
        panel.add(backButton, BorderLayout.NORTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        return panel;
    }

    private void updateCharacterPreview(String characterName) {
        // Möglicherweise benötigst du eine Referenz auf das JLabel für die Vorschau.
        // Du musst den `previewLabel` als Instanzvariable speichern.
        previewLabel.setText("Selected: " + characterName);
        // Hier kannst du auch ein Bild des Charakters anzeigen, wenn du eines hast.
    }



    /*private JPanel createSelectCharacterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("Select your character");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(label, gbc);

        JButton character1Button = new JButton("Character 1");
        JButton character2Button = new JButton("Character 2");

        gbc.gridy = 1;
        contentPanel.add(character1Button, gbc);

        gbc.gridy = 2;
        contentPanel.add(character2Button, gbc);

        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.NORTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        character1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "Character 1 selected");
            }
        });

        character2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "Character 2 selected");
            }
        });

        return panel;
    }*/

    private JPanel createPlayPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        JButton backButton = createCustomButton("Back", "path/to/back_icon.png");
        JButton connectButton = createCustomButton("Connect", "path/to/connect_icon.png");
        JButton cancelButton = createCustomButton("Cancel", "path/to/cancel_icon.png");
        JButton startServerButton = createCustomButton("Start Server", "src/main/resources/brawlhallaGame/MainBackground.png");

       /* JButton backButton = new JButton("Back");
        JButton connectButton = new JButton("Connect");
        JButton cancelButton = new JButton("Cancel");
        JButton startServerButton = new JButton("Start Server");*/

        JPanel connectPanel = new JPanel();
        JTextField serverAddressField = new JTextField("localhost", 15);
        connectPanel.add(serverAddressField);
        connectPanel.add(connectButton);
        connectPanel.add(cancelButton);

        JTextArea statusArea = new JTextArea(10, 30);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        panel.add(backButton, BorderLayout.NORTH);
        panel.add(connectPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);
        panel.add(startServerButton, BorderLayout.EAST);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer(serverAddressField.getText(), statusArea);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelConnection(statusArea);
            }
        });

        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer(statusArea);
            }
        });

        return panel;
    }

    private JButton createCustomButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Benutzerdefinierte Hintergrundfarbe
        button.setBorder(BorderFactory.createEtchedBorder()); // Benutzerdefinierter Rand
        button.setFocusPainted(false); // Entfernt den Standard-Fokus-Effekt
        return button;
    }


    private void startServer(JTextArea statusArea) {
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

    private GameClient client;
    private Thread clientThread;

    private void connectToServer(String serverAddress, JTextArea statusArea) {
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

    private void cancelConnection(JTextArea statusArea) {
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
        SwingUtilities.invokeLater(() -> new MainApplication().setVisible(true));
    }
}
