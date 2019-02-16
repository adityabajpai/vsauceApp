package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.animate().alpha(1f).scaleX(0.9f).scaleY(0.9f).setDuration(2500);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(getApplicationContext(), InteractionActivity.class);
//                startActivity(i);
//                finish();
//            }
//        }, 3600);

        Boolean isFirstRun = getSharedPreferences("PREFERENCES", MODE_PRIVATE)
                .getBoolean("isfirstrun", true);

        if (isFirstRun) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), InteractionActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3600);

            getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit()
                    .putBoolean("isfirstrun", false).commit();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), AskScheduleActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3600);
        }
    }
}
