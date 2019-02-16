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
 * Created by aakash on 3/3/2018.
 */
public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.ViewHolder> {
    String beveragename, beveragePrice, beverageDescription, beverageImage;
    String dishname;
    String dishprice;
    int dishQuantity = 0;
    int quantity = 0;
    int quant[];
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    int flag;
    int i = 0;
    private Context context;
    private List<DessertImageUpload> dessertUploads;


    public DessertAdapter(Context context, List<DessertImageUpload> dessertUploads) {
        this.context = context;
        this.dessertUploads = dessertUploads;
        quant = new int[getItemCount()];
        for (int i = 0; i < quant.length; i++)
            quant[i] = 0;
    }

    @Override
    public DessertAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dessert_item_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final DessertImageUpload dessertImageUpload = dessertUploads.get(position);
        final DessertImageUpload dessertUpload = dessertUploads.get(position);
        holder.txtDish.setText(dessertUpload.getName());
        Glide.with(context).load(dessertUpload.getUrl()).into(holder.imageView);
        holder.txtCart.setText(dessertUpload.getMinus());
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        holder.txtPrice.setText("Rs. " + dessertUpload.getPrice());
        holder.txtCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishname = dessertImageUpload.getName();
                Log.d("msg", dishname);
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Name").setValue(dishname);
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Price").setValue(0 + "");
                databaseReference.child("Users").child(user.getUid()).child("Cart").child(dishname).child("Quantity").setValue(0 + "");
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                beveragename = dessertUpload.getName();
                beverageDescription = dessertUpload.getDescription();
                beverageImage = dessertUpload.getUrl();
                beveragePrice = dessertUpload.getPrice();
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
        return dessertUploads.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtDish, txtPrice, txtCart;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            txtDish = itemView.findViewById(R.id.item);
            txtPrice = itemView.findViewById(R.id.itemPrice1);
            txtCart = itemView.findViewById(R.id.cart);
            linearLayout = itemView.findViewById(R.id.dessertitemcard);
        }
    }
}

