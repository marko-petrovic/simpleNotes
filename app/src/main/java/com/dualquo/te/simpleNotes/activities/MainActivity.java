package com.dualquo.te.simpleNotes.activities;

import android.os.Bundle;

import com.dualquo.te.simpleNotes.R;

public class MainActivity extends BaseActivity {

    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Navigator for {@link MainActivity}.
     */
    public class MainActivityNavigator implements Navigator {

        @Override
        public void navigate(Object data) {

        }
    }
}
