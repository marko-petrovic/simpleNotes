package com.dualquo.te.simpleNotes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dualquo.te.simpleNotes.Application;
import com.dualquo.te.simpleNotes.R;

import classes.simpleNotes.ui.presenter.HomePresenter;
import classes.simpleNotes.ui.view.IHomeView;

/**
 * The first screen of the app that has the list of added notes and FAB for adding new note.
 */
public class MainActivity extends BaseActivity {

    private HomePresenter homePresenter;

    /**
     * Creates a new {@link MainActivity} intent.
     *
     * @param context Application {@link Context}.
     * @return new {@link Intent} which can be used to start {@link MainActivity}.
     */
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application.getComponent().inject(this);

        IHomeView homeView = (IHomeView) findViewById(R.id.home_view);

        Navigator navigator = new MainActivityNavigator();

        homePresenter = new HomePresenter(
                homeView,
                navigator,
                backgroundTaskScheduler,
                mainThreadScheduler
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        homePresenter.onPause();
    }

    /**
     * Navigator for {@link MainActivity}.
     */
    public class MainActivityNavigator implements Navigator {
        public static final String NAVIGATE_ADD_NOTE = "NAVIGATE_ADD_NOTE";

        @Override
        public void navigate(Object data) {
            switch ((String) data) {
                case NAVIGATE_ADD_NOTE:
                    startActivity(NewNoteActivity.newIntent(MainActivity.this));
                    break;
            }
        }
    }
}
