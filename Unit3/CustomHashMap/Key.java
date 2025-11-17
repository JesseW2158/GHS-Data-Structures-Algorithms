package Unit3.CustomHashMap;

public class Key {
    private int key;

    public Key(int key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return (key * 0x9E3779B9) >>> 16;
    }

    public boolean equals(Key other) {
        return this.key == other.key;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "" + this.key;
    }
}
