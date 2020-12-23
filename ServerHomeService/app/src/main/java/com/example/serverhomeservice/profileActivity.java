package com.example.serverhomeservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {

    EditText name;
    EditText contact;
    EditText password;
    Button button;
    FirebaseDatabase database =FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference("Electrician") ;





    public void changeStatus( final String  text,final String Child, String ID)
    {

        myRef=database.getReference("Electrician") ;
      //  Log.i("ff","Fsaf");
        myRef.orderByKey().equalTo(ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    d.child(Child).getRef().setValue(text);
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
        setContentView(R.layout.activity_profile);


        name=findViewById(R.id.editTextName);
        contact=findViewById(R.id.editTextContact);
        password=findViewById(R.id.editTextPassword);
        button=findViewById(R.id.buttonSave);




        //Log.i("ff","Fsaf");
        myRef.orderByKey().equalTo("e1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {

                     ServiceProvider sp=d.getValue(ServiceProvider.class);
                     name.setText(sp.getName());
                    contact.setText(sp.getContactNo());
                    password.setText(sp.getPassword());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus(name.getText().toString(),"name","e1");
                changeStatus(contact.getText().toString(),"contactNo","e1");
                changeStatus(password.getText().toString(),"password","e1");
                 Toast.makeText(profileActivity.this,"Profile Updated succesfully", Toast.LENGTH_LONG).show();
                Intent in=new Intent(getApplicationContext(),NavigationUI.class);
                startActivity(in);



            }
        });











    }
}
