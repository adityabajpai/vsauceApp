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

public class TuteeCrush extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TuteeCrushAdapter adapter;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private List<EggDeliteSet> eggDeliteSets;
    private Button button;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mauthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee_crush);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        eggDeliteSets = new ArrayList<>();
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eggDeliteSets = new ArrayList<>();
                progressDialog.dismiss();
                eggDeliteSets = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.child("tuteeCrush").getChildren()) {
                    EggDeliteSet imageUpload = postSnapshot.getValue(EggDeliteSet.class);
                    eggDeliteSets.add(imageUpload);
                    adapter = new TuteeCrushAdapter(getApplicationContext(), eggDeliteSets);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
