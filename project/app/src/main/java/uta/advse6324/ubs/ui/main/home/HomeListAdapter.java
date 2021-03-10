package uta.advse6324.ubs.ui.main.home;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Post;

import static android.content.ContentValues.TAG;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    class PostViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView content;
        private final TextView author;
        private final TextView contact;
        private final TextView date;

        private final View view;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.post_title);
            content = itemView.findViewById(R.id.post_content);
            author = itemView.findViewById(R.id.post_author);
            contact = itemView.findViewById(R.id.post_contact);
            date = itemView.findViewById(R.id.post_date);
        }
    }
    class AdvViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView adv;
        public AdvViewHolder(@NonNull View itemView) {
            super(itemView);
            adv = itemView.findViewById(R.id.adv_image);
            title = itemView.findViewById(R.id.adv_title);
        }
    }

    final static int POST_TYPE = 0;
    final static int ADV_TYPE = 2;
    private final ArrayList<Post> postList;

    public HomeListAdapter(ArrayList<Post> post_list) {
        postList = post_list;
    }

    @Override
    public int getItemViewType(int position) {
        return postList.get(position).getAdvertisement() ? ADV_TYPE : POST_TYPE;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ADV_TYPE:
                return new AdvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_information_adv, parent, false));
            case POST_TYPE:
            default:
                return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_information_post, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case POST_TYPE:
                PostViewHolder pv = (PostViewHolder) holder;
                Post p = postList.get(position);
                pv.title.setText(p.getTitle());
                pv.content.setText(p.getContent());
                pv.author.setText(p.getOwner());
                pv.contact.setText(p.getContact());
                pv.date.setText(p.getTimestamp());
                pv.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), InformationDetailActivity.class);
                        intent.putExtra("INFORMATION", postList.get(position));
                        view.getContext().startActivity(intent);
                        Toast.makeText(view.getContext(), "Click item" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case ADV_TYPE:
                AdvViewHolder av = (AdvViewHolder) holder;
                Random rn = new Random();
                int answer = rn.nextInt(3) + 1;
                switch (answer) {
                    case 1:
                        av.adv.setImageResource(R.drawable.adv_01);
                        break;
                    case 2:
                        av.adv.setImageResource(R.drawable.adv_02);
                        break;
                    case 3:
                        av.adv.setImageResource(R.drawable.adv_03);
                        break;
                    case 4:
                        av.adv.setImageResource(R.drawable.adv_04);
                        break;
                }
                break;
        }
    }
}
