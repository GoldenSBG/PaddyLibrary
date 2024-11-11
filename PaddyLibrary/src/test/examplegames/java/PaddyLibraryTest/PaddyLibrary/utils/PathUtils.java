package PaddyLibraryTest.PaddyLibrary.utils;

import PaddyLibraryTest.PaddyLibrary.graphics.sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PathUtils {

    public static BufferedImage GetImage(String path) {
        try {
            return ImageIO.read(Sprite.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.print("[FAILED] loading texture at path: " + path + ": " + e.getMessage());
        }
        return null;
    }

}
