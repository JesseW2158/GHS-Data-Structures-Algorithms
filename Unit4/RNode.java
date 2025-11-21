package Unit4;

public class RNode<T> {
    T data;
    RNode<T> next;

    public RNode(T data) {
        this.data = data;
    }

    public void add(T d) {
        if(this.next == null) {
            this.next = new RNode<T>(d);
            return;
        }

        this.next.add(d);
    }

    @Override
    public String toString() {
        return this.next == null ? data.toString() : data.toString() + " -> " + this.next.toString();
    }
}
