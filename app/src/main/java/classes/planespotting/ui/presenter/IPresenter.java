package classes.planespotting.ui.presenter;

import java.io.Serializable;

/**
 * Presents data to views.
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
     * Called when {@link IPresenter} should return its state.
     *
     * @return current state or the presenter to be restored after recreation.
     */
    Serializable getState();
}

