/**
 * The OrderedSongIterable interface represents an iterable collection of songs that can be iterated in a specific order.
 * It extends the Iterable interface with the type parameter Song.
 */
public interface OrderedSongIterable extends Iterable<Song> {
    /**
     * Sets the scanning order for iterating over the songs.
     *
     * @param order the scanning order to set
     */
    public void setScanningOrder(ScanningOrder order);
}
