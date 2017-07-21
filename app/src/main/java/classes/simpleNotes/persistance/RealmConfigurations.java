package classes.simpleNotes.persistance;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Manages Realm configurations.
 */
public class RealmConfigurations {
    /**
     * Version of Realm model database.
     */
    private static final int MODEL_SCHEMA_VERSION = 1;

    /**
     * Name for the Realm model database configuration.
     */
    private static final String MODEL_DATABASE_CONFIGURATION_NAME = "simplenotes.realm";

    /**
     * Realm configuration for our model database.
     */
    private static RealmConfiguration modelDatabaseConfiguration;

    /**
     * Builds default realm configuration for storing application model data with encryption.
     *
     * @return {@link RealmConfiguration}
     */
    public static RealmConfiguration getModelConfiguration() {
        if (modelDatabaseConfiguration == null) {
            throw new ConfigurationInitializationException(
                    "Configuration is not initialized."
            );
        }

        return modelDatabaseConfiguration;
    }

    /**
     * Initializes Realm model database configuration.
     *
     * @param configuration new {@link RealmConfiguration}.
     */
    public static void setModelConfiguration(RealmConfiguration configuration) {
        modelDatabaseConfiguration = configuration;

        Realm.setDefaultConfiguration(
                modelDatabaseConfiguration
        );
    }

    /**
     * Initializes Realm model database configuration with {@link Context} and encryption key. <br>
     * Uses {@link ModelSchemaModule} to provide database with models from the schema.
     *
     * @param context       Android application {@link Context}.
     * @param encryptionKey encryption key as {@code byte} array.
     */
    public static void setModelConfiguration(Context context, byte[] encryptionKey) {
        Realm.init(context);

        modelDatabaseConfiguration = new RealmConfiguration.Builder()
                .name(MODEL_DATABASE_CONFIGURATION_NAME)
                .schemaVersion(MODEL_SCHEMA_VERSION)
                .modules(new ModelSchemaModule())
                .encryptionKey(encryptionKey)
                .build();

        Realm.setDefaultConfiguration(modelDatabaseConfiguration);
    }

    /**
     * Removes Realm model database configuration.
     */
    public static void removeModelConfiguration() {
        modelDatabaseConfiguration = null;
    }

    /**
     * Thrown when Realm configuration is not initialized
     */
    private static class ConfigurationInitializationException extends RuntimeException {
        ConfigurationInitializationException(String message) {
            super(message);
        }
    }
}