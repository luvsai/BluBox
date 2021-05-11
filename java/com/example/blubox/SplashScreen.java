package com.example.blubox;
//Import Statements

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/*

 *Documentation:------------------------------

    *Name: SplashScreen.java (Activity)
            activity_splash_screen.xml (for layout)

    *Description :-----------
        ->This is the first Displayed Actvity of the app
        ->it displays the app logo and name of the app for a Splash_time_out (3secs)


    *Functionalities(FN):----------


        *FN1 :
         Check the User Status for new user and redirect the user to update his profile to
         Userdata.java Activity

        *Approach
            the code will use the getStatus() function of class userbio.java which gives the status of user
            it return false if user is a new user



      *FN1 :
         Display the app  logo for a 3 Secs and redirect to MainActivity

        *Input:
            ->The Activity is launched first when the app is opened by the User

        *Processing:
            ->Uses handler which post delays the redirection to the MainActivity

        *Output:
            ->The app Will reDirect to the MainActivity (After the 3 Sec timer)

  * --------------------------------------------

 */




public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    Animation animation1 ;
    ImageView imgLogo;

    String Destination ;// Variable to store the Destination activity to redirect User From Current Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Making the Activity a full screen to hide the action and notification bar

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);

        /*
        Animation for logo in splash screen
         */

        animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        imgLogo = findViewById(R.id.imgLogo);
        imgLogo.startAnimation(animation1);


        /*
            * Code to check if User is new User or not
            * the userbio class uses Shared preferences to store user bio details
            * it returns false incase the userdata is not updated
         */
        userbio dat = new userbio(SplashScreen.this);
        String status = dat.getStatus();

        Destination = "com.example.blubox.MainActivity" ; //Path for the nextActivity to reDirect userby default the MainActivity (old user)

        if (status.equals("false")) {
            /*
             * The user is a new user
             * Change the Destination path for to Redirect the User to update his profile to Activity (UserData.java).
             */
            Destination = "com.example.blubox.Userdata";

        }


        /*
        * Showing splash screen with a timer and Redirect the User
        * to the Next Activity
        *   Case "newUser" :  Destination is Userdata.java(Activity)
        *   Case "oldUser" :  Destination is MainActivity.jav(Activity) (redirect to app Home)
        */

        new Handler().postDelayed((Runnable) () -> {
                /*
                    * This method will be executed once the timer is over

                    * Starting an intent to Activity specified in Destination variable


                 */
                Intent i = null;
                try {
                    i = new Intent(SplashScreen.this, Class.forName(Destination));
                    i.putExtra("source","0"); // 0 indicates Splash Screen Activity
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                startActivity(i);

                // closing this activity when returned from the MainActivity
                finish();
            }, SPLASH_TIME_OUT);



        }







    }
