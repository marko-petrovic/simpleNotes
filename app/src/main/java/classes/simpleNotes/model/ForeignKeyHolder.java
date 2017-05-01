package classes.simpleNotes.model;

/**
 * Database object's reference holder.
 */
public class ForeignKeyHolder {
    /**
     * {@link ForeignKey} object.
     */
    public final ForeignKey key;

    public ForeignKeyHolder(ForeignKey key) {
        this.key = key;
    }
}
