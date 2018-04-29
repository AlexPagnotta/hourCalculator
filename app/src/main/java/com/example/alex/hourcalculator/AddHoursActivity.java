package com.example.alex.hourcalculator;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddHoursActivity extends AppCompatActivity implements TimePickerFragment.TimePickedListener{

    private boolean isStartHour = false;

    int startHour;
    int startMinutes;
    int endHour;
    int endMinutes;
    int finalHour;
    int finalMinutes;

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
        endMinutes = 0;

        Date startdate = new Date();
        Date endDate = new Date();
        startdate.setHours(startHour);
        startdate.setMinutes(startMinutes);
        endDate.setHours(endHour);
        endDate.setMinutes(endMinutes);

        long millis = endDate.getTime() - startdate.getTime();
        finalHour = (int) millis/(1000 * 60 * 60);
        finalMinutes = (int) (millis/(1000*60)) % 60;

        Button setStartHour = (Button) findViewById(R.id.setStartHourBtn);
        startHourtext = (EditText) findViewById(R.id.startTimeTxt);
        endHourText = (EditText) findViewById(R.id.endTimeTxt);
        finalHourtext = (EditText) findViewById(R.id.finalTimeTxt);

        startHourtext.setText(Integer.toString(startHour) + ": " + Integer.toString(startMinutes));
        endHourText.setText(Integer.toString(endHour) + ": " + Integer.toString(endMinutes));
        finalHourtext.setText(Integer.toString(finalHour) + ": " + Integer.toString(finalMinutes));

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
                Intent myIntent = new Intent(AddHoursActivity.this, MainActivity.class);
                myIntent.putExtra("finalHour", finalHour);
                myIntent.putExtra("finalMinutes", finalMinutes);
                setResult(RESULT_OK, myIntent);
                finish();
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
            bundle.putInt("minutes", endMinutes);
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

            Date startdate = new Date();
            Date endDate = new Date();
            startdate.setHours(startHour);
            startdate.setMinutes(startMinutes);
            endDate.setHours(endHour);
            endDate.setMinutes(endMinutes);

            long millis = endDate.getTime() - startdate.getTime();
            finalHour = (int) millis/(1000 * 60 * 60);
            finalMinutes = (int) (millis/(1000*60)) % 60;

            finalHourtext.setText(Integer.toString(finalHour) + ": " + Integer.toString(finalMinutes));
        }
        else{
            endHourText.setText(Integer.toString(hour) + ": " + Integer.toString(minutes));
            endHour = hour;
            endMinutes = minutes;

            Date startdate = new Date();
            Date endDate = new Date();
            startdate.setHours(startHour);
            startdate.setMinutes(startMinutes);
            endDate.setHours(endHour);
            endDate.setMinutes(endMinutes);

            long millis = endDate.getTime() - startdate.getTime();
            finalHour = (int) millis/(1000 * 60 * 60);
            finalMinutes = (int) (millis/(1000*60)) % 60;

            finalHourtext.setText(Integer.toString(finalHour) + ": " + Integer.toString(finalMinutes));
        }
    }
}
