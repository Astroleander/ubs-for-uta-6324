package uta.advse6324.ubs.ui.main.profile;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.ClubDBHelper;
import uta.advse6324.ubs.db.ClubMemberDBHelper;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.ClubMember;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.club.ClubListAdapter;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ProfileClubActivity extends AppCompatActivity {
    private ClubMemberDBHelper clubMemberDBHelper;
    private ClubDBHelper clubDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_my_club);
        this.getSupportActionBar().setTitle(R.string.title_btn_profile_my_club);
        initViewList();
    }

    private void initViewList() {
        final RecyclerView listview = findViewById(R.id.profile_club_list);
        clubMemberDBHelper = new ClubMemberDBHelper(this);
        clubDBHelper = new ClubDBHelper(this);
        clubMemberDBHelper.onCreate(clubMemberDBHelper.getReadableDatabase());;
        //test
//        clubMemberDBHelper.insert(new ClubMember("club0", "zyt"));
//        clubMemberDBHelper.insert(new ClubMember("club1", "yxz"));
//        clubMemberDBHelper.insert(new ClubMember("club2", "zyt"));
//        clubMemberDBHelper.insert(new ClubMember("club3", "zyt"));
//        clubMemberDBHelper.insert(new ClubMember("club4", "zzz"));
//        clubMemberDBHelper.insert(new ClubMember("club5", "yyy"));
//        clubMemberDBHelper.insert(new ClubMember("club6", "ttt"));
//        clubMemberDBHelper.insert(new ClubMember("club7", "yxz"));
//        clubMemberDBHelper.insert(new ClubMember("club8", "yxz"));
//        clubMemberDBHelper.insert(new ClubMember("club9", "zyt"));
        //
//        clubMemberDBHelper.insert(new ClubMember("club0", "xigua"));
//        clubMemberDBHelper.insert(new ClubMember("club1", "xigua"));
//        clubMemberDBHelper.insert(new ClubMember("club2", "xigua"));
//        clubMemberDBHelper.insert(new ClubMember("club3", "xigua"));
        //
         //clubMemberDBHelper.delete(new ClubMember("club2", "xig"));
        //
        User user = (User) getIntent().getSerializableExtra(LOGIN_USER_INFO);

//        ClubMember[] members = clubMemberDBHelper.queryAllMember();
//        System.out.println(members.length);
        String[] clubNameList = clubMemberDBHelper.queryClubByUsername(user.getUsername());
        ArrayList<Club> arr = new ArrayList<>();
        for(int  i=0; i<clubNameList.length; i++){
            Club[] club = clubDBHelper.queryClubByName(clubNameList[i]);
            arr.add(club[0]);
        }

        listview.setAdapter(new MyClubListAdapter(arr,user));//llw
        listview.setLayoutManager(new LinearLayoutManager(this));

    }
}
