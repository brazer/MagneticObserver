package by.org.cgm.magneticobserver.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import by.org.cgm.magneticobserver.R;
import by.org.cgm.magneticobserver.model.MagMessage;
import by.org.cgm.magneticobserver.util.FragmentTags;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment {

    @Bind(R.id.fragment_message__level) TextView mLevelTv;
    @Bind(R.id.fragment_message__date) TextView mDateTv;
    @Bind(R.id.fragment_message__begin) TextView mBeginTv;
    @Bind(R.id.fragment_message__end) TextView mEndTv;
    private OnShowChartsListener mListener;

    public interface OnShowChartsListener {
        void onShowChartsFragment();
    }

    public MessageFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            MagMessage magMessage = (MagMessage) args.getSerializable(MagMessage.TAG);
            if (magMessage != null) {
                mLevelTv.setText(magMessage.getStormLevel());
                mLevelTv.setBackgroundColor(getResources().getColor(magMessage.getColorId()));
                mDateTv.setText(magMessage.getDate());
                mBeginTv.setText(magMessage.getBegin());
                mEndTv.setText(magMessage.getEnd());
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_message, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.show_charts) {
            mListener.onShowChartsFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnShowChartsListener) context;
        } catch (ClassCastException ex) {
            Log.d(FragmentTags.MESSAGE, "The listener must be MainActivity", ex);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
