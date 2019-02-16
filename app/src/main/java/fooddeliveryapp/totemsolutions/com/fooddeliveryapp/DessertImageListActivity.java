package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DessertImageListActivity extends AppCompatActivity {

    TextView cartQuantity;
    LinearLayout cartBtn;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private List<DessertImageUpload> dessertUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_image_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        dessertUploads = new ArrayList<>();
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dessertUploads = new ArrayList<>();
                progressDialog.dismiss();
                dessertUploads = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.child("desserts").getChildren()) {
                    DessertImageUpload dessertUpload = postSnapshot.getValue(DessertImageUpload.class);
                    dessertUploads.add(dessertUpload);
                    adapter = new DessertAdapter(getApplicationContext(), dessertUploads);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cartBtn = (LinearLayout) findViewById(R.id.cartBtn);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DessertImageListActivity.this, CheckoutActivity.class));
            }
        });

//        String quan = getIntent().getExtras().getString("Quantity");
//        cartQuantity = (TextView) findViewById(R.id.cartQuan);
//        cartQuantity.setText(quan+"");
    }
}
