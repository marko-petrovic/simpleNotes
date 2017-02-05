package classes.planespotting.injection.modules;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import classes.planespotting.injection.schedulers.BackgroundScheduler;
import classes.planespotting.injection.schedulers.MainThreadScheduler;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulersModule {
    public final static String BACKGROUND_SCHEDULER = "BackgroundScheduler";
    public final static String MAIN_THREAD_SCHEDULER = "MainThreadScheduler";

    @Provides
    @Singleton
    Scheduler provideScheduler() {
        return Schedulers.io();
    }

    @MainThreadScheduler
    @Provides
    @Named(MAIN_THREAD_SCHEDULER)
    @Singleton
    Scheduler provideAndroidScheduler() {
        return Schedulers.io();
    }

    @BackgroundScheduler
    @Provides
    @Named(BACKGROUND_SCHEDULER)
    @Singleton
    Scheduler provideBackgroundScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(16));
    }
}
