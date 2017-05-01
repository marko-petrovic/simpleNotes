package classes.simpleNotes.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Represents base parent class for all model objects.
 */
public class ModelObject extends RealmObject implements AutoIdObject, Serializable {
    private String id;
    private Double createdTimeStamp;
    private Double updatedTimeStamp;

    public ModelObject() {
    }

    public ModelObject(
            String id,
            Double createdTimeStamp,
            Double updatedTimeStamp) {
        this.id = id;
        this.createdTimeStamp = createdTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Double getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Double createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Double getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(Double updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ModelObject that = (ModelObject) object;

        return id != null ?
                id.equals(that.id) :
                that.id == null && (
                        createdTimeStamp != null ?
                                createdTimeStamp.equals(that.createdTimeStamp) :
                                that.createdTimeStamp == null && (
                                        updatedTimeStamp != null ?
                                                updatedTimeStamp.equals(that.updatedTimeStamp) :
                                                that.updatedTimeStamp == null
                                )
                );
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        result = 31 * result + (updatedTimeStamp != null ? updatedTimeStamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ModelObject{" +
                "id='" + id + '\'' +
                ", createdTimeStamp=" + createdTimeStamp +
                ", updatedTimeStamp=" + updatedTimeStamp +
                '}';
    }
}
