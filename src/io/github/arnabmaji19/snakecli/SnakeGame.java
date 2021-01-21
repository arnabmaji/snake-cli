package io.github.arnabmaji19.snakecli;

import java.util.Scanner;

public class SnakeGame {

    /*
     * Author: Arnab Maji
     * Date: 20.01.2021
     * Title:
     * Snake CLI
     * Description:
     * Console Based Snake Game
     * Instructions:
     * Set your preferred screen height and width
     * Available Pixels of Screen:
     * (0, 0) -> (width, height) (including width and height)
     * Y
     * ^
     * | Screen follows
     * | Standard Co-ordinate System
     * |------>X
     *
     */

    private static final Scanner scanner = new Scanner(System.in);

    private Screen screen;
    private Snake snake;
    private Food food;
    private int score = 0;
    private boolean manualQuit = false;

    public SnakeGame(int screenHeight, int screenWidth) {
        this.screen = new Screen(screenHeight, screenWidth);
        this.snake = new Snake(screenHeight, screenWidth);
        this.food = new Food(screenHeight, screenWidth);
    }

    public void render() {

        displayWelcomeMenu();

        while (true) {

            // Generate food locations out side the snake body points
            while (snake.getBodyPoints().contains(food.getPoint()))
                food.create();

            screen.clear();
            screen.draw(snake.getNodes());
            screen.draw(food.getNode());

            Console.clear();
            screen.render();

            // snake hits its body or ends
            if (snake.hasCrashed())
                break;

            listenKeyEvents();

            if (manualQuit)
                break;

            snake.move();

            if (snake.collidesWith(food.getPoint())) {
                snake.increaseLength();
                score++;
            }
        }

        displayGameOverMenu();
    }

    private void listenKeyEvents() {
        /*
         * Waits for the user to enter a key
         * Available Key Inputs:
         * W: UP
         * S: DOWN
         * A: LEFT
         * D: RIGHT
         * Q: QUIT
         * Others: IGNORE
         */

        System.out.println("KEY: ");
        String key = scanner.nextLine().toUpperCase();

        if (!key.isEmpty()) {
            if (snake.isGoingOnXAxis() && key.equals("W"))
                snake.setVelocity(0, 1);
            else if (snake.isGoingOnXAxis() && key.equals("S"))
                snake.setVelocity(0, -1);
            else if (snake.isGoingOnYAxis() && key.equals("D"))
                snake.setVelocity(1, 0);
            else if (snake.isGoingOnYAxis() && key.equals("A"))
                snake.setVelocity(-1, 0);
            else if (key.equals("Q")) {
                manualQuit = true;
            }
        }
    }

    private void displayWelcomeMenu() {
        Console.clear();
        screen.clear();
        screen.drawText(
                new String[]
                        {
                                "Welcome To Snake CLI",
                                "A Console Based Snake Game",
                                "Press Enter to Continue"
                        }
        );
        screen.render();
        scanner.nextLine();
        Console.clear();
        screen.clear();
    }

    private void displayGameOverMenu() {
        Console.clear();
        screen.clear();
        screen.drawText(
                new String[]
                        {
                                "Game Over!",
                                "Score: " + score,
                                "Developed with Love",
                                "By Arnab Maji"
                        }
        );
        screen.render();
        screen.clear();
    }
}
