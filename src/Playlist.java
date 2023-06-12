import java.util.ArrayList ;
import java.util.Comparator;
import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList <Song> songs;

    public Playlist() {
        songs = new ArrayList<>();
    }
    public void addSong(Song song) {
        for (Song existingSong : songs) {
            if (existingSong.getName().equals(song.getName()) && existingSong.getArtist().equals(song.getArtist())) {
                throw new SongAlreadyExistsException("SongAlreadyExistsException");
            }
        }
        songs.add(song);
    }


    public boolean removeSong(Song song) {
        for(int i = 0; i< songs.size(); i++) {
            if(songs.get(i).getName().equals(song.getName()) && songs.get(i).getArtist().equals(song.getArtist())) {
                songs.remove(i);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder("[");
        for (int i = 0; i < songs.size(); i++) {
            if (i > 0) {
                strBuilder.append(", ");
            }
            strBuilder.append("(").append(songs.get(i).toString()).append(")");
        }
        strBuilder.append("]");
        return strBuilder.toString();
    }


    public Playlist clone() {
        try {
            Playlist clonedPlaylist = (Playlist) super.clone();
            clonedPlaylist.songs = new ArrayList<>();
            for(int i = 0; i< songs.size(); i++) {
                clonedPlaylist.songs.add(songs.get(i).clone());
            }
            return clonedPlaylist;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Playlist) {
            Playlist p = (Playlist) o;
            if (this.songs.size() == p.songs.size()) {
                for (int i = 0; i < songs.size(); i++) {
                    int flag  = 0;
                    for (int j = 0; j < songs.size(); j++) {//checks if the song in the i place is in the other playlist
                        if (this.songs.get(i).equals(p.songs.get(j))) {
                            flag =1;
                        }
                    }
                    if(flag==0){return  false;}// is the song in both playlists?
                }
                 return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for(int i = 0; i< songs.size(); i++) {
            hash = 31 * hash + songs.hashCode();
        }
        return hash;
    }
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }


    class PlaylistIterator implements Iterator<Song>{
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < songs.size() && songs.get(currentIndex) != null;
            }
            @Override
            public Song next() {
                if(hasNext()) {
                    return songs.get(currentIndex++);
                }
                else{
                    throw new UnsupportedOperationException();
                }
            }
        }

    public void filterArtist(String artist) {
        ArrayList<Song> filteredSongs = new ArrayList<>();
        for (Song song : songs) {
            Song clonedSong = song.clone(); // Create a deep copy of the song using the clone() method
            if (clonedSong.getArtist().equals(artist)) {
                filteredSongs.add(clonedSong);
            }
        }
        songs = filteredSongs; // Replace the original songs list with the filtered list
    }

    public void filterGenre(Song.Genre genre) {
        ArrayList<Song> filteredSongs = new ArrayList<>();
        for (Song song : songs) {
            Song clonedSong = song.clone(); // Create a deep copy of the song using the clone() method
            if (clonedSong.getGenre().equals(genre)) {
                filteredSongs.add(clonedSong);
            }
        }
        songs = filteredSongs; // Replace the original songs list with the filtered list
    }

    public void filterDuration(int duration) {
        ArrayList<Song> filteredSongs = new ArrayList<>();
        for (Song song : songs) {
            Song clonedSong = song.clone(); // Create a deep copy of the song using the clone() method
            if (clonedSong.getDuration() <= duration) {
                filteredSongs.add(clonedSong);
            }
        }
        songs = filteredSongs; // Replace the original songs list with the filtered list
    }



    @Override
    public void setScanningOrder(ScanningOrder order) {
        if (order == ScanningOrder.ADDING) {
            // No need to change the order, as songs are added randomly
            return;
        } else if (order == ScanningOrder.NAME) {
            songs.sort(Comparator.comparing(Song::getName));
        } else if (order == ScanningOrder.DURATION) {
            songs.sort(Comparator.comparingInt(Song::getDuration));
        }
    }

}