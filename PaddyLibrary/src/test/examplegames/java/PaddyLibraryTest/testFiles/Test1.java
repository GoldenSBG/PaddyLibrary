package PaddyLibraryTest.testFiles;

import PaddyLibrary.Application;
import PaddyLibrary.entities.Player;
import PaddyLibrary.graphics.Renderer;
import PaddyLibrary.utils.WindowUtils;
import PaddyLibrary.world.LevelGenerator;
import PaddyLibrary.world.World;

@SuppressWarnings("serial")
public class Test1 extends Application {

    private Player player;
    private World world;
    private LevelGenerator levelGenerator;
    private int cameraX = 0, cameraY = 0;

    public Test1() {
        CreateWindow(850, 580, "test1", this).display(WindowUtils.TERMINATE_WINDOW, true, true);
        getMainWindow().setResizable(true);

        setMainApplicationIcon("/player/img.png");

        player = new Player(200, 100, 500);
        levelGenerator = new LevelGenerator();
        world = new World(levelGenerator);
        enableLog(false);
    }

    public void render() {
        Renderer.Color3f(1.0f, 0, 0);
        Renderer.Rect(0, 0, getWidth(), getHeight());

        levelGenerator.renderCameraPlatforms(cameraX, cameraY);
        player.cameraRender(cameraX, cameraY);

        Renderer.Color3f(0.5f, 0.25f, 0); // Brauner Boden
        //Renderer.Rect(0, player.getGroundLevel(), getWidth(), getHeight() - player.getGroundLevel());
        Renderer.Rect(0, player.getGroundLevel() - cameraY, getWidth(), getHeight() - (player.getGroundLevel() - cameraY));
    }

    public void update() {
        setQuitKey(0, true);
        player.update();
        world.checkPlayerCollisions(player);
        levelGenerator.updatePlatforms(player.getX(), player.getY());

        cameraX = player.getX() - getWidth() / 2;
        cameraY = player.getY() - getHeight() / 2;
    }

    public static void main(String[] args) {
        new Test1().launch(args);
    }
}
