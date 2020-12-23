package com.example.homeserviceapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mview;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mview = itemView;

    }

    public void setDetails(Context ctx, String name, String rating, String image, String experience) {
        TextView nameTview = mview.findViewById(R.id.service_provider_name_recyle);
        TextView ratingTview = mview.findViewById(R.id.service_provider_rating_recyle);
        TextView experienceTview = mview.findViewById(R.id.service_provider_experience_recyle);
        ImageView imgIview = mview.findViewById(R.id.service_provider_image_recyle);
        nameTview.setText(name);
        ratingTview.setText(rating);
        experienceTview.setText(experience);
        Picasso.get().load(image).into(imgIview);


    }
}
