package com.example.serverhomeservice;

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

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.serverhomeservice.HomepageActivity;
import com.example.serverhomeservice.Model.Order;
import com.example.serverhomeservice.R;
import com.example.serverhomeservice.User;
import com.example.serverhomeservice.orderReview;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MyService extends Service implements ChildEventListener {
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    DatabaseReference myRef1;
     String userLoc;
    String orderID;
    String userID;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"hiiiiiii,Toast.LENGTH_LONG).show();
       // Date currentTime = Calendar.getInstance().getTime();
       // String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
       // String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
       // Toast.makeText(this,currentTime.toString(),Toast.LENGTH_LONG).show();


        myRef.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference("Order");


    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot,  String s) {

        Order d=dataSnapshot.getValue(Order.class);
        SharedPreferences usershared=getSharedPreferences("userdetails",Context.MODE_PRIVATE);
        userID=usershared.getString("userid","");
        Toast.makeText(this,userID.toString(),Toast.LENGTH_LONG).show();
        Log.i("n","n5");
        createNotificationChannel();
        myRef1 = database.getReference("User");
        // myRef1= myRef1.orderByKey().equalTo(d.getUserid()).getRef();
        String Phone="0011";
        if(d.getProviderid().equalsIgnoreCase(userID))
        {
            orderID=dataSnapshot.getKey().toString();
            myRef1.orderByKey().equalTo(d.getUserid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot d: dataSnapshot.getChildren())
                    {
                        User u=d.getValue(User.class);
                        userLoc=u.getLocation();
                        showNotification("Contact no: "+u.getContactNo()+" Location: "+u.getLocation(),userLoc,orderID);
                    }

                    // Log.i(dataSnapshot.getKey(),"oiu112");
                    // Log.i(u.getName(),"oiu11");
                    // showNotification(u.getContactNo());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }





    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot,  String s) {
/*
        Order d=dataSnapshot.getValue(Order.class);
        SharedPreferences usershared=getSharedPreferences("userdetails",Context.MODE_PRIVATE);
        userID=usershared.getString("userid","");
        Toast.makeText(this,userID.toString(),Toast.LENGTH_LONG).show();
        Log.i("n","n5");
        createNotificationChannel();
        myRef1 = database.getReference("User");
        // myRef1= myRef1.orderByKey().equalTo(d.getUserid()).getRef();
        String Phone="0011";
        if(d.getProviderid().equalsIgnoreCase(userID))
        {
            orderID=dataSnapshot.getKey().toString();
            myRef1.orderByKey().equalTo(d.getUserid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot d: dataSnapshot.getChildren())
                    {
                        User u=d.getValue(User.class);
                        userLoc=u.getLocation();
                        showNotification("Contact no: "+u.getContactNo()+" Location: "+u.getLocation(),userLoc,orderID);
                    }

                    // Log.i(dataSnapshot.getKey(),"oiu112");
                    // Log.i(u.getName(),"oiu11");
                    // showNotification(u.getContactNo());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }



*/

    }
    public void showNotification(String key,String userLoc,String orderID)
    {
        Intent intent =new Intent(getBaseContext(), orderReview.class);
        intent.putExtra("userloc",userLoc);
        intent.putExtra("orderID",orderID);
        PendingIntent content=PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getBaseContext(),"CHANNEL_ID");
        Log.i("n","n1");
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("Content Title")
                .setWhen(System.currentTimeMillis())
                .setTicker("Ticker")
                .setContentInfo("Updated")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentText("Your Order  "+key+ " was updated");

        Log.i("n","n2");
        // NotificationManagerCompat nm=NotificationManagerCompat.from(this);
        NotificationManager nm=(NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1,builder.build());
        Log.i("n","n3");
    }
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name="fsf";
            String desciption="Vdfvdfvd";
            int importance=NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel nc=new NotificationChannel("CHANNEL_ID",name,importance);
            nc.setDescription(desciption);
            NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            nm.createNotificationChannel(nc);
        }

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot,  String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
