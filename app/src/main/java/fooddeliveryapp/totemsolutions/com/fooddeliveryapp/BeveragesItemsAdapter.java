package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by aakash and adtiya on 3/3/2018.
 */

public class BeveragesItemsAdapter extends RecyclerView.Adapter<BeveragesItemsAdapter.ViewHolder> {
    String beveragename, beveragePrice, beverageDescription, beverageImage;
    String dishname;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    int flag;
    private Context context;
    private List<BeveragesUpload> beveragesUploads;

    public BeveragesItemsAdapter(Context context, List<BeveragesUpload> beveragesUploads) {
        this.context = context;
        this.beveragesUploads = beveragesUploads;
    }

    @Override
    public BeveragesItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BeveragesItemsAdapter.ViewHolder holder, final int position) {
        final BeveragesUpload beveragesUpload = beveragesUploads.get(position);
        holder.txtDish.setText(beveragesUpload.getName());
        Glide.with(context).load(beveragesUpload.getUrl()).into(holder.imageView);
        holder.txtPrice.setText("Rs. " + beveragesUpload.getPrice());
        holder.txtCart.setText(beveragesUpload.getMinus());
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        holder.txtCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishname = beveragesUpload.getName();
                Log.d("msg", dishname);
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Name").setValue(dishname);
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Price").setValue(0 + "");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Quantity").setValue(0 + "");
            }
        });
        holder.itemCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 0;
                beveragename = beveragesUpload.getName();
                beverageDescription = beveragesUpload.getDescription();
                beverageImage = beveragesUpload.getUrl();
                beveragePrice = beveragesUpload.getPrice();
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Name").setValue(dishname);
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Price").setValue(0 + "");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(beveragename).child("Quantity").setValue(0 + "");
                Intent intent = new Intent(context, ItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("beverage_name", beveragename);
                bundle.putString("beverage_Description", beverageDescription);
                bundle.putString("beverage_Image", beverageImage);
                bundle.putString("beverage_Price", beveragePrice);
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
        return beveragesUploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtDish, txtPrice, txtCart;
        LinearLayout itemCardLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            txtDish = itemView.findViewById(R.id.item);
            txtPrice = itemView.findViewById(R.id.itemPrice1);
            txtCart = itemView.findViewById(R.id.cart);
            itemCardLayout = (LinearLayout) itemView.findViewById(R.id.itemCardLayout);
        }
    }
}
