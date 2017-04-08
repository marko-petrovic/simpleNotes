package classes.simpleNotes.ui.model;

import static classes.simpleNotes.ui.model.NoteValidationViewModel.NewNoteValidationResult.NEW_NOTE_VALID;

/**
 * Represents view model of a new note validation.
 */
public class NoteValidationViewModel implements IViewModel {
    /**
     * Validation result as enum.
     */
    public enum NewNoteValidationResult {
        /**
         * New note is valid and can be saved.
         */
        NEW_NOTE_VALID,
        /**
         * New note is not valid, title is missing.
         */
        NEW_NOTE_NOT_VALID_MISSING_TITLE,
        /**
         * New note is not valid, body message is missing.
         */
        NEW_NOTE_NOT_VALID_MISSING_BODY,
        /**
         * New note is not valid, both title and body message are missing.
         */
        NEW_NOTE_NOT_VALID_MISSING_BOTH
    }

    /**
     * Message info that goes together with enum validation result.
     */
    public final String validationMessage;

    /**
     * Validation result.
     */
    public final NewNoteValidationResult validationResult;

    private NoteValidationViewModel(Builder builder) {
        validationMessage = builder.validationMessage;
        validationResult = builder.validationResult;
    }

    public static final class Builder {
        private NewNoteValidationResult validationResult = NEW_NOTE_VALID;
        private String validationMessage = "";

        public Builder() {
        }

        public Builder(NoteValidationViewModel copy) {
            this.validationResult = copy.validationResult;
            this.validationMessage = copy.validationMessage;
        }

        public Builder validationResult(NewNoteValidationResult value) {
            validationResult = value;
            return this;
        }

        public Builder validationMessage(String value) {
            validationMessage = value;
            return this;
        }

        public NoteValidationViewModel build() {
            return new NoteValidationViewModel(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteValidationViewModel that = (NoteValidationViewModel) o;

        return validationMessage != null ?
                validationMessage.equals(that.validationMessage) :
                that.validationMessage == null && validationResult == that.validationResult;

    }

    @Override
    public int hashCode() {
        int result = validationMessage != null ? validationMessage.hashCode() : 0;
        result = 31 * result + (validationResult != null ? validationResult.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NoteValidationViewModel{" +
                "validationMessage='" + validationMessage + '\'' +
                ", validationResult=" + validationResult +
                '}';
    }
}
