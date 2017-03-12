package classes.simpleNotes.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.dualquo.te.simpleNotes.R;
import com.dualquo.te.simpleNotes.databinding.NewNoteViewBinding;

import org.jetbrains.annotations.NotNull;

import classes.simpleNotes.ui.model.NoteValidationViewModel;
import classes.simpleNotes.ui.model.NoteViewModel;

public class NewNoteView extends FrameLayout implements INewNoteView {
    private Listener listener = Listener.NULL;
    private ActionBar actionBar;
    private AlertDialog newNoteNotValidDialog = null;

    public NewNoteView(Context context, AttributeSet attrs) {
        super(context, attrs);

        actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();

        NewNoteViewBinding newNoteViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.new_note_view,
                this,
                true
        );

        newNoteViewBinding
                .newNoteSaveNoteButton
                .setOnClickListener(
                        view -> listener.saveNote(
                                new NoteViewModel.Builder()
                                        .noteTitle(newNoteViewBinding.newNoteTitle.getText().toString())
                                        .noteText(newNoteViewBinding.newNoteBody.getText().toString())
                                        .build()
                        )
                );
    }

    @Override
    public void setListener(@NotNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public void newNoteNotValid(NoteValidationViewModel noteValidationViewModel) {
        if (newNoteNotValidDialog == null || !newNoteNotValidDialog.isShowing()) {
            newNoteNotValidDialog = new AlertDialog
                    .Builder(getContext())
                    .setTitle(R.string.new_note_validation_error_title)
                    .setMessage(noteValidationViewModel.validationMessage)
                    .setPositiveButton(
                            R.string.new_note_validation_error_dialog_positive_button,
                            ((dialogInterface, i) -> dialogInterface.dismiss())
                    )
                    .create();

            newNoteNotValidDialog.show();
        }
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);

        if (actionBar == null) {
            actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();
        }

        actionBar.show();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.new_note_title);
    }

    @Override
    public void hide() {
        setVisibility(GONE);
    }
}
