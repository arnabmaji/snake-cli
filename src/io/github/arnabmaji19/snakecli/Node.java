package io.github.arnabmaji19.snakecli;

public class Node extends Point {
    private char icon;

    public Node(char icon, int x, int y) {
        super(x, y);
        this.icon = icon;
    }

    public char getIcon() {
        return icon;
    }

    public void setIcon(char icon) {
        this.icon = icon;
    }
}
