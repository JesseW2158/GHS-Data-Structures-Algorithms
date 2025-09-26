package Unit2.LinkedLists;

import Unit2.LinkedLists.Playlist.Playlist;
import Unit2.LinkedLists.Playlist.Track;

public class Link {
    public static void main(String[] args) {
        Playlist pop = new Playlist(new Track("Manchild", 130));

        pop.addFirst(new Track("Love Story", 255));
        pop.addFirst(new Track("Dancing Queen", 200));
        pop.addFirst(new Track("Happy Face", 200));
        System.out.println(pop);
        System.out.println();
        pop.shuffle();
        System.out.println(pop);
    }
}
