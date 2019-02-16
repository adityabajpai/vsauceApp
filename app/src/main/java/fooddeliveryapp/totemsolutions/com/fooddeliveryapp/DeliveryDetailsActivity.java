package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeliveryDetailsActivity extends AppCompatActivity {

    EditText editText_name, editText_email, editText_address, editText_phoneno, editText_expectedtime;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Button buttonsave, buttonproceed, buttonapplypromocode;
    int deductedprice;
    TextView textView;
    int check = 0;
    int btnclick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.d("user", user + "");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            deductedprice = bundle.getInt("concession");
            check = bundle.getInt("check");
            btnclick = bundle.getInt("btnclick");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editText_name = findViewById(R.id.userName);
        editText_email = findViewById(R.id.userEmail);
        editText_address = findViewById(R.id.userAddress);
        editText_phoneno = findViewById(R.id.userPhone);
        editText_expectedtime = findViewById(R.id.userdeliverytime);
        textView = findViewById(R.id.promocodedeductedamt);
        if (check == 0) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Amount that will be reduced from your order is" + " " + deductedprice);
        }
        buttonsave = findViewById(R.id.saveProfileBtn);
        buttonproceed = findViewById(R.id.finalSubmission);
        buttonapplypromocode = findViewById(R.id.applypromocode);
        buttonapplypromocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnclick == 0) {
                    startActivity(new Intent(DeliveryDetailsActivity.this, ApplyPromoCodeActivity.class));
                } else {
                    Toast.makeText(DeliveryDetailsActivity.this, "Only one promo code can be applied at a time", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonproceed.setVisibility(View.INVISIBLE);
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String key = databaseReference.push().getKey();
                buttonsave.setVisibility(View.INVISIBLE);
                String name = editText_name.getText().toString().trim();
                String email = editText_email.getText().toString().trim();
                String address = editText_address.getText().toString().trim();
                String phoneno = editText_phoneno.getText().toString().trim();
                String expectedTime = editText_expectedtime.getText().toString().trim();
                databaseReference.child("Users").child(user.getUid()).child("DeliveryDetails").child("Name").setValue(name);
                databaseReference.child("Users").child(user.getUid()).child("DeliveryDetails").child("Email").setValue(email);
                databaseReference.child("Users").child(user.getUid()).child("DeliveryDetails").child("Address").setValue(address);
                databaseReference.child("Users").child(user.getUid()).child("DeliveryDetails").child("Phoneno").setValue(phoneno);
                databaseReference.child("Users").child(user.getUid()).child("DeliveryDetails").child("ExpectedTime").setValue(expectedTime);
                Toast.makeText(DeliveryDetailsActivity.this, "Details Uploaded!!", Toast.LENGTH_SHORT).show();
                buttonproceed.setVisibility(View.VISIBLE);
            }
        });
        buttonproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryDetailsActivity.this, FinalDeliveryDetails.class);
                startActivity(intent);
            }
        });
    }
}
