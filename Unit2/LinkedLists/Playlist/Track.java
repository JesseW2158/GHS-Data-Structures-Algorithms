package Unit2.LinkedLists.Playlist;

public class Track {
    private String title;
    private String artist;
    private int duration;
    private Track next;

    public Track(String title, String artist, int duration, Track next) {
        super();
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.next = next;
    }

    public Track(String title, int duration) {
        this(title, "Artist", duration, null);
    }

    public boolean equals(Track other) {
        if(other == null) {
            return false;
        }

        return this.title == other.title;
    }

    @Override
    public String toString() {
        return "Track [title=" + title + ", artist=" + artist + ", duration=" + duration + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Track getNext() {
        return next;
    }

    public void setNext(Track next) {
        this.next = next;
    }    
}
