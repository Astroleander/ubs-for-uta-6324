package uta.advse6324.ubs.ui.main.market;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.User;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class Sell_Activity extends AppCompatActivity {
    private User user;
    private Merchandise merchandise;

    private static final int IMAGE = 1;



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sell);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        this.getSupportActionBar().setTitle("SELL OR LEND");
        user = (User) this.getIntent().getSerializableExtra(LOGIN_USER_INFO);//这个
        initView();
//        this. Release();
    }


    private void initView(){

    }

    public void onClick(View v) {
        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        ((ImageView)findViewById(R.id.image)).setImageBitmap(bm);
    }



}