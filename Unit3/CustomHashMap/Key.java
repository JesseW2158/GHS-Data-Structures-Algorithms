package Unit3.CustomHashMap;

public class Key {
    private int key;

    public Key(int key) {
        this.key = key;
    }

    public int hashCode() {
        String hash = Integer.toString(key * key);

        return (hash.length() <= 2) ? Integer.parseInt(hash) : Integer.parseInt(hash.substring(1, hash.length() - 1));
    }

    public boolean equals(Key other) {
        return this.key == other.key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
