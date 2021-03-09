package uta.advse6324.ubs.ui.main.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private Activity activity;

    private Button bt_view_profile;

    private User user;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activity = this.getActivity();
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        this.root = inflater.inflate(R.layout.fragment_profile, container, false);
        user = (User) activity.getIntent().getSerializableExtra(LOGIN_USER_INFO);
        this.initView();
        this.initViewProfileButton();

//        final TextView textView = root.findViewById(R.id.text_profile);
//        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        return root;
    }

    private void initView() {
        bt_view_profile = root.findViewById(R.id.view_profile);
    }


    private void initViewProfileButton() {
        bt_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ViewProfileActivity.class);
                intent.putExtra(PROFILE_TOKEN, user);
                startActivityForResult(intent, 1);
            }
        });
    }

}