package uta.advse6324.ubs.ui.main.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Post;

public class InformationDetailActivity extends AppCompatActivity {
    private TextView tv_id;
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_owner;
    private TextView tv_contact;
    private TextView tv_timestamp;
    private Button bt_back;

    private Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);

        post = (Post) this.getIntent().getSerializableExtra("INFORMATION");
        this.initView();
        this.submitBack();
    }

    private void submitBack() {
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        tv_title = findViewById(R.id.information_detail_title);
        tv_content = findViewById(R.id.information_detail_content);
        tv_owner = findViewById(R.id.information_detail_owner);
        tv_contact = findViewById(R.id.information_detail_contact);
        tv_timestamp = findViewById(R.id.information_detail_timestamp);
        bt_back = findViewById(R.id.information_detail_back);
        tv_title.setText(post.getTitle());
        tv_content.setText(post.getContent());
        tv_owner.setText(post.getOwner());
        tv_contact.setText(post.getContact());
        tv_timestamp.setText(post.getTimestamp());
    }
}
