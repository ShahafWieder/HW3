import java.util.ArrayList ;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * The Playlist class represents a collection of songs.
 * It implements the Cloneable, FilteredSongIterable, and OrderedSongIterable interfaces.
 */
public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList <Song> playList;
    private ArrayList <Song> filteredSongs;
    /**
     * Constructs a new Playlist object.
     * Initializes the internal playlist and filtered songs list.
     */
    public Playlist() {
        playList = new ArrayList<>();
        filteredSongs=(ArrayList<Song>) playList.clone();
    }
    /**
     * Adds a song to the playlist.
     * Throws a SongAlreadyExistsException if the song already exists in the playlist.
     *
     * @param song the song to add
     * @throws SongAlreadyExistsException if the song already exists in the playlist
     */
    public void addSong(Song song) {
        for (Song existingSong : playList) {
            if (existingSong.getName().equals(song.getName()) && existingSong.getArtist().equals(song.getArtist())) {
                throw new SongAlreadyExistsException("SongAlreadyExistsException");
            }
        }
        playList.add(song);
        filteredSongs.add(song);
    }


    /**
     * Removes a song from the playlist.
     *
     * @param song the song to remove
     * @return true if the song was found and removed, false otherwise
     */
    public boolean removeSong(Song song) {
        for(int i = 0; i< playList.size(); i++) {
            if(playList.get(i).getName().equals(song.getName()) && playList.get(i).getArtist().equals(song.getArtist())) {
                playList.remove(i);
                filteredSongs.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the string representation of the playlist.
     *
     * @return the string representation of the playlist
     */
    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder("[");
        for (int i = 0; i < playList.size(); i++) {
            if (i > 0) {
                strBuilder.append(", ");
            }
            strBuilder.append("(").append(playList.get(i).toString()).append(")");
        }
        strBuilder.append("]");
        return strBuilder.toString();
    }


    /**
     * Creates and returns a deep copy of the Playlist object.
     *
     * @return a clone of the Playlist object
     */
    public Playlist clone() {
        try {
            Playlist clonedPlaylist = (Playlist) super.clone();
            clonedPlaylist.playList = new ArrayList<>();
            for(int i = 0; i< playList.size(); i++) {
                clonedPlaylist.playList.add(playList.get(i).clone());
            }
            return clonedPlaylist;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    /**
     * Checks if the Playlist object is equal to another object.
     * Two Playlist objects are considered equal if they have the same songs in the same order.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) o;
        if (this.playList.size() != other.playList.size()) {
            return false;
        }

        // Sort both playlists before comparing
        ArrayList<Song> sortedThis = new ArrayList<>(this.playList);
        ArrayList<Song> sortedOther = new ArrayList<>(other.playList);
        Collections.sort(sortedThis, Comparator.comparing(Song::getName));
        Collections.sort(sortedOther, Comparator.comparing(Song::getName));

        // Compare each song in the sorted playlists
        for (int i = 0; i < sortedThis.size(); i++) {
            if (!sortedThis.get(i).equals(sortedOther.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the hash code of the Playlist object.
     * The hash code is calculated based on the sorted list of songs.
     *
     * @return the hash code of the Playlist object
     */
    @Override
    public int hashCode() {
        ArrayList<Song> sortedSongs = new ArrayList<>(playList);
        Collections.sort(sortedSongs, Comparator.comparing(Song::getName));
        return sortedSongs.hashCode();
    }

    /**
     * Returns an iterator over the filtered songs in the playlist.
     *
     * @return an iterator over the filtered songs in the playlist
     */
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }


    /**
     * Implements the Iterator interface for iterating over the filtered songs in the playlist.
     */
    class PlaylistIterator implements Iterator<Song>{
            private int currentIndex = 0;
        /**
         * Checks if there are more songs in the playlist.
         *
         * @return true if there are more songs, false otherwise
         */
            @Override
            public boolean hasNext() {
                if(currentIndex < filteredSongs.size() &&filteredSongs.get(currentIndex) != null){
                    return true;
                }
                else{
                    filteredSongs = (ArrayList<Song>) playList.clone();
                    return false;
                }
            }
        /**
         * Returns the next song in the playlist.
         *
         * @return the next song in the playlist
         * @throws UnsupportedOperationException if there are no more songs
         */
            @Override
            public Song next() {
                if(hasNext()) {
                    return filteredSongs.get(currentIndex++);
                }
                else{
                    throw new UnsupportedOperationException();
                }
            }
        }

    /**
     * Filters the songs in the playlist by the given artist.
     *
     * @param artist the artist to filter by
     */
        public void filterArtist(String artist){
        if(artist == null){
            return;
        }
        else {
            int i = 0;
            while (i < filteredSongs.size()) {
                if (!filteredSongs.get(i).getArtist().equals(artist)) {
                    filteredSongs.remove(filteredSongs.get(i));
                } else {
                    i++;
                }
            }
        }
        }

    /**
     * Filters the songs in the playlist by the given genre.
     *
     * @param genre the genre to filter by
     */
        public void filterGenre(Song.Genre genre){
        if(genre == null){
            return;
        }
        else {
            int i = 0;
            while (i < filteredSongs.size()) {
                if (!filteredSongs.get(i).getGenre().equals(genre)) {
                    filteredSongs.remove(filteredSongs.get(i));
                } else {
                    i++;
                }
            }
        }
        }

    /**
     * Filters the songs in the playlist by the given duration.
     *
     * @param duration the duration to filter by
     */
        public void filterDuration(int duration){
            int i = 0;
            while (i < filteredSongs.size()) {
                if (!(filteredSongs.get(i).getDuration() <= duration)) {
                    filteredSongs.remove(filteredSongs.get(i));
                } else {
                    i++;
                }
            }
        }

    /**
     * Sets the scanning order for iterating over the playlist.
     *
     * @param order the scanning order
     */
        public void setScanningOrder(ScanningOrder order){
            if(order.equals(ScanningOrder.ADDING)){
                return;
            }
            else if(order.equals(ScanningOrder.NAME)){
                Collections.sort(filteredSongs, Comparator.comparing(Song::getName).thenComparing(Song::getArtist));//: thenComparing.Song::artist
            }
            else if(order.equals(ScanningOrder.DURATION)){
                Collections.sort(filteredSongs, Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist));
            }


        }

}