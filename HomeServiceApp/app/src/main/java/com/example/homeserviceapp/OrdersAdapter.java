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

import com.example.homeserviceapp.Model.Order;
import com.example.homeserviceapp.Model.ServiceProvider;
import com.example.homeserviceapp.Model.Tutors;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrdersAdapter extends ArrayAdapter<Order> {

    private Activity context;
    String status;
    private List<Order> orderlist;

    public OrdersAdapter(Activity context, List<Order> orderlist) {
        super(context, R.layout.myorderlist, orderlist);
        this.context = context;
        this.orderlist = orderlist;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.myorderlist, null, true);
        final Order myorder = orderlist.get(position);
        final TextView name, service, status, contact, time, cost, orderId;
        Button payment;
        ImageView img;
        //status=tutors.getStatus();
        name = (TextView) view.findViewById(R.id.order_provider_name_id);
        service = (TextView) view.findViewById(R.id.order_service_name_id);
        cost = (TextView) view.findViewById(R.id.order_cost_id);
        time = (TextView) view.findViewById(R.id.order_time_id);
        // contact=(TextView)view.findViewById(R.id.order_provider_contact_id);
        status = (TextView) view.findViewById(R.id.order_status_id);
        orderId = (TextView) view.findViewById(R.id.order_order_id);

        // payment=(Button)view.findViewById(R.id.order_payment_id);

        service.setText("Ordered Service: " + myorder.getServices());
        cost.setText("Cost: " + myorder.getTotalpayment());
        time.setText("Order time :" + myorder.getOrdertime());
        // contact.setText("Contact No: ");

        if (myorder.getStatus().equalsIgnoreCase("0"))
            status.setText("Order Status: " + "Your Order is Cancelled");

        else if (myorder.getStatus().equalsIgnoreCase("1"))
            status.setText("Order Status: " + "Your Order is accepted by provider");

        else if (myorder.getStatus().equalsIgnoreCase("2"))
            status.setText("Order Status: " + "Provider is on his way");
        else if (myorder.getStatus().equalsIgnoreCase("3"))
            status.setText("Order Status: " + "Order is finished");
       // status.setText("Order Status: " + status);
        orderId.setText("Your OrderKey is: " + myorder.getKey());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1;
        //Toast.makeText(getContext(), "prevOrderName: "+"getName()", Toast.LENGTH_LONG).show();

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
                       // name.setText("fgfr");
                        name.setText("Name: "+u.getName());
                      //  Toast.makeText(getContext(), "prevOrderName: "+u.getName(), Toast.LENGTH_LONG).show();


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
                       // Toast.makeText(getContext(), "prevOrderName: "+u.getName(), Toast.LENGTH_LONG).show();

                        name.setText("Name: " + u.getName());
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


        return view;
    }

}
