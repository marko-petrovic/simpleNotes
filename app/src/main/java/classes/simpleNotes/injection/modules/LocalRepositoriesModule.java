package classes.simpleNotes.injection.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import classes.simpleNotes.injection.schedulers.BackgroundScheduler;
import classes.simpleNotes.persistance.RealmDatabaseConnector;
import classes.simpleNotes.persistance.RealmDatabasesFactory;
import classes.simpleNotes.persistance.model.NoteModel;
import classes.simpleNotes.persistance.repositories.ModelRepository;
import classes.simpleNotes.persistance.repositories.RealmModelRepository;
import classes.simpleNotes.persistance.repositories.RealmRepository;
import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class LocalRepositoriesModule {
    @Provides
    @Singleton
    ModelRepository<NoteModel> provideModelRepositoryNoteModel(
            RealmDatabasesFactory realmDatabasesFactory,
            Context context,
            RealmDatabaseConnector realmDatabaseConnector,
            @BackgroundScheduler @Named(SchedulersModule.BACKGROUND_SCHEDULER)
                    Scheduler backgroundScheduler) {
        return new RealmNoteModelRepository( //TODO create this one over RealmModelRepository for NoteModel
                realmDatabasesFactory,
                context,
                realmDatabaseConnector,
                backgroundScheduler
        );
    }

    @Singleton
    @Provides
    RealmRepository provideRealmRepository(
            RealmDatabasesFactory realmDatabasesFactory,
            Context context,
            RealmDatabaseConnector realmDatabaseConnector,
            @BackgroundScheduler @Named(SchedulersModule.BACKGROUND_SCHEDULER)
                    Scheduler backgroundScheduler) {
        return new RealmModelRepository<>(
                realmDatabasesFactory,
                context,
                realmDatabaseConnector,
                backgroundScheduler
        );
    }
}