package uta.advse6324.ubs.ui.ReleaseNewClub;

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
import uta.advse6324.ubs.db.ClubDBHelper;
import uta.advse6324.ubs.pojo.Billing;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.ReleaseNewBilling.ReleaseNewBilling;
import uta.advse6324.ubs.ui.main.profile.ProfileMyBillings;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ReleaseNewClub extends AppCompatActivity {
    private Button release_button;
    private ClubDBHelper dbHelper;
    private User user;
    private Club club;
    private EditText text_name;
    //    private String text_name;
    //    private String text_userId;
    private EditText text_description;
    private Button cancel_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_new_club);
        this.getSupportActionBar().setTitle(R.string.title_release_new_information);
        user = (User) this.getIntent().getSerializableExtra(LOGIN_USER_INFO);//这个
        initView();
        this.Release();
        this.Cancel();
    }
    private void initView() {
        this.dbHelper = new ClubDBHelper(this);
        this.dbHelper.getReadableDatabase();
        release_button = findViewById(R.id.realse_club_button);
        text_name = findViewById(R.id.club_name);
//        text_name = findViewById(R.id.text_name);
//        text_userId = findViewById(R.id.text_UserId);
        text_description = findViewById(R.id.club_description);
        cancel_button = findViewById(R.id.cancle_club_button);

    }
    private void Release() {
        release_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String club_name = getStringFromEditText(text_name);
                String name = user.getUsername();
                String userId = user.getId();
                String description = getStringFromEditText(text_description);
                if (description.length() == 0 || club_name.length() == 0){
                    Toast.makeText(ReleaseNewClub.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();
                }else if (club_name.length() < 6) {
                    Toast.makeText(ReleaseNewClub.this, "The entered club name should contain more than 6 characters.", Toast.LENGTH_LONG).show();
                }else{
                        club = new Club(
                                club_name,
                                name,
                                "10", // TODO： 确认一下category是如何得到的
                                description
                        );
                        Log.d("initSubmit", club.toString());
                        dbHelper.insert(club);
                        Toast.makeText(ReleaseNewClub.this, "Released successfully.", Toast.LENGTH_LONG).show();
                        finish();
                }

            }
        });
    }
    private void Cancel() {
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }
}