package com.example.alex.hourcalculator;

 import android.app.Dialog;
 import android.app.TimePickerDialog;
 import android.os.Bundle;
 import android.text.format.DateFormat;
 import android.widget.TimePicker;
 import android.widget.Toast;
 import java.util.Calendar;
 import android.support.v4.app.DialogFragment;

public class TimePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), timeSetListener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Toast.makeText(getActivity(), "selected time is "
                                    + view.getCurrentHour() +
                                    " / " + view.getCurrentMinute()
                            , Toast.LENGTH_SHORT).show();
                }
            };
}
