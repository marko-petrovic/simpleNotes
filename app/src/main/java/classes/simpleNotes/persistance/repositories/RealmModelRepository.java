package classes.simpleNotes.persistance.repositories;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import classes.simpleNotes.injection.modules.SchedulersModule;
import classes.simpleNotes.injection.schedulers.BackgroundScheduler;
import classes.simpleNotes.persistance.RealmDatabaseConnector;
import classes.simpleNotes.persistance.RealmDatabasesFactory;
import classes.simpleNotes.persistance.model.ModelObject;
import fj.data.List;
import rx.Observable;
import rx.Scheduler;
import rx.subjects.BehaviorSubject;

/**
 * Provides methods to work with Realm model object's repository
 */
@Singleton
public class RealmModelRepository<T extends ModelObject> implements CreateUpdateDeleteRepository<T> {
    private static final String TEMPORARY_DATABASE_FILE_NAME = "database_with_new_key";
    private static final String DATABASE_BACKUP_FILE_NAME = "bak.realm";
    private static final String DATABASE_BACKUP_FILE_PATH_NAME = "database_bak";

    private static AtomicBoolean initialized = new AtomicBoolean(false);

    protected final BehaviorSubject<Long> updates = BehaviorSubject.create((Long) null);

    protected RealmDatabasesFactory realmDatabasesFactory;
    protected Context context;
    protected RealmDatabaseConnector realmDatabaseConnector;

    private Scheduler backgroundScheduler;

    private long oldSchemaVersion = Long.MIN_VALUE;

    @Inject
    public RealmModelRepository(RealmDatabasesFactory realmDatabasesFactory,
                                Context context,
                                RealmDatabaseConnector realmDatabaseConnector,
                                @BackgroundScheduler @Named(SchedulersModule.BACKGROUND_SCHEDULER)
                                        Scheduler backgroundScheduler) {
        this.realmDatabasesFactory = realmDatabasesFactory;
        this.context = context;
        this.realmDatabaseConnector = realmDatabaseConnector;
        this.backgroundScheduler = backgroundScheduler;
    }

    //TODO implement these! (SunSep17y2017):

    /**
     * @return Realm file name.
     */
    @Override
    public String getRealmFileName() {
        return null;
    }

    /**
     * @return Realm configuration encryption key.
     */
    @Override
    public byte[] getEncryptionKey() throws RealmNotInitializedException {
        return new byte[0];
    }

    /**
     * @return Realm configuration schema version.
     */
    @Override
    public long getSchemaVersion() {
        return 0;
    }

    /**
     * @return Realm configuration old schema version, if there is such.
     */
    @Override
    public long getOldSchemaVersion() {
        return 0;
    }

    /**
     * Deletes current Realm database.
     */
    @Override
    public void deleteRealmDatabase() {

    }

    /**
     * Restores last Realm database in case delete fails.
     */
    @Override
    public void restoreLastRealmDatabase() {

    }

    /**
     * Sets new RealmConfiguration and changes encryption key.
     *
     * @param key
     */
    @Override
    public void setRealmDatabaseKey(byte[] key) {

    }

    /**
     * Changes the encryption key used by the repository. Assumes that repository was already
     * decrypted before (by calling {@link #setRealmDatabaseKey(byte[])}).
     *
     * @param newKey new key to use for the database.
     * @throws RealmNotInitializedException if repository is not yet decrypted.
     * @throws IOException                  if attempt to change key failed due to I/O reasons.
     */
    @Override
    public void changeRealmDatabaseKey(byte[] newKey) throws RealmNotInitializedException, IOException {

    }

    /**
     * Writes copy of default Realm database file to the destination file.
     *
     * @param destination
     */
    @Override
    public void writeCopyTo(File destination) throws IOException {

    }

    /**
     * Replaces local Realm database file with a new one.
     *
     * @param newDBLocalPath
     */
    @Override
    public void replaceRealmDatabase(String newDBLocalPath) throws IOException {

    }

    /**
     * Replaces local Realm database file with a new one with new encryption key.
     *
     * @param newRealmDatabaseLocalPath
     * @param encryptionKey
     */
    @Override
    public void replaceRealmDatabase(String newRealmDatabaseLocalPath, byte[] encryptionKey) throws IOException {

    }

    /**
     * Creates new object in the database.
     *
     * @param object
     */
    @Override
    public void createObject(ModelObject object) {

    }

    /**
     * Updates object in the database.
     *
     * @param object
     * @param incrementVersion
     */
    @Override
    public void updateObject(ModelObject object, boolean incrementVersion) {

    }

    /**
     * Deletes object from the database by its id.
     *
     * @param objectId   object's id.
     * @param modelClass {@link ModelObject} class.
     */
    @Override
    public void deleteObject(String objectId, Class modelClass) {

    }

    /**
     * @return {@link Observable} which produces signals on database updates.
     */
    @Override
    public Observable<?> updates() {
        return null;
    }

    /**
     * Returns {@link Observable} of an object T from Realm database by that object's id value.
     *
     * @param id {@code String} value of object's database id.
     * @return {@link T} object from database by object id.
     */
    @Override
    public Observable<T> getById(String id) {
        return null;
    }

    /**
     * Returns {@link List} of all objects from Realm database by their type T.
     *
     * @return {@link List<T>} of objects from Realm database.
     */
    @Override
    public Observable<List<T>> getAll() {
        return null;
    }

    /**
     * Notifies repository that there was an update to underlying data.
     */
    @Override
    public void triggerUpdate() {

    }
}