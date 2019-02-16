package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    CheckOutAdapter checkOutAdapter;
    TextView txtToatalPrice;
    Button btnproceedtocheckout;
    ArrayList<String> arrayListdishes = new ArrayList<>();
    String name, quantity, price;
    String totaldishPrice = "";
    int total = 0;
    int pricecredit = 0;
    int finalprice = 0;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private CheckOutSet checkOutSet;
    private List<CheckOutSet> checkOutSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        txtToatalPrice = findViewById(R.id.totalPrice);
        btnproceedtocheckout = findViewById(R.id.proceedtocheckout);
        btnproceedtocheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, ModeOfPayment.class));
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        recyclerView = findViewById(R.id.checkoutRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        checkOutSets = new ArrayList<>();
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("Users").child(user.getUid()).child("Cart").getChildren()) {
                    String dishes = dataSnapshot1.getKey();
                    Log.d("dishesname", dishes);
                    arrayListdishes.add(dishes);
                }
                Log.d("arrayListDishes", String.valueOf(arrayListdishes));
                for (int j = 0; j < arrayListdishes.size(); j++) {
                    final String dishname = arrayListdishes.get(j);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            name = dataSnapshot.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Name").getValue() + "";
                            Log.d("namedish", String.valueOf(name));
                            quantity = dataSnapshot.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Quantity").getValue() + "";
                            Log.d("quantitydish", String.valueOf(quantity));
                            price = dataSnapshot.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Price").getValue() + "";
                            totaldishPrice = (Integer.parseInt(price)) * (Integer.parseInt(quantity)) + "";
                            Log.d("totalphle", String.valueOf(total));
                            total += Integer.parseInt(totaldishPrice);
                            Log.d("total", String.valueOf(total));
                            Log.d("pricedish", String.valueOf(price));
                            checkOutSet = new CheckOutSet(name, quantity, totaldishPrice);
                            checkOutSets.add(checkOutSet);
                            Log.d("total", String.valueOf(total));
                            txtToatalPrice.setText(total + "");
                            checkOutAdapter = new CheckOutAdapter(getApplicationContext(), checkOutSets);
                            recyclerView.setAdapter(checkOutAdapter);
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
