package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.homeserviceapp.Model.Tutors;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TutorList extends AppCompatActivity {
    TextView tutorlist;
    private ListView listview;
    private List<Tutors> tutorsList;
    private listViewAdapter listviewAdapter;
    private String str, div, class_name, subjects;
    DatabaseReference databaseReference;
    String needStr;//form theke asbe
    String partsNeed[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);
        //tutorlist=(TextView)findViewById(R.id.tutor_list_view_id);
        Intent intent = getIntent();
        // String key =databaseReference.push().getKey();

        SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);

        String userid = usershared.getString("userid", "");
        String userloc = usershared.getString("userloc", "");


        SharedPreferences tutorsharing = getSharedPreferences("tutordetails", Context.MODE_PRIVATE);
        String sub = tutorsharing.getString("sub", "");

        needStr = sub;
        partsNeed = needStr.split("_");
        div = tutorsharing.getString("division", " ");
        class_name = tutorsharing.getString("class", " ");
        str = div + "_" + class_name;

        // Tutors tutor=new Tutors("fg","DFG","fg","Gf","g","G","g","g");
        // Tutors tutor=new Tutors("Hfgh","GHf","contact","location","dffg","fgdh","gkg","kgk");
        //  databaseReference.child(key).setValue(tutor);
        databaseReference = FirebaseDatabase.getInstance().getReference("Tutor");
        // tutorlist.setText(s);
        tutorsList = new ArrayList<>();
        listviewAdapter = new listViewAdapter(TutorList.this, tutorsList, userid, sub);
        listview = findViewById(R.id.tutor_listview_id);
    }


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tutorsList.clear();
                Log.i("whi1", "sdfgasfasfg");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String str = "r_hfifc";
                    Tutors tutor = dataSnapshot1.getValue(Tutors.class);
                    try {
                        str = tutor.getSubject();//database theke asbe
                        Log.i("subjjjects", str);
                    } catch (Exception e) {

                    }
                    //  str="physics_chemistry";
                    String partsIndatabase[] = str.split("_");
                    int flg = 0;
                    int count = 0;
                    for (int i = 0; i < partsNeed.length; i++) {
                        for (int j = 0; j < partsIndatabase.length; j++) {
                            if (partsNeed[i].equalsIgnoreCase(partsIndatabase[j])) {
                                count++;
                                break;

                            }
                        }
                    }
                    if (partsNeed.length == count) {
                        //check division && class also
                        if (tutor.getStatus().equalsIgnoreCase("1") && tutor.getclass().equalsIgnoreCase(class_name) && tutor.getDivison().equalsIgnoreCase(div))
                            tutorsList.add(tutor);

                        Log.i("ms33g", "Matched");
                    } else {
                        System.out.println("NOt matched");
                        //Log.i("msg","Not Matched");
                    }
                    //  Log.i("who",subjects.toString());
                    //  if(tutor.getDivison().equalsIgnoreCase(div) && tutor.getclass().equalsIgnoreCase(class_name))

                }
                //listviewAdapter.updateTutorList(tutorsList);
                //  listviewAdapter.notifyDataSetChanged();
                listview.setAdapter(listviewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();


    }


}
