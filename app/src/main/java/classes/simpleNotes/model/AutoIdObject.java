package classes.simpleNotes.model;

/**
 * Provides methods to get and set Object's id.
 */
public interface AutoIdObject {

    String DEFAULT_ID_FIELD_NAME = "id";

    /**
     * @return {@code String} value of an Object id.
     */
    String getId();

    /**
     * Sets object id.
     *
     * @param id {@code String} value of an id to set to an Object.
     */
    void setId(String id);
}
