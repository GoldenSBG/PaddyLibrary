package PaddyLibrary.brawlhallaGame.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCharacterWindow extends JFrame {
    public SelectCharacterWindow() {
        setTitle("Select Character");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Erstelle das Panel für die Buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        topPanel.add(backButton);

        // Füge das Panel und den Rest des Inhalts hinzu
        add(topPanel, BorderLayout.NORTH);

        // Inhalte für das Select Character-Fenster
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

        add(contentPanel, BorderLayout.CENTER);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Schließt das SelectCharacterWindow
            }
        });

        character1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Wähle Charakter 1 aus
                JOptionPane.showMessageDialog(SelectCharacterWindow.this, "Character 1 selected");
            }
        });

        character2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Wähle Charakter 2 aus
                JOptionPane.showMessageDialog(SelectCharacterWindow.this, "Character 2 selected");
            }
        });
    }
}
