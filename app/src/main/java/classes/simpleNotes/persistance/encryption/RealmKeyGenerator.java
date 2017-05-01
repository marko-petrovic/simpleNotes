package classes.simpleNotes.persistance.encryption;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import tgio.rncryptor.RNCryptorNative;

/**
 * Generates Realm key using RNCryptorNative lib.
 */
public class RealmKeyGenerator implements KeyGenerator {
    @Inject
    public RealmKeyGenerator() {
    }

    /**
     * Generates key for Realm database encryption.
     *
     * @return key as {@code byte} array generated from password.
     */
    @Override
    public byte[] generateRealmKey(@NotNull String password) {
        RNCryptorNative cryptor = new RNCryptorNative();

        return cryptor.encrypt(password, password);
    }
}
