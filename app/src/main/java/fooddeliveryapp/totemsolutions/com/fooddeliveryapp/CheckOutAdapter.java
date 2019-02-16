package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by asus on 28-03-2018.
 */

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.ViewHolder> {
    private Context context;
    private List<CheckOutSet> checkOutSets;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public CheckOutAdapter(Context context, List<CheckOutSet> checkOutSets) {
        this.context = context;
        this.checkOutSets = checkOutSets;
    }

    @Override
    public CheckOutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CheckOutAdapter.ViewHolder holder, int position) {
        CheckOutSet checkOutSet = checkOutSets.get(position);
        holder.txtname.setText(checkOutSet.getName());
        holder.txtquant.setText(checkOutSet.getQuantity());
        holder.txtprice.setText(checkOutSet.getPrice());
    }

    @Override
    public int getItemCount() {
        return checkOutSets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtquant, txtprice;

        public ViewHolder(View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.dishname);
            txtquant = itemView.findViewById(R.id.dishquantity);
            txtprice = itemView.findViewById(R.id.dishprice);
        }
    }
}
