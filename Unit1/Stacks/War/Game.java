package Unit1.Stacks.War;

import java.util.Scanner;

// MY ACES ARE 1
// FULL SCREEN TERMINAL OR ELSE TERMINAL MIGHT DISPLAY WEIRDLY
// ANOTHER NOTE, CLEAR TERMINAL BEFORE RUNNING BECAUSE ASCII MIGHT AGAIN DISPLAY WEIRDLY
// TESTING ON A MAC HAS A WEIRD ISSUE WHERE YOU PRESS ENTER TWICE FOR CONTINUE

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean finished = false;

        while (!finished) { // while the user is not done playing
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

            while (war.evaluate() == 2) { // while game has not finished
                if (war.battle() == 0) { // checks if a war occurs
                    war.refill(); // refills deck to ensure if tie occurs, cards can be taken out

                    if (war.evaluate() != 2) { // if game cannot progress meaning one or more players decks are empty
                        if (war.evaluate() == 1) { // if player 1's deck is empty
                            System.out.println("Your opponent's cards ran out!");
                        } else if (war.evaluate() == -1) { // if player 2's deck is empty
                            System.out.println("You ran out of cards!");
                        } else { // if both players' decks are empty
                            System.out.println("You both ran out of cards!");
                        }

                        continue; // finishes the if to prevent error from pop.
                    }

                    war.getGame().push(war.getPlayer1Deck().pop());
                    war.getGame().push(war.getPlayer2Deck().pop());
                }

                war.refill(); // checks for empty in case a war didn't occur but one or more decks are empty
                              // and refills
            }

            System.out.println(war.evaluate() == 1 ? "You win!" : war.evaluate() == 0 ? "A tie...?" : "You lost... :C");

            System.out.println("Are you done playing (True/False): ");
            finished = scanner.nextBoolean();

            if (!finished) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }

        scanner.close(); // Prevents memory leak
    }
}
