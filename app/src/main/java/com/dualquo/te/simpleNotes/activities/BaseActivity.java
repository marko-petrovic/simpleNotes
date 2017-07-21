package com.dualquo.te.simpleNotes.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dualquo.te.simpleNotes.Application;

import javax.inject.Inject;
import javax.inject.Named;

import classes.simpleNotes.injection.modules.SchedulersModule;
import classes.simpleNotes.injection.schedulers.BackgroundScheduler;
import classes.simpleNotes.injection.schedulers.MainThreadScheduler;
import classes.simpleNotes.ui.interactor.GetPendingGeneralAlertsUseCase;
import classes.simpleNotes.ui.presenter.GeneralAlertsPresenter;
import classes.simpleNotes.ui.view.alerts.GeneralAlertDialogView;
import rx.Scheduler;

/**
 * Base {@link android.app.Activity} that provides common features to all other activities.
 */
public class BaseActivity extends AppCompatActivity {
    @Inject
    GetPendingGeneralAlertsUseCase getPendingGeneralAlertsUseCase;

    @BackgroundScheduler
    @Inject
    @Named(SchedulersModule.BACKGROUND_SCHEDULER)
    Scheduler backgroundTaskScheduler;

    @MainThreadScheduler
    @Inject
    @Named(SchedulersModule.MAIN_THREAD_SCHEDULER)
    Scheduler mainThreadScheduler;

    private GeneralAlertsPresenter generalAlertsPresenter;
    private GeneralAlertDialogView generalAlertDialogView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Application.getComponent().inject(this);

        generalAlertDialogView = new GeneralAlertDialogView(BaseActivity.this);

        generalAlertsPresenter = new GeneralAlertsPresenter(
                getPendingGeneralAlertsUseCase,
                backgroundTaskScheduler,
                mainThreadScheduler
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        generalAlertsPresenter.resume(generalAlertDialogView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        generalAlertsPresenter.pause();
    }

    /**
     * Navigator that helps Activity do navigation based on Presenters logic.
     */
    public interface Navigator {
        /**
         * Navigate depending on type of given data.
         *
         * @param data {@link Object} that defines navigational parameters.
         */
        void navigate(Object data);

        /**
         * Null-object pattern.
         */
        Navigator NULL = data -> {
            // do nothing
        };
    }
}
