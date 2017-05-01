package classes.simpleNotes.persistance.encryption;

import org.jetbrains.annotations.NotNull;

/**
 * Generates keys using encryption.
 */
public interface KeyGenerator {
    /**
     * Generates key for Realm database encryption.
     *
     * @param password {@code String} value for the password.
     * @return {@code byte} array as encrypted Realm key.
     */
    byte[] generateRealmKey(@NotNull String password);
}
