package Stacks.War;

public class Card implements Comparable<Card> {
    private static int max;
    private int value;
    private int suit;

    /**
     * No need for default constructor since {@link Deck#Deck()} builds the deck without using default constructor
     * @param value
     * @param value
     * @param suit
     */
    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    /**
     * 
     * 
     * @return 
     */
    @Override
    public int compareTo(Card card) {
        if(card.value == max && this.value == 1 || card.value < this.value) {
            return 1;
        }

        if(card.value > this.value) {
            return -1;
        }

        return 0;        
    }

    @Override
    public String toString() {
        return "┌─────────────┐\n│ " + (value == 1 ? "A" : value == 11 ?  "J" : value == 12 ? "Q" : value == 13 ? "K" : value) + "           │\n│             │\n│             │\n│     " + (suit == 0 ? "Spa" : suit == 1 ? "Dia" : suit == 2 ? "Hrt" : "Clb") + "     │\n│             │\n│             │\n│           " + (value == 1 ? "A" : value == 11 ?  "J" : value == 12 ? "Q" : value == 13 ? "K" : value) + " │\n└─────────────┘";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }
}