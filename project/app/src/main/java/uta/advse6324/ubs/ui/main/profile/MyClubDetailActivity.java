package uta.advse6324.ubs.ui.main.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.ClubDBHelper;
import uta.advse6324.ubs.db.ClubMemberDBHelper;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.ClubMember;
import uta.advse6324.ubs.pojo.User;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MyClubDetailActivity extends AppCompatActivity {
    private TextView tv_name;
    private TextView tv_introduction;
    private TextView tv_category;
    private TextView tv_manager;
    private Button bt_back;
    private Button bt_quit;

    private Club club;
    private User user;
    private ClubMemberDBHelper clubMemberDBHelper;
    private ClubDBHelper clubDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_club_detail);
        this.getSupportActionBar().setTitle(R.string.title_club_detail);

        club = (Club) this.getIntent().getSerializableExtra("INFORMATION");
        user = (User) this.getIntent().getSerializableExtra(LOGIN_USER_INFO);
        this.initView();
        this.submitBack();
    }
    private void refresh() {
        onCreate(null);
    }
    private void submitBack() {
        clubMemberDBHelper = new ClubMemberDBHelper(this);
        clubDBHelper = new ClubDBHelper(this);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
//        bt_join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//
//                new AlertDialog.Builder(view.getContext())
//                        .setTitle("Confirm Join the club?")
//                        .setMessage("Confirm Join the club?")
//                        .setIcon(R.drawable.ic_baseline_warning_24)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                clubMemberDBHelper.insert(new ClubMember(club.getName(), user.getUsername()));//将clubname传进来，和用户名
//
//                                finish();
//
//                            }
//                        })
//                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {}
//                        })
//                        .show();
//            }
//        });
        bt_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirm Quit The Club?")
                        .setMessage("Confirm Quit The Club?")
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                clubMemberDBHelper.delete(new ClubMember(club.getName(), user.getUsername()));//将clubname传进来，和用户名

                                finish();

                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        })
                        .show();
            }

        });
    }

    private void initView() {
        tv_name = findViewById(R.id.my_club_name);
        tv_introduction = findViewById(R.id.my_club_intro);
        tv_category = findViewById(R.id.my_club_category);
        tv_manager = findViewById(R.id.my_club_manager);
        bt_back = findViewById(R.id.my_club_back);

        bt_quit = findViewById(R.id.my_club_quite);
        tv_name.setText(club.getName());
        tv_introduction.setText(club.getIntroduction());
        tv_category.setText(club.getCategory());
        tv_manager.setText(club.getManagerid());

    }
}
