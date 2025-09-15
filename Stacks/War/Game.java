package Stacks.War;

import java.util.Scanner;

// MY ACES ARE 1
// FULL SCREEN TERMINAL OR ELSE TERMINAL MIGHT DISPLAY WEIRDLY

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to War!");

        System.out.println("Enter the max value of the cards (1-13): ");
        int max = scanner.nextInt();

        System.out.println("Enter if the cards are shown to the player (True/False): ");
        boolean isOpen = scanner.nextBoolean();

        System.out.println("Enter if you want game to automatically play (True/False): ");
        boolean auto = scanner.nextBoolean();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        War war = new War(max, isOpen, auto);

        boolean finished = false;
        boolean lose = false;

        while (!finished && !lose) {
            war.battle();

            lose = (war.evaluate() == 1 ? );
        }

        scanner.close();
    }

    public static String winner(War war) {

    }

    public static boolean playerFinished(Scanner scanner) {
        System.out.println("Another game (True/False): ");

        return scanner.nextBoolean();
    }
}
