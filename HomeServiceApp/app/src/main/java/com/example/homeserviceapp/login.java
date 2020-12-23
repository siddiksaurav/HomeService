package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeserviceapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    TextView Contact_No, userPassword;
    Button signIn, register;
    String usercontact;
    int flag = 0;
    String Password;

    SharedPreferences usersharing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Contact_No = (EditText) findViewById(R.id.userID);
        userPassword = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.login_signIn_btn);
        register = (Button) findViewById(R.id.login_register_btn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
                //Intent signin= new Intent(getApplicationContext(),ServiceList.class);
                //startActivity(signin);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(getApplicationContext(), userRegistration.class);
                startActivity(reg);

            }
        });
    }

    public void userlogin() {
        usercontact = Contact_No.getText().toString();
        Password = userPassword.getText().toString();

        ProgressDialog prgdlg = new ProgressDialog(login.this);
        prgdlg.setMessage("Please Wait...");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_table = database.getReference("User");

        if (usercontact.isEmpty()) {
            Contact_No.setError("Your Contact No Please");
            Contact_No.requestFocus();

        } else if (Password.isEmpty()) {
            userPassword.setError("Enter a password");
            userPassword.requestFocus();

        } else if (usercontact.length() != 11) {
            Contact_No.setError("Phone No is not Valid");
            Contact_No.requestFocus();

        } else if (Password.length() < 6) {
            userPassword.setError("Password must be at least 6 digits");
            userPassword.requestFocus();
        } else {

            user_table.orderByChild("contactNo").equalTo(usercontact).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot d : dataSnapshot.getChildren()) {

                        flag = 1;
                        User user = d.getValue(User.class);


                        if (Password.equals(user.getPassword())) {
                            //Toast.makeText(login.this, d.getKey().toString(), Toast.LENGTH_LONG).show();

                            usersharing = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
                            SharedPreferences.Editor usereditor = usersharing.edit();
                            usereditor.putString("userid", d.getKey());
                            usereditor.putString("username", user.getName());
                            usereditor.putString("userloc", user.getLocation());
                            usereditor.putString("usercontact", user.getContactNo());
                            usereditor.apply();

                            Intent intent = new Intent(getApplicationContext(), NavigationUI.class);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(login.this, "Contact No or password is not correct..Please Try Again ", Toast.LENGTH_LONG).show();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (flag == 0) {
            //Toast.makeText(getApplicationContext(), "User Does not Exist", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean check() {
        if (usercontact.isEmpty()) {
            Contact_No.setError("Your Contact No Please");
            Contact_No.requestFocus();
            return false;
        }

        if (usercontact.length() != 11) {
            Contact_No.setError("Phone No is not Valid");
            Contact_No.requestFocus();
            return false;
        }

        if (Password.isEmpty()) {
            userPassword.setError("Enter a password");
            userPassword.requestFocus();
            return false;
        }

        if (Password.length() < 6) {
            userPassword.setError("Password must be at least 6 digits");
            userPassword.requestFocus();
            return false;
        }
        return true;
    }
}
