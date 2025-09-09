package Stacks.War;

public class Card implements Comparable<Card> {
    private int value;
    private char rank;
    private int suit;

    /**
     * No need for default constructor since {@link Deck#Deck()} builds the deck without using default constructor
     * @param value
     * @param rank
     * @param suit
     */
    public Card(int value, char rank, int suit) {
        this.value = value;
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card card) {
        // return this.rank == 
    }

    

    @Override
    public String toString() {
        return "┌─────────────┐\n│ " + value + "           │\n│             │\n│             │\n│      " + Character.toString(suit == 0 ? 'S' : suit == 1 ? 'D' : suit == 2 ? 'H' : 'C') + "      │\n│             │\n│             │\n│           " + Character.toString(rank) + " │\n└─────────────┘";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getRank() {
        return rank;
    }

    public void setRank(char rank) {
        this.rank = rank;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }
}