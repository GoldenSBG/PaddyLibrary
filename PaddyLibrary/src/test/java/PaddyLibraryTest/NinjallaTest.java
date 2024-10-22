package PaddyLibraryTest;

import PaddyLibrary.Application;
import PaddyLibrary.utils.WindowUtils;

import static PaddyLibrary.graphics.Renderer.SJGL_Color3f;

public class NinjallaTest extends Application {

    public NinjallaTest() {
        SJGL_CreateWindow(850, 580, "My SJGL Window", this).display(WindowUtils.TERMINATE_WINDOW, true, true);
    }

    public void render() {
        SJGL_Color3f(0.0f, 0.0f, 0.0f);
    }

    public static void main(String[] args) {
        new NinjallaTest().launch(args);
    }

}