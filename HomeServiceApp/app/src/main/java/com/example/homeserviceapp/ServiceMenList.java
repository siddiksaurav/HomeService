package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import  com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceMenList extends AppCompatActivity {
    TextView service_provider_name, service_provider_rating, service_provider_location;
    private String name, rating, location, service_type;
    DatabaseReference databaseReference;
    RecyclerView mRecyclerView;
    //  private FirebaseRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_men_list);
        //service_provider_location=(TextView)findViewById(R.id.service_provider_location);
        //service_provider_name=(TextView)findViewById(R.id.service_provider_name);
        //service_provider_rating=(TextView)findViewById(R.id.service_provider_rating);
        Intent intent = getIntent();
        service_type = intent.getStringExtra("selectedService");
        // databaseReference= FirebaseDatabase.getInstance().getReference("service_type");
        databaseReference = FirebaseDatabase.getInstance().getReference("Electrician");
        mRecyclerView = findViewById(R.id.RecyclerView_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        // mRef.child("User").child( product.getUid()).child("fullName")
        //databaseReference.orderByChild("location").equalTo("dhanmondhi").limitToFirst(1).addValueEventListener(new ValueEventListener() {
        /*  databaseReference.child("Electrician").child("1").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ServiceProvider serviceprovider=dataSnapshot.getValue(ServiceProvider.class);
                name=serviceprovider.getName();
                rating=serviceprovider.getRating();
                location=serviceprovider.getLocation();
                service_provider_location.setText(location);
                service_provider_name.setText(name);
                service_provider_rating.setText(rating);
                Log.i("whi1","sdfgasfasfg");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

       /* super.onStart();
        FirebaseRecyclerAdapter<ServiceProvider, ViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<ServiceProvider, ViewHolder>(
              ServiceProvider.class,
              R.layout.row,
              ViewHolder.class,
              databaseReference

        ) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull ServiceProvider serviceProvider) {

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };
    }*/
    }
}