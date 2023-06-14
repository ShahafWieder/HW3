/**
 * The FilteredSongIterable interface represents an iterable collection of songs that can be filtered based on specific criteria.
 * It extends the Iterable interface with the type parameter Song.
 */
public interface FilteredSongIterable extends Iterable<Song>{
    /**
     * Filters the songs by the specified artist.
     *
     * @param artist the artist to filter by
     */
    public void filterArtist(String artist);
    /**
     * Filters the songs by the specified genre.
     *
     * @param genre the genre to filter by
     */
    public void filterGenre(Song.Genre genre);
    /**
     * Filters the songs by the specified duration.
     *
     * @param duration the duration to filter by
     */
    public void filterDuration(int duration);
}
