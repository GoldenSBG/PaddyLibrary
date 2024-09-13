package PaddyLibrary.bloontowerdefence;

import javax.swing.*;
import java.awt.event.*;

public class MainWinow extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    public static final int FPSDelay = 10;
    Timer timer = new Timer(FPSDelay, this);

    int mouseX = 0, mouseY = 0;
    String isSelectingMonkey = "no";
    String tip = "";


    public MainWinow() {
        timer.start();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
