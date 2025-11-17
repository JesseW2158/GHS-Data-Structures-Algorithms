package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomHashMap {
    private Entry[] table;
    private int size;

    private static final double MAX_LOAD_FACTOR = 0.75;
    private static final double MIN_LOAD_FACTOR = 0.1;
    private static final int MIN_TABLE_LENGTH = 10;
    private static final int TABLE_SIZE = 8;

    public CustomHashMap() {
        this.table = new Entry[TABLE_SIZE];
        this.size = 0;
    }

    /**
     * Inserts or replaces an entry based on its key, using that key's hash to
     * choose a bucket.
     *
     * @param entry the entry to insert
     * @return the previous value for this key, or {@code null} if it did not
     *         exist
     */
    public Value put(Entry entry) {
        if (entry == null || entry.getKey() == null) {
            return null;
        }

        // Evaluates previous entry; checks for null; if true, returns null, if false,
        // returns value
        Value prev = (table[entry.getKey().hashCode() % table.length] == null) ? null
                : table[entry.getKey().hashCode() % table.length].getValue();

        if (prev == null) {
            table[entry.getKey().hashCode() % table.length] = entry;
        } else { // Iterate through linked list from separate chaining to find potentially
                 // matching target
            Entry dummy = new Entry();
            dummy.setNext(table[entry.getKey().hashCode() % table.length]);
            Entry runner = dummy;
            boolean inserted = false;

            while (runner.getNext() != null) {
                if (runner.getNext().getKey().equals(entry.getKey())) { // If any value is found that is equal to the
                                                                        // entry, it's replaced
                    prev = runner.getNext().getValue();
                    Entry temp = runner.getNext().getNext();

                    runner.setNext(entry);
                    runner.getNext().setNext(temp);

                    inserted = true;

                    this.size--;

                    break;
                }

                runner = runner.getNext();
            }

            if (!inserted) { // No entry found that matches entry then program appends entry to end
                System.out.println("Collision!");
                runner.setNext(entry);
                prev = null;
            }

            table[entry.getKey().hashCode() % table.length] = dummy.getNext(); // Puts original local reference to nodes
                                                                               // of the linked list into the table so
                                                                               // table updates changes reflected
        }

        this.size++;

        if (loadFactor() >= MAX_LOAD_FACTOR) { // If load factor increases past the maximum load factor, rehashing
                                               // occurs
            rehash();
        }

        return prev;
    }

    /**
     * Inserts or replaces an entry directly at a specific bucket index; Same as
     * original put just using i instead of hashcode
     *
     * @param i     the bucket index to place the entry in
     * @param entry the entry to insert
     * @return the previous value at this index, or {@code null} if it was empty
     */
    public Value put(int i, Entry entry) {
        if (entry == null || entry.getKey() == null || i >= table.length) {
            return null;
        }

        Value prev = (table[i] == null) ? null : table[i].getValue();

        if (prev == null) {
            table[i] = entry;
        } else {
            Entry dummy = new Entry();
            dummy.setNext(table[i]);
            Entry runner = dummy;
            boolean inserted = false;

            while (runner.getNext() != null) {
                if (runner.getNext().getKey().equals(entry.getKey())) {
                    prev = runner.getNext().getValue();
                    Entry temp = runner.getNext().getNext();

                    runner.setNext(entry);
                    runner.getNext().setNext(temp);

                    inserted = true;

                    this.size--;

                    break;
                }

                runner = runner.getNext();
            }

            if (!inserted) {
                System.out.println("Collision!");
                runner.setNext(entry);
                prev = null;
            }

            table[i] = dummy.getNext();
        }

        this.size++;

        if (loadFactor() >= MAX_LOAD_FACTOR) {
            rehash();
        }

        return prev;
    }

    /**
     * Counts how many collisions are present by counting all links after the head
     * of each bucket chain.
     *
     * @return total number of collisions in the table
     */
    public int countCollisions() {
        int collisions = 0;

        for (Entry entry : table) {
            if (entry == null) {
                continue;
            }

            Entry current = entry;

            while (current.getNext() != null) {
                collisions++;

                current = current.getNext();
            }
        }

        return collisions;
    }

    /**
     * Looks up a value by its key.
     *
     * @param key the key to search for
     * @return the value associated with the key, or {@code null} if not found
     */
    public Value get(Key key) {
        if (table[key.hashCode() % table.length] == null) {
            return null;
        } else if (table[key.hashCode() % table.length] != null) { // Iterates through entries that have linked lists
                                                                   // due to collisions and checks if the key matches
                                                                   // any values inside the chaining
            Entry runner = table[key.hashCode() % table.length];

            while (runner != null && !runner.getKey().equals(key)) {
                runner = runner.getNext();
            }

            return (runner == null) ? null : runner.getValue(); // Either no such entry exists for the key or one does
        }

        return null;
    }

    /**
     * Removes the entry for a given key, shrinking the table if it becomes too
     * empty.
     *
     * @param key the key whose entry should be removed
     * @return the value that was removed, or {@code null} if the key was not
     *         present
     */
    public Value remove(Key key) {
        if (table[key.hashCode() % table.length] == null) {
            return null;
        } else if (table[key.hashCode() % table.length] != null) {
            Entry current = new Entry(); // dummy to simplify removal
            current.setNext(table[key.hashCode() % table.length]); // dummy to head reference
            int index = 0;

            while (current.getNext() != null && !current.getNext().getKey().equals(key)) { // Iterates through until
                                                                                           // value is found
                current = current.getNext();
                index++;
            }

            size--;

            Value prev;

            if (index == 0) { // If the object to be removed happens to be the head
                prev = current.getNext().getValue();

                table[key.hashCode() % table.length] = table[key.hashCode() % table.length].getNext(); // remove dummy
                                                                                                       // and apply
                                                                                                       // changes to
                                                                                                       // table

                if (loadFactor() <= MIN_LOAD_FACTOR && table.length > MIN_TABLE_LENGTH) {
                    rehash();
                }

                return prev;
            } else { // All other cases
                prev = current.getNext().getValue();

                current.setNext(current.getNext().getNext());

                if (loadFactor() <= MIN_LOAD_FACTOR && table.length > MIN_TABLE_LENGTH) {
                    rehash();
                }

                return prev;
            }
        }

        return null;
    }

    /**
     * Computes the current load factor of the map.
     *
     * @return the load factor (size / capacity)
     */
    public double loadFactor() {
        return (double) size / table.length;
    }

    /**
     * Resizes the underlying table (growing or shrinking) and re-inserts all
     * existing entries into the new table.
     */
    public void rehash() {
        System.out.println("Rehashing!");
        Entry[] oldTable = table.clone();

        if (loadFactor() >= MAX_LOAD_FACTOR) {
            this.table = new Entry[oldTable.length * 2];
        } else { // Only other possible case being a reduction in size
            this.table = new Entry[Math.max(oldTable.length / 2, MIN_TABLE_LENGTH)];
        }

        this.size = 0; // Recount all size values when rehashing

        for (Entry entry : oldTable) {
            Entry current = entry;

            while (current != null) {
                if (table[current.getKey().hashCode() % table.length] == null) { // Insert rehashed entry into new slot if nothing is already in it
                    // Avoiding changes made after to runner affecting the values already set in by
                    // copying inside
                    table[current.getKey().hashCode() % table.length] = new Entry(current.getKey(), current.getValue());
                } else { // If an entry already exists in the new rehashed position, then the new entry is appended to the end.
                    Entry runner = table[current.getKey().hashCode() % table.length];

                    while (runner.getNext() != null) {
                        runner = runner.getNext();
                    }

                    runner.setNext(new Entry(current.getKey(), current.getValue()));
                }

                this.size++;
                current = current.getNext();
            }
        }
    }

    /**
     * Loads a fixed number of key-value pairs from a CSV file and inserts them
     * into the map.
     *
     * @param file the CSV file containing data
     * @throws FileNotFoundException if the file cannot be opened
     */
    public void loadFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] columns = scanner.nextLine().split(",");

            this.put(new Entry(new Key(Integer.parseInt(columns[0])), new Value(columns[1]))); // Gets only business ID and business name
        }

        scanner.close();
    }

    public Entry[] getTable() {
        return table;
    }

    public void setTable(Entry[] table) {
        this.table = table;
    }

    /**
     * Returns a detailed string view of the map, including bucket contents and
     * basic statistics, useful for debugging.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================== HASHMAP INFO ====================\n");
        sb.append(String.format("Size: %d | Capacity: %d | Load Factor: %.3f | Collisions: %d%n", this.size,
                this.table.length, this.loadFactor(), this.countCollisions()));
        sb.append("-------------------------------------------------------\n");

        for (int i = 0; i < Math.min(table.length, 50); i++) {
            sb.append(String.format("[%02d] ", i));

            if (table[i] == null) {
                sb.append("(empty)\n");
                continue;
            }

            Entry runner = table[i];
            while (runner != null) {
                sb.append(String.format("<k=%s, v=%s>", runner.getKey(), runner.getValue()));
                runner = runner.getNext();

                if (runner != null) {
                    sb.append(" -> ");
                }
            }

            sb.append("\n");
        }

        if (table.length > 50) {
            sb.append(".\n.\n.\n");
        }

        sb.append("======================================================\n");

        return sb.toString();
    }
}
