package PaddyLibrary.brawlhallaGame.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        // Fenster-Eigenschaften
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Buttons erstellen
        JButton playButton = new JButton("Play");
        JButton selectCharacterButton = new JButton("Select Character");
        JButton quitButton = new JButton("Quit");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(playButton, gbc);

        gbc.gridy = 1;
        add(selectCharacterButton, gbc);

        gbc.gridy = 2;
        add(quitButton, gbc);

        // ActionListener für den Play-Button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPlayWindow();
            }
        });

        // ActionListener für den Select Character-Button
        selectCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSelectCharacterWindow();
            }
        });

        // ActionListener für den Quit-Button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void openPlayWindow() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayWindow().setVisible(true);
            }
        });
    }

    private void openSelectCharacterWindow() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SelectCharacterWindow().setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        // Hauptmenü starten
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
