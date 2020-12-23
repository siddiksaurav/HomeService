package com.example.serverhomeservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class HomepageActivity extends AppCompatActivity {
    String providerID;
    Switch activeswitch;
    TextView activetextView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference("Electrician") ;
    private String status="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        SharedPreferences usershared=getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        providerID=usershared.getString("userid","");

        activeswitch = (Switch) findViewById(R.id.activeswitch);
        activetextView = (TextView) findViewById(R.id.activetextView);
        activeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    status="1";
                    activetextView.setText("ACTIVE");
                }
                else {
                    status="0";
                    activetextView.setText("INACTIVE");
                }
                myRef.orderByKey().equalTo(providerID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            d.child("status").getRef().setValue(status);
                            // ServiceProvider sp=d.getValue(ServiceProvider.class);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                }
        });


    }
}
