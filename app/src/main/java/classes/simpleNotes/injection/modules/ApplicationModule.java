package classes.simpleNotes.injection.modules;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @SuppressLint("StaticFieldLeak")
    private static Application application;

    public ApplicationModule(Application application) {
        ApplicationModule.application = application;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

    @Provides
    Context providesApplicationContext() {
        return application;
    }

    @Provides
    SharedPreferences providesSharedPreferences() {
        return application.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
    }

    @Provides
    ContentResolver provideContentResolver() {
        return application.getContentResolver();
    }

    @Provides
    Resources provideResources() {
        return application.getApplicationContext().getResources();
    }
}
