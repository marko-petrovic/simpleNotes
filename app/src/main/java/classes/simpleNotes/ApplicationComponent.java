package classes.simpleNotes;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.dualquo.te.simpleNotes.Application;
import com.dualquo.te.simpleNotes.activities.BaseActivity;
import com.dualquo.te.simpleNotes.activities.MainActivity;

import javax.inject.Singleton;

import classes.simpleNotes.injection.modules.ApplicationModule;
import classes.simpleNotes.injection.modules.SchedulersModule;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
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
}
