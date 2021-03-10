package uta.advse6324.ubs.ui.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.utils.DBHelper;

import static uta.advse6324.ubs.ui.main.profile.MyPostsListAdapter.DETAIL;
import static uta.advse6324.ubs.ui.main.profile.ProfileFragment.PROFILE_TOKEN;

public class EditPostActivity extends AppCompatActivity {

    private EditText et_title;
    private EditText et_content;
    private EditText et_liked;
    private EditText et_owner;
    private EditText et_contact;
    private EditText et_time;
    private Button bt_save;
    private Button bt_back;

    private Post post;
    private PostDBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        post = (Post) this.getIntent().getSerializableExtra("POST");

        this.initView();
        this.initSubmit();
        this.initBack();
    }


    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }
    private void initSubmit() {
        dbHelper = new PostDBHelper(this);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = getStringFromEditText(et_title);
                String content = getStringFromEditText(et_content);
                if (title.length() == 0 || content.length() == 0){
                    Toast.makeText(EditPostActivity.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();
                } else {
                    post.setTitle(title);
                    post.setContent(content);

                    String result = dbHelper.editPost(post);
                    Intent intent = getIntent();
                    intent.setClass(EditPostActivity.this, PostDetailActivity.class);
                    intent.putExtra(DETAIL, post);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void initBack() {
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.setClass(EditPostActivity.this, PostDetailActivity.class);
                intent.putExtra(DETAIL, post);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        et_title = findViewById(R.id.edit_post_title);
        et_content = findViewById(R.id.edit_post_content);
        et_liked = findViewById(R.id.edit_post_liked);
        et_owner = findViewById(R.id.edit_post_owner);
        et_contact = findViewById(R.id.edit_post_contact);
        et_time = findViewById(R.id.edit_post_timestamp);
        bt_save = findViewById(R.id.edit_post_save);
        bt_back = findViewById(R.id.edit_post_back);
        et_title.setText(post.getTitle());
        et_content.setText(post.getContent());
        et_liked.setText(post.getLiked()+"");
        et_owner.setText(post.getOwner());
        et_contact.setText(post.getContact());
        et_time.setText(post.getTimestamp());
        et_time.setEnabled(false);
        et_liked.setEnabled(false);
        et_owner.setEnabled(false);
        et_contact.setEnabled(false);
    }
}
