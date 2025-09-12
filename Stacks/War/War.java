package Stacks.War;

import java.util.Stack;

public class War {
    private int max;
    private boolean isOpen;

    private Stack<Card> player1Deck;
    private Stack<Card> player1Refill;
    private Stack<Card> player2Deck;
    private Stack<Card> player2Refill;

    public War(int max, boolean isOpen) {
        this.max = max;
        this.isOpen = isOpen;

        Stack<Card> deck = new Stack<Card>();
        fillDeck(deck);
        System.out.println(deck);
    }
    
    private void fillDeck(Stack<Card> deck) {
        for(int i = 0; i < max; i++) {
            for(int j = 0; j < 4; j++) {
                deck.push(new Card(j, i));
            }
        }
    }

    /**
     * Shuffles a stack using the Fisher-Yates algorithm.
     * This is the most efficient and unbiased method for stack shuffling.
     */
    private void shuffle() {
        
    }

    @Override
    public String toString() {
        String output = "";
        output += "Player 1's Deck:\n";

        for(int i = 0; i < player1Deck.size() - 1; i++) {
            output += Integer.toString(card.getValue()) + Integer.toString(card.getSuit()) + " | ";
        }

        return String
    }
}
