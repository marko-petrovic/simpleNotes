package classes.simpleNotes.ui.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import classes.simpleNotes.alerts.GeneralAlertsQueue;
import classes.simpleNotes.ui.model.GeneralAlert;
import rx.Observable;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.when;

public class GetPendingGeneralAlertsUseCaseTest {
    @Mock
    GeneralAlertsQueue generalAlertsQueue;

    private GetPendingGeneralAlertsUseCase getPendingGeneralAlertsUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        getPendingGeneralAlertsUseCase = new GetPendingGeneralAlertsUseCase(generalAlertsQueue);
    }

    @Test
    public void shouldRequestToQueue() throws Exception {
        Observable<GeneralAlert> observable = Observable.create(subscriber -> {
        });

        when(generalAlertsQueue.pendingGeneralAlerts())
                .thenReturn(observable);

        assertSame(
                getPendingGeneralAlertsUseCase.pendingGeneralAlerts(),
                observable
        );
    }
}