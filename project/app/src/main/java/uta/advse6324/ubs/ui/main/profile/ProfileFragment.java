package uta.advse6324.ubs.ui.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.NavigationActivity;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ProfileFragment extends Fragment {

    private ProfileViewModel martketViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        martketViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        initView();
        return root;
    }

    private void initView() {
        final Button myPost = root.findViewById(R.id.my_posts);
        myPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user = (User) getActivity().getIntent().getSerializableExtra(LOGIN_USER_INFO);
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
}