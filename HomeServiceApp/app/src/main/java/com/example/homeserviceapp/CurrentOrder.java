package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.homeserviceapp.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder extends AppCompatActivity {

    private ListView listview;
    private List<Order> orderList;
    private currentOrderAdapter ordersAdapter;
    private String str, div, class_name, subjects;
    DatabaseReference databaseReference;
    String uid;
    // String needStr;//form theke asbe
    //String partsNeed[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        //tutorlist=(TextView)findViewById(R.id.tutor_list_view_id);
        //Intent intent=getIntent();
        // String key =databaseReference.push().getKey();

        SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);

        uid = usershared.getString("userid", "");
        // String userloc = usershared.getString("userloc","");


        // SharedPreferences tutorsharing = getSharedPreferences("tutordetails", Context.MODE_PRIVATE);
        //String sub = tutorsharing.getString("sub", "");

        // needStr=sub;
        // partsNeed=needStr.split(" ");
        //  div=tutorsharing.getString("division"," ");
        // class_name=tutorsharing.getString("class"," ");
        // str=div+"_"+class_name;

        // Tutors tutor=new Tutors("fg","DFG","fg","Gf","g","G","g","g");
        // Tutors tutor=new Tutors("Hfgh","GHf","contact","location","dffg","fgdh","gkg","kgk");
        //  databaseReference.child(key).setValue(tutor);
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        // tutorlist.setText(s);
        orderList = new ArrayList<>();
        ordersAdapter = new currentOrderAdapter(CurrentOrder.this, orderList);
        listview = findViewById(R.id.MycurrentOrder_listview_id);
    }


    @Override
    protected void onStart() {

        databaseReference.orderByChild("userid").equalTo(uid).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderList.clear();
                //Log.i("whi1","sdfgasfasfg");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    //String str="r_hfifc";
                    Order myorder = dataSnapshot1.getValue(Order.class);
                   /* try {
                      // str = tutor.getSubject();//database theke asbe
                        Log.i("subjjjects",str);
                    }
                    catch (Exception e)
                    {

                    }
                    //  str="physics_chemistry";
                    String partsIndatabase[]=str.split("_");
                    int flg=0;
                    int count=0;*/


                    //check division && class also
                    //if(tutor.getStatus().equalsIgnoreCase("1")&&tutor.getclass().equalsIgnoreCase(class_name)&& tutor.getDivison().equalsIgnoreCase(div))
                    if (myorder.getDueStatus().equalsIgnoreCase("0") && (!myorder.getStatus().equalsIgnoreCase("0"))&&myorder.getUserid().equalsIgnoreCase(uid))
                        orderList.add(myorder);

                    //  Log.i("ms33g","Matched");


                    //  Log.i("who",subjects.toString());
                    //  if(tutor.getDivison().equalsIgnoreCase(div) && tutor.getclass().equalsIgnoreCase(class_name))

                }
                //listviewAdapter.updateTutorList(tutorsList);
                //  listviewAdapter.notifyDataSetChanged();
                listview.setAdapter(ordersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }


}
