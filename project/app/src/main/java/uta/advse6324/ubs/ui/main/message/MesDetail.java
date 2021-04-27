package uta.advse6324.ubs.ui.main.message;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uta.advse6324.ubs.R;

import uta.advse6324.ubs.db.ClubDBHelper;
import uta.advse6324.ubs.db.ClubMemberDBHelper;
import uta.advse6324.ubs.db.MesDBHelper;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.ClubMember;
import uta.advse6324.ubs.pojo.Message;
import uta.advse6324.ubs.pojo.User;

public class MesDetail extends AppCompatActivity {
    private TextView tv_time;
    private TextView tv_send;
    private TextView tv_content;
    private Button bt_back;
    private Button bt_delete;

    private Message message;
    private User user;
    private MesDBHelper mesDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = (Message) this.getIntent().getSerializableExtra("INFORMATION");
        setContentView(R.layout.activity_mes_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.initView();
        this.submitBack();

        //???
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void submitBack() {
        mesDBHelper = new MesDBHelper(this);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirm delete the message?")
                        .setMessage("Confirm delete the message?")
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                 mesDBHelper.delete(new Message(message.getTime(), message.getSend(),message.getReceive(),message.getContent(),message.isRead_status()));//这里可能有问题。
                                finish();

                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        })
                        .show();
            }
        });

    }
    private void initView() {
        tv_time = findViewById(R.id.text_time);
        tv_send = findViewById(R.id.text_sned);
        tv_content = findViewById(R.id.text_detail_content);
        bt_back = findViewById(R.id.ms_bt_back);
        bt_delete = findViewById(R.id.ms_bt_delete);

        tv_time.setText(message.getTime());
        tv_send.setText(message.getSend());
        tv_content.setText(message.getContent());

    }
}

