package com.example.homeserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class tutorService extends AppCompatActivity {
    RadioGroup radiogrp_class, radiogrp_division;
    Button btn_choice_submit;
    CheckBox english, bangla, ict, math, physcis, chemistry, biology, economics, islamic_history;
    RadioButton radio_btn_class, radio_btn_division;
    private String class_name, division;

    SharedPreferences tutorsharing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_service2);
        radiogrp_class = (RadioGroup) findViewById(R.id.radiogrp_class_id);
        radiogrp_division = (RadioGroup) findViewById(R.id.radiogrp_division_id);
        english = (CheckBox) findViewById(R.id.sub_english);
        bangla = (CheckBox) findViewById(R.id.sub_bangla);
        ict = (CheckBox) findViewById(R.id.sub_ict);
        math = (CheckBox) findViewById(R.id.sub_math);
        physcis = (CheckBox) findViewById(R.id.sub_physics);
        chemistry = (CheckBox) findViewById(R.id.sub_chemistry);
        economics = (CheckBox) findViewById(R.id.sub_economics);
        biology = (CheckBox) findViewById(R.id.sub_biology);
        islamic_history = (CheckBox) findViewById(R.id.sub_islamic_history);
        btn_choice_submit = (Button) findViewById(R.id.btn_tutor_form_submit);
//       Log.i("fdgdg",division.toString());
        btn_choice_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmChoice();
            }
        });


    }

    public void confirmChoice() {
        int classId = radiogrp_class.getCheckedRadioButtonId();
        int divisionId = radiogrp_division.getCheckedRadioButtonId();
        radio_btn_class = (RadioButton) findViewById(classId);
        radio_btn_division = (RadioButton) findViewById(divisionId);
        class_name = radio_btn_class.getText().toString();
        division = radio_btn_division.getText().toString();
        StringBuilder subjects = new StringBuilder();

        if (biology.isChecked()) {
            String value = biology.getText().toString();
            subjects.append(value + "_");
        }
        if (math.isChecked()) {
            String value = math.getText().toString();
            subjects.append(value + "_");
        }
        if (chemistry.isChecked()) {
            String value = chemistry.getText().toString();
            subjects.append(value + "_");
        }
        if (physcis.isChecked()) {
            String value = physcis.getText().toString();
            subjects.append(value + "_");
        }
        if (english.isChecked()) {
            String value = english.getText().toString();
            subjects.append(value + "_");
        }
        if (bangla.isChecked()) {
            String value = bangla.getText().toString();
            // Log.i("yes"+"yess");
            subjects.append(value + "_");
        }
        if (ict.isChecked()) {
            String value = ict.getText().toString();
            subjects.append(value + "_");
        }
        if (economics.isChecked()) {
            String value = economics.getText().toString();
            subjects.append(value + "_");
        }
        if (islamic_history.isChecked()) {
            String value = islamic_history.getText().toString();
            subjects.append(value + "_");
        }
        String s = subjects.toString();

        Intent intent = new Intent(getApplicationContext(), TutorList.class);

        tutorsharing = getSharedPreferences("tutordetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor usereditor = tutorsharing.edit();
        usereditor.putString("sub", s);
        usereditor.putString("class", class_name);
        usereditor.putString("division", division);
        usereditor.apply();

        startActivity(intent);


    }
}
