import java.util.Objects;
/**
 * The Song class represents a musical song.
 * It implements the Cloneable interface.
 */
public class Song implements Cloneable{
    private  String name;
    private  String artist;
    private  Genre genre;
    private  int duration;

    /**
     * Constructs a new Song object with the specified name, artist, genre, and duration.
     *
     * @param name     the name of the song
     * @param artist   the artist of the song
     * @param genre    the genre of the song
     * @param duration the duration of the song in seconds
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration=duration;

    }
    /**
     * Returns the name of the song.
     *
     * @return the name of the song
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the formatted time representation of the song duration (in mm:ss format).
     *
     * @return the formatted time representation of the song duration
     */
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

    /**
     * Returns the artist of the song.
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the genre of the song.
     *
     * @return the genre of the song
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Returns the duration of the song in seconds.
     *
     * @return the duration of the song in seconds
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Sets the duration of the song.
     *
     * @param duration the duration of the song in seconds
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the string representation of the song.
     *
     * @return the string representation of the song
     */
    @Override
    public String toString(){
        return this.name+", "+this.artist+", "+this.genre+", "+this.getTime();
    }

    /**
     * Creates and returns a deep copy of the Song object.
     *
     * @return a clone of the Song object
     */
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
    /**
     * Checks if the Song object is equal to another object.
     * Two Song objects are considered equal if they have the same name, artist, genre, and hash code.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
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

    /**
     * Returns the hash code of the Song object.
     * The hash code is calculated based on the name and artist fields.
     *
     * @return the hash code of the Song object
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }

    /**
     * The Genre enum represents the genre of a song.
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
