import java.util.ArrayList ;

public class Playlist implements Cloneable {
    private ArrayList <Song> songs;

    public Playlist() {
        songs = new ArrayList<>();
    }
    public void addSong(Song song) {
        for(int i = 0; i< songs.size(); i++) {
            if(songs.get(i).getName().equals(song.getName()) && songs.get(i).getArtist().equals(song.getArtist())) {
                throw new SongAlreadyExistsException("SongAlreadyExistsException");
            }
            else{
                songs.add(song);
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
        String str = "[";
        for(int i = 0; i< songs.size(); i++) {
            str += songs.get(i).toString() + ",";
        }
        str += "]";
        return str;
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
}