package Stacks.War;

import java.util.Scanner;

// MY ACES ARE 1
// FULL SCREEN TERMINAL OR ELSE TERMINAL MIGHT DISPLAY WEIRDLY
// ANOTHER NOTE, CLEAR TERMINAL BEFORE RUNNING BECAUSE ASCII MIGHT AGAIN DISPLAY WEIRDLY
// TESTING ON A MAC HAS A WEIRD ISSUE WHERE YOU PRESS ENTER TWICE
// REFILL DECK IS NOT SHOWN

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean finished = false;

        while (!finished) {
            // int max = 2;
            // boolean isOpen = true;
            // boolean auto = true;

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

            while (war.evaluate() == 2) {
                if (war.battle() == 0) {
                    war.refill();

                    if (war.evaluate() != 2) {
                        continue;
                    }

                    war.getGame().push(war.getPlayer1Deck().pop());
                    war.getGame().push(war.getPlayer2Deck().pop());
                }

                war.refill();
            }

            System.out.println(war.evaluate() == 1 ? "You win!" : war.evaluate() == 0 ? "A tie...?" : "You lost :C");

            // finished = playerFinished(scanner);
            finished = false;

            if (!finished) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }

        scanner.close();
    }

    // public static String winner(War war) {

    // }

    public static boolean playerFinished(Scanner scanner) {
        System.out.println("Are you done playing (True/False): ");

        return scanner.nextBoolean();
    }
}
