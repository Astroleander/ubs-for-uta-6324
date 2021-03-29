package uta.advse6324.ubs.ui.main.market;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.ui.main.home.InformationDetailActivity;

import static android.content.ContentValues.TAG;

public class MerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final TextView owner_name;
        private final TextView owner_contact;
        private final TextView date;
        private final TextView price;
        private final ImageView image;

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
        }
    }

    final static int SELL_TYPE = 0;
    final static int LEND_TYPE = 2;
    private final ArrayList<Merchandise> merchandiseList;
    private final Context context;

    public MerListAdapter(ArrayList<Merchandise> list, Context ctx) {
        merchandiseList = list;
        context = ctx;
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market, parent, false));
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
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Click item" + position, Toast.LENGTH_SHORT).show();
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
