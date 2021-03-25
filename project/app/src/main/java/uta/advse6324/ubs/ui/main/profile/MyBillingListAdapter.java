package uta.advse6324.ubs.ui.main.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.BillingDBHelper;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Billing;
import uta.advse6324.ubs.pojo.Post;

public class MyBillingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String DETAIL = "BILLING_DETAIL";

    class BillingViewHolder extends RecyclerView.ViewHolder {

        private final TextView Id;
        private final TextView name;
        private final TextView address;
        private final TextView userId;
        private final Button detail;
        private final Button delete;

        public BillingViewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.billing_Id);
            name = itemView.findViewById(R.id.billing_name);
            address = itemView.findViewById(R.id.billing_address);
            userId = itemView.findViewById(R.id.billing_userId);
            delete = itemView.findViewById(R.id.my_billing_btn_delete);
            detail = itemView.findViewById(R.id.my_billing_btn_detail);
        }
    }

    final int BILLING_TYPE = 0;
    private ArrayList<Billing> billingList;

    public MyBillingListAdapter(ArrayList<Billing> Billing_list) {
        billingList = Billing_list;
    }

    @Override
    public int getItemViewType(int position) {
        return BILLING_TYPE;
    }

    @Override
    public int getItemCount() {
        return billingList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_my_billing, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        BillingViewHolder bv = (BillingViewHolder) holder;
        Billing b = billingList.get(position);
        bv.Id.setText(b.getId());
        bv.name.setText(b.getName());
        bv.address.setText(b.getAddress());
        bv.userId.setText(b.getUserId());
//        bv.detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), PostDetailActivity.class);
//                i.putExtra(DETAIL, postList.get(position));
//                view.getContext().startActivity(i);
//            }
//        });
        bv.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirm delete?")
                        .setMessage("The Billing can not be restored.")
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                BillingDBHelper dbhelper = new BillingDBHelper(view.getContext());
                                dbhelper.delete(billingList.get(position));
                                billingList.remove(position);
                                MyBillingListAdapter.this.notifyItemRemoved(position);
                                MyBillingListAdapter.this.notifyItemChanged(position, billingList.size());
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        })
                        .show();
            }
        });
    }
}
