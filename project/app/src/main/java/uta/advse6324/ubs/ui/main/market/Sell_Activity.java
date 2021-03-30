package uta.advse6324.ubs.ui.main.market;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.ui.registration.RegistrationActivity;
import uta.advse6324.ubs.utils.DBHelper;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class Sell_Activity extends AppCompatActivity {
    private User user;
    private Merchandise merchandise;


    private EditText et_name;
    private EditText et_price;
    private EditText et_description;


    private Button bt_sell_lend;

    private MerDBHelper dbHelper;
    private Merchandise merForm;

    private CheckBox cb;
    Boolean lend=Boolean.FALSE;

    private static final int IMAGE = 1;
    ImageView iv;



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

        iv = (ImageView) findViewById((R.id.imageView));

        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(v == iv)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMAGE);
//                    if(isChanged){
//                        iv.setImageDrawable(getResources().getDrawable(R.drawable.icon));
//                    }else
//                    {
//                        iv.setImageDrawable(getResources().getDrawable(R.drawable.png1));
//                    }
//                    isChanged = !isChanged;

                }
            }
        });
        cb = (CheckBox)findViewById(R.id.check_lend);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    lend=Boolean.TRUE;
                }else{
                    lend=Boolean.FALSE;
                }
            }
        });
        this.initView();

//        this. Release();
    }


    private void initView(){
        this.dbHelper = new MerDBHelper(this);
        this.dbHelper.getReadableDatabase();

        this.initForm();
        this.initSubmit();
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
        ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bm);
    }

    private void initSubmit() {
//        bt_register = findViewById(R.id.register_signup);
        bt_sell_lend = findViewById(R.id.bt_sell_lend);
        bt_sell_lend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String id = getStringFromEditText(et_id);
                String name = getStringFromEditText(et_name);
                String price= getStringFromEditText(et_price);
                String description = getStringFromEditText(et_description);
                String owner_id = user.getId();
                Bitmap bitmap = ((BitmapDrawable)iv.getDrawable()).getBitmap();
                byte[] picture = Bitmap2Bytes(bitmap);


                merForm = new Merchandise(

                        name,
                        description,
                        picture,
                        price,
                        Boolean.TRUE,
                        lend,
                        owner_id);
//                    System.out.println(userForm);
                if(name.length()==0 || price.length()==0||description.length()==0){
                    Toast.makeText(Sell_Activity.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();

                }else{
                    Log.d("initSubmit", merForm.toString());
                    dbHelper.insert(merForm);

                    Toast.makeText(Sell_Activity.this, "Add successfully.", Toast.LENGTH_LONG).show();
                    finish();
                }





            }
        });

    }

    private void initForm() {

        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        et_description = findViewById(R.id.et_description);

    }
    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

}