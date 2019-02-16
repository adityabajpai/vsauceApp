package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class InteractionActivity extends AppCompatActivity {

    ImageView van, food;
    LinearLayout logo;
    Animation van_from_left, food_from_right, logo_from_bottom;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);

        van_from_left = AnimationUtils.loadAnimation(this, R.anim.van_from_left);
        food_from_right = AnimationUtils.loadAnimation(this, R.anim.food_from_right);
        logo_from_bottom = AnimationUtils.loadAnimation(this, R.anim.logo_from_bottom);

        van = (ImageView) findViewById(R.id.van);
        food = (ImageView) findViewById(R.id.food);
        logo = (LinearLayout) findViewById(R.id.logo);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        van.setAnimation(van_from_left);
        food.setAnimation(food_from_right);
        logo.setAnimation(logo_from_bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextBtn.setVisibility(View.VISIBLE);
            }
        }, 2600);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InteractionActivity.this, UserRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
