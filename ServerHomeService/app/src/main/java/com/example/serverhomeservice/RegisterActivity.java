package com.example.serverhomeservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serverhomeservice.Model.profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
     EditText name,contact,email,password,location,Nid,services,rating;
     Button regbtn;
     String key,em,nam,con,loc,pass,nid_num;
     DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference= FirebaseDatabase.getInstance().getReference("Electrician");
        //Toast.makeText(getApplicationContext(), databaseReference.toString(), Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_register);
        email=(EditText)findViewById(R.id.provider_email);
        password=(EditText)findViewById(R.id.provider_password);
        location=(EditText)findViewById(R.id.provider_location);
        contact=(EditText)findViewById(R.id.provider_contact_No);
        name=(EditText)findViewById(R.id.provider_name);
        contact=(EditText)findViewById(R.id.provider_contact_No);
        Nid=(EditText)findViewById(R.id.provider_NId) ;
        regbtn=(Button)findViewById(R.id.btn_register);
        Toast.makeText(getApplicationContext(), "aaaaaa", Toast.LENGTH_SHORT).show();
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "aluiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();
                Registration();
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void Registration()
    {

          em=email.getText().toString();
          pass=password.getText().toString();
          con=contact.getText().toString();
          loc=location.getText().toString();
          nam=name.getText().toString();
          nid_num=Nid.getText().toString();
        Toast.makeText(getApplicationContext(), em, Toast.LENGTH_SHORT).show();

         String status="0";
         String url="hhgggg ";
         String rating= "3";
         String services=" hhh";
        // check();

         key =databaseReference.push().getKey();
        Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();


        profile pro=new profile(nam,con,nid_num,em,pass,loc,status,url,services,rating);
         databaseReference.child(key).setValue(pro);
        Toast.makeText(this, "Register is successful", Toast.LENGTH_SHORT).show();




    }
    public void check(){
        boolean isok = true;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            email.setError("Enter a valid email address");
            email.requestFocus();
            isok = false;
            return;
        }

        if (pass.isEmpty()) {
            password.setError("Enter a password");
            password.requestFocus();
            isok = false;
            return;
        }

        if(pass.length() < 6){
            password.setError("Password must be at least 6 digits");
            password.requestFocus();
            isok = false;
            return;
        }

        if (loc.isEmpty()) {
            location.setError("Your Location Please");
            location.requestFocus();
            isok = false;
            return;
        }

        if (con.isEmpty()) {
            contact.setError("Your Contact No Please");
            contact.requestFocus();
            isok = false;
            return;
        }

        if(contact.length() != 11) {
             contact.setError("Phone No is not Valid");
             contact.requestFocus();
            isok = false;
            return;
        }


        if(isok == true) {
            Toast.makeText(this, "Register is successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
