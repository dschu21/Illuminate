package ledcontrol.androidarduinocollab;

/**
 * Created by Danyon on 2015-10-05.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splash_screen);

        Thread timer = new Thread() {
            // Thread to show the splash screen, then launch the main screen
            @Override
            public void run() {
                try {
                    sleep(2000); // set this to how long you want to wait before
                    // showing the main screen
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent mainScreen=new Intent(getBaseContext(),MainScreen.class);
                    startActivity(mainScreen);
                }
            }
        };
        timer.start();

// METHOD 1

//        /****** Create Thread that will sleep for 5 seconds *************/
//        Thread background = new Thread() {
//            public void run() {
//
//                try {
//                    // Thread will sleep for 3 seconds
//                    sleep(3*1000);
//
//
//
//                } catch (Exception e) {
//
//                } finally {
//                    // After 3 seconds redirect to another intent
//                    Intent i=new Intent(getBaseContext(),MainScreen.class);
//                    startActivity(i);
//
//                    //Remove activity
//                    finish();
//                }
//            }
//        };
//
//        // start thread
//        background.start();

//METHOD 2

        /*
        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                Intent i = new Intent(MainSplashScreen.this, FirstScreen.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 5*1000); // wait for 5 seconds
        */
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}