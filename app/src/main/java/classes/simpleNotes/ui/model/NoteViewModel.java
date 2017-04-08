package classes.simpleNotes.ui.model;

/**
 * Represents view model of a note.
 */
public class NoteViewModel implements IViewModel {
    /**
     * Title of a note.
     */
    public final String noteTitle;
    /**
     * Text body of a note.
     */
    public final String noteText;

    private NoteViewModel(Builder builder) {
        noteTitle = builder.noteTitle;
        noteText = builder.noteText;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        NoteViewModel that = (NoteViewModel) object;

        return noteTitle != null ?
                noteTitle.equals(that.noteTitle) :
                that.noteTitle == null && (noteText != null ?
                        noteText.equals(that.noteText) :
                        that.noteText == null);
    }

    @Override
    public int hashCode() {
        int result = noteTitle != null ? noteTitle.hashCode() : 0;
        result = 31 * result + (noteText != null ? noteText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NoteViewModel{" +
                "noteTitle='" + noteTitle + '\'' +
                ", noteText='" + noteText + '\'' +
                '}';
    }

    /**
     * Builder for this view model.
     */
    public static final class Builder {
        private String noteTitle = "";
        private String noteText = "";

        public Builder() {
        }

        public Builder(NoteViewModel copy) {
            this.noteTitle = copy.noteTitle;
            this.noteText = copy.noteText;
        }

        public Builder noteTitle(String value) {
            noteTitle = value;
            return this;
        }

        public Builder noteText(String value) {
            noteText = value;
            return this;
        }

        public NoteViewModel build() {
            return new NoteViewModel(this);
        }
    }
}
