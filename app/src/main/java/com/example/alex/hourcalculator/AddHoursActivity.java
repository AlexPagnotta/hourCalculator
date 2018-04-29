package com.example.alex.hourcalculator;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddHoursActivity extends AppCompatActivity implements TimePickerFragment.TimePickedListener{

    private boolean isStartHour = false;

    int startHour;
    int startMinutes;
    int endHour;
    int endMInutes;

    EditText startHourtext;
    EditText endHourText ;
    EditText finalHourtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hours);

        //Set default time
        startHour = 9;
        startMinutes = 0;
        endHour = 18;
        endMInutes = 0;

        Button setStartHour = (Button) findViewById(R.id.setStartHourBtn);
        startHourtext = (EditText) findViewById(R.id.startTimeTxt);
        endHourText = (EditText) findViewById(R.id.endTimeTxt);
        finalHourtext = (EditText) findViewById(R.id.finalTimeTxt);

        setStartHour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isStartHour = true;
                showTimePickerDialog(v);
            }
        });

        Button setEndHour = (Button) findViewById(R.id.setEndHourBtn);
        setEndHour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isStartHour = false;
                showTimePickerDialog(v);
            }
        });

        Button saveHours = (Button) findViewById(R.id.saveHours);
        saveHours.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        Bundle bundle = new Bundle();
        if(isStartHour){
            bundle.putInt("hour", startHour);
            bundle.putInt("minutes", startMinutes);
        }
        else {
            bundle.putInt("hour", endHour);
            bundle.putInt("minutes", endMInutes);
        }
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onTimePicked(int hour, int minutes) {
        if(isStartHour){
            startHourtext.setText(Integer.toString(hour) + ": " + Integer.toString(minutes));
            startHour = hour;
            startMinutes = minutes;
        }
        else{
            endHourText.setText(Integer.toString(hour) + ": " + Integer.toString(minutes));
            endHour = hour;
            endMInutes = minutes;
        }
    }
}
