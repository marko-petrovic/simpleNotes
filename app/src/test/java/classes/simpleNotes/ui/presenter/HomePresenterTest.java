package classes.simpleNotes.ui.presenter;

import com.dualquo.te.simpleNotes.BuildConfig;
import com.dualquo.te.simpleNotes.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.annotation.Config;

import classes.simpleNotes.ui.view.IHomeView;
import rx.schedulers.Schedulers;

import static com.dualquo.te.simpleNotes.activities.BaseActivity.Navigator;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class HomePresenterTest {

    @Mock
    IHomeView homeView;

    @Mock
    Navigator navigator;

    private HomePresenter homePresenter;

    @Before
    public void setUp() throws Exception {
        homePresenter = new HomePresenter(
                homeView,
                navigator,
                Schedulers.immediate(),
                Schedulers.immediate()
        );
    }

    @Test
    public void setViewListeners() throws Exception {
        homePresenter = new HomePresenter(
                homeView,
                navigator,
                Schedulers.immediate(),
                Schedulers.immediate()
        );

        verify(homeView).setListener(eq(homePresenter));
    }

    @Test
    public void addNewNoteClickedShouldNavigateToAddingNoteScreen() throws Exception {
        // When
        homePresenter.addNewNote();

        // Then
        verify(navigator).navigate(MainActivity.MainActivityNavigator.NAVIGATE_ADD_NOTE);
    }
}
