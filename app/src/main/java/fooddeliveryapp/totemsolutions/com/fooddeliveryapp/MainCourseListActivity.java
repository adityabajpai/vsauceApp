package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainCourseListActivity extends AppCompatActivity {

    CardView northF, southF, punjabiF, chinsesF;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String typecontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course_list);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        northF = (CardView) findViewById(R.id.northF);
        southF = (CardView) findViewById(R.id.southF);
        chinsesF = (CardView) findViewById(R.id.chineseF);
        punjabiF = (CardView) findViewById(R.id.punjabiF);

        northF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typecontent = "NorthIndian";
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("NorthIndian").child("Name").setValue("NorthIndian");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("NorthIndian").child("Price").setValue(0 + "");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("NorthIndian").child("Quantity").setValue(0 + "");
                Intent intent = new Intent(MainCourseListActivity.this, ThaliTypeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Type", typecontent);
                Log.d("type", "north");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        southF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typecontent = "SouthIndian";
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("SouthIndian").child("Name").setValue("SouthIndian");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("SouthIndian").child("Price").setValue(0 + "");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("SouthIndian").child("Quantity").setValue(0 + "");
                Intent intent = new Intent(MainCourseListActivity.this, ThaliTypeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Type", typecontent);
                Log.d("type", "south");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        chinsesF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typecontent = "ChineseFood";
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("ChineseFood").child("Name").setValue("ChineseFood");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("ChineseFood").child("Price").setValue(0 + "");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("ChineseFood").child("Quantity").setValue(0 + "");
                Intent intent = new Intent(MainCourseListActivity.this, ThaliTypeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Type", typecontent);
                Log.d("type", "chinese");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        punjabiF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typecontent = "PunjabiFood";
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("PunjabiFood").child("Name").setValue("PunjabiFood");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("PunjabiFood").child("Price").setValue(0 + "");
//                databaseReference.child("Users").child(firebaseUser.getUid()).child("Cart").child("PunjabiFood").child("Quantity").setValue(0 + "");
                Intent intent = new Intent(MainCourseListActivity.this, ThaliTypeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Type", typecontent);
                Log.d("type", "punjabi");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
