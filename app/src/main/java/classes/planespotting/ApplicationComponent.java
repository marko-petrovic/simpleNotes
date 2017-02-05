package classes.planespotting;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.dualquo.te.planespotting.Application;
import com.dualquo.te.planespotting.activities.BaseActivity;

import javax.inject.Singleton;

import classes.planespotting.injection.modules.ApplicationModule;
import classes.planespotting.injection.modules.SchedulersModule;
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
}
