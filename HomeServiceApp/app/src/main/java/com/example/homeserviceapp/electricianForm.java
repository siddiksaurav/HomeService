package com.example.homeserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.HashMap;

public class electricianForm extends AppCompatActivity {
    RadioGroup radiogrp;
    RadioButton radiobtn;
    Button button;
    Button info;
    TextView t;
    ToggleButton ac_btn, generel_btn, tv_btn, fridge_btn, Ips_btn, security_cam_btn;
    SharedPreferences electriciandetails;
    String service_name;
    SharedPreferences orders;
    HashMap<String, String> hashmap = new HashMap<String, String>();


    public void generateData() {

        hashmap.put("AC Repair", "500");
        hashmap.put("General", "200");
        hashmap.put("IPS Installtion and Repair", "400");
        hashmap.put("FAN repair", "300");
        hashmap.put("Freeze Repair", "400");
        hashmap.put("TV Repair", "500");
        hashmap.put("Secuity Cam Installtion", "300");
        hashmap.put("physics", "7000");
        hashmap.put("math", "6000");
        hashmap.put("economics", "4000");
        hashmap.put("chemistry", "4000");
        hashmap.put("Islamic History", "4000");
        hashmap.put("bangla", "4000");


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
        setContentView(R.layout.activity_electrician_form);
        generateData();
        // t=(TextView)findViewById(R.id.elctician_text_btn);
        button = (Button) findViewById(R.id.electrician_form_confirm_id);
        info = (Button) findViewById(R.id.electrician_form_info_id);
        //ac_btn=(ToggleButton) findViewById(R.id.ac_info_btn);
        // generel_btn=(ToggleButton) findViewById(R.id.generel_info_btn);
        // tv_btn=(ToggleButton) findViewById(R.id.Tv_info_btn);
        // fridge_btn=(ToggleButton) findViewById(R.id.freeze_info_btn);
        //Ips_btn=(ToggleButton) findViewById(R.id.Ips_info_btn);
        //  security_cam_btn=(ToggleButton) findViewById(R.id.Security_info_btn);
        radiogrp = (RadioGroup) findViewById(R.id.radiogrp_electrical_service_id);


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialogue();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                electriciandetails = getSharedPreferences("electricalOrder", Context.MODE_PRIVATE);
                SharedPreferences.Editor orderdetails = electriciandetails.edit();

                int rdbtn = radiogrp.getCheckedRadioButtonId();
                radiobtn = (RadioButton) findViewById(rdbtn);
                String services = radiobtn.getText().toString();
                orderdetails.putString("serviceName", services);
                orderdetails.putString("cost", calculateMoney(services));
                orderdetails.apply();
                // Intent in= new Intent(getApplicationContext(),Orderdetailshow.class);
                // in.putExtra("serviceId",services);
                //  startActivity(in);
                orderDialogues();


                //OpenDialogue();
            }
        });

    }

    public void OpenDialogue() {
        dialogue dia = new dialogue();
        dia.show(getSupportFragmentManager(), "example d");
    }

    public void orderDialogues() {
        OrderDialogue od = new OrderDialogue();
        od.show(getSupportFragmentManager(), "eeee");
    }
}
