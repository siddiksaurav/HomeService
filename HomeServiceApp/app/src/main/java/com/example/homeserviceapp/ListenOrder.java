package com.example.homeserviceapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


import com.example.homeserviceapp.Model.Order;
import com.example.homeserviceapp.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListenOrder extends Service implements ChildEventListener {
    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef1;
    String userLoc;
    String orderID;
    int flag = 0;
    private String userID;


    public ListenOrder() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences usershared1 = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        // usershared1.getString("userid","");

        Toast.makeText(this, usershared1.getString("userid", ""), Toast.LENGTH_LONG).show();
        // Intent intent=new Intent(MainActivity.this,ConfirmationOrder.class);
        //  Intent intent = new Intent(MainActivity.this, login.class);
        //startActivity(intent);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Order");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"hiiiiiii,Toast.LENGTH_LONG).show();
        // Toast.makeText(this,"fgsfs",Toast.LENGTH_LONG).show();


        myRef.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Order d = dataSnapshot.getValue(Order.class);
        SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        userID = usershared.getString("userid", "");
        // Toast.makeText(this,userID.toString(),Toast.LENGTH_LONG).show();
        Log.i("n", "n5");
        createNotificationChannel();
        myRef1 = database.getReference("User");
        // myRef1= myRef1.orderByKey().equalTo(d.getUserid()).getRef();
        String Phone = "0011";

        //calculateMoney("AC repair");
        //showNotification("services: "+d.getServices()+" Location: "+"loc","Dhanmondi","o2");
        // SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);


        // userID = usershared.getString("userid","");

        ;
        //Intent intent=new Intent(getApplicationContext(),ConfirmationOrder.class);
        //  Intent intent = new Intent(M3ainActivity.this, login.class);
        // startActivity(intent);

        d = dataSnapshot.getValue(Order.class);
        //   Toast.makeText(this,d.getUserid(),Toast.LENGTH_LONG).show();
        // Toast.makeText(this,dataSnapshot.getKey().toString(),Toast.LENGTH_LONG).show();
        Log.i("n", "n5");
        createNotificationChannel();
        //Toast.makeText(this,userID,Toast.LENGTH_LONG).show();


        // myRef1= myRef1.orderByKey().equalTo(d.getUserid()).getRef();
        // User r=myRef1.child("contactNo").toString();
        try {
            if (d.getUserid().equalsIgnoreCase(userID)) {
                if (d.getStatus().equalsIgnoreCase("3") && d.getDueStatus().equalsIgnoreCase("0")) {

                    // final String s1 = calculateMoney(d.getServices());
                    orderID = dataSnapshot.getKey().toString();
                    myRef1.orderByKey().equalTo(d.getUserid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot d : dataSnapshot.getChildren()) {
                                User u = d.getValue(User.class);
                                userLoc = u.getLocation();
                                Log.i("what", "df");
                                showNotification1("your Order is finished,check payment", userLoc, orderID);

                                //showNotification("Contact no: "+u.getContactNo()+" Location: "+u.getLocation(),userLoc,orderID);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else if (d.getDueStatus().equalsIgnoreCase("0")) {
                    if (!d.getStatus().equalsIgnoreCase("-1"))
                        showNotification(d.getStatus().toString());
                }
            }
        }
        catch(Exception e)
        {

        }

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Order d = dataSnapshot.getValue(Order.class);
        SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        userID = usershared.getString("userid", "");
        // Toast.makeText(this,userID.toString(),Toast.LENGTH_LONG).show();
        Log.i("n", "n5");
        createNotificationChannel();
        myRef1 = database.getReference("User");
        // myRef1= myRef1.orderByKey().equalTo(d.getUserid()).getRef();
        String Phone = "0011";

        //calculateMoney("AC repair");
        //showNotification("services: "+d.getServices()+" Location: "+"loc","Dhanmondi","o2");
        // SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);


        // userID = usershared.getString("userid","");

        ;
        //Intent intent=new Intent(getApplicationContext(),ConfirmationOrder.class);
        //  Intent intent = new Intent(M3ainActivity.this, login.class);
        // startActivity(intent);

        d = dataSnapshot.getValue(Order.class);
        //   Toast.makeText(this,d.getUserid(),Toast.LENGTH_LONG).show();
        // Toast.makeText(this,dataSnapshot.getKey().toString(),Toast.LENGTH_LONG).show();
        Log.i("n", "n5");
        createNotificationChannel();
        //Toast.makeText(this,userID,Toast.LENGTH_LONG).show();


        // myRef1= myRef1.orderByKey().equalTo(d.getUserid()).getRef();
        // User r=myRef1.child("contactNo").toString();
        if (d.getUserid().equalsIgnoreCase(userID)) {
            if (d.getStatus().equalsIgnoreCase("3") && d.getDueStatus().equalsIgnoreCase("0")) {

                // final String s1 = calculateMoney(d.getServices());
                orderID = dataSnapshot.getKey().toString();
                myRef1.orderByKey().equalTo(d.getUserid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            User u = d.getValue(User.class);
                            userLoc = u.getLocation();
                            Log.i("what", "df");
                            showNotification1("your Order is finished,check payment", userLoc, orderID);

                            //showNotification("Contact no: "+u.getContactNo()+" Location: "+u.getLocation(),userLoc,orderID);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else if (d.getDueStatus().equalsIgnoreCase("0")) {
                if (!d.getStatus().equalsIgnoreCase("-1"))
                    showNotification(d.getStatus().toString());
            }
        }

    }


    public void showNotification1(String key, String userLoc, String orderID) {
        Intent intent = new Intent(getBaseContext(), confirmationPayment.class);
        intent.putExtra("userloc", userLoc);
        intent.putExtra("orderID", orderID);
        PendingIntent content = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), "CHANNEL_ID");
        Log.i("n", "n1");
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("Content Title")
                .setWhen(System.currentTimeMillis())
                .setTicker("Ticker")
                .setContentInfo("Updated")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentText(key);

        Log.i("n", "n2");
        // NotificationManagerCompat nm=NotificationManagerCompat.from(this);
        NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, builder.build());
        Log.i("n", "n3");
    }


    public void showNotification(String key) {
        String stat = "Undefined";
        if (key.equalsIgnoreCase("0"))
            stat = "Your Order is cancelled by provider";
        else if (key.equalsIgnoreCase("1"))
            stat = "Your Order is accepted by provider";
        else if (key.equalsIgnoreCase("2"))
            stat = "Provider is on his way";
        else if (key.equalsIgnoreCase("3"))
            stat = "Your Order is finished";


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), "CHANNEL_ID");
        Log.i("n", "n1");
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("Order details")
                .setWhen(System.currentTimeMillis())
                .setTicker("fgdg")
                .setContentInfo("Updated")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentText(stat);

        Log.i("n", "n2");
        // NotificationManagerCompat nm=NotificationManagerCompat.from(this);
        NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, builder.build());
        Log.i("n", "n3");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "fsf";
            String desciption = "Vdfvdfvd";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel nc = new NotificationChannel("CHANNEL_ID", name, importance);
            nc.setDescription(desciption);
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.createNotificationChannel(nc);
        }

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
