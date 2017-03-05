package classes.simpleNotes.ui.presenter;

import com.dualquo.te.simpleNotes.activities.BaseActivity.Navigator;

import java.io.Serializable;

import classes.simpleNotes.ui.view.IHomeView;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

/**
 * Presents and manages {@link IHomeView}, data, and interactions.
 */
public class HomePresenter implements IPresenter, IHomeView.Listener {

    private final IHomeView homeView;
    private final Navigator navigator;
    private final Scheduler backgroundTaskScheduler;
    private final Scheduler mainThreadScheduler;

    private CompositeSubscription subscriptions;

    public HomePresenter(
            IHomeView homeView,
            Navigator navigator,
            Scheduler backgroundTaskScheduler,
            Scheduler mainThreadScheduler) {
        this.homeView = homeView;
        this.navigator = navigator;
        this.backgroundTaskScheduler = backgroundTaskScheduler;
        this.mainThreadScheduler = mainThreadScheduler;

        this.homeView.setListener(this);
    }

    @Override
    public void onResume() {
        subscribe();
    }

    @Override
    public void onPause() {
        unsubscribe();
    }

    @Override
    public boolean onBack() {
        return false;
    }

    @Override
    public void subscribe() {
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void unsubscribe() {
        subscriptions.unsubscribe();
    }

    @Override
    public Serializable getState() {
        return null;
    }

    @Override
    public void addNewNote() {
        //TODO call Navigator here to navigate to note adding screen.
    }
}
