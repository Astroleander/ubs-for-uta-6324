package uta.advse6324.ubs.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.ui.pojo.User;
import uta.advse6324.ubs.ui.utils.DBHelper;

public class ChangepwActivity extends AppCompatActivity {

    private EditText password1;
    private EditText password2;

    private DBHelper dbHelper;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);
        initView();
    }
    private void initView() {
        password1= findViewById(R.id.pw_1);
        password2 = findViewById(R.id.pw_2);


        confirmButton = findViewById(R.id.confirm_button);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangepwActivity.this.confirm();
            }
        });
    }

    private void confirm() {
        dbHelper = new DBHelper(this);
        String username = getIntent().getStringExtra("userName");
        String pw1 = getStringFromEditText(this.password1);
        String pw2 = getStringFromEditText(this.password2);
        User user = dbHelper.queryUser(username);
        if(pw1.equals(pw2)){
            Intent intent = new Intent(this, LoginActivity.class);
            user.setPassword(pw1);
            dbHelper.changePW(user);


            startActivity(intent);
            Toast.makeText(ChangepwActivity.this,"succesfull",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ChangepwActivity.this, "The passwords did not match", Toast.LENGTH_LONG).show();

        }
    }


    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }
}