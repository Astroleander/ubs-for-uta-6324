package uta.advse6324.ubs.ui.main.market;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.main.NavigationActivity;
import uta.advse6324.ubs.ui.main.home.InformationDetailActivity;
import uta.advse6324.ubs.utils.DBHelper;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MerchandiseDetail extends AppCompatActivity {
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_description;
    private TextView tv_publisher;
    private Button bt_back;
    private Button bt_buyorborrow;
    private Merchandise merchandise;
    private User user;
    private ImageView image_merchandies;

    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchandise_detail);
        merchandise = (Merchandise) this.getIntent().getSerializableExtra("Merchandise");
        user = (User) this.getIntent().getSerializableExtra("User");
        this.initView();
        this.submitBack();
        this.submitBuyorBorrow();
    }
    private void submitBuyorBorrow() {
        bt_buyorborrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(view.getContext());
                //  todo: NavigationActivity.class need change to Buy_BorrowActivity.class
                Intent intent = getIntent().setClass(MerchandiseDetail.this, buy_borrow.class);
                intent.putExtra("Merchandise", merchandise);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();
            }
        });
    }
    private void submitBack() {
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
    private void initView() {
        dbHelper = new DBHelper(this);
        User owner = dbHelper.queryUser(merchandise.getOwner_id());
        tv_title = findViewById(R.id.Merchandise_detail_title);
        tv_price = findViewById(R.id.Merchandise_detail_price);
        tv_description = findViewById(R.id.Merchandise_detail_description);
        tv_publisher = findViewById(R.id.Merchandise_detail_publisher);
        bt_back = findViewById(R.id.Merchandise_detail_back);
        bt_buyorborrow = findViewById(R.id.Merchandise_detail_buy);
        image_merchandies = findViewById(R.id.imageView2);
        tv_title.setText(merchandise.getName());
//        String tmp = String.valueOf(merchandise.getPrice());
        tv_price.setText(String.valueOf(merchandise.getPrice()));
        tv_description.setText(merchandise.getDescription());
        tv_publisher.setText(owner.getUsername());
        image_merchandies.setImageBitmap(Bytes2Bimap(merchandise.getPicture()));
    }
}