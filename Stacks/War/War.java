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

    }
    
    private void fillDeck(Stack<Card> deck) {
        for(int i = 0; i < max; i++) {
            for(int j = 0; j < 4; j++) {
                deck.push(new Card(j, i))
            }
        }
    }

    /**
     * Shuffles a stack using the Fisher-Yates algorithm.
     * This is the most efficient and unbiased method for stack shuffling.
     */
    private void shuffle() {
        
    }
}
