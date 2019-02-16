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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 09-04-2018.
 */

public class PromoCodeAdapter extends RecyclerView.Adapter<PromoCodeAdapter.ViewHolder> {
    public static int check = 0;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private Context context;
    private List<PromoCodeSet> promoCodeSets;
    private ArrayList<String> arrayListCreditdelete;

    public PromoCodeAdapter(Context context, List<PromoCodeSet> promoCodeSets) {
        this.context = context;
        this.promoCodeSets = promoCodeSets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        PromoCodeSet promoCodeSet = promoCodeSets.get(position);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final int price = Integer.parseInt(promoCodeSet.getPrice());
        Log.d("pricecard", String.valueOf(price));
        holder.textViewname.setText(promoCodeSet.getName());
        holder.textViewPrice.setText(promoCodeSet.getPrice());
        holder.textViewcreditread.setText(promoCodeSet.getDetails());
        Glide.with(context).load(promoCodeSet.getUrl()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == 0) {
                    Log.d("checkpromo", check + "");
                    Bundle bundle = new Bundle();
                    bundle.putInt("concession", price);
                    bundle.putInt("check", 1);
                    bundle.putInt("btnclick", 1);
//                    databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            arrayListCreditdelete = new ArrayList<>();
//                            for (DataSnapshot dataSnapshot1 : dataSnapshot.child("Users").child(user.getUid()).child("Credits").getChildren()){
//                                String keycredit = dataSnapshot1.getKey();
//                                arrayListCreditdelete.add(keycredit);
//                            }
//                            Log.d("arrayListCreditdelete",arrayListCreditdelete+"");
//                            Log.d("positionclicked",position+"");
//                            Log.d("creditclicked",arrayListCreditdelete.get(position));
//                            databaseReference.child("Users").child(user.getUid()).child("Credits").child(arrayListCreditdelete.get(position)).child("description").removeValue();
//                            databaseReference.child("Users").child(user.getUid()).child("Credits").child(arrayListCreditdelete.get(position)).child("name").removeValue();
//                            databaseReference.child("Users").child(user.getUid()).child("Credits").child(arrayListCreditdelete.get(position)).child("price").removeValue();
//                            databaseReference.child("Users").child(user.getUid()).child("Credits").child(arrayListCreditdelete.get(position)).child("url").removeValue();
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
                    Intent intent = new Intent(context, DeliveryDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    check = 1;
//                    Log.d("arraylistposition",arrayListCreditdelete.get(position));
                } else {
                    Log.d("checkpromo", check + "");
                    Toast.makeText(context, "Only one promo code can be applied at a time", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, DeliveryDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return promoCodeSets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewPrice, textViewname, textViewcreditread;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPrice = itemView.findViewById(R.id.creditPrice);
            textViewname = itemView.findViewById(R.id.creditname);
            textViewcreditread = itemView.findViewById(R.id.creditdetails);
            imageView = itemView.findViewById(R.id.creditImage);
            linearLayout = itemView.findViewById(R.id.promoCardLayout);
        }
    }
}
