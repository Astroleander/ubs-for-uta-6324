package uta.advse6324.ubs.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Post;

public class InformationDetailActivity  extends AppCompatActivity {
    private TextView tv_id;
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_liked;
    private TextView tv_owner;
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
                Intent intent = getIntent();
            }
        });
    }

    private void initView() {
        tv_id = findViewById(R.id.information_detail_id);
        tv_title = findViewById(R.id.information_detail_title);
        tv_content = findViewById(R.id.information_detail_content);
        tv_liked = findViewById(R.id.information_detail_liked);
        tv_owner = findViewById(R.id.information_detail_owner);
        tv_timestamp = findViewById(R.id.information_detail_postdate);
        bt_back = findViewById(R.id.information_detail_back);
    }
}
