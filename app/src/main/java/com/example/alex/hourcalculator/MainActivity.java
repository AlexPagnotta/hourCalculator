package com.example.alex.hourcalculator;

import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button addHoursBtn;
    Button resetButton;
    TickerView hoursTickerView;

    int startHour;
    int startMinutes;
    int endHour;
    int endMinutes;

    final List<Pair<Integer, Integer>> hoursList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize components
        addHoursBtn = (Button) findViewById(R.id.addHoursBtn);
        resetButton = (Button) findViewById(R.id.resetHoursBtn);

        TimePicker startTimePicker = (TimePicker) findViewById(R.id.startTimePicker);
        startTimePicker.setIs24HourView(true);

        TimePicker endTimePicker = (TimePicker) findViewById(R.id.endTimePicker);
        endTimePicker.setIs24HourView(true);

        hoursTickerView = (TickerView) findViewById(R.id.tickerView);
        hoursTickerView.setCharacterLists(TickerUtils.provideNumberList());
        hoursTickerView.setText("0 " + getResources().getString(R.string.hour));

        startHour=8;
        startMinutes=30;
        endHour=18;
        endMinutes=0;

        startTimePicker.setCurrentHour(8);
        startTimePicker.setCurrentMinute(30);

        endTimePicker.setCurrentHour(18);
        endTimePicker.setCurrentMinute(0);

        //Set listeners
        startTimePicker.setOnTimeChangedListener(startTimeChangedListener);
        endTimePicker.setOnTimeChangedListener(endTimeChangedListener);
        addHoursBtn.setOnClickListener(addHoursListener);
        resetButton.setOnClickListener(resetListener);

    }

    //LISTENERS
    private Button.OnClickListener addHoursListener =
            new View.OnClickListener() {
                public void onClick(View v) {
                    hoursList.add(GetDifferenceBetweenTimes());
                    UpdateHours();
                }
            };

    private Button.OnClickListener resetListener =
            new View.OnClickListener() {
                public void onClick(View v) {
                    hoursList.clear();
                    hoursTickerView.setText("0 " + getResources().getString(R.string.hour));
                }
            };

    private TimePicker.OnTimeChangedListener startTimeChangedListener =
            new TimePicker.OnTimeChangedListener(){
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    startHour = hourOfDay;
                    startMinutes = minute;
                }
            };

    private TimePicker.OnTimeChangedListener endTimeChangedListener =
            new TimePicker.OnTimeChangedListener(){
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    endHour = hourOfDay;
                    endMinutes = minute;
                }
            };

    //METHODS
    private void UpdateHours(){

        int sum = 0;

        for(Pair<Integer,Integer> pair:hoursList){
            int hours = pair.first;
            int minutes = pair.second;

            int mins = hours * 60 + minutes;
            sum += mins;
        }

        hoursTickerView.setText(
                Integer.toString((int)Math.floor(sum/60))+
                        " " +
                        getResources().getString(R.string.hour) +
                        " " + Integer.toString(sum % 60 ) +
                        " " + getResources().getString(R.string.minutes));
    }

    private Pair<Integer,Integer> GetDifferenceBetweenTimes(){

        Date startDate = new Date();
        Date endDate = new Date();

        startDate.setHours(startHour);
        startDate.setMinutes(startMinutes);
        endDate.setHours(endHour);
        endDate.setMinutes(endMinutes);

        long millis;

        //If endTime is less than startTime, add a day to avoid negative results
        if(endDate.getTime() < startDate.getTime()){
            millis = ((endDate.getTime() + 86400000) - startDate.getTime());
        }
        else{
            millis = endDate.getTime() - startDate.getTime();
        }

        int differenceHours = (int) millis/(1000 * 60 * 60);
        int differenceMinutes = (int) (millis/(1000*60)) % 60;

        return new Pair<>(differenceHours,differenceMinutes);
    }

}
