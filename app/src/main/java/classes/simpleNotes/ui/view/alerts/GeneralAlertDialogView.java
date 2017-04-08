package classes.simpleNotes.ui.view.alerts;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.dualquo.te.simpleNotes.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import classes.simpleNotes.ui.model.NoteValidationViewModel;

/**
 * A View that uses alert dialogs to present general alert messages globally through the app.
 */
public class GeneralAlertDialogView implements IGeneralAlertView {
    /**
     * Android {@link Context} needed to create an {@link AlertDialog}
     * and to be shared with Presenter of this View.
     */
    public final Context context;

    @NotNull
    private Listener listener = Listener.NULL;

    @Nullable
    private AlertDialog currentDialog = null;

    public GeneralAlertDialogView(Context context) {
        this.context = context;
    }

    @Override
    public void setListener(@NotNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public void updateAlertState(GeneralAlert newGeneralAlert) {
        switch (newGeneralAlert.generalAlertType) {
            case NOTE_NOT_VALID:
                showNoteNotValidDialog(newGeneralAlert);
                break;
            case NOT_SPECIFIED:
                hideDialog();
                break;
        }
    }

    private void showNoteNotValidDialog(GeneralAlert generalAlert) {
        showDialog(
                new AlertDialog.Builder(context)
                        .setTitle(
                                R.string.new_note_validation_error_title
                        )
                        .setMessage(
                                ((NoteValidationViewModel) generalAlert.viewModel).validationMessage
                        )
                        .setNegativeButton(
                                context
                                        .getResources()
                                        .getString(
                                                R.string.new_note_validation_error_dialog_positive_button
                                        ).toUpperCase(),
                                null
                        )
                        .setOnDismissListener(
                                dialogInterface -> listener.dismiss()
                        )
                        .create()
        );
    }

    private void showDialog(AlertDialog dialog) {
        hideDialog();
        currentDialog = dialog;
        currentDialog.show();
    }

    private void hideDialog() {
        if (currentDialog != null) {
            currentDialog.dismiss();
            currentDialog = null;
        }
    }
}
