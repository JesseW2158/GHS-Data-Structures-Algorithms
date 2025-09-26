package Unit2.LinkedLists.Playlist;

public class Playlist {
    Track head;
    Track current;

    public Playlist(Track track) {
        head = track;
        current = track;
    }

    public boolean addEnd(Track track) {
        if (head == null) {
            head = track;
            current = track;

            return true;
        }

        Track runner = head;

        while (runner.getNext() != null) {
            runner = runner.getNext();
        }

        runner.setNext(track);
        runner.getNext().setNext(null);

        return true;
    }

    public boolean addFirst(Track track) {
        if (track == null) {
            return false;
        }

        track.setNext(head);
        head = track;

        return true;
    }

    public boolean add(Track newTrack, String prec) {
        if (newTrack == null) {
            return false;
        }

        Track runner = head;
        Track temp = new Track(prec, 0);

        while (runner != null && !runner.equals(newTrack)) {
            runner = runner.getNext();
        }

        if (runner == null) {
            return false;
        }

        newTrack = runner.getNext();
        runner.setNext(newTrack);

        return true;
    }

    public boolean add(Track newTrack, int i) {
        Track runner = head;

        if (i <= 1) {
            return addFirst(newTrack);
        }

        while (runner != null && i > 1) {
            runner = runner.getNext();
            i--;
        }

        if (runner == null) {
            return false;
        }

        newTrack.setNext(runner.getNext());
        runner.setNext(newTrack);

        return true;
    }

    public int size() {
        int size = 0;
        Track runner = head;

        if(runner != null) {
            size++;
        }

        while(runner.getNext() != null) {
            size++;
            runner = runner.getNext();
        }
        
        return size;
    }

    public Track getFront() {
        return head;
    }

    public Track getBack() {
        Track runner = head;

        while(runner.getNext() != null) {
            runner = runner.getNext();
        }

        return runner;
    }

    public Track remove() {
        if(head == null) {
            return null;
        }

        Track temp = head;
        head = head.getNext();

        return temp;
    }

    public void shuffle() {
        if(this.size() == 0) {
            return;
        }

        Track temp;
        int random = (int) (Math.random() * this.size());
        Track runner = head;

        for(int i = 0; i < random - 1; i++) {
            runner = runner.getNext();
        }

        temp = runner.getNext();
        Track tempRunner = temp;
        runner.setNext(runner.getNext().getNext()); // drops list by 1

        while(this.size() > 1) {
            random = (int) (Math.random() * this.size());
            System.out.println(random);

            for(int i = 0; i < random - 1; i++) {
                runner = runner.getNext();
            }

            tempRunner.setNext(runner.getNext());
            tempRunner = tempRunner.getNext();
            runner.setNext(runner.getNext().getNext());
        }

        tempRunner.setNext(runner);

        this.head = temp;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "Empty List";
        }

        String output = "";
        Track runner = head;

        while (runner != null) {
            output += runner + "\n";
            runner = runner.getNext();
        }

        return output + "Current: " + current;
    }
}
