package com.example.alex.hourcalculator;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.sql.Time;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    final List<Pair<Integer, Integer>> hoursList = new ArrayList<>();
    EditText hoursSumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoursSumText = (EditText) findViewById(R.id.hoursSumText);

        Button addButton = (Button) findViewById(R.id.addHoursBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddHoursActivity.class);
                MainActivity.this.startActivityForResult(myIntent, 0);
            }
        });

    }

    private void UpdateHours(){
        if(hoursList.size() == 0){
            hoursSumText.setText("Aggiungi un orario");
        }
        else{
            //TimeZone timeZone = TimeZone.getTimeZone("UTC");
            //final Calendar sum = Calendar.getInstance(timeZone);
            //sum.setTimeInMillis(0);
            //sum.setTimeZone(TimeZone.);

            int sum = 0;

            for(Pair<Integer,Integer> pair:hoursList){
                int hours = pair.first;
                int minutes = pair.second;

                int mins = hours * 60 + minutes;
                sum += mins;

                //sum.add(Calendar.HOUR, hours);
                //sum.add(Calendar.MINUTE, minutes);
            }

            hoursSumText.setText(Integer.toString((int)Math.floor(sum/60))+": " + Integer.toString(sum % 60 ));
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        int finalHour = data.getIntExtra("finalHour",0);
        int finalMinutes = data.getIntExtra("finalMinutes", 0);

        hoursList.add(new Pair<>(finalHour, finalMinutes));

        UpdateHours();
    }


}
