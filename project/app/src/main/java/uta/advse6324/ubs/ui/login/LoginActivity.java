package uta.advse6324.ubs.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.ui.main.NavigationActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button registerButton;
    private Button loginButton;
    private Button resetButton;

    private EditText username;
    private EditText password;

//    private DBHelper dbHelper;
//    private String qusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            startActivity(new Intent(this, RegisterActivity.class));
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

    private void login() {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
}