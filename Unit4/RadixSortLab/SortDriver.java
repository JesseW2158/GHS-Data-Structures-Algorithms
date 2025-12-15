import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SortDriver {
  private static String[] sortChoices = { "MSD", "LSD" };
  private static String[] startStates = { "Random", "Reverse" };

  static JFrame window;
  static Table table;
  static int timesAccessed = 0;
  static int timesMutated = 0;
  static int delayInMillis;

  static final int BASE = 10;

  // TODO Add way to input path to file
  public static void main(String[] args) throws InterruptedException {
    window = new JFrame("Escaping the Matrix"); // creating JFrame window

    window.setSize(800, 650);
    window.setResizable(false);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the JFrame window if they click the red X

    // Asks how many milliseconds do you want between each comparison?
    delayInMillis = Integer
        .parseInt(JOptionPane.showInputDialog(null, "How many milliseconds do you want between each comparison?"));

    // asks for the number of pieces you want to sort?
    int pieces = Integer
        .parseInt(JOptionPane.showInputDialog(null, "How many pieces do you want to sort? (Min: 3, Max: 50)"));

    table = new Table(pieces, window.getWidth(), delayInMillis);

    // which sorting algorithm do you want to use?
    String sortType = JOptionPane.showInputDialog(null, "Which sort do you want to use?", "Sorting Type",
        JOptionPane.QUESTION_MESSAGE, null, sortChoices, sortChoices[0]).toString();

    // random start or reverse start?
    String startState = JOptionPane.showInputDialog(null, "Which start state do you want to use?", "Start State",
        JOptionPane.QUESTION_MESSAGE, null, startStates, startStates[0]).toString();

    // if user wants to sort in reverse order
    if (startState.equals("Reverse")) {
      table.reverseOrder();
    }

    window.add(table);

    new Thread(() -> {
      while (true) {
        table.repaint();
      }
    }).start();

    window.repaint();
    window.setVisible(true);

    if (sortType.equals(sortChoices[0])) { // user selects MSD sort
      MSDSort(table.getPieces());
    } else { // user selects LSD sort
      LSDSort((table.getPieces()));
    }

    // lets user know sorting has finished
    JOptionPane.showMessageDialog(null, "Sorting has finished.", sortType,
        JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null, "Accessors: " + timesAccessed +
        "\nMutators: " + timesMutated,
        "Accesses and Mutates", JOptionPane.INFORMATION_MESSAGE);

    System.exit(0); // quits the program
  }

  public static void printBuckets(ArrayList<Piece>[] buckets) {
    for (ArrayList<Piece> bucket : buckets) {
      System.out.print("[ ");
      for (Piece piece : bucket) {
        System.out.print(piece.number + " ");
      }
      System.out.println("]");
    }
    System.out.println();
  }

  public static void printTable(Table table) {
    for (Piece piece : table.pieces) {
      System.out.print(piece.number + " ");
    }

    System.out.println();
  }

  public static Piece[] MSDSort(Piece[] pieces) {
    return MSDSortHelper(pieces, maxNumLength(pieces) - 1);
  }

  private static Piece[] MSDSortHelper(Piece[] pieces, int digitPos) {
    if (pieces.length <= 1 || digitPos < 0) {
      return pieces;
    }

    int[] cumulative = new int[BASE];

    @SuppressWarnings("unchecked")
    ArrayList<Piece>[] buckets = new ArrayList[10];

    for (int i = 0; i < BASE; i++) {
      buckets[i] = new ArrayList<Piece>();
    }

    for (Piece piece : pieces) {
      buckets[getDigitAt(piece, digitPos)].add(piece);
    }

    // printBuckets(buckets);

    // TODO Add showing the numbers going back to the original places but sorted

    Piece[] sorted = new Piece[0];

    for (ArrayList<Piece> bucket : buckets) {
      Piece[] temp = new Piece[bucket.size()];

      for (int i = 0; i < bucket.size(); i++) {
        temp[i] = bucket.get(i);
      }

      Piece[] bucketSortedResult = MSDSortHelper(temp, digitPos - 1);

      if (bucketSortedResult.length > 0) {
        sorted = combineArrays(sorted, bucketSortedResult);
      }
    }

    return sorted;
  }

  public static Piece[] combineArrays(Piece[] original, Piece[] addition) {
    Piece[] combined = new Piece[original.length + addition.length];

    for (int i = 0; i < original.length; i++) {
      combined[i] = original[i];
    }

    for (int i = 0; i < addition.length; i++) {
      combined[original.length + i] = addition[i];
    }

    return combined;
  }

  public static void LSDSort(Piece[] pieces) {

  }

  private static int getDigitAt(Piece piece, int digitPos) {
    int digit = piece.number;

    for (int i = 0; i < digitPos; i++) {
      digit /= BASE;
    }

    String temp = digit + "";

    return temp.charAt(temp.length() - 1) - '0';
  }

  private static int maxNumLength(Piece[] pieces) {
    int max = 0;

    for (Piece piece : pieces) {
      max = (piece.number > max) ? piece.number : max;
    }

    return ("" + max).length();
  }
}

/**
 * public static void bubbleSort(Piece[] pieces) throws InterruptedException {
 * boolean listSorted = false;
 * int scans = 0;
 * 
 * while (!listSorted) {
 * listSorted = true; // sets variable as true until a mistake is found
 * 
 * for (int i = 0; i < pieces.length - scans - 1; i++) { // sets the two objects
 * we are comparing to different colors
 * pieces[i].color = (Color.BLUE); // object being compared
 * pieces[i + 1].color = (Color.RED); // other object being compared
 * 
 * window.repaint();
 * Thread.sleep(delayInMillis);
 * 
 * timesAccessed++;
 * timesAccessed++;
 * 
 * if (pieces[i].number > pieces[i + 1].number) { // if the number after i is
 * less then i, a swap occurs
 * listSorted = false;
 * 
 * // sets the pieces to moving so they scramble
 * pieces[i].setMoving(true);
 * pieces[i + 1].setMoving(true);
 * 
 * for (int j = 0; j < (int) (Math.random() * 10000); j++) {
 * window.repaint();
 * Thread.sleep(5);
 * }
 * 
 * movePieces(pieces, i, i + 1); // moves the pieces
 * 
 * // unscrambling the pieces
 * pieces[i].moving = (false);
 * pieces[i + 1].moving = (false);
 * }
 * 
 * // sets the colors back to green
 * pieces[i].color = Color.green;
 * pieces[i + 1].color = Color.green;
 * }
 * scans++;
 * }
 * window.repaint();
 * }
 * }
 */