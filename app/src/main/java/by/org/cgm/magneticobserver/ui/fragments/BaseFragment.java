package by.org.cgm.magneticobserver.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Author: Anatol Salanevich
 * Date: 01.10.2015
 */
public abstract class BaseFragment  extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //todo: ButterKnife.unbind(this);
    }

}
