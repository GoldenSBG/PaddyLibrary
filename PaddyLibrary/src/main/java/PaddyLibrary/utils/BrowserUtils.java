package PaddyLibrary.utils;

import java.awt.*;
import java.net.*;

/**
 * Utilities class regarding the use of browsers.
 *
 * @author yiwuen
 *
 */

public class BrowserUtils {

    /**
     * Opens the URL in the OS's preferred browser.
     *
     * @param url Absolute link to web-page
     */
    public static void open(String url) {
        try {
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            System.err.println("[BROWSER ERROR] Unable to open url.");
        }
    }

}