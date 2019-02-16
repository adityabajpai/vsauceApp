package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ItemActivity extends AppCompatActivity {

    String beveragename, beveragedescription, beveragePrice, beverageImage;
    TextView textViewitemname, textViewitemPrice, textViewDescription;
    ImageView imageViewItem;
    TextView textViewquantity, textViewminus, textViewPlus;
    int quantity;
    int position;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    int quant;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        beveragename = bundle.getString("beverage_name");
        beveragedescription = bundle.getString("beverage_Description");
        beveragePrice = bundle.getString("beverage_Price");
        beverageImage = bundle.getString("beverage_Image");
        position = bundle.getInt("position");
        flag = bundle.getInt("Choice");
        quant = bundle.getInt("quant");
        textViewitemname = findViewById(R.id.item);
        textViewitemPrice = findViewById(R.id.itemPrice);
        textViewDescription = findViewById(R.id.description);
        imageViewItem = findViewById(R.id.imageView);
        textViewquantity = findViewById(R.id.itemQuantity);
        textViewquantity.setText(quant + "");
        textViewPlus = findViewById(R.id.plus);
        textViewminus = findViewById(R.id.minus);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                quantity = Integer.parseInt(dataSnapshot.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Quantity").getValue().toString());
                Log.e("KIET", quantity + "");
                textViewquantity.setText(quantity + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        textViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity >= 0) {
                    quantity++;
                    textViewquantity.setText(quantity + "");
                    databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Name").setValue(beveragename);
                    databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Price").setValue(beveragePrice);
                    databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Quantity").setValue(quantity);
                }
            }
        });
        textViewitemname.setText(beveragename);
        textViewDescription.setText(beveragedescription);
        textViewitemPrice.setText(beveragePrice);
        Glide.with(ItemActivity.this).load(beverageImage).into(imageViewItem);
    }
}
