package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AskScheduleActivity extends AppCompatActivity {

    private static final String TAG = "PhoneAuthActivity";
    CardView beverages, desserts, mainCourse, snacks;
    String flag;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_schedule);
        Button mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        beverages = (CardView) findViewById(R.id.beverages);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth != null) {
        }
        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(AskScheduleActivity.this, UserRegisterActivity.class));
                finish();
            }
        });
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = "BEVERAGE";
                Bundle bundle = new Bundle();
                bundle.putString("Beverage", flag);
                Intent intent = new Intent(AskScheduleActivity.this, BeveragesMenuListActivity.class);
                startActivity(intent);
            }
        });

        desserts = (CardView) findViewById(R.id.desserts);
        desserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = "DESSERT";
                Bundle bundle = new Bundle();
                bundle.putString("Dessert", flag);
                Intent intent = new Intent(AskScheduleActivity.this, DessertImageListActivity.class);
                startActivity(intent);
            }
        });

        mainCourse = (CardView) findViewById(R.id.maincCourse);
        mainCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = "MAINCOURSE";
                Bundle bundle = new Bundle();
                bundle.putString("MAINCOURSE", flag);
                Intent intent = new Intent(AskScheduleActivity.this, MainCourseListActivity.class);
                startActivity(intent);
            }
        });


        snacks = (CardView) findViewById(R.id.snacks);
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = "SNACKS";
                Bundle bundle = new Bundle();
                bundle.putString("SNACKS", flag);
                Intent intent = new Intent(AskScheduleActivity.this, SnackListActivity.class);
                startActivity(intent);
            }
        });
    }

}
