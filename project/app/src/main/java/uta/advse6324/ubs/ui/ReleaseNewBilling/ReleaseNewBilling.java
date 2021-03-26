package uta.advse6324.ubs.ui.ReleaseNewBilling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.BillingDBHelper;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Billing;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.ReleaseNewInfo.ReleaseNewInformation;
import uta.advse6324.ubs.ui.main.NavigationActivity;
import uta.advse6324.ubs.ui.main.profile.ProfileMyBillings;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ReleaseNewBilling extends AppCompatActivity {
    private Button release_button;
    private BillingDBHelper dbHelper;
    private User user;
    private Billing billing;
    private EditText text_Id;
//    private String text_name;
//    private String text_userId;
    private EditText text_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_new_billing);
        this.getSupportActionBar().setTitle(R.string.title_release_new_information);
        user = (User) this.getIntent().getSerializableExtra(LOGIN_USER_INFO);//这个
        initView();
        this. Release();
    }
    private void initView() {
        this.dbHelper = new BillingDBHelper(this);
        this.dbHelper.getReadableDatabase();
        release_button = findViewById(R.id.release_button);
        text_Id = findViewById(R.id.text_Id);
//        text_name = findViewById(R.id.text_name);
//        text_userId = findViewById(R.id.text_UserId);
        text_address = findViewById(R.id.text_address);

    }
    private void Release() {
        release_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = getStringFromEditText(text_Id);
                String name = user.getUsername();
                String userId = user.getId();
                String address = getStringFromEditText(text_address);
                billing = new Billing(
                        Id,
                        name,
                        address,
                        userId
                );
                Log.d("initSubmit", billing.toString());
                dbHelper.insert(billing);
                Toast.makeText(ReleaseNewBilling.this, "Released successfully.", Toast.LENGTH_LONG).show();


                Intent intent = getIntent();
                intent = intent.setClass(ReleaseNewBilling.this, ProfileMyBillings.class);
                startActivity(intent);
                finish();
            }
        });
    }
    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

}