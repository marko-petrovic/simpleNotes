package classes.simpleNotes.ui.interactor;

import android.content.res.Resources;

import com.dualquo.te.simpleNotes.R;

import javax.inject.Inject;

import classes.simpleNotes.ui.model.NoteValidationViewModel;
import classes.simpleNotes.ui.model.NoteViewModel;
import rx.Observable;

import static classes.simpleNotes.ui.model.NoteValidationViewModel.Builder;
import static classes.simpleNotes.ui.model.NoteValidationViewModel.NewNoteValidationResult;
import static classes.simpleNotes.ui.model.NoteValidationViewModel.NewNoteValidationResult.NEW_NOTE_NOT_VALID_MISSING_BODY;
import static classes.simpleNotes.ui.model.NoteValidationViewModel.NewNoteValidationResult.NEW_NOTE_NOT_VALID_MISSING_BOTH;
import static classes.simpleNotes.ui.model.NoteValidationViewModel.NewNoteValidationResult.NEW_NOTE_NOT_VALID_MISSING_TITLE;
import static classes.simpleNotes.ui.model.NoteValidationViewModel.NewNoteValidationResult.NEW_NOTE_VALID;
import static rx.Observable.just;

/**
 * Returns validity check info for a new note that is about to be added and saved.
 */
public class ValidateNewNoteUseCase {

    private final Resources resources;

    @Inject
    public ValidateNewNoteUseCase(Resources resources) {
        this.resources = resources;
    }

    /**
     * Validates new note before it gets saved.
     *
     * @param noteViewModel {@link NoteViewModel} that is being validated.
     * @return {@link Observable} of {@link NoteValidationViewModel}.
     */
    public Observable<NoteValidationViewModel> validateNewNote(NoteViewModel noteViewModel) {
        return just(noteViewModel.noteText.isEmpty() || noteViewModel.noteTitle.isEmpty())
                .flatMap(somethingIsMissing -> just(
                        new Builder()
                                .validationResult(somethingIsMissing ?
                                        getNonValidResult(noteViewModel) :
                                        NEW_NOTE_VALID
                                )
                                .validationMessage(somethingIsMissing ?
                                        getNonValidMessage(noteViewModel) :
                                        resources.getString(R.string.new_note_validation_passed_fine)
                                )
                                .build()
                ));
    }

    private NewNoteValidationResult getNonValidResult(NoteViewModel noteViewModel) {
        if (noteViewModel.noteTitle.isEmpty() && noteViewModel.noteText.isEmpty()) {
            return NEW_NOTE_NOT_VALID_MISSING_BOTH;
        } else if (noteViewModel.noteText.isEmpty()) {
            return NEW_NOTE_NOT_VALID_MISSING_BODY;
        } else {
            return NEW_NOTE_NOT_VALID_MISSING_TITLE;
        }
    }

    private String getNonValidMessage(NoteViewModel noteViewModel) {
        if (noteViewModel.noteTitle.isEmpty() && noteViewModel.noteText.isEmpty()) {
            return resources.getString(R.string.new_note_validation_error_message_both_empty);
        } else if (noteViewModel.noteText.isEmpty()) {
            return resources.getString(R.string.new_note_validation_error_message_body_empty);
        } else {
            return resources.getString(R.string.new_note_validation_error_message_title_empty);
        }
    }
}
