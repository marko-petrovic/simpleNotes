package classes.simpleNotes.ui.presenter;

import classes.simpleNotes.ui.view.alerts.IAlertView;

/**
 * Interface for Presenters that present various alert data to various alert views.
 */
public interface IAlertsPresenter<AlertView extends IAlertView<?>, AlertState> {
    /**
     * Called when {@link IAlertsPresenter} should resume.
     *
     * @param alertView which will be used to display data.
     */
    void resume(AlertView alertView);

    /**
     * Called when {@link IAlertsPresenter} should pause. This is the default state. <br>
     * At this state view will never be updated even if some long-running operation completes after
     * this point.
     */
    void pause();

    /**
     * Updates alert or stores it until alert view becomes available.
     *
     * @param alertState state of the alert for which view should be notified.
     */
    void updateAlertState(AlertState alertState);
}
