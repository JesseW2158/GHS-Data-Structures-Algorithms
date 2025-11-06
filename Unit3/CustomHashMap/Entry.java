package Unit3.CustomHashMap;

public class Entry {
    private Key key;
    private Value value;
    private Entry next;

    public Entry() {
        this(null, null);
    }

    public Entry(Key key, Value value) {
        this(key, value, null);
    }

    public Entry(Key key, Value value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Entry getNext() {
        return next;
    }

    public void setNext(Entry next) {
        this.next = next;
    }
}
