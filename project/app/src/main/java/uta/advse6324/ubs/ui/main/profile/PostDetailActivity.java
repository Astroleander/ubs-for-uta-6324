package uta.advse6324.ubs.ui.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.utils.DBHelper;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;
import static uta.advse6324.ubs.ui.main.profile.MyPostsListAdapter.DETAIL;

public class PostDetailActivity extends AppCompatActivity {
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_liked;
    private TextView tv_owner;
    private TextView tv_contact;
    private TextView tv_timestamp;
    private Button bt_back;
    private Button bt_edit;

    private Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        post = (Post) this.getIntent().getSerializableExtra(DETAIL);

        this.initView();
        this.initEdit();
        this.submitBack();
    }

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    private void initEdit() {
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetailActivity.this, EditPostActivity.class);
                intent.putExtra("POST", post);
                startActivity(intent);
            }
        });
    }

    private void submitBack() {
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(view.getContext());
                Intent intent = getIntent().setClass(PostDetailActivity.this, ProfileMyPostsActivity.class);
                intent.putExtra(LOGIN_USER_INFO, dbHelper.queryUserByUsername(post.getOwner()));
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        tv_title = findViewById(R.id.post_detail_title);
        tv_content = findViewById(R.id.post_detail_content);
        tv_liked = findViewById(R.id.post_detail_liked);
        tv_owner = findViewById(R.id.post_detail_owner);
        tv_contact = findViewById(R.id.post_detail_contact);
        tv_timestamp = findViewById(R.id.post_detail_timestamp);
        bt_back = findViewById(R.id.post_detail_back);
        bt_edit = findViewById(R.id.post_detail_edit);
        tv_title.setText(post.getTitle());
        tv_content.setText(post.getContent());
        tv_owner.setText(post.getOwner());
        tv_contact.setText(post.getContact());
        tv_liked.setText(post.getLiked()+"");
        tv_timestamp.setText(post.getTimestamp());
    }
}
