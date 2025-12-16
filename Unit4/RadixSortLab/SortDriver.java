import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SortDriver {
  private static String[] sortChoices = { "MSD", "LSD" };

  static JFrame window;
  static Table table;
  static Buckets buckets;
  static int delayInMillis;

  static final int BASE = 10;

  public static void main(String[] args) throws InterruptedException, FileNotFoundException {
    window = new JFrame("Escaping the Matrix"); // creating JFrame window

    window.setSize(800, 600);
    window.setResizable(false);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the JFrame window if they click the X

    // Asks how many milliseconds do you want between each comparison?
    delayInMillis = Integer
        .parseInt(JOptionPane.showInputDialog(null, "How many milliseconds do you want between each comparison?"));

    JFileChooser file = new JFileChooser(new File(System.getProperty("user.dir")));
    boolean fileFound = false;

    while (!fileFound) { // Ensures some file has been selected
      if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        System.out.println("File selected.");
        fileFound = true;
      } else {
        System.out.println("File not selected. Please try again.");
      }
    }

    table = new Table(loadFromFile(file.getSelectedFile(), window.getWidth()), window.getWidth(), delayInMillis);

    // which sorting algorithm do you want to use?
    String sortType = JOptionPane.showInputDialog(null, "Which sort do you want to use?", "Sorting Type",
        JOptionPane.QUESTION_MESSAGE, null, sortChoices, sortChoices[0]).toString();

      window.add(table);

      new Thread(() -> {
        while (true) {
          table.repaint();
        }
      }).start();

    window.repaint();
    window.setVisible(true);

    if (sortType.equals(sortChoices[0])) { // user selects MSD sort
      table.pieces = MSDSort(table.getPieces(), maxNumLength(table.getPieces()) - 1);
    } else { // user selects LSD sort
      table.pieces = LSDSort(table.getPieces(), maxNumLength(table.getPieces()));
    }

    // lets user know sorting has finished
    JOptionPane.showMessageDialog(null, "Sorting has finished.", sortType,
        JOptionPane.INFORMATION_MESSAGE);

    System.exit(0); // quits the program
  }

  public static Piece[] loadFromFile(File file, int screenWidth) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    ArrayList<Integer> temp = new ArrayList<Integer>();

    while (scanner.hasNext()) {
      temp.add(scanner.nextInt());
    }

    Piece[] pieces = new Piece[temp.size()];

    for (int i = 0; i < temp.size(); i++) {
      pieces[i] = new Piece(temp.size(), temp.get(i), screenWidth);
    }

    scanner.close();

    return pieces;
  }

  public static Piece[] MSDSort(Piece[] pieces, int digitPos) {
    if (pieces.length <= 1 || digitPos < 0) {
      return pieces;
    }

    @SuppressWarnings("unchecked")
    ArrayList<Piece>[] buckets = new ArrayList[BASE];

    for (int i = 0; i < BASE; i++) {
      buckets[i] = new ArrayList<Piece>();
    }

    for (Piece piece : pieces) {
      buckets[getDigitAt(piece, digitPos)].add(piece);
    }

    Piece[] sorted = new Piece[0];

    for (ArrayList<Piece> bucket : buckets) {
      Piece[] temp = bucket.toArray(new Piece[bucket.size()]);
      Piece[] bucketSortedResult = MSDSort(temp, digitPos - 1);

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

  public static Piece[] LSDSort(Piece[] pieces, int maxLength) throws InterruptedException {
    for (int i = 0; i < maxLength; i++) {
      int[] counts = new int[BASE];
      int[] cumulative = new int[BASE];
      Buckets buckets = new Buckets(counts);

      window.add(buckets);

      boolean keepRunning = true;

      new Thread(() -> {
        while (keepRunning) {
          buckets.repaint();
        }
      }).start();

      for (Piece piece : pieces) {
        piece.color = Color.WHITE;
        counts[getDigitAt(piece, i)]++;
        Thread.sleep(delayInMillis);
        piece.color = Color.GREEN;
      }

      System.out.println();

      cumulative[0] = counts[0];

      for (int j = 1; j < BASE; j++) {
        cumulative[j] = cumulative[j - 1] + counts[j];
      }

      Piece[] temp = new Piece[pieces.length];

      for (int j = pieces.length - 1; j >= 0; j--) {
        temp[cumulative[getDigitAt(pieces[j], i)] - 1] = pieces[j];
        cumulative[getDigitAt(pieces[j], i)]--;
      }

      pieces = temp;
    }

    return pieces;
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