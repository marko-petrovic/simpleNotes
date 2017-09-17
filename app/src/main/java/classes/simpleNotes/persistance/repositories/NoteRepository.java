package classes.simpleNotes.persistance.repositories;

import classes.simpleNotes.persistance.RealmDatabase;
import classes.simpleNotes.persistance.model.NoteModel;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func2;

/**
 * Provides methods to work with {@link NoteModel} objects repository.
 */
public interface NoteRepository extends ModelRepository<NoteModel> {
    /**
     * Called to retrieve all existing notes and then do some additional list transformation. <br>
     * In this context, transformation could be filtering, mapping, or else. The point is that in cases
     * of large result lists, we should better use RealmResults and Realm thread for this operations,
     * than Android's resources.
     *
     * @param transformerFunction Function applied to result list - performed on the Realm reader thread.
     * @return Returns all existing notes and applying the transformation inside UseCase but on Realm thread.
     */
    <T> Observable<T> getAllNotes(
            Func2<RealmDatabase, RealmResults<NoteModel>, T> transformerFunction
    );
}