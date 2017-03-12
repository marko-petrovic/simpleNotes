package classes.simpleNotes.ui.view;

import org.jetbrains.annotations.NotNull;

/**
 * Represents home view of the app.
 */
public interface IHomeView extends IView {
    /**
     * Sets UI interactions listener into a view.
     *
     * @param listener {@link Listener} that will be notified about UI interactions.
     */
    void setListener(@NotNull Listener listener);

    /**
     * Show that there are no notes added yet.
     */
    void showNoAvailableNotesYet();

    /**
     * Show the list of added notes.
     */
    void showAddedNotesList();

    /**
     * UI interactions listener.
     */
    interface Listener {
        /**
         * Null-object pattern.
         */
        Listener NULL = () -> {
            // Implement in Presenter.
        };

        /**
         * Called when user wants to add new note by pressing FAB.
         */
        void addNewNote();
    }
}
