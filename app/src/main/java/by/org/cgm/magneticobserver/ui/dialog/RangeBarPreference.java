package by.org.cgm.magneticobserver.ui.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.appyvet.rangebar.RangeBar;

import org.jetbrains.annotations.NotNull;

import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.R;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

/**
 * Author: Anatol Salanevich
 * Date: 14.10.2015
 */
public class RangeBarPreference extends DialogPreference implements
        RangeBar.OnRangeBarChangeListener, CheckBox.OnCheckedChangeListener {

    private final static String DEFAULT_VALUE = "0-4";
    public static final String OFF = "off";
    private SettingsValue mValue = new SettingsValue();
    private boolean isInit;
    private CheckBox mEnabledNotificationCb;
    private RangeBar mRangeBar;
    private RangeBar.PinTextFormatter mFormatter = new RangeBar.PinTextFormatter() {
        @Override
        public String getText(String value) {
            int index = Integer.parseInt(value);
            return AppCache.getInstance().getShortLevels()[index];
        }
    };

    public RangeBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_preference);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        mEnabledNotificationCb = (CheckBox) view.findViewById(R.id.dialog_preference__checkbox);
        mEnabledNotificationCb.setOnCheckedChangeListener(this);
        mRangeBar = (RangeBar) view.findViewById(R.id.dialog_preference__rangebar);
        mRangeBar.setOnRangeBarChangeListener(this);
        mRangeBar.setPinTextFormatter(mFormatter);
        onSetInitialValue(true, null);
        init();
    }

    private void init() {
        if (mValue.getValue().equals(OFF)) {
            mRangeBar.setEnabled(false);
            mEnabledNotificationCb.setChecked(true);
        }
        else {
            setRangeBarPins();
            mEnabledNotificationCb.setChecked(false);
        }
    }

    private void setRangeBarPins() {
        isInit = false;
        mRangeBar.setRangePinsByIndices(mValue.getLeft(), mValue.getRight());
        isInit = true;
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        if (restorePersistedValue) {
            String value = getPersistedString(DEFAULT_VALUE);
            mValue.setValue(value);
        } else {
            mValue.setValue((String) defaultValue);
            persistString(mValue.getValue());
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
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
        if (isInit) {
            mValue.setLeft(leftPinIndex);
            mValue.setRight(rightPinIndex);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) mValue.setValue(OFF);
        else {
            mValue.setRight(mRangeBar.getRightIndex());
            mValue.setLeft(mRangeBar.getLeftIndex());
        }
        mRangeBar.setEnabled(!b);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            return superState;
        }
        final SavedState myState = new SavedState(superState);
        myState.value = mValue.getValue();
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state==null || !state.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());
        mValue.setValue(myState.value);
        init();
    }

    public static class SettingsValue {

        private static final String SEPARATOR = "-";
        @Getter @Setter
        private int left, right;

        @SneakyThrows(Exception.class)
        public void setValue(@NotNull String value) {
            if (value.equals(OFF)) {
                left = -1;
                right = -1;
            } else {
                String arr[] = value.split(SEPARATOR);
                left = Integer.parseInt(arr[0]);
                right = Integer.parseInt(arr[1]);
            }
        }

        public String getValue() {
            if (left==-1) return OFF;
            return left + SEPARATOR + right;
        }
    }

    private static class SavedState extends BaseSavedState {

        public static Parcelable.Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel parcel) {
                        return new SavedState(parcel);
                    }

                    @Override
                    public SavedState[] newArray(int i) {
                        return new SavedState[i];
                    }
                };
        private String value;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            value = source.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(value);
        }
    }
}
