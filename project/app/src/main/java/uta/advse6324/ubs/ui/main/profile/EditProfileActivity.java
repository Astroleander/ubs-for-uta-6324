package uta.advse6324.ubs.ui.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.NavigationActivity;
import uta.advse6324.ubs.utils.DBHelper;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;
import static uta.advse6324.ubs.ui.main.profile.ProfileFragment.PROFILE_TOKEN;

public class EditProfileActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_username;
    private EditText et_lastname;
    private EditText et_firstname;
    private EditText et_phone;
    private EditText et_email;
    private Button bt_edit;
    private Button bt_back;

    private User user;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        user = (User) this.getIntent().getSerializableExtra(PROFILE_TOKEN);
        this.initView();
        this.initSubmit();
        this.initBack();
    }

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    private void initSubmit() {
        dbHelper = new DBHelper(this);
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = getStringFromEditText(et_username);
                String lastname = getStringFromEditText(et_lastname);
                String firstname = getStringFromEditText(et_firstname);
                String phone = getStringFromEditText(et_phone);
                String email = getStringFromEditText(et_email);
                if (username.length() == 0 || lastname.length() == 0 || firstname.length() == 0 || phone.length() == 0 ||email.length() == 0){
                    Toast.makeText(EditProfileActivity.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();
                } else {
                    user.setUsername(username);
                    user.setLastname(lastname);
                    user.setFirstname(firstname);
                    user.setPhone(phone);
                    user.setEmail(email);

                    String result = dbHelper.editUser(user);
                    Intent intent = getIntent();
                    intent.setClass(EditProfileActivity.this, ViewProfileActivity.class);
                    intent.putExtra(PROFILE_TOKEN, user);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void initBack() {
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.setClass(EditProfileActivity.this, ViewProfileActivity.class);
                intent.putExtra(PROFILE_TOKEN, user);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        et_id = findViewById(R.id.edit_profile_id);
        et_username = findViewById(R.id.edit_profile_username);
        et_lastname = findViewById(R.id.edit_profile_lastname);
        et_firstname = findViewById(R.id.edit_profile_firstname);
        et_phone = findViewById(R.id.edit_profile_phone);
        et_email = findViewById(R.id.edit_profile_email);
        bt_edit = findViewById(R.id.edit_profile_edit);
        bt_back = findViewById(R.id.edit_profile_back);
        et_id.setText(user.getId());
        et_id.setEnabled(false);
        et_username.setText(user.getUsername());
        et_lastname.setText(user.getLastname());
        et_firstname.setText(user.getFirstname());
        et_phone.setText(user.getPhone());
        et_email.setText(user.getEmail());
    }
}
