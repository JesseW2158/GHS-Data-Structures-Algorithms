import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Piece implements Comparable<Piece> {
    private Color color;
    private int x;
    private int number, width;
    private int numOfPieces;
    private boolean moving;

    private int yPos = -750;

    public Piece(int numOfPieces, int screenWidth) {
        color = Color.GREEN;
        x = 0;
        number = 1;
        width = screenWidth/numOfPieces;
        moving = false;
        this.numOfPieces = numOfPieces;
    }

    public void drawNumbers(Graphics graphics) { //draws the numbers on the graphics window
        graphics.setColor(color);
        graphics.setFont(new Font("Arial", Font.BOLD, width));

        if(moving) { //if the piece is moving, scrambles the numbers
            graphics.drawString(randomNumbers(), yPos/numOfPieces, -x);
        } else { //if the piece is not moving, draws the numbers normally
            graphics.drawString(convertToBinary(), 0, -x);
        }
    }

    private String randomNumbers() { //generates a random string of numbers
        return "" + (Math.random() * 10);
    }

    private String convertToBinary() { //converts the piece number to a binary string
        String output = "";
        int temp = number;

        while(temp > 0) {
            output += temp % 2;
            temp /= 2;
        }

        return new StringBuilder(output).reverse().toString(); //reverses the binary string
    }

    @Override
    public int compareTo(Piece piece) {
        return number - piece.getNumber();
    }

    //#region Getters & Setters & toString
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public void setNumOfPieces(int numOfPieces) {
        this.numOfPieces = numOfPieces;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public String toString() {
        return "" + this.number;
    }

    //#endregion
}