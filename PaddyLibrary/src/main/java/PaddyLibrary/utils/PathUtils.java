package PaddyLibrary.utils;

import PaddyLibrary.graphics.sprites.Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Contains methods for getting a particular object based on the given path.
 *
 * @author yiwuen
 *
 */

public class PathUtils {

    /**
     * Returns an {@link BufferedImage} based on the given path.
     *
     * @param path Path to the {@link BufferedImage}
     * @return {@link BufferedImage} image
     *
     * @see BufferedImage
     */
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
