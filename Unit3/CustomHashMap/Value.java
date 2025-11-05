package Unit3.CustomHashMap;

public class Value {
    private int value;
    private Value next;

    public Value(int value, Value next) {
        this.value = value;
        this.next = next;
    }

    public Value(int value) {
        this(value, null);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Value getNext() {
        return next;
    }

    public void setNext(Value next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + value + " -> " + next;
    }
}
