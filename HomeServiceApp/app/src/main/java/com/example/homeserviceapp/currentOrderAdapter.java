package com.example.homeserviceapp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.homeserviceapp.Model.Order;
import com.example.homeserviceapp.Model.ServiceProvider;
import com.example.homeserviceapp.Model.Tutors;
import com.example.homeserviceapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class currentOrderAdapter extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> orderlist;
    TextView name, service, status, contact, time, cost, orderId;
    ImageView img;
    public String key = "11";

    public currentOrderAdapter(Activity context, List<Order> orderlist) {
        super(context, R.layout.mycurrentorderlist, orderlist);
        this.context = context;
        this.orderlist = orderlist;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.mycurrentorderlist, null, true);
        final Order myorder = orderlist.get(position);

        Button payment;

        //status=tutors.getStatus();
        name = (TextView) view.findViewById(R.id.corder_provider_name_id);
        service = (TextView) view.findViewById(R.id.corder_service_name_id);
        cost = (TextView) view.findViewById(R.id.corder_cost_id);
        time = (TextView) view.findViewById(R.id.corder_time_id);
        contact = (TextView) view.findViewById(R.id.corder_provider_contact_id);
        status = (TextView) view.findViewById(R.id.corder_status_id);
        orderId = (TextView) view.findViewById(R.id.corder_order_id);

        payment = (Button) view.findViewById(R.id.corder_payment_id);

        service.setText("Orderd Service: " + myorder.getServices());
        cost.setText("Approximated Cost: " + myorder.getTotalpayment());
        time.setText("Order time :" + myorder.getOrdertime());
      //  contact.setText("Contact No: ");
        String stat = "Undefined";
        String key = myorder.getStatus();
        if (key.equalsIgnoreCase("0"))
            stat = "Your Order is cancelled by provider";
        else if (key.equalsIgnoreCase("1"))
            stat = "Your Order is accepted by provider";
        else if (key.equalsIgnoreCase("-1"))
            stat = "Your Order is Placed";
        else if (key.equalsIgnoreCase("2"))
            stat = "Provider is on his way";
        else if (key.equalsIgnoreCase("3"))
            stat = "Your Order is finished";
        status.setText(stat);
        orderId.setText("Your OrderKey is: " + myorder.getKey());
       // name.setText("provider Name: " + myorder.);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1;
        // orderID = dataSnapshot.getKey().toString();
        // Log.i(myorder.getUserid(),"ty");
        String type = myorder.getProviderType();
        if (type.equalsIgnoreCase("1")) {
            myRef1 = database.getReference("Electrician");
            myRef1.orderByKey().equalTo(myorder.getProviderid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        ServiceProvider u = d.getValue(ServiceProvider.class);
                        name.setText("Name: " + u.getName());
                        contact.setText("Contact No: "+u.getContactNo());
                       // Toast.makeText(context, "provider: "+u.getContactNo(), Toast.LENGTH_LONG).show();
                        // userLoc = u.getLocation();
                        Log.i("what", "df");
                        // // showNotification1("your Order is finished,check payment", userLoc, orderID);

                        //showNotification("Contact no: "+u.getContactNo()+" Location: "+u.getLocation(),userLoc,orderID);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            myRef1 = database.getReference("Tutor");
            myRef1.orderByKey().equalTo(myorder.getProviderid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        Tutors u = d.getValue(Tutors.class);
                        name.setText("name: " + u.getName());
                        contact.setText("Contact No: "+u.getContactNo());
                        //   Log.i("what", "df");
                        //  showNotification1("your Order is finished,check payment", userLoc, orderID);

                        //showNotification("Contact no: "+u.getContactNo()+" Location: "+u.getLocation(),userLoc,orderID);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        //t1.setText("Name :"+tutors.getName());
        // t2.setText("Rating: "+tutors.getRating());
        //t3.setText("Experience :"+tutors.getExperience());
        // contact=tutors.getContactNo();
        // Picasso.get().load(s).into(img);
        // String u=tutors.getUrl().toString();
        // Picasso.with(this.context).load(u).into(img);
        if (!myorder.getStatus().equalsIgnoreCase("3"))
            payment.setVisibility(View.INVISIBLE);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), confirmationPayment.class);
                //intent.putExtra("userloc","dhanmondhi");
                intent.putExtra("orderID", myorder.getKey());
                context.startActivity(intent);
            }
        });


        return view;
    }

}
