package uta.advse6324.ubs.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.ui.main.NavigationActivity;
import uta.advse6324.ubs.ui.pojo.User;
import uta.advse6324.ubs.ui.registration.RegistrationActivity;
import uta.advse6324.ubs.ui.utils.DBHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";
    private Button registerButton;
    private Button loginButton;
    private Button resetButton;

    private EditText idEditText;
    private EditText pwdEdittext;

    private DBHelper dbHelper;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        idEditText = findViewById(R.id.idnumber);
        pwdEdittext = findViewById(R.id.password);

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                Toast.makeText(LoginActivity.this, "TBC", Toast.LENGTH_LONG).show();
            }
        });

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.login();
            }
        });

        resetButton = findViewById(R.id.reset_password);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ForgetpwActivity.class));
            }
        });
    }

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void login() {
        dbHelper = new DBHelper(this);
        id = getStringFromEditText(this.idEditText);
        String pwd = getStringFromEditText(this.pwdEdittext);
        if (id.length() > 0 && pwd.length() > 0) {
            User result = dbHelper.queryLogin(id, pwd);
            handleAfterLogin(result);
        } else {
            Toast.makeText(this, "Field is required!", Toast.LENGTH_LONG).show();
            // TODO: to handle exception
        }
    }

    private void handleAfterLogin(User result) {
        if (result != null) {
            Intent intent;
            intent = new Intent(this, NavigationActivity.class);
            intent.putExtra(LOGIN_USER_INFO, result);
            startActivity(intent);
            finish();
//            Toast.makeText(this, "Successful Login!", Toast.LENGTH_LONG).show();
        } else {
            pwdEdittext.setText("");
            Toast.makeText(this, "Incorrectly Inputs! Please re-try", Toast.LENGTH_LONG).show();
        }
    }
}