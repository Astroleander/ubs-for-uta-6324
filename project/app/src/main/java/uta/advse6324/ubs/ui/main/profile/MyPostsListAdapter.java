package uta.advse6324.ubs.ui.main.profile;

import android.app.AlertDialog;
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
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Post;

public class MyPostsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String DETAIL = "DETAIL";

    class PostViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView content;
        private final TextView author;
        private final TextView contact;
        private final TextView date;
        private final Button detail;
        private final Button delete;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title);
            content = itemView.findViewById(R.id.post_content);
            author = itemView.findViewById(R.id.post_author);
            contact = itemView.findViewById(R.id.post_contact);
            date = itemView.findViewById(R.id.post_date);
            delete = itemView.findViewById(R.id.my_posts_btn_delete);
            detail = itemView.findViewById(R.id.my_posts_btn_detail);
        }
    }

    final int POST_TYPE = 0;
    private ArrayList<Post> postList;

    public MyPostsListAdapter(ArrayList<Post> post_list) {
        postList = post_list;
    }

    @Override
    public int getItemViewType(int position) {
        return POST_TYPE;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_my_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
            PostViewHolder pv = (PostViewHolder) holder;
            Post p = postList.get(position);
            pv.title.setText(p.getTitle());
            pv.content.setText(p.getContent());
            pv.author.setText(p.getOwner());
            pv.contact.setText(p.getContact());
            pv.date.setText(p.getTimestamp());
            pv.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: my posts detail
//                    Intent i = new Intent(view.getContext(), /*FILL UP WITH YOUR CLASS*/);
//                    i.putExtra(DETAIL, postList.get(position));
//                    view.getContext().startActivities();
                }
            });
            pv.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Confirm delete?")
                            .setMessage("The post can not be restored.")
                            .setIcon(R.drawable.ic_baseline_warning_24)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    PostDBHelper dbhelper = new PostDBHelper(view.getContext());
                                    dbhelper.delete(postList.get(position));
                                    postList.remove(position);
                                    MyPostsListAdapter.this.notifyItemRemoved(position);
                                    MyPostsListAdapter.this.notifyItemChanged(position, postList.size());
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
