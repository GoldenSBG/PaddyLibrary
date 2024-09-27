package PaddyLibrary.ninjalla.world;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Platform {
    private int x;
    private int y;
    private int width;
    private int height;
    BufferedImage platform;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // Plattformfarbe
        g.fillRect(x, y, width, height);

        //loadImage();
        //g.drawImage(platform, x, y, null);
    }
    /*
    private void loadImage() {
        try {
            platform = ImageIO.read(getClass().getResourceAsStream("/ninjalla/platforms/grassplatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
