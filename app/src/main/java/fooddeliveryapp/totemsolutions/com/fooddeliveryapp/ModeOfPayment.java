package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class ModeOfPayment extends AppCompatActivity {

    CardView cardView_paytm, cardView_cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_of_payment);
        cardView_paytm = findViewById(R.id.paytm);
        cardView_cod = findViewById(R.id.cod);
        cardView_paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModeOfPayment.this, DeliveryDetailsActivity.class));
            }
        });
        cardView_cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModeOfPayment.this, DeliveryDetailsActivity.class));
            }
        });
    }
}
