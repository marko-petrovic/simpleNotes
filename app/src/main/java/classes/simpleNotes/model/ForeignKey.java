package classes.simpleNotes.model;

/**
 * Database object's reference.
 */
public class ForeignKey {
    /**
     * Object's class name.
     */
    public final String kind;

    /**
     * Object's id.
     */
    public final String id;

    public ForeignKey(String kind, String id) {
        this.kind = kind;
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ForeignKey that = (ForeignKey) object;

        return kind != null ?
                kind.equals(that.kind) :
                that.kind == null && (
                        id != null ? id.equals(that.id) : that.id == null
                );
    }

    @Override
    public int hashCode() {
        int result = kind != null ? kind.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ForeignKey{" +
                "kind='" + kind + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
