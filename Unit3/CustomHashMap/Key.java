package Unit3.CustomHashMap;

public class Key {
    private int key;

    public Key(int key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        String hash = Long.toString((long) key * (long) key);

        return (hash.length() <= 4) ? Integer.parseInt(hash) : Integer.parseInt(hash.substring(2, hash.length() - 2));
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
