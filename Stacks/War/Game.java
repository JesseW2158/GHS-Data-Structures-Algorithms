package Stacks.War;

import java.util.Scanner;

// WHAT TO DO WHEN TIED?
// WHAT TO DO WHEN ALL CARDS ARE THE SAME?
// MINIMUM NUMBER OF CARDS?
// HOW TO DISPLAY CARDS WHEN IN WAR?
// DOES BATTLE HAVE TO BE A BOOLEAN?
// WHAT DO I DO WHEN TIED IN WAR BUT ONE PLAYER'S DECK EMPTY

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

        scanner.close();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        War war = new War(max, isOpen, auto);

        while(!war.getPlayer1Deck().empty() && !war.getPlayer2Deck().empty()) {
            System.out.println(war);

            war.battle();
        }
    }
}
