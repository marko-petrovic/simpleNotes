package classes.simpleNotes.alerts;

import javax.inject.Inject;
import javax.inject.Singleton;

import classes.simpleNotes.ui.model.GeneralAlert;
import classes.simpleNotes.util.ObservableQueue;
import rx.Observable;

/**
 * Observable queue of general information messages presented as alert dialogs throughout the app.
 */
@Singleton
public class GeneralAlertsQueue {
    private final ObservableQueue<GeneralAlert> observableQueue;

    @Inject
    public GeneralAlertsQueue(ObservableQueue<GeneralAlert> observableQueue) {
        this.observableQueue = observableQueue;
    }

    /**
     * Posts {@link GeneralAlert} as an offer to the queue.
     */
    public void postAlert(GeneralAlert generalAlert) {
        observableQueue.offer(generalAlert);
    }

    /**
     * @return {@link Observable} which produces {@link GeneralAlert}s
     * as soon as they need to be displayed.
     */
    public Observable<GeneralAlert> pendingGeneralAlerts() {
        return observableQueue.consume();
    }
}