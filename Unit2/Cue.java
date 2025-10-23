package Unit2;

public class Cue<T> {
    Box<T> head, tail;

    public Cue() {
        head = new Box<T>(0, null);
    }

    public boolean encue(Box<T> box) {
        if(box == null) {
            return false;
        }

        if(tail== null) {
            tail.next = box;
            box.next = null;
            tail = box;
            
            return true;
        }

        tail.next = box;
        box.next = null;
        tail = box;

        return true;
    }

    public Box<T> deque() {
        if(head == null) {
            return null;
        }

        Box<T> temp = head;
        head = head.next;

        return temp;
    }

    public int size() {
        int size = 0;

        Box<T> runner = head;

        while(runner != null) {
            runner = runner.next;
            size++;
        }

        return size;
    }

    @Override
    public String toString() {
        return "" + head;
    }
}
