package Stacks.War;

import java.io.IOException;
import java.util.Stack;

public class War {
    private int max;
    private boolean isOpen;
    private boolean auto;

    private Stack<Card> player1Deck;
    private Stack<Card> player1Refill;

    private Stack<Card> player2Deck;
    private Stack<Card> player2Refill;

    private Stack<Card> game;

    /**
     * Constructor
     * 
     * @param max    the max value of the cards
     * @param isOpen if the cards are shown to the player
     */
    public War(int max, boolean isOpen, boolean auto) {
        this.max = Math.min(Math.max(2, max), 13);
        this.isOpen = isOpen;
        this.auto = auto;

        this.player1Deck = new Stack<Card>();
        this.player2Deck = new Stack<Card>();

        this.player1Refill = new Stack<Card>();
        this.player2Refill = new Stack<Card>();

        this.game = new Stack<Card>();

        Stack<Card> deck = new Stack<Card>();
        fillDeck(deck);
        shuffle(deck);

        for (int i = 0; i < max * 2; i++) {
            this.player1Deck.push(deck.pop());
            this.player2Deck.push(deck.pop());
        }

        // this.player2Deck.push(new Card(max, max, max));
        // this.player2Deck.push(new Card(max, max, max));

        
        // this.player1Deck.push(new Card(1, max, max));
        // this.player1Deck.push(new Card(1, max, max));
    }

    /**
     * Under the assumption that both player's decks are not empty
     */
    public int battle() {
        Card player1Card = player1Deck.pop();
        Card player2Card = player2Deck.pop();

        game.push(player1Card);
        game.push(player2Card);

        System.out.println(player1Card.compareTo(player2Card));

        displayBattle(player1Card, player2Card);

        if (player1Card.compareTo(player2Card) > 0) {
            System.out.println("Here1");
            if(player1Deck.empty()) {
                transfer(player1Deck, player1Refill);
            }

            if(player2Deck.empty()) {
                transfer(player2Deck, player2Refill);
            }

            emptyGameDeck(player1Refill);
            return 1;
        } else if (player1Card.compareTo(player2Card) < 0) {
            System.out.println("Here-1");
            if(player1Deck.empty()) {
                transfer(player1Deck, player1Refill);
            }

            if(player2Deck.empty()) {
                transfer(player2Deck, player2Refill);
            }
            
            emptyGameDeck(player2Refill);
            return -1;
        } else {
            System.out.println("Here0");
            if(player1Deck.empty()) {
                transfer(player1Deck, player1Refill);
            }

            if(player2Deck.empty()) {
                transfer(player2Deck, player2Refill);
            }

            return 0;
        }
    }

    /**
     * Transfers all cards from the refill deck to the playing deck
     * 
     * @param deck
     * @param refill
     */
    public void transfer(Stack<Card> deck, Stack<Card> refill) {
        while (!refill.empty()) {
            deck.push(refill.pop());
        }
    }

    /**
     * Evaluates whether a deck has appeared
     * 
     * @return 1 if player 1 wins return -1 if player 2 wins return 0 if tied returns 2 if game has not finished
     */
    public int evaluate() {
        if (player1Deck.empty() && player2Deck.empty()) {
            return 0;
        } else if (player2Deck.empty()) {
            return 1;
        } else if (player1Deck.empty()) {
            return -1;
        }

        return 2;
    }

    private void emptyGameDeck(Stack<Card> winner) {
        while (!game.empty()) {
            winner.push(game.pop());
        }
    }

    private void displayBattle(Card player1Card, Card player2Card) {
        System.out.println(toString());

        System.out.println("Player 1:" + player1Card + "\n\n Player 2:" + player2Card);

        if (!auto) {
            System.out.println("\nPress enter to continue...");

            try {
                System.in.read();
                System.in.read(); // Read the newline character as well to prevent cutoff (error found by chatgpt)
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        clearScreen();
    }

    /**
     * Clears the terminal screen
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Fills a deck with cards.
     * 
     * @param deck
     * @return filled deck
     */
    private void fillDeck(Stack<Card> deck) {
        for (int i = 1; i <= max; i++) {
            for (int j = 0; j < 4; j++) {
                deck.push(new Card(i, j, max));
            }
        }
    }

    /**
     * Shuffles a stack using the Fisher-Yates algorithm.
     * This is the most efficient and unbiased method for stack shuffling.
     * 
     * @param deck
     * @return shuffled deck
     */
    private void shuffle(Stack<Card> deck) {
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));

            Card temp = deck.get(i);

            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    /**
     * @return deck size of both players
     */
    private String score() {
        return "Player 1 deck size: " + (player1Deck.size() + player1Refill.size()) + "\nPlayer 2 deck size: "
                + (player2Deck.size() + player2Refill.size());
    }

    @Override
    public String toString() {
        String output = score() + "\n\n";
        
        if (isOpen) {
            output += "Player 1's Deck:\n| ";

            for (Card card : player1Deck) {
                output += card.getValue() + "-" + card.getSuit() + " | ";
            }

            output += "\n\nPlayer 2's Deck:\n| ";

            for (Card card : player2Deck) {
                output += card.getValue() + "-" + card.getSuit() + " | ";
            }

            output += "\n";

        } else {
            output += "Player 1's Deck:\n| ";

            output += player1Deck.peek().getValue() + "-" + player1Deck.peek().getSuit() + " | ";

            for (int i = 1; i < player1Deck.size(); i++) {
                output += "X | ";
            }

            output += "\n\nPlayer 2's Deck:\n| ";

            output += player2Deck.peek().getValue() + "-" + player2Deck.peek().getSuit() + " | ";

            for (int i = 1; i < player2Deck.size(); i++) {
                output += "X | ";
            }

            output += "\n";
        }

        return output;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Stack<Card> getPlayer1Deck() {
        return player1Deck;
    }

    public void setPlayer1Deck(Stack<Card> player1Deck) {
        this.player1Deck = player1Deck;
    }

    public Stack<Card> getPlayer1Refill() {
        return player1Refill;
    }

    public void setPlayer1Refill(Stack<Card> player1Refill) {
        this.player1Refill = player1Refill;
    }

    public Stack<Card> getPlayer2Deck() {
        return player2Deck;
    }

    public void setPlayer2Deck(Stack<Card> player2Deck) {
        this.player2Deck = player2Deck;
    }

    public Stack<Card> getPlayer2Refill() {
        return player2Refill;
    }

    public void setPlayer2Refill(Stack<Card> player2Refill) {
        this.player2Refill = player2Refill;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public Stack<Card> getGame() {
        return game;
    }

    public void setGame(Stack<Card> game) {
        this.game = game;
    }
}
