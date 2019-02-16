package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThaliTypeActivity extends AppCompatActivity {

    CardView cardViewBasic, cardViewStandard, cardViewDeluxe;
    String type;
    TextView textViewbasic, textViewstandard, textViewdeluxe;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thali_type);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cardViewBasic = findViewById(R.id.basicC);
        cardViewStandard = findViewById(R.id.standardC);
        cardViewDeluxe = findViewById(R.id.deluxeC);
        textViewbasic = findViewById(R.id.basicT);
        textViewstandard = findViewById(R.id.standardT);
        textViewdeluxe = findViewById(R.id.deluxeT);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        type = bundle.getString("Type");
        Log.d("chutiya", type);
        textViewbasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("thali", "basic");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Basic)").child("Name").setValue(type + "(Basic Thali)");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Basic)").child("Price").setValue(0 + "");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Basic)").child("Quantity").setValue(0 + "");
                Log.d("thali", "basic2");
            }
        });
        textViewstandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("thali", "standard");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Standard)").child("Name").setValue(type + "(StandardThali)");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Standard)").child("Price").setValue(0 + "");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Standard)").child("Quantity").setValue(0 + "");
                Log.d("thali", "standard2");
            }
        });
        textViewdeluxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("thali", "deluxe");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Deluxe)").child("Name").setValue(type + "(Deluxe Thali)");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Deluxe)").child("Price").setValue(0 + "");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(type + "(Deluxe)").child("Quantity").setValue(0 + "");
                Log.d("thali", "deluxe2");
            }
        });
    }
}
