package uta.advse6324.ubs.ui.main.profile;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.db.TraDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.pojo.transaction;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ProfileBuyOrBorrowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_my_buy_or_borrow);
        this.getSupportActionBar().setTitle(R.string.title_btn_profile_my_buy_or_borrow);
        initViewList();
    }

    private void initViewList() {
        final RecyclerView listview = findViewById(R.id.list);
        User user = (User) getIntent().getSerializableExtra(LOGIN_USER_INFO);

        TraDBHelper tradbhelper = new TraDBHelper(this);
        tradbhelper.onCreate(tradbhelper.getReadableDatabase());

        transaction[] trans =  tradbhelper.queryTransactionByUserID(user.getId());
        tradbhelper.close();
        HashSet<String> mer_ids = new HashSet<String>();
        for (transaction tran : trans) {
            mer_ids.add(tran.getMerid());
        }

        MerDBHelper merdbhelper = new MerDBHelper(this);
        merdbhelper.onCreate(merdbhelper.getReadableDatabase());

        Merchandise[] list = merdbhelper.queryAllMerchandiseContainsUNAVAILABLE();
        ArrayList<Merchandise> arr = new ArrayList<>();
        for (Merchandise merchandise : list) {
            if (mer_ids.contains(merchandise.getId())) {
                arr.add(merchandise);
            }
        }

        listview.setAdapter(new MyBuyOrBorrowListAdapter(arr, this, user));
        listview.setLayoutManager(new LinearLayoutManager(this));
    }
}