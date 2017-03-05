package classes.simpleNotes.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.dualquo.te.simpleNotes.R;
import com.dualquo.te.simpleNotes.databinding.HomeViewBinding;

import org.jetbrains.annotations.NotNull;

public class HomeView extends FrameLayout implements IHomeView {

    private Listener listener = Listener.NULL;
    private ActionBar actionBar;

    private HomeViewBinding homeViewBinding;

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();

        homeViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.home_view,
                this,
                true
        );

        homeViewBinding.homeFabAddNote.setOnClickListener(v -> listener.addNewNote());
    }

    @Override
    public void setListener(@NotNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public void showNoAvailableNotesYet() {
        homeViewBinding.homeNoAvailableNotes.setVisibility(VISIBLE);
        homeViewBinding.homeNotesList.setVisibility(GONE);
    }

    @Override
    public void showAddedNotesList() {
        homeViewBinding.homeNoAvailableNotes.setVisibility(GONE);
        homeViewBinding.homeNotesList.setVisibility(VISIBLE);
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);

        if (actionBar == null) {
            actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();
        }

        actionBar.show();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    public void hide() {
        setVisibility(GONE);
    }
}
