package com.example.alex.hourcalculator;

 import android.app.Dialog;
 import android.app.TimePickerDialog;
 import android.content.Context;
 import android.os.Bundle;
 import android.support.v7.widget.ContentFrameLayout;
 import android.text.format.DateFormat;
 import android.widget.TimePicker;
 import android.widget.Toast;
 import java.util.Calendar;
 import android.support.v4.app.DialogFragment;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private TimePickedListener listener;

    public static interface TimePickedListener {
        void onTimePicked(int hour, int minutes);
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            listener = (TimePickedListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDateSetListener.");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int hour = getArguments().getInt("hour");
        int minute = getArguments().getInt("minutes");

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        listener.onTimePicked(hourOfDay,minute);
    }
}
