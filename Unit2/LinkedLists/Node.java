package Unit2.LinkedLists;

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
}
