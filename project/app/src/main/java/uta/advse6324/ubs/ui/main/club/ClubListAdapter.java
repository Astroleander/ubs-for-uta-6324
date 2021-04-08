package uta.advse6324.ubs.ui.main.club;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Club;

public class ClubListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    class ClubViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView category;
        private final TextView introduction;

        private final View view;
        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.club_name);
            category = itemView.findViewById(R.id.club_category);
            introduction = itemView.findViewById(R.id.club_introduction);
        }
    }


    private final ArrayList<Club> clubList;

    public ClubListAdapter(ArrayList<Club> club_list) {
        clubList = club_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClubListAdapter.ClubViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ClubListAdapter.ClubViewHolder cvh = (ClubViewHolder) holder;
        Club club = clubList.get(position);
        cvh.name.setText(club.getName());
        cvh.category.setText(club.getCategory());
        cvh.introduction.setText(club.getIntroduction());
        cvh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ClubDetailActivity

            }
        });
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }
}
