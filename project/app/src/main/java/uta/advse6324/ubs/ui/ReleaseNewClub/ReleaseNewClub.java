package uta.advse6324.ubs.ui.ReleaseNewClub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.BillingDBHelper;
import uta.advse6324.ubs.db.ClubDBHelper;
import uta.advse6324.ubs.db.ClubMemberDBHelper;
import uta.advse6324.ubs.pojo.Billing;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.ClubMember;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.ReleaseNewBilling.ReleaseNewBilling;
import uta.advse6324.ubs.ui.main.profile.ProfileMyBillings;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ReleaseNewClub extends AppCompatActivity {
    private Button release_button;
    private ClubDBHelper dbHelper;
    private ClubMemberDBHelper CMdbHelper;
    private User user;
    private Club club;
    private EditText text_name;
    private TextView tv_select;
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String>adapter;
    private String category;
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
        this.CMdbHelper = new ClubMemberDBHelper(this);
        this.CMdbHelper.getReadableDatabase();
        release_button = findViewById(R.id.realse_club_button);
        text_name = findViewById(R.id.club_name);
        tv_select = findViewById(R.id.select_club_category);
        spinner = (Spinner)findViewById(R.id.spinner_category);
        list = new ArrayList<String>();
        list.add("sport");
        list.add("music");
        list.add("academic");
        list.add("movie");
        list.add("professional");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = list.get(position);
//                Toast.makeText(ReleaseNewClub.this, category, Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
                ClubMember clubMember;
                clubMember = new ClubMember(club_name, name);
                if (description.length() == 0 || club_name.length() == 0){
                    Toast.makeText(ReleaseNewClub.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();
                }else if (club_name.length() < 6) {
                    Toast.makeText(ReleaseNewClub.this, "The entered club name should contain more than 6 characters.", Toast.LENGTH_LONG).show();
                }else{
                        club = new Club(
                                club_name,
                                name,
                                category,
                                description
                        );
                        Log.d("initSubmit", club.toString());
                        dbHelper.insert(club);
                        CMdbHelper.insert(clubMember);

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