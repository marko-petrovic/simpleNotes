package classes.simpleNotes.persistance;

/**
 * {@link DatabaseTransaction} implementation for repositories.
 */
public class RealmModelTransaction implements DatabaseTransaction {

    //TODO basically this is a stub for now.


    /**
     * Commits transaction changes.
     *
     * @param func
     * @return {@code true} or {@code false}.
     */
    @Override
    public boolean commit(TransactionFunction func) {
        return false;
    }
}
