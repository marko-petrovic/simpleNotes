package classes.simpleNotes.injection.modules;

import javax.inject.Named;

import classes.simpleNotes.persistance.DatabaseTransaction;
import classes.simpleNotes.persistance.RealmModelTransaction;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class DatabaseTransactionsModule {
    public final static String DEFAULT_TRANSACTION = "DefaultTransaction";

    @Named(DEFAULT_TRANSACTION)
    @Binds
    public abstract DatabaseTransaction bindDatabaseTransactionRealmModel(
            RealmModelTransaction realmModelTransaction
    );
}