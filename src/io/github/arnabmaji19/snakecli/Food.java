package io.github.arnabmaji19.snakecli;

public class Food {

    private final static char ICON = 'O';

    private final int screenHeight;
    private final int screenWidth;
    private final Point point;

    public Food(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.point = new Point();
        this.create();
    }

    public Node getNode() {
        return new Node(ICON, point.getX(), point.getY());
    }

    public void create() {
        /*
         * Create a Random Point for Food
         * within the screen height and width
         */
        int x = (int) (Math.random() * screenWidth);
        int y = (int) (Math.random() * screenHeight);
        point.setX(x);
        point.setY(y);
    }

    public Point getPoint() {
        return point;
    }
}
