package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ApplyPromoCodeActivity extends AppCompatActivity {

    ArrayList<String> arrayListCredit = new ArrayList<>();
    private RecyclerView recyclerView;
    private PromoCodeAdapter promoCodeAdapter;
    private List<PromoCodeSet> promoCodeSets = new ArrayList<>();
    private PromoCodeSet promoCodeSet;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_promo_code);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recyclerViewpromocode);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayListCredit = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Promo Codes...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayListCredit = new ArrayList<>();
                progressDialog.dismiss();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Log.d("currentUser", user + "");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("Users").child(user.getUid()).child("Credits").getChildren()) {
                    String creditskey = dataSnapshot1.getKey();
                    Log.d("creditskey", creditskey);
                    arrayListCredit.add(creditskey);
                }
                Log.d("arrayListCredit", arrayListCredit + "");
                for (int i = 0; i < arrayListCredit.size(); i++) {
                    String key = arrayListCredit.get(i);
                    String description = dataSnapshot.child("Users").child(user.getUid()).child("Credits").child(key).child("description").getValue() + "";
                    String name = dataSnapshot.child("Users").child(user.getUid()).child("Credits").child(key).child("name").getValue() + "";
                    String price = dataSnapshot.child("Users").child(user.getUid()).child("Credits").child(key).child("price").getValue() + "";
                    String url = dataSnapshot.child("Users").child(user.getUid()).child("Credits").child(key).child("url").getValue() + "";
                    promoCodeSet = new PromoCodeSet(name, url, price, "Read Details>>");
                    promoCodeSets.add(promoCodeSet);
                    promoCodeAdapter = new PromoCodeAdapter(getApplicationContext(), promoCodeSets);
                    recyclerView.setAdapter(promoCodeAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
