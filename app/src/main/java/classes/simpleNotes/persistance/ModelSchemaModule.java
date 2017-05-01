package classes.simpleNotes.persistance;

import classes.simpleNotes.model.NoteModel;
import io.realm.annotations.RealmModule;

/**
 * Represents Realm database schema.
 */
@RealmModule(classes = {
        NoteModel.class
})

public class ModelSchemaModule {
}
