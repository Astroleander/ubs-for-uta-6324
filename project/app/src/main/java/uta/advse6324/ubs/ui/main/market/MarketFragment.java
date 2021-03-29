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

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MarketFragment extends Fragment {

    private MarketViewModel marketViewModel;
    private Boolean isOpen = false;
    private RecyclerView list;
    private View root;

    private FloatingActionButton fabSell;
    private FloatingActionButton fabMcd;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        marketViewModel = new ViewModelProvider(this).get(MarketViewModel.class);
        root = inflater.inflate(R.layout.fragment_market, container, false);

        initFABMenu();
        initFAB();
        initView();
        initSearch();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MarketFragment", "[onResume]");
        list = root.findViewById(R.id.list);
        marketViewModel.getList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Merchandise>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Merchandise> arr) {
                list.setAdapter(new MerListAdapter(arr, getContext()));
                list.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
        });
    }

    private void initSearch() {
        SearchView searchView = root.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() < 1) {
                    return false;
                }
                ArrayList<Merchandise> all = marketViewModel.getList().getValue();
                ArrayList<Merchandise> result = new ArrayList<>();
                for (Merchandise p: all) {
                    if (p.getName().contains(s) || p.getDescription().contains(s) || p.getOwner(getContext()).getUsername().contains(s)) {
                        result.add(p);
                    }
                }
                list.setAdapter(new MerListAdapter(result, getContext()));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() < 1) {
                    list.setAdapter(new MerListAdapter(marketViewModel.getList().getValue(), getContext()));
                }
                return false;
            }
        });
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

        fabMcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user = (User) getActivity().getIntent().getSerializableExtra(LOGIN_USER_INFO);
                    Intent intent;
                    // TODO: replace activity
                    intent = new Intent(getActivity(), ProfileMyPostsActivity.class);
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
        fabMcd       =    root.findViewById(R.id.fab_my_merchandise);
        final Animation fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open),
                fab_close  = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close),
                fab_rotate  = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rorate_clock),
                fab_rotate_anti = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_rorate_anticlock);

        final TextView tvSell = root.findViewById(R.id.tv_sell);
        final TextView tvMcd = root.findViewById(R.id.tv_my_merchandise);

        // fab menu animation controller
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    tvSell.setVisibility(View.INVISIBLE);
                    tvMcd.setVisibility(View.INVISIBLE);
                    fabSell.startAnimation(fab_close);
                    fabMcd.startAnimation(fab_close);
                    fabMain.startAnimation(fab_rotate_anti);
                    fabSell.setClickable(false);
                    fabMcd.setClickable(false);
                    isOpen = false;
                } else {
                    tvSell.setVisibility(View.VISIBLE);
                    tvMcd.setVisibility(View.VISIBLE);
                    fabSell.startAnimation(fab_open);
                    fabMcd.startAnimation(fab_open);
                    fabMain.startAnimation(fab_rotate);
                    fabSell.setClickable(true);
                    fabMcd.setClickable(true);
                    isOpen = true;
                }
            }
        });

    }

    private void initView() {
        list = root.findViewById(R.id.list);
        marketViewModel.getList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Merchandise>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Merchandise> arr) {
                list.setAdapter(new MerListAdapter(arr, getContext()));
                list.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
        });
    }
}