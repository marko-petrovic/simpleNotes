package classes.simpleNotes.persistance.repositories;

import java.io.File;
import java.io.IOException;

import io.realm.RealmConfiguration;

/**
 * Provides basic set of methods to work with Realm model database object's repository.
 */
public interface RealmRepository {
    /**
     * @return Realm file name.
     */
    String getRealmFileName();

    /**
     * @return Realm configuration encryption key.
     */
    byte[] getEncryptionKey() throws RealmNotInitializedException;

    /**
     * @return Realm configuration schema version.
     */
    long getSchemaVersion();

    /**
     * @return Realm configuration old schema version, if there is such.
     */
    long getOldSchemaVersion();

    /**
     * Deletes current Realm database.
     */
    void deleteRealmDatabase();

    /**
     * Restores last Realm database in case delete fails.
     */
    void restoreLastRealmDatabase();

    /**
     * Sets new {@link RealmConfiguration} and changes encryption key.
     */
    void setRealmDatabaseKey(byte[] key);

    /**
     * Changes the encryption key used by the repository. Assumes that repository was already
     * decrypted before (by calling {@link #setRealmDatabaseKey(byte[])}).
     *
     * @param newKey new key to use for the database.
     * @throws RealmNotInitializedException if repository is not yet decrypted.
     * @throws IOException                  if attempt to change key failed due to I/O reasons.
     */
    void changeRealmDatabaseKey(byte[] newKey) throws RealmNotInitializedException, IOException;

    /**
     * Writes copy of default Realm database file to the destination file.
     */
    void writeCopyTo(File destination) throws IOException;

    /**
     * Replaces local Realm database file with a new one.
     */
    void replaceRealmDatabase(String newDBLocalPath) throws IOException;

    /**
     * Replaces local Realm database file with a new one with new encryption key.
     */
    void replaceRealmDatabase(String newRealmDatabaseLocalPath, byte[] encryptionKey) throws IOException;

    /**
     * Thrown when Realm repository is not initialized.
     */
    class RealmNotInitializedException extends RuntimeException {
    }
}