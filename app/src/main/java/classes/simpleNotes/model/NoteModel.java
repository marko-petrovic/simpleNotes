package classes.simpleNotes.model;

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
}
