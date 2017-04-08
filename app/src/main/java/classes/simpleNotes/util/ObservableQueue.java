package classes.simpleNotes.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;

import static classes.simpleNotes.util.Event.event;
import static rx.Observable.from;

/**
 * Queue which stores values which can later on be consumed as an observable sequence.
 * <p>
 * This class is thread safe.
 */
public class ObservableQueue<T> {
    private final BehaviorSubject<Event> updates = BehaviorSubject.create();
    private final Observable<T> itemsObservable;
    private final Queue<T> items = new LinkedList<>();

    @Inject
    public ObservableQueue() {
        itemsObservable = updates
                .flatMap(event -> {
                    synchronized (items) {
                        final ArrayList<T> items = new ArrayList<>(this.items);

                        return from(items);
                    }
                })
                .doOnNext(alert -> {
                    synchronized (items) {
                        items.remove(alert);
                    }
                })
                .share();
    }

    /**
     * Stores offered item in a queue.
     */
    public void offer(T item) {
        synchronized (items) {
            items.offer(item);
            updates.onNext(event());
        }
    }

    /**
     * @return observable which produces items as soon as they become available. <p>
     * Every emitted item is then removed from the queue, or better named, consumed.
     */
    public Observable<T> consume() {
        return itemsObservable;
    }
}
