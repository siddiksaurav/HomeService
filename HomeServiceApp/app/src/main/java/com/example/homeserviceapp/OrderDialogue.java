package com.example.homeserviceapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.homeserviceapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class OrderDialogue extends AppCompatDialogFragment {
    TextView service_name, cost;
    SharedPreferences elecdetails;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_order, null);

        builder.setView(view).setTitle("Order details").setPositiveButton("okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in = new Intent(getActivity(), ServiceproviderList.class);
                startActivity(in);
                //getActivity().finish();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in1 = new Intent(getActivity(), ServiceList.class);
                startActivity(in1);
                //getActivity().finish();
            }
        });
        SharedPreferences elecdetails = getActivity().getSharedPreferences("electricalOrder", Context.MODE_PRIVATE);
        String name = elecdetails.getString("serviceName", "");
        String totalcost = elecdetails.getString("cost", "");

        service_name = (TextView) view.findViewById(R.id.service_name_dialogue);
        cost = (TextView) view.findViewById(R.id.service_cost_dialogue);
        //Toast.makeText(getContext(),name,Toast.LENGTH_LONG).show();

        service_name.setText("Service Type: " + name);
        cost.setText("Approximated cost: " + totalcost);
        return builder.create();


    }
}
