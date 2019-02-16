package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BeveragesMenuListActivity extends AppCompatActivity {

    int i = 0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private List<BeveragesUpload> beveragesUploads;
    private Button button;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mauthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages_menu_list);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String flag = bundle.getString("Beverage");
//        Log.d("flag",flag);
//        button = findViewById(R.id.logout);
//        mAuth = FirebaseAuth.getInstance();
//        mauthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser() == null) {
//                    startActivity(new Intent(BeveragesMenuListActivity.this, UserRegisterActivity.class));
//                }
//            }
//        };
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//            }
//        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        beveragesUploads = new ArrayList<>();
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                beveragesUploads = new ArrayList<>();
                progressDialog.dismiss();
                beveragesUploads = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.child("beverages").getChildren()) {
                    BeveragesUpload imageUpload = postSnapshot.getValue(BeveragesUpload.class);
                    beveragesUploads.add(imageUpload);
                    adapter = new BeveragesItemsAdapter(getApplicationContext(), beveragesUploads);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //signout method
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mauthStateListener);
//    }
}
