package classes.simpleNotes.ui.presenter;

import com.dualquo.te.simpleNotes.activities.BaseActivity.Navigator;

import java.io.Serializable;

import classes.simpleNotes.ui.interactor.ValidateNewNoteUseCase;
import classes.simpleNotes.ui.model.NoteViewModel;
import classes.simpleNotes.ui.view.INewNoteView;
import rx.Scheduler;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

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

    private CompositeSubscription compositeSubscription;

    private final PublishSubject<NoteViewModel> saveNoteSubject = PublishSubject.create();

    public NewNotePresenter(
            INewNoteView newNoteView,
            ValidateNewNoteUseCase validateNewNoteUseCase,
            Navigator navigator,
            Scheduler backgroundTaskScheduler,
            Scheduler mainThreadScheduler) {
        this.newNoteView = newNoteView;
        this.validateNewNoteUseCase = validateNewNoteUseCase;
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
                                            //TODO note is valid, so proceed with saving it,
                                            //TODO and then navigating back..
                                            navigator.navigate(NAVIGATE_TO_MAIN_SCREEN);
                                            break;
                                        default:
                                            newNoteView.newNoteNotValid(noteValidationViewModel);
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
