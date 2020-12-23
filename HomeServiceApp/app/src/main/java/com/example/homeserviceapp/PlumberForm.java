package com.example.homeserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.HashMap;

public class PlumberForm extends AppCompatActivity {
    RadioGroup radiogrp;
    RadioButton radiobtn;
    Button button;

    TextView t;
    SharedPreferences plumberdetails;
    String service_name;
    SharedPreferences orders;
    HashMap<String, String> hashmap = new HashMap<String, String>();


    public void generateData() {

        hashmap.put("Ac repair", "500");
        hashmap.put("Generel", "200");
        hashmap.put("ips installtion and Repair", "400");
        hashmap.put("FAN repair", "300");
        hashmap.put("Freeze Repair", "400");
        hashmap.put("TV Repair", "500");
        hashmap.put("Secuity cam installtion", "300");
        hashmap.put("physics", "7000");
        hashmap.put("math", "6000");
        hashmap.put("economics", "4000");
        hashmap.put("chemistry", "4000");
        hashmap.put("Islamic History", "4000");
        hashmap.put("bangla", "4000");


    }

    public String getMoney(String data) {
        return hashmap.get(data);
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
        setContentView(R.layout.activity_plumber_form);
        generateData();

        button = (Button) findViewById(R.id.plumber_form_confirm_id);

        radiogrp = (RadioGroup) findViewById(R.id.radiogrp_plumbing_service_id);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plumberdetails = getSharedPreferences("plumbingOrder", Context.MODE_PRIVATE);
                SharedPreferences.Editor order = plumberdetails.edit();

                int rdbtn = radiogrp.getCheckedRadioButtonId();
                radiobtn = (RadioButton) findViewById(rdbtn);
                String services = radiobtn.getText().toString();
                order.putString("serviceName", services);
                order.putString("cost", "500");
                order.apply();
                // Intent in= new Intent(getApplicationContext(),Orderdetailshow.class);
                // in.putExtra("serviceId",services);
                //  startActivity(in);
                PlumberorderDialogues();

                //OpenDialogue();
            }
        });

    }



    public void PlumberorderDialogues() {
        PlumberOrderDialogue od = new PlumberOrderDialogue();
        od.show(getSupportFragmentManager(), "eeee");
    }
}
