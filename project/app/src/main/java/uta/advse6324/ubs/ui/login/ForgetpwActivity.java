package uta.advse6324.ubs.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.ui.main.NavigationActivity;
import uta.advse6324.ubs.ui.utils.DBHelper;

public class ForgetpwActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private Button confirmButton;

    private EditText username;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpw);
        initView();
    }

    private void initView() {
        username = findViewById(R.id.username);
        phoneNumber = findViewById(R.id.tel);


        confirmButton = findViewById(R.id.confirm_button);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetpwActivity.this.confirm();
            }
        });
    }

    private void confirm() {
        dbHelper = new DBHelper(this);
        String qusername = getStringFromEditText(this.username);
        String phonenumber = getStringFromEditText(this.phoneNumber);
        String phone_db = dbHelper.queryUserPhonenumber(qusername);
        if(phone_db.equals(phonenumber)){
            Intent intent = new Intent(this, ChangepwActivity.class);

            intent.putExtra("userName",qusername);
            intent.putExtra("phoneNumber",phonenumber);
            startActivity(intent);
        }else{
            Toast.makeText(ForgetpwActivity.this, "userName or phoneNumber wrong"+phone_db+phonenumber, Toast.LENGTH_LONG).show();

        }
    }

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }
}