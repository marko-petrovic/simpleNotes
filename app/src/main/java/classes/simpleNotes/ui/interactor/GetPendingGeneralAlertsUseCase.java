package classes.simpleNotes.ui.interactor;

import javax.inject.Inject;

import classes.simpleNotes.alerts.GeneralAlertsQueue;
import classes.simpleNotes.ui.model.GeneralAlert;
import rx.Observable;

/**
 * Provides Observable general alerts stream using {@link GeneralAlertsQueue}.
 */
public class GetPendingGeneralAlertsUseCase {
    private final GeneralAlertsQueue generalAlertsQueue;

    @Inject
    public GetPendingGeneralAlertsUseCase(GeneralAlertsQueue generalAlertsQueue) {
        this.generalAlertsQueue = generalAlertsQueue;
    }

    /**
     * Emits general alerts as {@link Observable}.
     *
     * @return pending {@link GeneralAlert}s.
     */
    public Observable<GeneralAlert> pendingGeneralAlerts() {
        return generalAlertsQueue.pendingGeneralAlerts();
    }
}