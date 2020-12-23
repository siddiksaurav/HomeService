package com.example.homeserviceapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.homeserviceapp.Model.Order;
import com.example.homeserviceapp.Model.Tutors;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class listViewAdapter extends ArrayAdapter<Tutors> {

    private Activity context;
    private List<Tutors> tutorlist;
    String key, status, contact;
    private String uid, sub;

    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    DatabaseReference dbref = fdb.getReference("Tutor");
    SharedPreferences orders;
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

    public listViewAdapter(Activity context, List<Tutors> tutorlist, String uid, String sub) {
        super(context, R.layout.tutorlist_layout, tutorlist);
        this.context = context;
        this.tutorlist = tutorlist;
        this.uid = uid;
        this.sub = sub;
    }

    public void updateTutorList(List<Tutors> tutorslist) {
        this.tutorlist = tutorlist;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        generateData();
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tutorlist_layout, null, true);
        final Tutors tutors = tutorlist.get(position);
        TextView t1, t2, t3,amount;
        Button btn;
        ImageView img;
        status = tutors.getStatus();
        t1 = (TextView) view.findViewById(R.id.tutor_name_textview_id);
        t2 = (TextView) view.findViewById(R.id.tutor_rating_textview_id);
        t3 = (TextView) view.findViewById(R.id.tutor_experience_textview_id);
        amount=(TextView)view.findViewById(R.id.tutor_select_money_item_id);
        btn = (Button) view.findViewById(R.id.tutor_select_list_item_id);
        img = (ImageView) view.findViewById(R.id.tutor_image_id);
        String s = tutors.getUrl().toString();

        t1.setText("Name :" + tutors.getName());
        t2.setText("Rating: " + tutors.getRating());
        t3.setText("Experience :" + tutors.getExperience());
       // Toast.makeText(context, calculateMoney(sub.toLowerCase()), Toast.LENGTH_LONG).show();
        amount.setText("Honorarium:"+calculateMoney(sub.toLowerCase()));
        contact = tutors.getContactNo();
        Picasso.get().load(s).into(img);
        // String u=tutors.getUrl().toString();
        // Picasso.with(this.context).load(u).into(img);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent =new Intent(context,ConfirmationOrder.class);
               // Toast.makeText(context, contact, Toast.LENGTH_LONG).show();
                // Log.i("ht",tutors.getContactNo());
                Log.i("ht1", tutors.getName());
                Log.i("ht1", position + "");
                dbref.orderByChild("contactNo").equalTo(tutors.getContactNo()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {

                            Log.i(key, "inside");

                            key = d.getKey();
                            orderPlace(key);
                           // Toast.makeText(context, key, Toast.LENGTH_LONG).show();
                            break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Log.i(key, "outside");

                context.startActivity(new Intent(context, ConfirmationOrder.class));
            }
        });


        return view;
    }

    public void orderPlace(String key) {

        Log.i(key, "y11");
        String providerid = key;
        Log.i(status, "y11");
        String stat = status;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf1.format(new Date());
        String ordertime = currentDateandTime;


        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Order");
        String key1 = databaseReference1.push().getKey();
        String name = "";






      //  Toast.makeText(context, "provider: "+key, Toast.LENGTH_LONG).show();
        Order order = new Order(uid, providerid, ordertime, "-1", sub, "100", "0", key1, "2","-1");
        databaseReference1.child(key1).setValue(order);
    }
}
