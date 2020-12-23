package com.example.serverhomeservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class orderReview extends AppCompatActivity {
    TextView text;
    Button confirm,cancel,location;
    Intent in;
    FirebaseDatabase database =FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference("Order") ;

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }

    public void changeStatus( final String  status, String orderID)
    {
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("Order") ;
        Log.i("ff","Fsaf");
        myRef.orderByKey().equalTo(orderID).addListenerForSingleValueEvent(new ValueEventListener() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_review2);
        text=(TextView)findViewById(R.id.locaion_id) ;
        confirm=(Button)findViewById(R.id.Confirm_btn);
        cancel=(Button)findViewById(R.id.Cancel_btn);
        location=(Button)findViewById(R.id.map_btn);
         in=getIntent();
        text.setText(in.getStringExtra("userloc"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),orderStatus.class);
                intent.putExtra("orderID",in.getStringExtra("orderID"));
                startActivity(intent);
                changeStatus("1",in.getStringExtra("orderID"));

                Log.i("whoo1","ui");


            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),HomepageActivity.class);
                intent.putExtra("orderID",in.getStringExtra("orderID"));
                //  Toast.makeText(MainActivity.this,"login succesful",Toast.LENGTH_LONG).show();
                startActivity(intent);
                changeStatus("0",in.getStringExtra("orderID"));
                Log.i("whoo","ui");
            }

        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("Maps Location",in.getStringExtra("userloc"));
                startActivity(intent);
               // changeStatus("gdfg","jcgj");
            }
        });


    }

}
