package io.github.arnabmaji19.snakecli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Screen {
    /*
     * Generates a console based
     * GUI Screen with Borders.
     * Available Pixels: (0, 0) -> (width, height)
     * includes width and height
     * Follows Standard Co-ordinate System.
     * Draws graphic node of type char.
     *
     */

    private static final int BORDER_SIZE = 2;

    private final char[][] pixels;
    private final int height;
    private final int width;
    private boolean cleared;

    public Screen(int height, int width) {
        this.height = height;
        this.width = width;
        this.pixels = new char[height + 1 + BORDER_SIZE][width + 1 + BORDER_SIZE];
        clear();
    }

    public void draw(Node node) {
        /*
         * Draw a graphic node on screen
         * WARNING: Screen also has borders,
         * available pixels to draw
         * without borders: (1, 1) -> (width + 1, height + 1)
         */

        int xPos = node.getX();
        int yPos = node.getY();

        if ((xPos < 0 || xPos > width) || (yPos < 0 || yPos > height))
            throw new IllegalStateException("Pixel not allowed to be drawn.");

        /*
         * Map Standard Co-ordinate System
         * To Array Based Co-ordinate System
         */
        int y = yPos;
        yPos = xPos;
        xPos = height - y;

        // Adjust positions according to the border
        xPos++;
        yPos++;
        pixels[xPos][yPos] = node.getIcon();
        cleared = false;
    }

    public void draw(Node[] nodes) {
        for (Node node : nodes)
            draw(node);
    }

    public void clear() {
        /*
         * Set screen to Blank
         * and add borders on each side
         */

        if (cleared)
            return;

        for (char[] row : pixels)
            Arrays.fill(row, ' ');

        Arrays.fill(pixels[0], '-');
        Arrays.fill(pixels[pixels.length - 1], '-');
        for (int i = 0; i < pixels.length; i++)
            pixels[i][0] = pixels[i][pixels[0].length - 1] = '|';

        cleared = true;
    }

    public void render() {
        /*
         * Draw all pixels of the screen on the console
         */

        for (char[] row : pixels) {
            for (char pixel : row)
                System.out.print(pixel);
            System.out.println();
        }
    }

    public void drawText(String[] texts) {
        /*
         * Display text in the center of the screen
         */

        List<Node> nodes = new ArrayList<>();
        int height = this.height / 2 + texts.length / 2;
        for (String text : texts) {
            int x = this.width / 2 - text.length() / 2;
            for (char c : text.toCharArray())
                nodes.add(new Node(c, x++, height));
            height--;
        }
        draw(nodes.toArray(new Node[0]));
    }

}
