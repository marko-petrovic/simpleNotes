package classes.simpleNotes.persistance;

import classes.simpleNotes.persistance.model.NoteModel;
import io.realm.annotations.RealmModule;

/**
 * Represents Realm database schema.
 */
@RealmModule(classes = {
        NoteModel.class
})

public class ModelSchemaModule {
}
