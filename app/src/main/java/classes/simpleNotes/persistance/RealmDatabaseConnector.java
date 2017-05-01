package classes.simpleNotes.persistance;

import android.os.HandlerThread;
import android.support.annotation.NonNull;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Singleton {@link RealmDatabase} holder class.
 */
public class RealmDatabaseConnector {
    private static final String REALM_WRITER_THREAD = "REALM_WRITER_THREAD";
    private static final String REALM_READER_THREAD = "REALM_READER_THREAD";
    private static final String MODEL_PREFIX = "MODEL_";

    /**
     * Instance of writable Realm database.
     */
    protected RealmDatabase writableRealm;

    /**
     * Instance of readable Realm database.
     */
    protected RealmDatabase readableRealm;

    /**
     * All Realm writing operations must be executed on this {@link HandlerThread}.
     */
    private final HandlerThread writerThread;

    /**
     * All Realm reading operations must be executed on this {@link HandlerThread}.
     */
    private final HandlerThread readerThread;

    public RealmDatabaseConnector() {
        this(MODEL_PREFIX);
    }

    protected RealmDatabaseConnector(String threadPrefix) {
        writerThread = new HandlerThread(threadPrefix + REALM_WRITER_THREAD);
        writerThread.start();

        readerThread = new HandlerThread(threadPrefix + REALM_READER_THREAD);
        readerThread.start();
    }

    /**
     * Create writer scheduler using {@link AndroidSchedulers}.
     *
     * @return {@link Scheduler} for Realm writing operations.
     */
    public Scheduler createWriterScheduler() {
        return AndroidSchedulers.from(writerThread.getLooper());
    }

    /**
     * Create reader scheduler using {@link AndroidSchedulers}.
     *
     * @return {@link Scheduler} for Realm reading operations.
     */
    public Scheduler createReaderScheduler() {
        return AndroidSchedulers.from(readerThread.getLooper());
    }

    /**
     * @return and init {@link RealmDatabase} for reading data.
     */
    @NonNull
    public RealmDatabase initReaderDatabase(RealmDatabasesFactory realmDatabasesFactory) {
        verifyReaderThread();

        if (this.readableRealm == null) {
            this.readableRealm = realmDatabasesFactory.createModelDatabase();
        }

        return this.readableRealm;
    }

    /**
     * @return and initialise {@link RealmDatabase} for writing data.
     */
    @NonNull
    public RealmDatabase initWriterDatabase(RealmDatabasesFactory realmDatabasesFactory) {
        verifyWriterThread();

        if (this.writableRealm == null) {
            this.writableRealm = realmDatabasesFactory.createModelDatabase();
        }

        return this.writableRealm;
    }

    /**
     * Close reader Realm database instance.
     */
    public void closeReaderDatabase() {
        verifyReaderThread();

        if (readableRealm != null) {
            readableRealm.close();
            readableRealm = null;
        }
    }

    /**
     * Closes writer Realm database instance.
     */
    public void closeWriterDatabase() {
        verifyWriterThread();

        if (writableRealm != null) {
            writableRealm.close();
            writableRealm = null;
        }
    }

    /**
     * @return {@code true} if reader thread, {@code false} otherwise.
     */
    public boolean isReaderThread() {
        return Thread.currentThread().getId() == readerThread.getId();
    }

    /**
     * @return {@code true} if writer thread, {@code false} otherwise.
     */
    public boolean isWriterThread() {
        return Thread.currentThread().getId() == writerThread.getId();
    }

    /**
     * Verifies if operation is executed on the reader thread.
     */
    protected void verifyReaderThread() {
        if (Thread.currentThread().getId() != readerThread.getId()) {
            throw new RuntimeException("Could not create reader Realm database on non reader thread.");
        }
    }

    /**
     * Verifies if operation is executed on the writer thread.
     */
    protected void verifyWriterThread() {
        if (Thread.currentThread().getId() != writerThread.getId()) {
            throw new RuntimeException("Could not create reader Realm database on non writer thread.");
        }
    }
}