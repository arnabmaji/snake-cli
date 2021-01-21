package io.github.arnabmaji19.snakecli;

public class Console {

    public static void clear() {
        /*
         * Clears the console
         * WARNING: OS dependent method,
         * may not work for every OS
         * adjust according to your console.
         */
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
