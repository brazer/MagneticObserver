package by.org.cgm.magneticobserver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Author: Anatol Salanevich
 * Date: 13.11.2015
 */
public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        readArgs(getIntent());
        if (savedInstanceState != null) readSavedInstanceState(savedInstanceState);
        initViews();
    }

    protected abstract void readArgs(Intent args);

    protected void readSavedInstanceState(Bundle state) { }

    protected abstract void initViews();
}
