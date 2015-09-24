package by.org.cgm.magneticobserver.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import by.org.cgm.magneticobserver.R;
import by.org.cgm.magneticobserver.models.MagMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMessage extends Fragment {

    @Bind(R.id.fragment_message__message) TextView mMessageTv;

    public FragmentMessage() {
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
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        if (args != null) {
            MagMessage magMessage = (MagMessage) args.getSerializable(MagMessage.TAG);
            if (magMessage != null) {
                mMessageTv.setText(magMessage.toMessage(getString(R.string.message)));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
