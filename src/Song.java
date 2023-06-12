import java.util.Objects;
public class Song implements Cloneable{
    private  String name;
    private  String artist;
    private  Genre genre;
    private  int duration;

    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration=duration;

    }
    public String getName() {
        return name;
    }
    public String getTime(){
        int minute=this.duration/60;
        int second=this.duration-(minute*60);
        String time;
        if(second<10){
            time=minute+":0"+second;
        }
        else {
            time=minute+":"+second;
        }
        return time;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString(){
        return this.name+", "+this.artist+", "+this.genre+", "+this.getTime();
    }

    @Override
    public Song clone() {
        try {
            Song clonedSong = (Song) super.clone();
            clonedSong.genre = this.genre;  // Assuming Genre is an immutable class

            // Create new instances of name and artist
            clonedSong.name = new String(this.name);
            clonedSong.artist = new String(this.artist);

            return clonedSong;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Song){
            Song s=(Song) o;
            if (this.name.equals(s.name) && this.artist.equals(s.artist) && Objects.equals(this.genre, s.genre) && this.hashCode()==s.hashCode()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }

    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
