// Step 1: Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}



// Step 5: Concrete Iterator
class SongIterator implements Iterator<Song> {
    private Song[] songs;
    private int position = 0;

    public SongIterator(Song[] songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.length && songs[position] != null;
    }

    @Override
    public Song next() {
        return songs[position++];
    }
}

// Step 3: Song class
class Song {
    private String title;
    private String singer;

    public Song(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    @Override
    public String toString() {
        return title + " by " + singer;
    }
}
// Step 2: Aggregate interface (Collection interface)
interface SongCollection {
    Iterator<Song> createIterator();
}
// Step 4: Concrete collection
class SongList implements SongCollection {
    private Song[] songs;
    private int index = 0;

    public SongList(int size) {
        songs = new Song[size];
    }

    public void addSong(Song song) {
        if (index < songs.length) {
            songs[index++] = song;
        }
    }

    @Override
    public Iterator<Song> createIterator() {
        return new SongIterator(songs);
    }
}



// Step 6: Client
public class Main {
    public static void main(String[] args) {
        SongList playlist = new SongList(5);

        playlist.addSong(new Song("Shape of You", "Ed Sheeran"));
        playlist.addSong(new Song("Blinding Lights", "The Weeknd"));
        playlist.addSong(new Song("Levitating", "Dua Lipa"));

        Iterator<Song> iterator = playlist.createIterator();

        System.out.println("Playlist:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
