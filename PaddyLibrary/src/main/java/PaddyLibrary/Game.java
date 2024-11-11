package PaddyLibrary;

import PaddyLibrary.audio.Audio;
import PaddyLibrary.utils.WindowUtils;

import static PaddyLibrary.graphics.Renderer.Color3f;

public class Game extends Application {
    private Audio audio;
    public Game(int width, int height, String title, int closeOperation, boolean visibility, boolean center, boolean resizable) {
        CreateWindow(width, height, title, this).display(closeOperation, visibility, center);
        getMainWindow().setResizable(resizable);
    }

    public void render() {
        Color3f(0.0f, 0.0f, 0.0f);
    }

    /*
    Testing in Intellij
     */
    public static void main(String[] args) {
        new Game(850, 580, "Test Window", WindowUtils.DISPOSE_WINDOW, true, true, true).launch(args);

    }

}