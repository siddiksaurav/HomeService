package com.example.homeserviceapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ServiceList extends Fragment {
    ImageButton tutor, plumber, electrician;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_list, null);
        tutor = (ImageButton) view.findViewById(R.id.image_btn_tutor);
        plumber = (ImageButton) view.findViewById(R.id.image_btn_plumber);
        electrician = (ImageButton) view.findViewById(R.id.image_btn_electrician);

        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tutor();
            }
        });
        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlumberList();
            }
        });
        electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showElectricianList();
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);


        tutor = (ImageButton) findViewById(R.id.image_btn_tutor);
        plumber = (ImageButton) findViewById(R.id.image_btn_plumber);
        electrician = (ImageButton) findViewById(R.id.image_btn_electrician);

        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tutor();
            }
        });
        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlumberList();
            }
        });
        electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showElectricianList();
            }
        });

    }
*/

    private void showElectricianList() {
        Intent intent = new Intent(getContext(), electricianForm.class);
        intent.putExtra("selectedService", "Electrician");
        startActivity(intent);
    }

    private void showPlumberList() {
        Intent intent = new Intent(getContext(), PlumberForm.class);
        intent.putExtra("selectedService", "Plumber");
        startActivity(intent);
    }

    private void Tutor() {
        Intent intent = new Intent(getContext(), tutorService.class);
        //intent.putExtra("selectedService",val);
        startActivity(intent);

    }

}

