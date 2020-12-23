package com.example.serverhomeservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class orderStatus extends AppCompatActivity {
    Button button;
    Intent in;
    int f=0;

    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        button=(Button) findViewById(R.id.OnTheWaybutton);
        Log.i("ert11","fff");
        in=getIntent();
        Toast.makeText(this,in.getStringExtra("orderID"),Toast.LENGTH_LONG).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f++;

                Intent intent=new Intent(getApplicationContext(),orderFinished.class);
                intent.putExtra("orderID",in.getStringExtra("orderID"));

                //Toast.makeText(this,"login succesful1",Toast.LENGTH_LONG).show();
                Log.i("ert"+f,in.getStringExtra("orderID"));
                changeStatus("2",in.getStringExtra("orderID"));
                startActivity(intent);


            }






        });


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

}
