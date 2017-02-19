package com.dualquo.te.planespotting.activities;

import android.os.Bundle;

import com.dualquo.te.planespotting.R;

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
