package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by asus on 02-04-2018.
 */

public class ChineseAdapter extends RecyclerView.Adapter<ChineseAdapter.ViewHolder> {
    String beveragename, beveragePrice, beverageDescription, beverageImage;
    String dishname;
    FirebaseAuth firebaseAuth;
    int flag;
    DatabaseReference databaseReference;
    private Context context;
    private List<ChineseGet> chineseGets;

    public ChineseAdapter(Context context, List<ChineseGet> chineseGets) {
        this.context = context;
        this.chineseGets = chineseGets;
    }

    @Override
    public ChineseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chinesecard, parent, false);
        ChineseAdapter.ViewHolder viewHolder = new ChineseAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChineseAdapter.ViewHolder holder, final int position) {
        final ChineseGet chineseGet = chineseGets.get(position);

        holder.txtDish.setText(chineseGet.getName());
        Glide.with(context).load(chineseGet.getUrl()).into(holder.imageView);
//        holder.txtCart.setText(chineseGet.getMinus());
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
//        holder.txtCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dishname = chineseGet.getName();
//                Log.d("msg", dishname);
//                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Name").setValue(dishname);
//                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Price").setValue(0 + "");
//                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Quantity").setValue(0 + "");
//            }
//        });
        holder.itemCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 0;
                beveragename = chineseGet.getName();
                beverageDescription = chineseGet.getDescription();
                beverageImage = chineseGet.getUrl();
//                beveragePrice = chineseGet.getPrice();
//                databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Name").setValue(dishname);
//                databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Price").setValue(0 + "");
//                databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Quantity").setValue(0 + "");
                Intent intent = new Intent(context, SnackItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("beverage_name", beveragename);
                bundle.putString("beverage_Description", beverageDescription);
                bundle.putString("beverage_Image", beverageImage);
//                bundle.putString("beverage_Price", beveragePrice);
                bundle.putInt("position", position);
                bundle.putInt("Choice", flag);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return chineseGets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtDish;
        LinearLayout itemCardLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            txtDish = itemView.findViewById(R.id.item);
            itemCardLayout = (LinearLayout) itemView.findViewById(R.id.dessertitemcard);
        }
    }
}
