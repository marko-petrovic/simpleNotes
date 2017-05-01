package classes.simpleNotes.persistance;

import javax.inject.Inject;

import classes.simpleNotes.model.ModelObject;
import classes.simpleNotes.model.NoteModel;

/**
 * Provides access to Realm model object repositories.
 */
public class RepositoryProvider {
    private final ModelRepository<NoteModel> noteModelRepository;

    @Inject
    public RepositoryProvider(ModelRepository<NoteModel> noteModelRepository) {
        this.noteModelRepository = noteModelRepository;
    }

    /**
     * Returns Realm model repository based on type of the model class.
     *
     * @return instance of {@link ModelRepository} associated with {@link T} type.
     */
    @SuppressWarnings("unchecked")
    public <T extends ModelObject> ModelRepository<T> getRepository(Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "NoteModel":
                return (ModelRepository<T>) noteModelRepository;

            default:
                return null;
        }
    }
}
