package classes.simpleNotes.ui.presenter;

import android.content.Context;

import javax.inject.Inject;

import classes.simpleNotes.ui.interactor.GetPendingGeneralAlertsUseCase;
import classes.simpleNotes.ui.model.GeneralAlert;
import classes.simpleNotes.ui.view.alerts.GeneralAlertDialogView;
import classes.simpleNotes.ui.view.alerts.IGeneralAlertView;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Android Presenter for {@link IGeneralAlertView}.
 * <p>
 * Listens for pending general alerts and presents them.
 */
public class GeneralAlertsPresenter
        extends AlertsPresenter<GeneralAlert, GeneralAlertDialogView>
        implements IGeneralAlertView.Listener {

    private final GetPendingGeneralAlertsUseCase getPendingGeneralAlertsUseCase;
    private final Scheduler backgroundTaskScheduler;
    private final Scheduler uiScheduler;

    /**
     * Can be used for Activity transitions or other Context dependent actions.
     */
    private Context context;

    /**
     * Manages consumption of pending general alerts.
     */
    private GeneralAlertsSubscriber generalAlertsSubscriber;

    @Inject
    public GeneralAlertsPresenter(
            GetPendingGeneralAlertsUseCase getPendingGeneralAlertsUseCase,
            Scheduler backgroundTaskScheduler,
            Scheduler uiScheduler) {
        this.getPendingGeneralAlertsUseCase = getPendingGeneralAlertsUseCase;
        this.backgroundTaskScheduler = backgroundTaskScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public void resume(GeneralAlertDialogView alertView) {
        super.resume(alertView);

        alertView.setListener(this);

        context = alertView.context;

        generalAlertsSubscriber = new GeneralAlertsSubscriber();

        getPendingGeneralAlertsUseCase
                .pendingGeneralAlerts()
                .subscribeOn(backgroundTaskScheduler)
                .observeOn(uiScheduler)
                .subscribe(generalAlertsSubscriber);
    }

    @Override
    public void pause() {
        super.pause();

        generalAlertsSubscriber.unsubscribe();
    }

    @Override
    public void dismiss() {
        generalAlertsSubscriber.requestNextAlert();

        updateAlertState(new GeneralAlert.Builder().build());
    }

    @Override
    public void positiveButtonAction(GeneralAlert generalAlert) {
        // Implementation isn't needed for current GeneralAlertType types
    }

    @Override
    public void negativeButtonAction(GeneralAlert generalAlert) {
        // Implementation isn't needed for current GeneralAlertType types
    }

    @Override
    public void neutralButtonAction(GeneralAlert generalAlert) {
        // Implementation isn't needed for current GeneralAlertType types
    }

    private void displayGeneralAlert(GeneralAlert generalAlert) {
        updateAlertState(generalAlert);
    }

    /**
     * Manages consumption of pending general alerts.
     */
    private class GeneralAlertsSubscriber extends Subscriber<GeneralAlert> {

        GeneralAlertsSubscriber() {
            request(1);
        }

        @Override
        public void onCompleted() {
            // Do nothing
        }

        @Override
        public void onError(Throwable exception) {
            throw new UnsupportedOperationException(exception);
        }

        @Override
        public void onNext(GeneralAlert generalAlert) {
            displayGeneralAlert(generalAlert);
        }

        /**
         * Requests new general alert from the queue.
         */
        void requestNextAlert() {
            request(1);
        }
    }
}