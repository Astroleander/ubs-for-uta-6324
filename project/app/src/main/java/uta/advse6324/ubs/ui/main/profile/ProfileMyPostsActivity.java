package uta.advse6324.ubs.ui.main.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class ProfileMyPostsActivity extends AppCompatActivity {
    private PostDBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_my_posts);
        this.getSupportActionBar().setTitle(R.string.title_btn_profile_my_posts);
        initViewList();
    }

    private void initViewList() {
        final RecyclerView listview = findViewById(R.id.list);
        dbhelper = new PostDBHelper(this);
        dbhelper.onCreate(dbhelper.getReadableDatabase());

        User user = (User) getIntent().getSerializableExtra(LOGIN_USER_INFO);
        Post[] list = dbhelper.queryPostByOwner(user.getUsername());
        ArrayList<Post> arr = new ArrayList<>(Arrays.asList(list));
        listview.setAdapter(new MyPostsListAdapter(arr));
        listview.setLayoutManager(new LinearLayoutManager(this));
    }
}