package classes.simpleNotes.persistance.repositories;

import classes.simpleNotes.injection.modules.LocalRepositoriesModule;
import classes.simpleNotes.persistance.model.ModelObject;
import fj.data.List;
import rx.Observable;

/**
 * Provides methods to work with model object's repositories. <br>
 * <p>
 * Each ModelRepository requires Dagger Provider {@link LocalRepositoriesModule},
 * and has to be added in {@link RepositoryProvider}.
 */
public interface ModelRepository<T extends ModelObject> extends RealmRepository {
    /**
     * @return {@link Observable} which produces signals on database updates.
     */
    Observable<?> updates();

    /**
     * Returns {@link Observable} of an object T from Realm database by that object's id value.
     *
     * @param id {@code String} value of object's database id.
     * @return {@link T} object from database by object id.
     */
    Observable<T> getById(String id);

    /**
     * Returns {@link List} of all objects from Realm database by their type T.
     *
     * @return {@link List<T>} of objects from Realm database.
     */
    Observable<List<T>> getAll();

    /**
     * Notifies repository that there was an update to underlying data.
     */
    void triggerUpdate();
}