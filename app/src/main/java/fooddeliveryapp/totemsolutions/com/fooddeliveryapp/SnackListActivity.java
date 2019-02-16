package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SnackListActivity extends AppCompatActivity {

    ImageView imageViewnorthIndian, imageViewchinese, imageVieweggDelite, imageViewtuteeCrush, imageViewSouthIndian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_list);
        imageViewnorthIndian = findViewById(R.id.snacknorthIndian);
        imageViewchinese = findViewById(R.id.snackchineseFood);
        imageVieweggDelite = findViewById(R.id.snackeggDelite);
        imageViewtuteeCrush = findViewById(R.id.snacktuteeCrush);
        imageViewSouthIndian = findViewById(R.id.snacksouthIndian);
        imageViewnorthIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SnackListActivity.this, NorthIndian.class);
                startActivity(intent);
            }
        });
        imageViewchinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SnackListActivity.this, Chinese.class);
                startActivity(intent);
            }
        });
        imageVieweggDelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SnackListActivity.this, EggDelite.class);
                startActivity(intent);
            }
        });
        imageViewSouthIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SnackListActivity.this, SouthIndian.class);
                startActivity(intent);
            }
        });
        imageViewtuteeCrush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SnackListActivity.this, TuteeCrush.class);
                startActivity(intent);
            }
        });
    }
}
