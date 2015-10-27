package by.org.cgm.magneticobserver.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import by.org.cgm.magneticobserver.R;
import by.org.cgm.magneticobserver.models.MagMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment {

    @Bind(R.id.fragment_message__level) TextView mLevelTv;
    @Bind(R.id.fragment_message__date) TextView mDateTv;
    @Bind(R.id.fragment_message__begin) TextView mBeginTv;
    @Bind(R.id.fragment_message__end) TextView mEndTv;

    public MessageFragment() {
        // Required empty public constructor
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

}
