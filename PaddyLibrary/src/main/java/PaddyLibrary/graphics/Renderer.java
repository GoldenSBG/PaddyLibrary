package PaddyLibrary.graphics;

import PaddyLibrary.Application;
import PaddyLibrary.physics.collision.CollisionBound;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Renderer {

    public static final void SJGL_Font(Font font) {
        Application.g.setFont(font);
    }

    public static final FontMetrics SJGL_FontMetrics() {
        return Application.g.getFontMetrics();
    }

    public static final FontMetrics SJGL_FontMetrics(Font font) {
        return Application.g.getFontMetrics(font);
    }

    public static final Font SJGL_CurrentFont() {
        return Application.g.getFont();
    }

    public static final void SJGL_DrawString(String str, Font font, int x, int y) {
        SJGL_Font(font);
        Application.g.drawString(str, x, y);
    }

    public static final Color SJGL_CurrentColor() {
        return Application.g.getColor();
    }

    public static final void SJGL_Colori(int v) {
        Application.g.setColor(new Color(v));
    }

    public static final void SJGL_Color3i(int r, int g, int b) {
        Application.g.setColor(new Color(r, g, b));
    }

    public static final void SJGL_Color4i(int r, int g, int b, int a) {
        Application.g.setColor(new Color(r, g, b, a));
    }

    public static final void SJGL_Color3f(float r, float g, float b) {
        Application.g.setColor(new Color(r, g, b));
    }

    /**
     * Sets the color with alpha (float RGBA) of the current graphic. The max value is 1.0.
     * @param r Red
     * @param g Green
     * @param b Blue
     *
     * @see Color
     */
    public static final void SJGL_Color4f(float r, float g, float b, float a) {
        Application.g.setColor(new Color(r, g, b, a));
    }

    public static final void SJGL_DrawTriangle(int[] x, int[] y) {
        Application.g.drawPolygon(x, y, 3);
    }

    public static final void SJGL_Triangle(int[] x, int[] y) {
        Application.g.fillPolygon(x, y, 3);
    }

    public static final void SJGL_DrawQuad(int[] x, int[] y) {
        Application.g.drawPolygon(x, y, 4);
    }

    public static final void SJGL_Quad(int[] x, int[] y) {
        Application.g.fillPolygon(x, y, 4);
    }

    public static final void SJGL_DrawCircle(int x, int y, int radius) {
        int d = radius * 2;
        Application.g.drawOval(x - radius, y - radius, d, d);
    }

    /**
     * Renders a color-filled circle based on the given x and y coordinates and radius.
     * @param x X position of the circle
     * @param y Y position of the circle
     * @param radius Radius of the circle
     *
     * @see Graphics2D
     * @see Application
     */
    public static final void SJGL_Circle(int x, int y, int radius) {
        int d = radius * 2;
        Application.g.fillOval(x - radius, y - radius, d, d);
    }

    /**
     * Renders a color-filled rectangle based on the given x, y, width, and height.
     * @param x X position of the rectangle
     * @param y Y position of the rectangle
     * @param w Width of the rectangle
     * @param h Height of the rectangle
     *
     * @see Graphics2D
     * @see Application
     */
    public static final void SJGL_Rect(int x, int y, int w, int h) {
        Application.g.fillRect(x, y, w, h);
    }

    /**
     * Renders a rectangle based on the given x, y, width, and height.
     * @param x X position of the rectangle
     * @param y Y position of the rectangle
     * @param w Width of the rectangle
     * @param h Height of the rectangle
     *
     * @see Graphics2D
     * @see Application
     */
    public static final void SJGL_DrawRect(int x, int y, int w, int h) {
        Application.g.drawRect(x, y, w, h);
    }

    /**
     * Renders the collision bounds.
     * @param bound Collision bound
     *
     * @see CollisionBound
     */
    public static final void SJGL_DrawBounds(CollisionBound bound) {
        Application.g.draw(bound.getBounds());
    }

    /**
     * Graphics int translation.
     * @param x X translation
     * @param y Y translation
     *
     * @see Graphics2D
     */
    public static final void SJGL_Translatei(int x, int y) {
        Application.g.translate(x, y);
    }

    /**
     * Graphics float translation.
     * @param x X translation
     * @param y Y translation
     *
     * @see Graphics2D
     */
    public static final void SJGL_Translatef(float x, float y) {
        Application.g.translate(x, y);
    }

    /**
     * Scales the graphics size.
     * @param sx Scale X (int)
     * @param sy Scale Y (int)
     *
     * @see Graphics2D
     */
    public static final void SJGL_Scalei(int sx, int sy) {
        Application.g.scale(sx, sy);
    }

    /**
     * Scales the graphics size.
     * @param sx Scale X (float)
     * @param sy Scale Y (float)
     *
     * @see Graphics2D
     */
    public static final void SJGL_Scalef(float sx, float sy) {
        Application.g.scale(sx, sy);
    }

}