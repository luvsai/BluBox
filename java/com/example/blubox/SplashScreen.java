package com.example.blubox;
//Import Statements

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;


/*

 *Documentation:------------------------------

    *Name: SplashScreen.java (Activity)

    *Description :-----------
        ->This is the first screen of the app
        ->it displays the app logo and name of the app for a Splash_time_out (3secs)


    *Functionalities(FN):----------

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Making the Activity a full screen to hide the action and notification bar

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //-------------
        setContentView(R.layout.activity_splash_screen);

        /*
         * Showing splash screen with a timer.
         */
        new Handler().postDelayed((Runnable) () -> {
            // This method will be executed once the timer is over
            // Starting an intent to MainActivity
            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);

            // closing this activity
            finish();
        }, SPLASH_TIME_OUT);




    }
}