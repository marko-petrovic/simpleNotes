package classes.simpleNotes.persistance;

import java.io.File;
import java.io.IOException;

import classes.simpleNotes.persistance.model.AutoIdObject;
import fj.data.List;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Realm database wrapper class.
 *
 * @see Realm
 */
public class RealmDatabase {

    protected final Realm realm;

    /**
     * Creates new instance of {@link RealmDatabase}.
     *
     * @param realmConfiguration {@link RealmConfiguration} used for realm database instantiation.
     */
    public RealmDatabase(RealmConfiguration realmConfiguration) {
        if (realmConfiguration != null) {
            this.realm = Realm.getInstance(realmConfiguration);
        } else {
            this.realm = Realm.getDefaultInstance();
        }
    }

    /**
     * @see Realm#beginTransaction()
     */
    public void beginTransaction() {
        realm.beginTransaction();
    }

    /**
     * @see Realm#commitTransaction()
     */
    public void commitTransaction() {
        realm.commitTransaction();
    }

    /**
     * @see Realm#cancelTransaction()
     */
    public void cancelTransaction() {
        realm.cancelTransaction();
    }

    /**
     * @see Realm#isInTransaction()
     */
    public boolean isInTransaction() {
        return realm.isInTransaction();
    }

    /**
     * @see Realm#where(Class)
     */
    public <E extends RealmObject> RealmQuery<E> where(Class<E> clazz) {
        return realm.where(clazz);
    }

    /**
     * @see Realm#copyToRealmOrUpdate(RealmModel)
     */
    public void copyToRealmOrUpdate(RealmObject object) {
        realm.copyToRealmOrUpdate(object);
    }

    /**
     * @see Realm#copyToRealm(RealmModel)
     */
    public void copyToRealm(RealmObject object) {
        realm.copyToRealm(object);
    }

    /**
     * Instantiates and adds a new object to the Realm.
     *
     * @param clazz the Class of the object to create.
     * @return the new object extending {@link RealmObject}.
     */
    public <E extends RealmObject> RealmObject createObject(Class<E> clazz) {
        return realm.createObject(clazz);
    }

    /**
     * Copies a collection of RealmObjects to the Realm instance and returns their copy.
     *
     * @param objects the Realm objects to copy to the Realm.
     * @param <E>     object extending {@link RealmObject}.
     * @return a list of the the converted RealmObjects that all has their properties managed by the Realm.
     * @see Realm#copyToRealm(RealmModel)
     */
    public <E extends RealmObject> java.util.List<E> copyToRealm(Iterable<E> objects) {
        return realm.copyToRealm(objects);
    }

    /**
     * @see Realm#close()
     */
    public void close() {
        realm.close();
    }

    /**
     * Returns all objects as {@link List}.
     *
     * @param clazz of requested object.
     * @return {@link List} of all {@link RealmObject}s stored in the database.
     */
    public <E extends RealmObject> List<E> allObjects(Class<E> clazz) {
        return List.list(realm.where(clazz).findAll());
    }

    /**
     * @see Realm#getConfiguration()
     */
    public RealmConfiguration getConfiguration() {
        return realm.getConfiguration();
    }

    /**
     * Saves encrypted database to the file.
     *
     * @see Realm#writeEncryptedCopyTo(File, byte[])
     */
    public void writeEncryptedCopyTo(File file, byte[] key) throws IOException {
        realm.writeEncryptedCopyTo(file, key);
    }

    /**
     * Returns Object extending RealmObject for given id.
     *
     * @param clazz object class.
     * @param id    object's Realm database id.
     * @return {@link E} object from Realm database by id.
     */
    public <E extends RealmObject> RealmObject getById(Class<E> clazz, String id) {
        if (id != null) {
            RealmResults<E> results = realm
                    .where(clazz)
                    .equalTo(AutoIdObject.DEFAULT_ID_FIELD_NAME, id)
                    .findAll();

            return results.size() > 0 ? results.get(0) : null;
        }

        return null;
    }
}