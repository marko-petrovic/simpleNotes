package classes.simpleNotes.persistance.model;

import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Represents Note model for Realm database.
 */
public class NoteModel extends ModelObject {
    @Ignore
    String kind = getClass().getSimpleName();

    @PrimaryKey
    @Required
    private String id;
    @Required
    private Double createdTimeStamp;
    @Required
    private Double updatedTimeStamp;

    /**
     * Title of a note.
     */
    @Required
    private String noteTitle;

    /**
     * Text body of a note.
     */
    @Required
    private String noteText;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Double getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public void setCreatedTimeStamp(Double createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    @Override
    public Double getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    @Override
    public void setUpdatedTimeStamp(Double updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}