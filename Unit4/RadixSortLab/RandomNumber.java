import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RandomNumber {
    int x;
    int y;

    public RandomNumber() { //random x and y coordinates
        x = (int)(Math.random() * 800);
        y = (int)(Math.random() * 650);
    }

    public void draw(Graphics graphics) { //draws random numbers to stream down the screen
        graphics.setColor(Color.CYAN);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString(Integer.toString((int)(Math.random() * 1000000)), y, -x);
    }

    //GETTERS AND SETTERS

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
