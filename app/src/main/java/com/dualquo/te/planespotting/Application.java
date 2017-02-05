package com.dualquo.te.planespotting;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import classes.planespotting.ApplicationComponent;
import classes.planespotting.DaggerApplicationComponent;
import classes.planespotting.injection.modules.ApplicationModule;

/**
 * Application class.
 */
public class Application extends MultiDexApplication {

    private static Application application;
    private static ApplicationComponent component;

    /**
     * Get Application.
     *
     * @return instance of {@link Application} class.
     */
    public static Application getApplication() {
        return application;
    }

    /**
     * Get ApplicationComponent.
     *
     * @return instance of {@link ApplicationComponent} class.
     */
    public static ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;

        MultiDex.install(this);

        initializeDagger();

        Application.getComponent().inject(this);
    }

    private void initializeDagger() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
