package Unit2;

public class Node {
    public int num;
    public Node next;

    public Node(int num, Node next) {
        this.num = num;
        this.next = next;
    }

    public Node(int num) {
        this(num, null);
    }

    @Override
    public String toString() {
        return "" + num + " -> " + next;
    }
}
