package com.example.alex.hourcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Date> hoursList;
    EditText hoursSumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoursSumText = (EditText) findViewById(R.id.hoursSumText);

        hoursList = new ArrayList<>();

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
            long millis =0;

            for(Date date:hoursList){
                millis = millis + date.getTime();
            }

            int sumHour = (int) millis/(1000 * 60 * 60);
            int sumMinutes = (int) (millis/(1000*60)) % 60;

            hoursSumText.setText(Integer.toString(sumHour)+": " + Integer.toString(sumMinutes));
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        int finalHour = data.getIntExtra("finalHour",0);
        int finalMinutes = data.getIntExtra("finalMinutes", 0);

        Date date = new Date();
        date.setHours(finalHour);
        date.setMinutes(finalMinutes);

        hoursList.add(date);

        UpdateHours();
    }


}
