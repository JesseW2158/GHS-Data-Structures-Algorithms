import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JPanel;

public class Table extends JPanel {
    Piece[] pieces;
    RandomNumber[] randomNumbers;
    int MAX_NUMBER = 1000;

    public Table(Piece[] pieces, int screenWidth, int delayInMillis) {
        this.pieces = pieces;

        randomNumbers = new RandomNumber[Math.min(200, (int) (Math.random() * 400) + 200)];

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = new RandomNumber();
        }

        for (int i = 0; i < this.pieces.length; i++) {
            if (i > 0) {
                this.pieces[i].x = this.pieces[i - 1].x + this.pieces[i - 1].width; // makes each piece a equal width of
                                                                                    // the previous piece
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D) graphics;

        // Rotate the coordinate system by 90 degrees clockwise
        AffineTransform at = AffineTransform.getQuadrantRotateInstance(1);
        g2d.setTransform(at);

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i].draw(graphics);
            randomNumbers[i].setY(randomNumbers[i].getY() + ((Math.random() * 2 > 0.5) ? 0 : 1));

            if (randomNumbers[i].getY() > 650) {
                randomNumbers[i].setY(0);
            }
        }

        setBackground(Color.BLACK);

        for (int i = 0; i < pieces.length; i++) {
            pieces[i].drawNumbers(graphics);
        }

        at = AffineTransform.getQuadrantRotateInstance(0);
        g2d.setTransform(at);
    }

    public void reverseOrder() { // sorts the array in reverse order
        for (int i = 0; i < pieces.length; i++) { // replaces numbers of the array making reverse mutation & accessor
                                                  // count work
            pieces[i].number = (i + 1);
        }

        Arrays.sort(pieces, Comparator.reverseOrder());

        for (int i = 0; i < this.pieces.length; i++) { // makes each piece start at 0 for reverse order to appear
                                                       // correctly
            this.pieces[i].x = 0;
        }

        for (int i = 0; i < this.pieces.length; i++) { // makes each piece a equal width of the previous piece
            if (i > 0) {
                this.pieces[i].x = this.pieces[i - 1].x + this.pieces[i - 1].width;
            }
        }
    }

    // GETTERS AND SETTERS

    public Piece[] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }

    public RandomNumber[] getRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(RandomNumber[] randomNumbers) {
        this.randomNumbers = randomNumbers;
    }
}
