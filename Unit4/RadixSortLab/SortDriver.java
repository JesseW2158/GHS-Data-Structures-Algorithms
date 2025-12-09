import java.awt.Color;
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
    JOptionPane.showMessageDialog(null, "Sorting has finished.", sortType, JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null, "Accessors: " + timesAccessed + "\nMutators: " + timesMutated,
        "Accesses and Mutates", JOptionPane.INFORMATION_MESSAGE);

    System.exit(0); // quits the program
  }

  public static void MSDSort(Piece[] pieces) {
    int[] buckets = new int[10];
    int maxLength = maxNumLength(pieces);

    
  }

  public static void LSDSort(Piece[] pieces) {

  }

  private static int countLength(Piece piece) {

  }

  private static int maxNumLength(Piece[] pieces) {
    int max = 0;

    for(Piece piece : pieces) {
      max = (piece.getNumber() > max) ? piece.getNumber() : max;
    }

    return max;
  } 

  public static void bubbleSort(Piece[] pieces) throws InterruptedException {
    boolean listSorted = false;
    int scans = 0;

    while(!listSorted) {
      listSorted = true; //sets variable as true until a mistake is found

      for(int i = 0; i < pieces.length - scans - 1; i++) { //sets the two objects we are comparing to different colors
        pieces[i].setColor(Color.BLUE); //object being compared
        pieces[i + 1].setColor(Color.RED); //other object being compared

        window.repaint();
        Thread.sleep(delayInMillis);
        
        timesAccessed++;
        timesAccessed++;

        if(pieces[i].getNumber() > pieces[i + 1].getNumber()) { //if the number after i is less then i, a swap occurs
          listSorted = false;

          //sets the pieces to moving so they scramble
          pieces[i].setMoving(true);
          pieces[i + 1].setMoving(true);
          
          for(int j = 0; j < (int)(Math.random() * 10000); j++) {
            window.repaint();
            Thread.sleep(5);
          }

          movePieces(pieces, i, i + 1); //moves the pieces

          //unscrambling the pieces
          pieces[i].setMoving(false);
          pieces[i + 1].setMoving(false);
        }

        //sets the colors back to green
        pieces[i].setColor(Color.green);
        pieces[i + 1].setColor(Color.green);
      }
      scans++;
    }
    window.repaint();
  }
}