package classes.simpleNotes.persistance.repositories;

import classes.simpleNotes.persistance.model.ModelObject;

/**
 * Provides methods to work with model object's repositories. <br>
 * Particularly: creating object, updating object, deleting object. <p>
 * Currently, this methods return void. We could possibly return something else.
 */
public interface CreateUpdateDeleteRepository<T extends ModelObject> extends ModelRepository<T> {
    /**
     * Creates new object in the database.
     */
    void createObject(ModelObject object);

    /**
     * Updates object in the database.
     */
    void updateObject(ModelObject object, boolean incrementVersion);

    /**
     * Deletes object from the database by its id.
     *
     * @param objectId         object's id.
     * @param modelClass {@link ModelObject} class.
     */
    void deleteObject(String objectId, Class modelClass);
}