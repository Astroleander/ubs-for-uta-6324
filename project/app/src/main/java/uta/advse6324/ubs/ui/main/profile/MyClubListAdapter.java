package uta.advse6324.ubs.ui.main.profile;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Member;
import java.util.ArrayList;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.ClubDBHelper;
import uta.advse6324.ubs.db.ClubMemberDBHelper;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.ClubMember;
import uta.advse6324.ubs.ui.main.club.ClubDetailActivity;
import uta.advse6324.ubs.pojo.User;
import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MyClubListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final String DETAIL = "CLUB_DETAIL";

    class ClubViewHolder  extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView category;
        private final TextView introduction;
        private final Button detail;

        private final View view;
        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.club_name);
            category = itemView.findViewById(R.id.club_category);
            introduction = itemView.findViewById(R.id.club_introduction);
            detail = itemView.findViewById(R.id.my_club_btn_detail);
        }

    }


    private final ArrayList<Club> clubList;
    private final User user;
    private ClubMemberDBHelper clubMemberDBHelper;
    private ClubDBHelper clubDBHelper;
    public MyClubListAdapter(ArrayList<Club> club_list,User u) {
        clubList = club_list;
        user= u;//
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyClubListAdapter.ClubViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_my_club, parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyClubListAdapter.ClubViewHolder cvh = (MyClubListAdapter.ClubViewHolder) holder;
        Club club = clubList.get(position);
        cvh.name.setText(club.getName());
        cvh.category.setText(club.getCategory());
        cvh.introduction.setText(club.getIntroduction());
        cvh.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MyClubDetailActivity.class);
                intent.putExtra("INFORMATION", clubList.get(position));
                intent.putExtra(LOGIN_USER_INFO, user);
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Click item" + position, Toast.LENGTH_SHORT).show();
                //llw
                clubList.remove(position);
                MyClubListAdapter.this.notifyItemRemoved(position);
                MyClubListAdapter.this.notifyItemChanged(position, clubList.size());

            }
        });
        cvh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clubMemberDBHelper = new ClubMemberDBHelper(view.getContext());
                clubDBHelper = new ClubDBHelper(view.getContext());

                Intent intent = new Intent(view.getContext(),MyClubDetailActivity.class);
                intent.putExtra("INFORMATION", clubList.get(position));
                intent.putExtra(LOGIN_USER_INFO, user);
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Click item" + position, Toast.LENGTH_SHORT).show();
                //clubList.remove(position);
//                MyClubListAdapter.this.notifyItemRemoved(position);
//                MyClubListAdapter.this.notifyItemChanged(position, clubList.size());




            }

        });

    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }
}
