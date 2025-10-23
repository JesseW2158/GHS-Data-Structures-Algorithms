package Unit2;

public class Box<T> {
    public int data;
    public Box<T> next;

    public Box(int data, Box<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + data + " -> ";
    }
}


