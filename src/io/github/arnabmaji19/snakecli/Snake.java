package io.github.arnabmaji19.snakecli;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Snake {

    private final static int INITIAL_LENGTH = 3;
    private final static char HEAD_ICON = 'A';
    private final static char BODY_ICON = 'X';
    private final static char TAIL_ICON = 'Y';

    private final int screenHeight;
    private final int screenWidth;
    private final Deque<Point> bodyPoints;
    private final Set<Point> pointSet;
    private int xVelocity = 1;
    private int yVelocity = 0;
    private boolean increaseLength = false;
    private boolean crashed = false;

    public Snake(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.pointSet = new HashSet<>();
        this.bodyPoints = getDefaultBodyPoints();
    }

    public void move() {
        /*
         * Move Snake along the screen
         * with x-velocity and y-velocity
         */

        Point head = bodyPoints.getFirst();
        Point newHead = new Point(
                head.getX() + xVelocity,
                head.getY() + yVelocity);

        // check if new head has crashed into ends or its own body
        if ((newHead.getX() < 0 || newHead.getX() > screenWidth) ||
                (newHead.getY() < 0 || newHead.getY() > screenHeight) ||
                pointSet.contains(newHead)) {
            crashed = true;
            return;
        }

        bodyPoints.addFirst(newHead);
        pointSet.add(newHead);

        // if snake wants to increase length do not remove tail point
        if (increaseLength) {
            increaseLength = false;
            return;
        }
        pointSet.remove(bodyPoints.removeLast());
    }

    public void setVelocity(int xVelocity, int yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public boolean isGoingOnXAxis() {
        return xVelocity != 0;
    }

    public boolean isGoingOnYAxis() {
        return yVelocity != 0;
    }

    public Node[] getNodes() {
        /*
         * Convert Snake Body Points
         * to an array of graphical nodes,
         * to draw it on the screen
         */

        Node[] nodes = new Node[bodyPoints.size()];
        int index = 0;
        for (Point p : bodyPoints)
            nodes[index++] = new Node(BODY_ICON, p.getX(), p.getY());

        nodes[0].setIcon(HEAD_ICON);
        nodes[nodes.length - 1].setIcon(TAIL_ICON);
        return nodes;
    }

    public boolean collidesWith(Point point) {
        return bodyPoints.getFirst().equals(point);
    }

    public Set<Point> getBodyPoints() {
        return pointSet;
    }

    public void increaseLength() {
        increaseLength = true;
    }

    public boolean hasCrashed() {
        return crashed;
    }

    private Deque<Point> getDefaultBodyPoints() {
        /*
         * By default create the snake
         * in the middle of the screen
         * with specified initial length
         */

        int y = screenHeight / 2;
        int x = screenWidth / 2 - INITIAL_LENGTH / 2;

        pointSet.clear();
        Deque<Point> bodyPoints = new ArrayDeque<>();
        for (int i = x; i < x + INITIAL_LENGTH; i++) {
            Point point = new Point(i, y);
            bodyPoints.addFirst(point);
            pointSet.add(point);
        }
        return bodyPoints;
    }
}
