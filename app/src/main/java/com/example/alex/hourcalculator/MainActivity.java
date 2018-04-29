package com.example.alex.hourcalculator;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;


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

        Button resetButton = (Button) findViewById(R.id.resetHoursBtn);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hoursList.clear();
                hoursSumText.setText("Aggiungi un orario");
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
        if (requestCode == 0 && data != null) {
            if (resultCode == RESULT_OK) {
                int finalHour = data.getIntExtra("finalHour",0);
                int finalMinutes = data.getIntExtra("finalMinutes", 0);

                hoursList.add(new Pair<>(finalHour, finalMinutes));

                UpdateHours();
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }


    }


}
