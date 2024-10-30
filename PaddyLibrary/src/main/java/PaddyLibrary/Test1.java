package PaddyLibrary;


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

    public Test1() {
        CreateWindow(850, 580, "test1", this).display(WindowUtils.TERMINATE_WINDOW, true, true);

        setMainApplicationIcon("/player/img.png");

        player = new Player(200, 100, 500);
        levelGenerator = new LevelGenerator();
        world = new World(levelGenerator);
        enableLog(false);
    }

    public void render() {
        Renderer.Color3f(1.0f, 0, 0);
        Renderer.Rect(0, 0, getWidth(), getHeight());

        player.render();
        levelGenerator.renderPlatforms();
        player.render();

        Renderer.Color3f(0.5f, 0.25f, 0); // Brauner Boden
        Renderer.Rect(0, player.getGroundLevel(), getWidth(), getHeight() - player.getGroundLevel());
    }

    public void update() {
        setQuitKey(0, true);
        player.update();
        world.checkCollisions(player);
        levelGenerator.updatePlatforms(player.getX());

    }

    public static void main(String[] args) {
        new Test1().launch(args);
    }
}
