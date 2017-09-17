package classes.simpleNotes.persistance;

import javax.inject.Inject;

/**
 * Creates instance of Realm database. Can be used for as many instances as we might need.
 */
public class RealmDatabasesFactory {
    @Inject
    public RealmDatabasesFactory() {
    }

    /**
     * Initialization of Realm database that we will use for our model objects.<br>
     * Realm database instances must be instantiated in the same thread where they are used.
     *
     * @return new instance of {@link RealmDatabase}.
     */
    public RealmDatabase createModelDatabase() {
        return new RealmDatabase(
                RealmConfigurations.getModelConfiguration()
        );
    }
}