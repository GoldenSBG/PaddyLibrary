package PaddyLibrary.bloontowerdefence;

import javax.swing.*;

public class Main {
    public static void main() {
        JFrame f = new JFrame("Bloons Tower Defense");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainWinow winow = new MainWinow();
        f.add(winow);
        f.setSize(WIDTH, HEIGHT);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
