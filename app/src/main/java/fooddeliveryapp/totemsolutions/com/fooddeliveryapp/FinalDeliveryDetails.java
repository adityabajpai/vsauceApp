package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
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

public class FinalDeliveryDetails extends AppCompatActivity {

    EditText editText_name, editText_Email, editText_Address, editText_Phone, editText_ExpectedTime;
    FirebaseAuth firebaseAuth;
    TextView txtToatalPrice;
    RecyclerView RecyclerViewfinalproduct;
    DatabaseReference databaseReference;
    ArrayList<String> arrayListdishes = new ArrayList<>();
    String name, quantity, price;
    String totaldishPrice = "";
    int total = 0;
    CheckOutAdapter checkOutAdapter;
    private CheckOutSet checkOutSet;
    private List<CheckOutSet> checkOutSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_delivery_details);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.d("uid", user.getUid());
        editText_name = findViewById(R.id.userName);
        editText_Email = findViewById(R.id.userEmail);
        editText_Address = findViewById(R.id.userAddress);
        editText_Phone = findViewById(R.id.userPhone);
        editText_ExpectedTime = findViewById(R.id.userdeliverytime);
        txtToatalPrice = findViewById(R.id.totalPrice);
        RecyclerViewfinalproduct = findViewById(R.id.recyclerViewFinalOrder);
        RecyclerViewfinalproduct.setHasFixedSize(true);
        RecyclerViewfinalproduct.setLayoutManager(new LinearLayoutManager(this));
        checkOutSets = new ArrayList<>();
        RecyclerViewfinalproduct = findViewById(R.id.recyclerViewFinalOrder);
        RecyclerViewfinalproduct.setHasFixedSize(true);
        RecyclerViewfinalproduct.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                checkOutSets = new ArrayList<>();
                arrayListdishes = new ArrayList<>();
//                progressDialog.dismiss();
                Log.d("datasnapshot", dataSnapshot + "");
                String name = dataSnapshot.child("Users").child(user.getUid()).child("DeliverDetails").child("Name").getValue() + "";
                Log.d("name", name);
                String email = dataSnapshot.child("Users").child(user.getUid()).child("DeliverDetails").child("Email").getValue() + "";
                Log.d("email", email);
                String address = dataSnapshot.child("Users").child(user.getUid()).child("DeliverDetails").child("Address").getValue() + "";
                Log.d("address", address);
                String phone = dataSnapshot.child("Users").child(user.getUid()).child("DeliverDetails").child("Phoneno").getValue() + "";
                Log.d("phone", phone);
                String expected = dataSnapshot.child("Users").child(user.getUid()).child("DeliverDetails").child("ExpectedTime").getValue() + "";
                Log.d("expected", expected);
                editText_name.setText(name);
                editText_Email.setText(email);
                editText_Address.setText(address);
                editText_Phone.setText(phone);
                editText_ExpectedTime.setText(expected);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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

                            txtToatalPrice.setText(total + "");
                            checkOutAdapter = new CheckOutAdapter(getApplicationContext(), checkOutSets);
                            RecyclerViewfinalproduct.setAdapter(checkOutAdapter);
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
