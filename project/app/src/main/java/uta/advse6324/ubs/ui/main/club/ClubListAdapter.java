package uta.advse6324.ubs.ui.main.club;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.User;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

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
    private final User user;

    public ClubListAdapter(ArrayList<Club> club_list ,User u) {
        clubList = club_list;
        user = u;
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

                Intent intent = new Intent(view.getContext(), ClubDetailActivity.class);
                intent.putExtra("INFORMATION", clubList.get(position));
                intent.putExtra(LOGIN_USER_INFO, user);
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Click item" + position, Toast.LENGTH_SHORT).show();
                //view.invalidate();//llw


            }


        });
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }
}
