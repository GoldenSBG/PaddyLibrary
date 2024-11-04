package PaddyLibraryTest.ninjalla.menus;

import PaddyLibraryTest.ninjalla.NinjallaMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

    public MenuPanel(NinjallaMain mainWindow) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Erstelle einen "Start Game"-Button
        JButton singlePlayerButton = new JButton("Singleplayer");
        JButton multiPlayerButton = new JButton("Multiplayer");
        JButton charakterButton = new JButton("Charakters");
        JButton quitButton = new JButton("Exit");

        singlePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        multiPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        charakterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Füge ActionListener hinzu, um das Spiel zu starten
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.startGame();  // Rufe die Methode auf, um das Spiel zu starten
            }
        });

        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Soon");;
            }
        });

        charakterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Soon");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Füge vertikale Abstände zwischen den Buttons hinzu
        add(Box.createVerticalGlue());  // Fügt flexiblen Platz oben hinzu
        add(singlePlayerButton);
        add(Box.createVerticalStrut(20));  // Fügt 20 Pixel Abstand hinzu
        add(multiPlayerButton);
        add(Box.createVerticalStrut(20));  // Fügt 20 Pixel Abstand hinzu
        add(charakterButton);
        add(Box.createVerticalStrut(20));  // Fügt 20 Pixel Abstand hinzu
        add(quitButton);
        add(Box.createVerticalGlue());  // Fügt flexiblen Platz unten hinzu

    }
}
