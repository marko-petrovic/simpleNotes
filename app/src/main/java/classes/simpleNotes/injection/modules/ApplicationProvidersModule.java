package classes.simpleNotes.injection.modules;

import javax.inject.Singleton;

import classes.simpleNotes.persistance.RealmDatabaseConnector;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationProvidersModule {
    @Provides
    @Singleton
    RealmDatabaseConnector provideRealmDatabaseConnector() {
        return new RealmDatabaseConnector();
    }
}