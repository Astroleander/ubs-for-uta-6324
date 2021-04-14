package uta.advse6324.ubs.ui.main.club;

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
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.ReleaseNewClub.ReleaseNewClub;
import uta.advse6324.ubs.ui.ReleaseNewInfo.ReleaseNewInformation;
import uta.advse6324.ubs.ui.main.home.HomeListAdapter;
import uta.advse6324.ubs.ui.main.profile.ProfileClubActivity;
import uta.advse6324.ubs.ui.main.profile.ProfileMyPostsActivity;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ClubFragment extends Fragment {
    private Boolean isOpen = false;

    private ClubViewModel clubViewModel;
    private RecyclerView list;

    private FloatingActionButton fabMy;
    private FloatingActionButton fabCreate;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clubViewModel = new ViewModelProvider(this).get(ClubViewModel.class);
        root = inflater.inflate(R.layout.fragment_club, container, false);
        initFABMenu();
        initFAB();
        initView();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ClubFragment", "[onResume]");
        clubViewModel.getList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Club>>() {
            @Override
            public void onChanged(ArrayList<Club> clubs) {
                list.setAdapter(new ClubListAdapter(clubs));
                list.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
        });
    }

    private void initFAB() {
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user = (User) getActivity().getIntent().getSerializableExtra(LOGIN_USER_INFO);
                    //TODO
                    Intent intent;
                    intent = new Intent(getActivity(), ReleaseNewClub.class);
                    intent.putExtra(LOGIN_USER_INFO, user);
                    startActivity(intent);
                } catch (Exception e){
                    Log.e("EROERROR", "onClick: ", e);
                    Toast.makeText(root.getContext(), "Load user info failed, try login again", Toast.LENGTH_LONG).show();
                }
            }
        });


        fabMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user = (User) getActivity().getIntent().getSerializableExtra(LOGIN_USER_INFO);
                    Intent intent;
                    intent = new Intent(getActivity(), ProfileClubActivity.class);
                    intent.putExtra(LOGIN_USER_INFO, user);
                    startActivity(intent);
                } catch (Exception e){
                    Log.e("EROERROR", "onClick: ", e);
                    Toast.makeText(root.getContext(), "Load user info failed, try login again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initFABMenu() {
        final FloatingActionButton fabMain =      root.findViewById(R.id.fab);
        fabMy      =    root.findViewById(R.id.fab_my_posts);
        fabCreate  =    root.findViewById(R.id.fab_create);
        final Animation fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open),
                fab_close  = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close),
                fab_rotate  = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rorate_clock),
                fab_rotate_anti = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rorate_anticlock);

        final TextView tvMyposts = root.findViewById(R.id.tv_my);
        final TextView tvCreate = root.findViewById(R.id.tv_create);

        // fab menu animation controller
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    tvMyposts.setVisibility(View.INVISIBLE);
                    tvCreate.setVisibility(View.INVISIBLE);
                    fabMy.startAnimation(fab_close);
                    fabCreate.startAnimation(fab_close);
                    fabMain.startAnimation(fab_rotate_anti);
                    fabMy.setClickable(false);
                    fabCreate.setClickable(false);
                    isOpen = false;
                } else {
                    tvMyposts.setVisibility(View.VISIBLE);
                    tvCreate.setVisibility(View.VISIBLE);
                    fabMy.startAnimation(fab_open);
                    fabCreate.startAnimation(fab_open);
                    fabMain.startAnimation(fab_rotate);
                    fabMy.setClickable(true);
                    fabCreate.setClickable(true);
                    isOpen = true;
                }
            }
        });

    }

    private void initView() {
        list = root.findViewById(R.id.club_list);
        clubViewModel.getList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Club>>() {
            @Override
            public void onChanged(ArrayList<Club> clubs) {
                list.setAdapter(new ClubListAdapter(clubs));
                list.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
        });
    }
}