package com.example.homeserviceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeserviceapp.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

//import com.example.homeservice.Model.Users;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class userRegistration extends AppCompatActivity {

    private EditText user_email, user_contact_no, user_password, user_location, name;
    private Button btn_register, photo;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    ImageView photoview;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    //String url;

    FirebaseStorage storage;
    StorageReference storageReference;

    public String email = null, password = null, contact = null, location = null, key = null, username = null, loc = null, url = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        photo = (Button) findViewById(R.id.photobtn);
        photoview = (ImageView) findViewById(R.id.imagev);
        name = (EditText) findViewById(R.id.user_name);
        user_email = (EditText) findViewById(R.id.user_email_id);
        user_password = (EditText) findViewById(R.id.user_password);
        user_location = (EditText) findViewById(R.id.user_location_id);
        user_contact_no = (EditText) findViewById(R.id.user_contact_No);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btn_register = (Button) findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userRegistration();

            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                photoview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadImage() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            loc = UUID.randomUUID().toString();
            //url = "gs://homeserviceapp-c10b0.appspot.com/images/" + loc;
            Log.i("location", loc);
           // Toast.makeText(userRegistration.this, loc, Toast.LENGTH_SHORT).show();
            StorageReference ref = storageReference.child("images/" + loc);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(userRegistration.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(userRegistration.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });

            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    url = uri.toString();
                    Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void userRegistration() {

        email = user_email.getText().toString();
        password = user_password.getText().toString();
        contact = user_contact_no.getText().toString();
        location = user_location.getText().toString();

        check();

        key = databaseReference.push().getKey();
        username = name.getText().toString();

        Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();

        uploadImage();

        User userprofile = new User(contact, email, location, password, username, url);
        databaseReference.child(key).setValue(userprofile);

    }

    public void check() {
        boolean isok = true;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isok = false;
            user_email.setError("Enter a valid email address");
            user_email.requestFocus();

            return;
        }
        if (username.isEmpty()) {
            isok = false;
            name.setError("Enter a password");
            name.requestFocus();

            return;
        }

        if (password.isEmpty()) {
            user_password.setError("Enter a password");
            user_password.requestFocus();
            isok = false;
            return;
        }

        if (password.length() < 6) {
            user_password.setError("Password must be at least 6 digits");
            user_password.requestFocus();
            isok = false;
            return;
        }

        if (location.isEmpty()) {
            user_location.setError("Your Location Please");
            user_location.requestFocus();
            isok = false;
            return;
        }

        if (contact.isEmpty()) {
            user_contact_no.setError("Your Contact No Please");
            user_contact_no.requestFocus();
            isok = false;
            return;
        }

        if (contact.length() != 11) {
            user_contact_no.setError("Phone No is not Valid");
            user_contact_no.requestFocus();
          //  isok = false;
            return;
        }


        if (isok == true) {
            Toast.makeText(getApplicationContext(), "Register is Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }


}

