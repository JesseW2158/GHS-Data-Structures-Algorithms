package Stacks.War;

public class Game {
    public static void main(String[] args) {
        Card c1 = new Card(11, 3);
        Card c2 = new Card(12, 1);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c2.compareTo(c1));
    }
}
