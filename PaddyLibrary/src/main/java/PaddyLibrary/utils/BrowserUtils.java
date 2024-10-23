package PaddyLibrary.utils;

import java.awt.*;
import java.net.*;

public class BrowserUtils {

    public static void open(String url) {
        try {
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            System.err.println("[BROWSER ERROR] Unable to open url.");
        }
    }

}