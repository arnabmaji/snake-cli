package io.github.arnabmaji19.snakecli;

public class Main {

    private static final int screenHeight = 20;
    private static final int screenWidth = 40;

    public static void main(String[] args) {

        SnakeGame game = new SnakeGame(screenHeight, screenWidth);
        game.render();

    }


}
