package uta.advse6324.ubs.ui.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.NavigationActivity;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;
import static uta.advse6324.ubs.ui.main.profile.ProfileFragment.PROFILE_TOKEN;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView tv_id;
    private TextView tv_username;
    private TextView tv_lastname;
    private TextView tv_firstname;
    private TextView tv_phone;
    private TextView tv_email;
    private Button bt_edit;
    private Button bt_back;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        user = (User) this.getIntent().getSerializableExtra(PROFILE_TOKEN);
        this.initView();
        this.initProfile();
        this.initEditButton();
        this.initBackButton();
    }


    private void initView() {
        tv_id = findViewById(R.id.profile_view_id);
        tv_username = findViewById(R.id.profile_view_username);
        tv_lastname = findViewById(R.id.profile_view_lastname);
        tv_firstname = findViewById(R.id.profile_view_firstname);
        tv_phone = findViewById(R.id.profile_view_phone);
        tv_email = findViewById(R.id.profile_view_email);
        bt_edit = findViewById(R.id.profile_view_edit);
        bt_back = findViewById(R.id.profile_view_back);
    }

    private void initProfile() {
        tv_id.setText(user.getId());
        tv_username.setText(user.getUsername());
        tv_lastname.setText(user.getLastname());
        tv_firstname.setText(user.getFirstname());
        tv_phone.setText(user.getPhone());
        tv_email.setText(user.getEmail());
    }

    private void initEditButton() {
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("PROFILE", user);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void initBackButton() {
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfileActivity.this, NavigationActivity.class);
                intent.putExtra(LOGIN_USER_INFO, user);
                intent.putExtra("VIEW_PROFILE", 4);
                startActivity(intent);
                finish();
            }
        });
    }
}
