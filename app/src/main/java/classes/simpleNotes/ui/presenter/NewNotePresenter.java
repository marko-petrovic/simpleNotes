package classes.simpleNotes.ui.presenter;

import com.dualquo.te.simpleNotes.activities.BaseActivity.Navigator;

import java.io.Serializable;

import classes.simpleNotes.alerts.GeneralAlertsQueue;
import classes.simpleNotes.ui.interactor.ValidateNewNoteUseCase;
import classes.simpleNotes.ui.model.NoteViewModel;
import classes.simpleNotes.ui.view.INewNoteView;
import classes.simpleNotes.ui.model.GeneralAlert;
import rx.Scheduler;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

import static classes.simpleNotes.ui.model.GeneralAlert.GeneralAlertType.NOTE_NOT_VALID;
import static com.dualquo.te.simpleNotes.activities.NewNoteActivity.NewNoteNavigator.NAVIGATE_TO_MAIN_SCREEN;

/**
 * Presenter for adding a new note screen, manages {@link INewNoteView}.
 */
public class NewNotePresenter implements IPresenter, INewNoteView.Listener {

    private final INewNoteView newNoteView;
    private final ValidateNewNoteUseCase validateNewNoteUseCase;
    private final Navigator navigator;
    private final Scheduler backgroundTaskScheduler;
    private final Scheduler mainThreadScheduler;
    private final PublishSubject<NoteViewModel> saveNoteSubject = PublishSubject.create();
    private final GeneralAlertsQueue generalAlertsQueue;

    private CompositeSubscription compositeSubscription;

    public NewNotePresenter(
            INewNoteView newNoteView,
            ValidateNewNoteUseCase validateNewNoteUseCase,
            GeneralAlertsQueue generalAlertsQueue,
            Navigator navigator,
            Scheduler backgroundTaskScheduler,
            Scheduler mainThreadScheduler) {
        this.newNoteView = newNoteView;
        this.validateNewNoteUseCase = validateNewNoteUseCase;
        this.generalAlertsQueue = generalAlertsQueue;
        this.navigator = navigator;
        this.backgroundTaskScheduler = backgroundTaskScheduler;
        this.mainThreadScheduler = mainThreadScheduler;

        this.newNoteView.setListener(this);
    }

    @Override
    public void onResume() {
        subscribe();
    }

    @Override
    public void onPause() {
        unsubscribe();
    }

    @Override
    public boolean onBack() {
        return false;
    }

    @Override
    public void subscribe() {
        compositeSubscription = new CompositeSubscription();

        compositeSubscription.add(
                saveNoteSubject
                        .observeOn(mainThreadScheduler)
                        .subscribeOn(backgroundTaskScheduler)
                        .flatMap(validateNewNoteUseCase::validateNewNote)
                        .subscribe(noteValidationViewModel -> {
                                    switch (noteValidationViewModel.validationResult) {
                                        case NEW_NOTE_VALID:
                                            //TODO should do saving here
                                            navigator.navigate(NAVIGATE_TO_MAIN_SCREEN);
                                            break;
                                        case NEW_NOTE_NOT_VALID_MISSING_BODY:
                                        case NEW_NOTE_NOT_VALID_MISSING_TITLE:
                                        case NEW_NOTE_NOT_VALID_MISSING_BOTH:
                                        default:
                                            generalAlertsQueue.postAlert(
                                                    new GeneralAlert.Builder()
                                                            .generalAlertType(NOTE_NOT_VALID)
                                                            .viewModel(noteValidationViewModel)
                                                            .build()
                                            );
                                            break;
                                    }
                                }
                        )
        );
    }

    @Override
    public void unsubscribe() {
        compositeSubscription.unsubscribe();
    }

    @Override
    public Serializable getState() {
        return null;
    }

    @Override
    public void saveNote(NoteViewModel noteViewModel) {
        saveNoteSubject.onNext(noteViewModel);
    }
}
