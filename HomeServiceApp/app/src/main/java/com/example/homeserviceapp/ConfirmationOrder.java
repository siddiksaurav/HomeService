package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeserviceapp.Model.Order;
import com.example.homeserviceapp.Model.Payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfirmationOrder extends AppCompatActivity {
    TextView tviewserve,payment;
    Payment p;
    String glob;
    Button btn;


    //@Override
  /*  public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),ServiceList.class);
        startActivity(intent);
    }*/

    /*@Override
    public void onBackPressed() {
       // super.onBackPressed();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order);
        btn=(Button)findViewById(R.id.back_btn);
        tviewserve=(TextView)findViewById(R.id.textView3);
        payment=(TextView)findViewById(R.id.textView31);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ServiceList.class);
                startActivity(intent);
               // finish();
            }
        });
      //fa(glob);


      // Toast.makeText(getApplicationContext(), glob, Toast.LENGTH_LONG).show();
       // Log.i("wer", myRef.toString());
        //myRef.child("o1").setValue("Dfsf");
        //myRef.child("o2").child("status").setValue("3");
       // g("40");
        //Toast.makeText(getApplicationContext(),"pay"+ glob, Toast.LENGTH_LONG).show();

        // tview=(TextView)findViewById(R.id.confirmation_order_id);

        //String s=tview.getText().toString();
        // tview.setText(s);
    }

public void fa(String s)
{
   // Toast.makeText(getApplicationContext(),"pay"+ s, Toast.LENGTH_LONG).show();
}
    protected void onStart() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Payment");
        myRef.orderByChild("name").equalTo("Ac repair").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren())
                {
                    p=d.getValue(Payment.class);
                    glob=p.getAmount();
                   // Toast.makeText(getApplicationContext(), glob, Toast.LENGTH_LONG).show();

                    //  Order order = new Order("uid", "providerid", "ordertime", "-1", "sub", "100", "0", key1, "2","-1");
                    // myRef.child("key1").setValue(order);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
