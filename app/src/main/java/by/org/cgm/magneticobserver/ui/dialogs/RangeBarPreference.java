package by.org.cgm.magneticobserver.ui.dialogs;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;

import com.appyvet.rangebar.RangeBar;

import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.R;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Anatol Salanevich
 * Date: 14.10.2015
 */
public class RangeBarPreference extends DialogPreference implements RangeBar.OnRangeBarChangeListener {

    private RangeBar mRangeBar;
    private SettingsValue mValue = new SettingsValue();
    private RangeBar.PinTextFormatter mFormatter = new RangeBar.PinTextFormatter() {
        @Override
        public String getText(String value) {
            int index = Integer.parseInt(value);
            return AppCache.getInstance().getLevels()[index];
        }
    };

    public RangeBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.rangebar_view);
        setPositiveButtonText(android.R.string.ok);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        mRangeBar = (RangeBar) view.findViewById(R.id.rangebar);
        mRangeBar.setOnRangeBarChangeListener(this);
        mRangeBar.setPinTextFormatter(mFormatter);
        mRangeBar.setSeekPinByIndex(mValue.getLeft());
        mRangeBar.setSeekPinByIndex(mValue.getRight());
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        if (restorePersistedValue) {
            mValue.setValue(this.getPersistedString("0-4"));
        } else {
            mValue.setValue((String) defaultValue);
            persistString(mValue.getValue());
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            persistString(mValue.getValue());
        }
    }

    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex,
                                      String leftPinValue, String rightPinValue)
    {

        mValue.setLeft(leftPinIndex);
        mValue.setRight(rightPinIndex);
    }

    class SettingsValue {

        private static final String SEPARATOR = "-";
        @Getter @Setter
        private int left, right;

        public void setValue(String value) {
            String arr[] = value.split(SEPARATOR);
            left = Integer.parseInt(arr[0]);
            right = Integer.parseInt(arr[1]);
        }

        public String getValue() {
            return left + SEPARATOR + right;
        }
    }
}
