package com.example.serverhomeservice;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serverhomeservice.Model.Electrician;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView Contact_No, userPassword;
    Button signIn, register;

    String usercontact;
    int flag=0;
    String Password;


    SharedPreferences usersharing;


    public void userlogin() {
        usercontact = Contact_No.getText().toString();
        Password = userPassword.getText().toString();

        ProgressDialog prgdlg = new ProgressDialog(MainActivity.this);
        prgdlg.setMessage("Please Wait...");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_table = database.getReference("Electrician");

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

        }
        else{

            user_table.orderByChild("contactNo").equalTo(usercontact).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot d : dataSnapshot.getChildren()) {

                        flag = 1;

                        Electrician user=d.getValue(Electrician.class);

                        if (Password.equals(user.getPassword())) {
                            Toast.makeText(MainActivity.this, "login succesful", Toast.LENGTH_LONG).show();
                            //Toast.makeText(MainActivity.this, d.getKey(), Toast.LENGTH_SHORT).show();
                            usersharing = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
                            SharedPreferences.Editor usereditor = usersharing.edit();
                            usereditor.putString("userid", d.getKey());
                            usereditor.putString("userloc", user.getLocation());
                            usereditor.putString("usercontact", user.getContactNo());
                            usereditor.apply();

                            Intent intent = new Intent(getApplicationContext(), NavigationUI.class);
                            startActivity(intent);

                        } else
                            Toast.makeText(MainActivity.this, "Contact No or password is not correct..Please Try Again ", Toast.LENGTH_LONG).show();


                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(flag==0)
        {
            Toast.makeText(getApplicationContext(),"User Doesnt Exist",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this, MyService.class);
        startService( intent);
        Contact_No = (TextView) findViewById(R.id.userID);
        userPassword = (TextView) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.login_signIn_btn);
        register = (Button) findViewById(R.id.login_register_btn);

      //  Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userlogin();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "alu", Toast.LENGTH_SHORT).show();
                Intent reg = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(reg);
            }
        });

    }
}

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Request");
        Log.i("","huhu2");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("","huhu1");
                Request r=dataSnapshot.getValue(Request.class);
                Log.i(r.getStatus(),"huhu");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //myRef.setValue("Hello, World!");
      //  Toast.makeText(this,myRef.toString(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,"fdf",Toast.LENGTH_LONG).show();

       // myRef.setValue("Hello, World!");
    }
    public void showNotification(String key)
    {
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getBaseContext(),"fgfdgdfgr");
        Log.i("n","n1");
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("fgdg")
                .setContentInfo("Updated")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Your Order #"+key+ "was updated");

        Log.i("n","n2");
        NotificationManager nm=(NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1,builder.build());
        Log.i("n","n3");
    }
}*/
