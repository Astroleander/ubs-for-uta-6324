package uta.advse6324.ubs.ui.main.market;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.ReleaseNewInfo.ReleaseNewInformation;
import uta.advse6324.ubs.ui.main.home.HomeViewModel;
import uta.advse6324.ubs.ui.main.profile.ProfileMyPostsActivity;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MarketFragment extends Fragment {

    private MarketViewModel martketViewModel;

    private Boolean isOpen = false;
    private RecyclerView list;
    private View root;

    private FloatingActionButton fabSell;
//    private FloatingActionButton fabCreate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        martketViewModel =
                new ViewModelProvider(this).get(MarketViewModel.class);
        root = inflater.inflate(R.layout.fragment_market, container, false);
//        final TextView textView = root.findViewById(R.id.text_market);
//        martketViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        initFABMenu();
        initFAB();
        return root;
    }

    private void initFAB() {
        fabSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user = (User) getActivity().getIntent().getSerializableExtra(LOGIN_USER_INFO);
                    Intent intent;
                    intent = new Intent(getActivity(), Sell_Activity.class);
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
        fabSell      =    root.findViewById(R.id.fab_sell);
//        fabCreate  =    root.findViewById(R.id.fab_create);
        final Animation fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open),
                fab_close  = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close),
                fab_rotate  = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rorate_clock),
                fab_rotate_anti = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rorate_anticlock);

        final TextView tvSell = root.findViewById(R.id.tv_sell);
//        final TextView tvCreate = root.findViewById(R.id.tv_create);

        // fab menu animation controller
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    tvSell.setVisibility(View.INVISIBLE);
//                    tvCreate.setVisibility(View.INVISIBLE);
                    fabSell.startAnimation(fab_close);
//                    fabCreate.startAnimation(fab_close);
                    fabMain.startAnimation(fab_rotate_anti);
                    fabSell.setClickable(false);
//                    fabCreate.setClickable(false);
                    isOpen = false;
                } else {
                    tvSell.setVisibility(View.VISIBLE);
//                    tvCreate.setVisibility(View.VISIBLE);
                    fabSell.startAnimation(fab_open);
//                    fabCreate.startAnimation(fab_open);
                    fabMain.startAnimation(fab_rotate);
                    fabSell.setClickable(true);
//                    fabCreate.setClickable(true);
                    isOpen = true;
                }
            }
        });

    }
}