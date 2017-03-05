package classes.simpleNotes.ui.presenter;

import java.io.Serializable;

/**
 * Represents a base Presenter interface.
 */
public interface IPresenter {
    /**
     * Called when {@link IPresenter} should resume.
     */
    void onResume();

    /**
     * Called when {@link IPresenter} should pause.
     */
    void onPause();

    /**
     * Called when {@link IPresenter} should navigate back.
     *
     * @return true if call is processed, false if call should be processed by parent or not supported.
     */
    boolean onBack();

    /**
     * Subscribes to Interactors.
     */
    void subscribe();

    /**
     * Unsubscribes from Interactors.
     */
    void unsubscribe();

    /**
     * Called when {@link IPresenter} should return its state.
     *
     * @return current state or the presenter to be restored after recreation.
     */
    Serializable getState();
}

