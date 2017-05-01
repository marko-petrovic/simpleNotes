package classes.simpleNotes;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.dualquo.te.simpleNotes.Application;
import com.dualquo.te.simpleNotes.activities.BaseActivity;
import com.dualquo.te.simpleNotes.activities.MainActivity;
import com.dualquo.te.simpleNotes.activities.NewNoteActivity;

import javax.inject.Singleton;

import classes.simpleNotes.injection.modules.ApplicationBindersModule;
import classes.simpleNotes.injection.modules.ApplicationModule;
import classes.simpleNotes.injection.modules.ApplicationProvidersModule;
import classes.simpleNotes.injection.modules.DatabaseTransactionsModule;
import classes.simpleNotes.injection.modules.LocalRepositoriesModule;
import classes.simpleNotes.injection.modules.SchedulersModule;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ApplicationBindersModule.class,
        ApplicationProvidersModule.class,
        DatabaseTransactionsModule.class,
        LocalRepositoriesModule.class,
        SchedulersModule.class
})
public interface ApplicationComponent {

    android.app.Application getCustomApplication();

    Context getCustomApplicationContext();

    SharedPreferences getSharedPreferences();

    ContentResolver getContentResolver();

    Resources getResources();

    void inject(Application target);

    void inject(BaseActivity target);

    void inject(MainActivity target);

    void inject(NewNoteActivity target);
}
