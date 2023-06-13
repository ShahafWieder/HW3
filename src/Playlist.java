import java.util.ArrayList ;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable{
    private ArrayList <Song> playList;
    private ArrayList <Song> filteredSongs;
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
        filteredSongs.add(song);
    }


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
                if(currentIndex < filteredSongs.size() &&filteredSongs.get(currentIndex) != null){
                    return true;
                }
                else{
                    filteredSongs = (ArrayList<Song>) playList.clone();
                    return false;
                }
            }
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