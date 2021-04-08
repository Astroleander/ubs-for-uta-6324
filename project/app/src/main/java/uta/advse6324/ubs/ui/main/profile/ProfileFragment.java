package uta.advse6324.ubs.ui.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.User;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ProfileFragment extends Fragment {
    public static final String PROFILE_TOKEN = "PROFILE";
    private ProfileViewModel profileViewModel;
    private View root;

    private User user;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        user = (User) getActivity().getIntent().getSerializableExtra(LOGIN_USER_INFO);
        initMyPostsButton();
        initViewProfileButton();
        initMyBiLling();
        initMerchandiseButtons();
        initMyClubButton();
        return root;
    }

    private void initMyClubButton() {
        final Button myClub = root.findViewById(R.id.my_club);
        myClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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

    private void initMyBiLling() {
        final Button myBilling = root.findViewById(R.id.my_Billings);
        myBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent;
                    intent = new Intent(getActivity(), ProfileMyBillings.class);
                    intent.putExtra(LOGIN_USER_INFO, user);
                    startActivity(intent);
                } catch (Exception e){
                    Log.e("EROERROR", "onClick: ", e);
                    Toast.makeText(root.getContext(), "Load user info failed, try login again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initMyPostsButton() {
        final Button myPosts = root.findViewById(R.id.my_posts);
        myPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent;
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
    private void initViewProfileButton() {
        Button bt_view_profile = root.findViewById(R.id.view_profile);
        bt_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewProfileActivity.class);
                intent.putExtra(PROFILE_TOKEN, user);
                startActivityForResult(intent, 1);
            }
        });
    }
    private void initMerchandiseButtons() {
        Button sellOrLend = root.findViewById(R.id.my_send_or_lend);
        Button buyOrBorrow = root.findViewById(R.id.my_buy_or_borrow);
        sellOrLend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent;
                    intent = new Intent(getActivity(), ProfileSellOrLendActivity.class);
                    intent.putExtra(LOGIN_USER_INFO, user);
                    startActivity(intent);
                } catch (Exception e){
                    Log.e("EROERROR", "onClick: ", e);
                    Toast.makeText(root.getContext(), "Load user info failed, try login again", Toast.LENGTH_LONG).show();
                }
            }
        });
        buyOrBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent;
                    intent = new Intent(getActivity(), ProfileBuyOrBorrowActivity.class);
                    intent.putExtra(LOGIN_USER_INFO, user);
                    startActivity(intent);
                } catch (Exception e){
                    Log.e("EROERROR", "onClick: ", e);
                    Toast.makeText(root.getContext(), "Load user info failed, try login again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}