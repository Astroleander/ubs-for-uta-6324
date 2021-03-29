package uta.advse6324.ubs.ui.main.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.market.MerchandiseDetail;

public class MySellOrLendListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String DETAIL = "Merchandise";
    private final User user;

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final TextView owner_name;
        private final TextView owner_contact;
        private final TextView date;
        private final TextView price;
        private final ImageView image;
        private final Button detail;
        private final Button delete;

        private final View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.mer_name);
            description = itemView.findViewById(R.id.mer_description);
            image = itemView.findViewById(R.id.mer_image);
            owner_name = itemView.findViewById(R.id.mer_owner);
            owner_contact = itemView.findViewById(R.id.mer_contact);
            price = itemView.findViewById(R.id.mer_price);
            date = itemView.findViewById(R.id.mer_post_date);
            detail = itemView.findViewById(R.id.my_mer_btn_detail);
            delete = itemView.findViewById(R.id.my_mer_btn_delete);
        }
    }

    final static int SELL_TYPE = 0;
    final static int LEND_TYPE = 2;
    private final ArrayList<Merchandise> merchandiseList;
    private final Context context;

    public MySellOrLendListAdapter(ArrayList<Merchandise> list, Context ctx, User u) {
        merchandiseList = list;
        context = ctx;
        user = u;
    }

    @Override
    public int getItemViewType(int position) {
        return merchandiseList.get(position).getSell_lend() ? SELL_TYPE : LEND_TYPE;
    }

    @Override
    public int getItemCount() {
        return merchandiseList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_my_sell_or_lend, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder vh = (ViewHolder) holder;
        Merchandise m = merchandiseList.get(position);
        vh.name.setText(m.getName());
        vh.description.setText(m.getDescription());
        if (m.getPrice() != null && m.getPrice().length() > 0) {
            vh.price.setText("$"+m.getPrice());
        } else {
            vh.price.setText("for Free");
        }
        byte[] picArr = m.getPicture();
        vh.image.setImageBitmap(BitmapFactory.decodeByteArray(picArr, 0, picArr.length));
        vh.date.setText(m.getTimestamp());
        vh.owner_contact.setText(m.getOwner(context).getEmail());
        vh.owner_name.setText(m.getOwner(context).getUsername());
        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MerchandiseDetail.class);
                intent.putExtra("Merchandise", merchandiseList.get(position));
                intent.putExtra("User", user);
                view.getContext().startActivity(intent);
            }
        });
        vh.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MerchandiseDetail.class);
                intent.putExtra("Merchandise", merchandiseList.get(position));
                intent.putExtra("User", user);
                view.getContext().startActivity(intent);
            }
        });
        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirm delete?")
                        .setMessage("The post can not be restored.")
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MerDBHelper dbhelper = new MerDBHelper(view.getContext());
                                dbhelper.delete(merchandiseList.get(position));
                                merchandiseList.remove(position);
                                MySellOrLendListAdapter.this.notifyItemRemoved(position);
                                MySellOrLendListAdapter.this.notifyItemChanged(position, merchandiseList.size());
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        })
                        .show();
            }
        });
        switch (holder.getItemViewType()) {
            case SELL_TYPE:
                break;
            case LEND_TYPE:
                break;
        }
    }
}
