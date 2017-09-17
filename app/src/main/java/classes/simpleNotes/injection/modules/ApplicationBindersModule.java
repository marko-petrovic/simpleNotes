package classes.simpleNotes.injection.modules;

import classes.simpleNotes.persistance.encryption.KeyGenerator;
import classes.simpleNotes.persistance.encryption.RealmKeyGenerator;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationBindersModule {
    @Binds
    public abstract KeyGenerator bindKeyGenerator(RealmKeyGenerator realmKeyGenerator);
}