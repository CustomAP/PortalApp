package com.aceshub.portal.today;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    OnTimeSet mCallback;
    Bundle bundle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bundle = getArguments();
        mCallback = bundle.getParcelable("interface");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = "" + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
        if (bundle.getInt("id") == 1) {
            mCallback.onTimeSet(1, time);
        } else {
            if (bundle.getInt("id") == 2) {
                mCallback.onTimeSet(2, time);
            }
        }
    }

    public interface OnTimeSet extends Parcelable {
        void onTimeSet(int id, String time);
    }
}
