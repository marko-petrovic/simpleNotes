package com.dualquo.te.planespotting.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.dualquo.te.planespotting.Application;

import javax.inject.Inject;
import javax.inject.Named;

import classes.planespotting.injection.modules.SchedulersModule;
import classes.planespotting.injection.schedulers.BackgroundScheduler;
import classes.planespotting.injection.schedulers.MainThreadScheduler;
import io.reactivex.Scheduler;

/**
 * Base {@link android.app.Activity} that provides common features to other activities.
 */
public class BaseActivity extends AppCompatActivity {
    @MainThreadScheduler
    @Named(SchedulersModule.MAIN_THREAD_SCHEDULER)
    @Inject
    Scheduler mainThreadScheduler;

    @BackgroundScheduler
    @Inject
    @Named(SchedulersModule.BACKGROUND_SCHEDULER)
    Scheduler backgroundTaskScheduler;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Application.getComponent().inject(this);
    }
    
    /**
     * Navigator that helps Activity do navigation based on Presenters logic.
     */
    public interface Navigator {

        /**
         * Navigate depending on type of given data
         *
         * @param data
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
