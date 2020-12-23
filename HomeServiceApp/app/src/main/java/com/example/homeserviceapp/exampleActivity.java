package com.example.homeserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class exampleActivity extends AppCompatActivity {




    HashMap<String, String> hashmap = new HashMap<String, String>();


    public void generateData() {

        hashmap.put("AC Repair", "500");
        hashmap.put("General", "200");
        hashmap.put("IPS Installtion and Repair", "400");
        hashmap.put("FAN Repair", "300");
        hashmap.put("Freeze Repair", "400");
        hashmap.put("TV Repair", "500");
        hashmap.put("Secuity cam installtion", "300");
        hashmap.put("physics", "7000");
        hashmap.put("math", "6000");
        hashmap.put("economics", "4000");
        hashmap.put("chemistry", "4000");
        hashmap.put("islamic history", "4000");
        hashmap.put("bangla", "4000");
        hashmap.put("Water Meter Servicing", "300");
        hashmap.put("Water Tap Servicing" ,"500");
        hashmap.put("Kitchen Sink Services", "400");
        hashmap.put("Wash Basin Services", "400");
        hashmap.put("Plumbing CheckUp", "400");


    }

    public String getMoney(String data) {
        return hashmap.get(data).toString();
    }

    public String calculateMoney(String allServices) {
        String s[] = allServices.split("_");
        int total = 0;
        String result;
        for (int i = 0; i < s.length; i++) {
            // Log.i(" vv4",result);
            result = getMoney(s[i]);
            Log.i(" vv3", result);
            total = total + Integer.parseInt(result);


        }
        return total + "";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateData();
        setContentView(R.layout.activity_example);
      //  Toast.makeText(this, calculateMoney("physics_chemistry"), Toast.LENGTH_LONG).show();
    }
}
