package com.aceshub.portal.today;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.aceshub.portal.MainActivity;
import com.aceshub.portal.R;
import com.aceshub.portal.Today;

import java.util.Calendar;

@SuppressLint("ParcelCreator")
public class AddLectureDialog extends AlertDialog implements View.OnClickListener, TimePickerFragment.OnTimeSet {

    private static String time;
    private TextView fromTv;
    private TextView toTv;
    private Spinner subjects;

    private DialogFragment newFragment = new TimePickerFragment();

    public AddLectureDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_lecture_dialog);

        //Spinner
        subjects = (Spinner) findViewById(R.id.subjects_spinner);

        //Buttons
        Button cancel = (Button) findViewById(R.id.newlec_dialog_cancel);
        Button add = (Button) findViewById(R.id.newlec_dialog_add);

        cancel.setOnClickListener(this);
        add.setOnClickListener(this);

        //Time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        fromTv = (TextView) findViewById(R.id.from_text);
        toTv = (TextView) findViewById(R.id.to_text);

        String time = "" + hour + ":" + minute;
        fromTv.setText(time);
        toTv.setText(time);

        ImageButton editFrom = (ImageButton) findViewById(R.id.from_editbtn);
        ImageButton editTo = (ImageButton) findViewById(R.id.to_editbtn);

        editFrom.setOnClickListener(this);
        editTo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newlec_dialog_add:
                int position = subjects.getSelectedItemPosition();
                String fromTime = fromTv.getText().toString();
                String toTime = toTv.getText().toString();
                int fromTimeInt = Integer.valueOf(fromTime.replace(":", ""));
                int toTimeInt = Integer.valueOf(toTime.replace(":", ""));

                if (position == 0) {
                    Toast.makeText(getContext(), "Please choose a subject.", Toast.LENGTH_SHORT).show();
                    break;
                }

                String time = fromTime + " - " + toTime;
                TodayData.addData(new TodayListItem(time, v.getResources().getStringArray(R.array.subject_names)[position]));
                Today.adapter.notifyItemInserted(TodayData.getSize() - 1);
                dismiss();
                break;

            case R.id.newlec_dialog_cancel:
                dismiss();
                break;

            case R.id.from_editbtn:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("id", 1);
                bundle1.putParcelable("interface", this);
                newFragment.setArguments(bundle1);
                newFragment.show(MainActivity.fragmentManager, "From");
                break;

            case R.id.to_editbtn:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("id", 2);
                bundle2.putParcelable("interface", this);
                newFragment.setArguments(bundle2);
                newFragment.show(MainActivity.fragmentManager, "To");
                break;
        }
    }

    @Override
    public void onTimeSet(int id, String time) {
        if (id == 1)
            fromTv.setText(time);
        else
            toTv.setText(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
