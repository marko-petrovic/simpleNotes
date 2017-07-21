package classes.simpleNotes.persistance;

import javax.inject.Singleton;

import classes.simpleNotes.persistance.model.ModelObject;
import fj.data.List;

/**
 * Represents transactions for Realm database.
 */
@Singleton
public interface DatabaseTransaction {
    /**
     * Commits database transaction changes.
     *
     * @return {@code true} or {@code false}.
     */
    boolean commit(TransactionFunction func);

    /**
     * Represents a function with {@link TransactionSession} argument.
     */
    interface TransactionFunction {
        void call(TransactionSession session);
    }

    /**
     * Provides create/update/delete operations for {@link DatabaseTransaction}.
     */
    interface TransactionSession {
        /**
         * Creates new {@link ModelObject} in Realm database.
         */
        void create(ModelObject object);

        /**
         * Creates new {@link List} of objects in Realm database.
         *
         * @param objects {@link List} of objects to be created.
         */
        void create(List<? extends ModelObject> objects);

        /**
         * Updates object in the database.
         *
         * @param object {@link ModelObject} that is going to be updated.
         */
        void update(ModelObject object);

        /**
         * Updates {@link List} of objects in the database.
         *
         * @param objects {@link List} of {@link ModelObject}s to get updated.
         */
        void update(List<? extends ModelObject> objects);

        /**
         * Deletes object from Realm database.
         *
         * @param object {@link ModelObject} that is going to be deleted.
         */
        void delete(ModelObject object);

        /**
         * Deletes {@link List} of objects from Realm database.
         *
         * @param objects {@link List} of {@link ModelObject}s that are going to be deleted.
         */
        void delete(List<? extends ModelObject> objects);
    }
}
