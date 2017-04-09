package classes.simpleNotes.ui.view;

import org.jetbrains.annotations.NotNull;

import classes.simpleNotes.ui.model.NoteValidationViewModel;
import classes.simpleNotes.ui.model.NoteViewModel;

/**
 * Represents adding a new note view.
 */
public interface INewNoteView extends IView {
    /**
     * Sets UI interactions listener into a view.
     *
     * @param listener {@link Listener} that will be notified about UI interactions.
     */
    void setListener(@NotNull Listener listener);

    /**
     * UI interactions listener.
     */
    interface Listener {
        /**
         * Null-object pattern.
         */
        Listener NULL = noteViewModel -> {
            // Implement in Presenter.
        };

        /**
         * Called when user saves new note.
         *
         * @param noteViewModel actual note that is going to be saved.
         */
        void saveNote(NoteViewModel noteViewModel);
    }
}
