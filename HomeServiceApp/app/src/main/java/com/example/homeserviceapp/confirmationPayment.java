package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.homeserviceapp.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class confirmationPayment extends AppCompatActivity {

    TextView t;
    Intent in;
    Button button;
    Button btn;


    public void changeStatus(final String status, String orderID, final String child) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Order");
        Log.i("ff", "Fsaf");
        myRef.orderByKey().equalTo(orderID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    d.child(child).getRef().setValue(status);
                    // ServiceProvider sp=d.getValue(ServiceProvider.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void ButtonClick(View v) {
        //changeStatus(ratingBar.getRating() + "", in.getStringExtra("orderID"), "rating");
    }

    public void Ratingbox() {
       final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.ratinglayout);
        dialog.setTitle("Rating Box");
        final RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        dialog.show();
        btn =dialog.findViewById(R.id.confirmRating);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus(ratingBar.getRating()+"", in.getStringExtra("orderID"),"rating");
                Intent intent = new Intent(confirmationPayment.this, NavigationUI.class);
                //intent.putExtra("userloc","dhanmondhi");
                //  intent.putExtra("orderID",myorder.getKey());
                startActivity(intent);
            }
        });


    }

    public void showServices(String orderID) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Order");
        Log.i("ff", "Fsaf");
        myRef.orderByKey().equalTo(orderID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    //  d.child("dueStatus").getRef().setValue(status);
                    Order order = d.getValue(Order.class);
                    t.setText("Your Services: " + order.getServices() + " Money: " + order.getTotalpayment());
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
        setContentView(R.layout.activity_confirmation_payment);
        in = getIntent();
        t = findViewById(R.id.textView3);
        // ratingBar=findViewById(R.id.confirmRating);
        Log.i("iu", in.getStringExtra("orderID"));
        showServices(in.getStringExtra("orderID"));
        button = findViewById(R.id.confirmPaymentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus("1", in.getStringExtra("orderID"), "dueStatus");
                Ratingbox();

               /* Dialog dialog=new Dialog(confirmationPayment.this);
                dialog.setContentView(R.layout.ratinglayout);
                dialog.setTitle("Rating Box");
                ratingBar=findViewById(R.id.ratingBar);
                btn=findViewById(R.id.confirmRating);
                dialog.show();
                */
                // btn=findViewById(R.id.confirmRating);

/*        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus("6"+"", in.getStringExtra("orderID"),"rating");
            }
        });
*/



            }
        });


    }
}
