package com.dualquo.te.simpleNotes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dualquo.te.simpleNotes.Application;
import com.dualquo.te.simpleNotes.R;

import javax.inject.Inject;

import classes.simpleNotes.ui.interactor.ValidateNewNoteUseCase;
import classes.simpleNotes.ui.presenter.NewNotePresenter;
import classes.simpleNotes.ui.view.INewNoteView;

/**
 * Screen for adding a new note.
 */
public class NewNoteActivity extends BaseActivity {

    @Inject
    ValidateNewNoteUseCase validateNewNoteUseCase;

    private NewNotePresenter newNotePresenter;

    /**
     * Creates a new {@link NewNoteActivity} intent.
     *
     * @param context Application {@link Context}.
     * @return new {@link Intent} which can be used to start {@link NewNoteActivity}.
     */
    public static Intent newIntent(Context context) {
        return new Intent(context, NewNoteActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        Application.getComponent().inject(this);

        INewNoteView newNoteView = (INewNoteView) findViewById(R.id.new_note_view);

        Navigator navigator = new NewNoteNavigator();

        newNotePresenter = new NewNotePresenter(
                newNoteView,
                validateNewNoteUseCase,
                navigator,
                backgroundTaskScheduler,
                mainThreadScheduler
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        newNotePresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        newNotePresenter.onPause();
    }

    /**
     * Navigator for {@link NewNoteActivity}.
     */
    public class NewNoteNavigator implements Navigator {
        public static final String NAVIGATE_TO_MAIN_SCREEN = "NAVIGATE_TO_MAIN_SCREEN";

        @Override
        public void navigate(Object data) {
            switch ((String) data) {
                case NAVIGATE_TO_MAIN_SCREEN:
                    startActivity(new Intent(NewNoteActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    }
}
