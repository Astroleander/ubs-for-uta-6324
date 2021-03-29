package uta.advse6324.ubs.ui.main.profile;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ProfileSellOrLendActivity extends AppCompatActivity {
    private MerDBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_my_sell_or_lend);
        this.getSupportActionBar().setTitle(R.string.title_btn_profile_my_sell_or_lend);
        initViewList();
    }

    private void initViewList() {
        final RecyclerView listview = findViewById(R.id.list);
        dbhelper = new MerDBHelper(this);
        dbhelper.onCreate(dbhelper.getReadableDatabase());

        User user = (User) getIntent().getSerializableExtra(LOGIN_USER_INFO);
        Merchandise[] list = dbhelper.queryMerchandiseByUserID(user.getId());
        ArrayList<Merchandise> arr = new ArrayList<>(Arrays.asList(list));
        listview.setAdapter(new MySellOrLendListAdapter(arr, this, user));
        listview.setLayoutManager(new LinearLayoutManager(this));
    }
}