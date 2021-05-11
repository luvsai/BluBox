package com.example.blubox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;



/*

 *Documentation:------------------------------

    *Name: Userdata.java (Activity)
          uses res/layout/activity_userdata.xml (Resource file for layout)
          uses class userbio.java to save user data


    *Description :-----------
        ->This activity is used to edit the user details
        ->it receives the user data from gui and saves them to shared preferences using
        ->It redirects the user to MainActivity.java (Activity) after updating the user data



*/




public class Userdata extends AppCompatActivity {
        Button sub;
        ImageView addpic;
        String status = "false";
        String username,bio, photoUrl = "logo";


        private static final int ACTSplashScreen = 0 ,ACTMainActivity =1;

        userbio dat ; //Object which helps to create shared preference storage for user data

        EditText etpn ;
        TextInputEditText  ebio;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_userdata);
            sub = findViewById(R.id.sub);
            addpic = findViewById(R.id.addpic);

            dat = new userbio(Userdata.this) ;//userbio ooject



            etpn =  findViewById(R.id.etpn);
            ebio = findViewById(R.id.ebio);

            /*
            Filling the User Form with Saved data
             */
            etpn.setText(dat.getName());
            ebio.setText(dat.getBio());

            addpic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    photoUrl = "" ; // Adding photo is not implemented yet

                    Toast.makeText(Userdata.this, "to ADD profile image" , Toast.LENGTH_LONG).show();
                }
            });


            /*

                * User clicks Submit button then code will save the user data
                * it saves the username , bio and user profile image url
                *



             */
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username = etpn.getText().toString();
                    bio = ebio.getText().toString();
                    if (username.isEmpty() ) {
                        Toast.makeText(getApplicationContext(), "Please fill the fields", Toast.LENGTH_SHORT).show();
                    }
                    else if (bio.isEmpty()){
                        bio = "You Are Awesome" ;

                    }

                        status = "true" ; //valid User


                        /*
                        Saving user data in Shared preferences
                         */

                        dat.save(status,username, bio,photoUrl); // Saving user data




                        String Destination = "com.example.blubox.MainActivity" ;

                        Intent i = null;
                        try {
                            i = new Intent(Userdata.this, Class.forName(Destination));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        startActivity(i);

                        Userdata.this.finish();

                }
            });









    }
}