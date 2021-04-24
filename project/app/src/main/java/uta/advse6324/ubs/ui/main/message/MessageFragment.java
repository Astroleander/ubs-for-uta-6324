package uta.advse6324.ubs.ui.main.message;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.ClubDBHelper;
import uta.advse6324.ubs.db.ClubMemberDBHelper;
import uta.advse6324.ubs.db.MesDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Message;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.market.MarketViewModel;
import uta.advse6324.ubs.ui.main.market.MerListAdapter;
import uta.advse6324.ubs.ui.main.market.Sell_Activity;
import uta.advse6324.ubs.ui.main.profile.ProfileSellOrLendActivity;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.profile.ProfileMyPostsActivity;
import uta.advse6324.ubs.ui.main.profile.ProfileSellOrLendActivity;
import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MessageFragment extends Fragment {


    private MessageViewModel messageViewModel;
    private Boolean isOpen = false;
    private RecyclerView list;
    private View root;

    private FloatingActionButton fabSell;
    private FloatingActionButton fabMcd;
    private User user;

    private  MesDBHelper messageDBHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        root = inflater.inflate(R.layout.fragment_message, container, false);
        user = (User) getActivity().getIntent().getSerializableExtra(LOGIN_USER_INFO);


        initView();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MessageFragment", "[onResume]");
        list = root.findViewById(R.id.list);
        messageViewModel.getList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Message> arr) {
                String receive = user.getId();
//                for(int i =0;i<arr.size();i++){
//                    Log.d(arr.get(i).getReceive(),receive);
//                    if(arr.get(i).getReceive().contains(receive)){
////                        arr.remove(i);
//
//                    }else {
//                        arr.remove(i);
//                    }
//
//                }

                list.setAdapter(new MesListAdapter(arr, user));
                list.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
        });
    }



    private void initView() {
        list = root.findViewById(R.id.list);


        messageViewModel.getList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Message> arr) {
                String receive = user.getId();
//                for(int i =0;i<arr.size();i++){
//                    Log.d(arr.get(i).getReceive(),receive);
//                    if(arr.get(i).getReceive().contains(receive)){
////                        arr.remove(i);
//
//                    }else {
//                        arr.remove(i);
//                    }
//
//                }



                list.setAdapter(new MesListAdapter(arr, user));
                list.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
        });
    }
}