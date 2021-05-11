package com.example.blubox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
        String username,bio, photoUrl ;

        int token ;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

        // flags for image slect
        public static final int REQUEST_IMAGE_CAPTURE = 1;
        public static final int PICK_IMAGES = 2;
        public static final int STORAGE_PERMISSION = 100;

        private static final int ACTSplashScreen = 0 ,ACTMainActivity =1;

        userbio dat ; //Object which helps to create shared preference storage for user data
        TextView intro;
        EditText etpn ;
        TextInputEditText  ebio;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_userdata);

            verifyStoragePermissions(Userdata.this);

            //used to find out the source activity the initiated the intent to the current activity
            token = Integer.parseInt(getIntent().getStringExtra("source"));


            dat = new userbio(Userdata.this);//userbio object

            /*
             *Referencing UI components

             */
            sub = findViewById(R.id.sub);
            addpic = findViewById(R.id.addpic);
            etpn = findViewById(R.id.etpn);
            ebio = findViewById(R.id.ebio);
            intro = findViewById(R.id.intro);



            if (token == 1) {
                intro.setText("Update Your Profile");
            }


            // Adding photo is not implemented yet

            /*
            Filling the User Form with Saved data
             */
            etpn.setText(dat.getName());
            ebio.setText(dat.getBio());
            photoUrl = dat.getPhoto() ;
            // display your images
            if (photoUrl.isEmpty()) {
                addpic.setImageDrawable(getImgRes("logo"));
            } else {
                addpic.setImageURI(Uri.parse(photoUrl));
            }





            //Registering Function for creating profile pic (Under Development)
            addpic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        startActivityForResult(intent, PICK_IMAGES);



                    //Toast.makeText(Userdata.this, "to ADD profile image", Toast.LENGTH_LONG).show();
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
                    if (username.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please fill the fields", Toast.LENGTH_SHORT).show();
                    } else if (bio.isEmpty()) {
                        bio = "You Are Awesome";

                    }

                    status = "true"; //valid User


                        /*
                        Saving user data in Shared preferences
                         */

                    dat.save(status, username, bio, photoUrl); // Saving user data


                    /*
                     *Redirecting the User Based on Token
                     *if token = 0 then to MainActivity
                     *if token = 1 then finish the activity to return back
                     */

                    if (token == 1) {
                        Userdata.this.finish();
                    } else {


                        String Destination = "com.example.blubox.MainActivity";

                        Intent i = null;
                        try {
                            i = new Intent(Userdata.this, Class.forName(Destination));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        startActivity(i);

                        Userdata.this.finish();
                    }
                }
            });

        }




    /*
                When returned form other intents
                 */
            @Override
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == RESULT_OK) {
                    if (requestCode == PICK_IMAGES) {
                       if (data.getData() != null) {
                            Uri uri = data.getData();
                            // display your image
                            photoUrl = uri.toString() ;
                            dat.savePhoto(photoUrl);
                            addpic.setImageURI(Uri.parse(photoUrl));
                        }
                    }
                }
            }









    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    /*
    Function to get Drawable image resource from the image name in drawable folder
     */

    public Drawable getImgRes(String name) {

        String uri = "@drawable/"+name;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        return res ;

    }






}
