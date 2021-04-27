package uta.advse6324.ubs.ui.main.message;

import android.app.Application;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MesDBHelper;
import uta.advse6324.ubs.pojo.Message;
import uta.advse6324.ubs.pojo.User;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MessageSendActivity extends AppCompatActivity {
    private MesDBHelper dbHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MesDBHelper(this);
        user = (User) this.getIntent().getSerializableExtra(LOGIN_USER_INFO);//这个

        setContentView(R.layout.activity_message_send);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        toolBarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ui_title = MessageSendActivity.this.findViewById(R.id.title);
                EditText ui_content = MessageSendActivity.this.findViewById(R.id.content);
                EditText ui_send = MessageSendActivity.this.findViewById(R.id.send);
                String title = ui_title.getText().toString();
                String content = ui_content.getText().toString();
                String receiver = ui_send.getText().toString();
                if (title.length() == 0 || content.length() < 10) {
                    Snackbar.make(view, "Please add some content!", Snackbar.LENGTH_LONG)
                        .setAction("Ok", null).show();
                } else if (receiver.length() == 0) {
                    Snackbar.make(view, "Receiver can not be empty!", Snackbar.LENGTH_LONG)
                            .setAction("Ok", null).show();
                } else {
                    dbHelper.insert(new Message(
                            (new Date()).toString(),
                            user.getUsername(),
                            receiver,
                            title + "\n" + content,
                            false
                    ));
                    Toast.makeText(MessageSendActivity.this.getApplicationContext(), "Message is send!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}