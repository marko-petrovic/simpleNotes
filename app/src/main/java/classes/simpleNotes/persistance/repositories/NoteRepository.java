package classes.simpleNotes.persistance.repositories;

import classes.simpleNotes.persistance.model.NoteModel;

/**
 * Provides methods to work with {@link NoteModel} objects repository.
 */
public interface NoteRepository extends ModelRepository<NoteModel> {
    //TODO for now, we need only getAll and getById.
}
