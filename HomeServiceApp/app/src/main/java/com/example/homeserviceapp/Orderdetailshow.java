package com.example.homeserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Orderdetailshow extends AppCompatActivity {
    TextView t1, t2;
    Button cancel, confirm;
    HashMap<String, String> hashmap = new HashMap<String, String>();


    public void generateData() {

        hashmap.put("Ac repair", "500");
        hashmap.put("AC Installation", "400");
        hashmap.put("FAN repair", "300");
        hashmap.put("FREEZE installation", "400");
        hashmap.put("TV Repair", "500");
        hashmap.put("TV installation", "300");
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
        setContentView(R.layout.activity_orderdetailshow);
        t1 = (TextView) findViewById(R.id.service_name_show_id);
        t2 = (TextView) findViewById(R.id.service_cost_show_id);
        Intent in = getIntent();
        generateData();
        String service_name = in.getStringExtra("serviceId");
        t1.setText(service_name);
        t2.setText(calculateMoney(service_name));
        Button cancel = (Button) findViewById(R.id.prder_detail_cancel_id);
        Button confirm = (Button) findViewById(R.id.prder_detail_confirm_id);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceList.class);
                startActivity(intent);

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceproviderList.class);
                //  intent.putExtra("payment",t2.getText().toString());
                startActivity(intent);

            }
        });


    }
}
