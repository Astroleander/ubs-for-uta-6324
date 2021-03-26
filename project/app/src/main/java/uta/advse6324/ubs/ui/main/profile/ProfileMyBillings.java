package uta.advse6324.ubs.ui.main.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.BillingDBHelper;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Billing;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.ReleaseNewBilling.ReleaseNewBilling;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.Arrays;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ProfileMyBillings extends AppCompatActivity {
    private BillingDBHelper dbhelper;
    private FloatingActionButton fabAdd;
    private FloatingActionButton fabCreate;
    private View root;
    private FloatingActionButton fabSell;
    private Button btn_addOne;
    private Boolean isOpen = false;
    private RecyclerView list;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_my_billings);
        this.getSupportActionBar().setTitle(R.string.title_btn_profile_my_Blings);
        user = (User) getIntent().getSerializableExtra(LOGIN_USER_INFO);
        initViewList();
    }
    /**
     *创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_billings_menu,menu); //通过getMenuInflater()方法得到MenuInflater对象，再调用它的inflate()方法就可以给当前活动创建菜单了，第一个参数：用于指定我们通过哪一个资源文件来创建菜单；第二个参数：用于指定我们的菜单项将添加到哪一个Menu对象当中。
        return true; // true：允许创建的菜单显示出来，false：创建的菜单将无法显示。
    }
    /**
     *菜单的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.id_add_billing:
                Intent intent = getIntent();
                intent = intent.setClass(ProfileMyBillings.this, ReleaseNewBilling.class);
                intent.putExtra(LOGIN_USER_INFO, user);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

    private void initViewList() {
        final RecyclerView listview = findViewById(R.id.billing_list);
        dbhelper = new BillingDBHelper(this);
        dbhelper.onCreate(dbhelper.getReadableDatabase());
//        User user = (User) getIntent().getSerializableExtra(LOGIN_USER_INFO);
        Billing[] list = dbhelper.queryBillingByOwner(user.getUsername());
        ArrayList<Billing> arr = new ArrayList<>(Arrays.asList(list));
        listview.setAdapter(new MyBillingListAdapter(arr));
        listview.setLayoutManager(new LinearLayoutManager(this));
    }

}