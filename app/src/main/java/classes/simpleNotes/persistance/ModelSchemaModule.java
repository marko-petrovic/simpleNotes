package classes.simpleNotes.persistance;

import classes.simpleNotes.persistance.model.NoteModel;
import io.realm.annotations.RealmModule;

/**
 * Represents Realm database schema. <p>
 * Each model should be added into classes, followed by incrementing realm model schema version.
 */
@RealmModule(classes = {
        NoteModel.class
})

public class ModelSchemaModule {
}