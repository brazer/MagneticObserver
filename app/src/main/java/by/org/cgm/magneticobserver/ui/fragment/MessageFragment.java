package by.org.cgm.magneticobserver.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private MagMessage mMessage;

    public interface OnShowChartsListener {
        void onShowChartsFragment();
    }

    public MessageFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_message;
    }

    @Override
    protected void readArgs(Bundle args) {
        mMessage = (MagMessage) args.getSerializable(MagMessage.TAG);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initViews() {
        mLevelTv.setText(mMessage.getStormLevel());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mLevelTv.setBackgroundColor(getResources().getColor(mMessage.getColorId(), null));
        } else mLevelTv.setBackgroundColor(getResources().getColor(mMessage.getColorId()));
        mDateTv.setText(mMessage.getDate());
        mBeginTv.setText(mMessage.getBegin());
        mEndTv.setText(mMessage.getEnd());
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
