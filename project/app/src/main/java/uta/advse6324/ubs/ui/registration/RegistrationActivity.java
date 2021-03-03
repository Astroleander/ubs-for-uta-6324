package uta.advse6324.ubs.ui.registration;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.utils.DBHelper;

public class RegistrationActivity  extends AppCompatActivity {

    private EditText et_id;
    private EditText et_username;
    private EditText et_password;
    private EditText et_lastname;
    private EditText et_firstname;
    private EditText et_phone;
    private EditText et_email;
    private Button bt_register;


    private DBHelper dbHelper;
    private User userForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.initView();

    }


    private void initView() {

        this.dbHelper = new DBHelper(this);
        this.dbHelper.getReadableDatabase();

        this.initForm();

        this.initSubmit();
        


    }

    private void initSubmit() {
        bt_register = findViewById(R.id.register_signup);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getStringFromEditText(et_id);
                String username = getStringFromEditText(et_username);
                String password = getStringFromEditText(et_password);
                String lastname = getStringFromEditText(et_lastname);
                String firstname = getStringFromEditText(et_firstname);
                String phone = getStringFromEditText(et_phone);
                String email = getStringFromEditText(et_email);
                if (id.length() != 10){
                    Toast.makeText(RegistrationActivity.this, "University ID is not valid.", Toast.LENGTH_LONG).show();
                } else if (!dbHelper.queryIdUniqe(id)){
                    Toast.makeText(RegistrationActivity.this, "University ID has been registered.", Toast.LENGTH_LONG).show();
                } else if (password.length() < 6 || password.length() >12){
                    Toast.makeText(RegistrationActivity.this, "Password should be 6-12 characters.", Toast.LENGTH_LONG).show();
                } else if (username.length() == 0 || lastname.length() == 0 || firstname.length() == 0 || phone.length() == 0 ||email.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();

                } else {
                    userForm = new User(
                            id,
                            username,
                            password,
                            lastname,
                            firstname,
                            phone,
                            email
                    );
//                    System.out.println(userForm);

                    Log.d("initSubmit", userForm.toString());
                    dbHelper.addUser(userForm);

                    Toast.makeText(RegistrationActivity.this, "Registered successfully.", Toast.LENGTH_LONG).show();
                    finish();
                }


            }
        });

    }

    private void initForm() {
        et_id = findViewById(R.id.register_id);
        et_username = findViewById(R.id.register_username);
        et_password = findViewById(R.id.register_password);
        et_lastname = findViewById(R.id.register_lastname);
        et_firstname = findViewById(R.id.register_firstname);
        et_phone = findViewById(R.id.register_phone);
        et_email = findViewById(R.id.register_email);
    }

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

}
