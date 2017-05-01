package classes.simpleNotes.persistance;

import javax.inject.Inject;

/**
 * Creates instance of Realm database. Can be used for as many instances as we need.
 */
public class RealmDatabasesFactory {
    @Inject
    public RealmDatabasesFactory() {
    }

    /**
     * Realm database instances must be instantiated in the same thread where they are used.
     *
     * @return new instance of {@link RealmDatabase} model database.
     */
    public RealmDatabase createModelDatabase() {
        return new RealmDatabase(
                RealmConfigurations.getModelConfiguration()
        );
    }
}
