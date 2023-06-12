import java.util.ArrayList ;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList <Song> playList;

    public Playlist() {
        playList = new ArrayList<>();
    }
    public void addSong(Song song) {
        for (Song existingSong : playList) {
            if (existingSong.getName().equals(song.getName()) && existingSong.getArtist().equals(song.getArtist())) {
                throw new SongAlreadyExistsException("SongAlreadyExistsException");
            }
        }
        playList.add(song);
    }


    public boolean removeSong(Song song) {
        for(int i = 0; i< playList.size(); i++) {
            if(playList.get(i).getName().equals(song.getName()) && playList.get(i).getArtist().equals(song.getArtist())) {
                playList.remove(i);
                return true;
            }
        }
        return false;
    }
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

    @Override
    public int hashCode() {
        ArrayList<Song> sortedSongs = new ArrayList<>(playList);
        Collections.sort(sortedSongs, Comparator.comparing(Song::getName));
        return sortedSongs.hashCode();
    }

    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }


    class PlaylistIterator implements Iterator<Song>{
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < playList.size() && playList.get(currentIndex) != null;
            }
            @Override
            public Song next() {
                if(hasNext()) {
                    return playList.get(currentIndex++);
                }
                else{
                    throw new UnsupportedOperationException();
                }
            }
        }

    public void filterArtist(String artist) {
        ArrayList <Song> filteredSongs = new ArrayList<>();
        for (Song song : playList) {
            Song clonedSong = song.clone(); // Create a deep copy of the song using the clone() method
            if (clonedSong.getArtist().equals(artist)) {
                filteredSongs.add(clonedSong);
            }
        }
        playList = filteredSongs; // Replace the original songs list with the filtered list
    }

    public void filterGenre(Song.Genre genre) {
        ArrayList<Song> filteredSongs = new ArrayList<>();
        for (Song song : playList) {
            Song clonedSong = song.clone(); // Create a deep copy of the song using the clone() method
            if (clonedSong.getGenre().equals(genre)) {
                filteredSongs.add(clonedSong);
            }
        }
        playList = filteredSongs; // Replace the original songs list with the filtered list
    }

    public void filterDuration(int duration) {
        ArrayList<Song> filteredSongs = new ArrayList<>();
        for (Song song : playList) {
            Song clonedSong = song.clone(); // Create a deep copy of the song using the clone() method
            if (clonedSong.getDuration() <= duration) {
                filteredSongs.add(clonedSong);
            }
        }
        playList = filteredSongs; // Replace the original songs list with the filtered list
    }



    @Override
    public void setScanningOrder(ScanningOrder order) {
        if (order == ScanningOrder.ADDING) {
            // No need to change the order, as songs are added randomly
            return;
        } else if (order == ScanningOrder.NAME) {
            playList.sort(Comparator.comparing(Song::getName));
        } else if (order == ScanningOrder.DURATION) {
            playList.sort(Comparator.comparingInt(Song::getDuration));
        }
    }

}