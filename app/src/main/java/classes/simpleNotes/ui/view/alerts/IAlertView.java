package classes.simpleNotes.ui.view.alerts;

/**
 * Interface used for alert views, with particular alert state type.
 */
public interface IAlertView<AlertState> {
    /**
     * Updates alert state of the alert view.
     *
     * @param newAlertState new alert state of the alert view.
     */
    void updateAlertState(AlertState newAlertState);
}

