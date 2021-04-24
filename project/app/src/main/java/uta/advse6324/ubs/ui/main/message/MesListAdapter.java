package uta.advse6324.ubs.ui.main.message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Message;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.club.ClubDetailActivity;
import uta.advse6324.ubs.ui.main.club.ClubListAdapter;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MesListAdapter.MesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MesListAdapter.MesViewHolder mvh = (MesListAdapter.MesViewHolder) holder;
//        ClubListAdapter.ClubViewHolder cvh = (ClubListAdapter.ClubViewHolder) holder;
        Message mes = messageList.get(position);
//        Club club = clubList.get(position);
        mvh.time.setText(mes.getTime());
        mvh.send.setText(mes.getSend());

        mvh.view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MesDetail.class);
                intent.putExtra("INFORMATION", messageList.get(position));
                intent.putExtra(LOGIN_USER_INFO, user);
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Click item" + position, Toast.LENGTH_SHORT).show();
                //view.invalidate();//llw


            }


        });

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class MesViewHolder extends RecyclerView.ViewHolder {
        private final TextView time;
        private final TextView send;

        private final View view;
        public MesViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            time = itemView.findViewById(R.id.message_time);
            send = itemView.findViewById(R.id.message_send);

        }
    }

    private final ArrayList<Message> messageList;
//    private final Context context;
    private final User user;

    public MesListAdapter(ArrayList<Message> list,  User u) {
        messageList = list;
//        context = ctx;
        user = u;
    }
}
