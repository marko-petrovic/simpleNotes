package classes.simpleNotes.ui.presenter;

import classes.simpleNotes.ui.view.alerts.IAlertView;

/**
 * Presenter for all kinds of alerts.
 *
 * @param <AlertState> type of alert.
 * @param <AlertView>  type of the {@link IAlertView}.
 */
public class AlertsPresenter<AlertState, AlertView extends IAlertView<AlertState>>
        implements IAlertsPresenter<AlertView, AlertState> {
    private AlertView alertView;
    private AlertState alertState;

    @Override
    public void resume(AlertView alertView) {
        if (alertState != null) {
            alertView.updateAlertState(alertState);
        }

        this.alertView = alertView;
    }

    @Override
    public void pause() {
        alertView = null;
    }

    @Override
    public void updateAlertState(AlertState alertState) {
        this.alertState = alertState;

        if (alertView != null) {
            alertView.updateAlertState(alertState);
        }
    }
}
