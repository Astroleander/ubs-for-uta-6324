package uta.advse6324.ubs.ui.main.market;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.db.TraDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.pojo.transaction;

public class buy_borrow extends AppCompatActivity {

    private User user;
    private Merchandise merchandise;

    private EditText editText;
    private EditText editText2;

    private Button button_buy;
    private Button button2;

    private transaction tra_new;
    private TraDBHelper dbHelper;
    private MerDBHelper merDBHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_borrow);


        user = (User) this.getIntent().getSerializableExtra("User");
        merchandise =(Merchandise)this.getIntent().getSerializableExtra("Merchandise");
        Log.d("user",user.toString());
        Log.d("merchadise", merchandise.toString());
        String user_id = user.getId();
        String mer_id = merchandise.getId();
        Boolean buy_borrow = merchandise.getSell_lend();
        Boolean pay_status = Boolean.TRUE;

        this.dbHelper = new TraDBHelper(this);
        this.merDBHelper = new MerDBHelper(this);
        this.dbHelper.getReadableDatabase();
        this.merDBHelper.getReadableDatabase();
        button_buy = findViewById(R.id.button);
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.editTextNumberSigned);
                editText2= findViewById(R.id.editTextNumberSigned2);
                String card_id = getStringFromEditText(editText);
                String card_verify_id = getStringFromEditText(editText2);



                tra_new = new transaction(
                        user_id,
                        mer_id,
                        buy_borrow,
                        pay_status
                );
                merchandise.setAvailable_status(Boolean.FALSE);
                merDBHelper.changeA(merchandise);
//                Log.d("test", String.valueOf(card_id.length()));
                Log.d("test", card_id);
//                Log.d("test", String.valueOf(card_verify_id.length()));
                if(card_id.length()==16 && card_verify_id.length()==4){
                    dbHelper.insert(tra_new);
//                    Log.d("test", String.valueOf(card_id.length()));
//                    Log.d("test", String.valueOf(card_verify_id.length()));
                    Toast.makeText(buy_borrow.this, "successfully.", Toast.LENGTH_LONG).show();
                    finish();
                }else if(card_id.length()!=16 && card_verify_id.length()==4){
                    Toast.makeText(buy_borrow.this, "Please fill in valid Credit card number.", Toast.LENGTH_LONG).show();
                }
                else if(card_verify_id.length()!=4 && card_id.length()==16){
                    Toast.makeText(buy_borrow.this, "Please fill in valid Credit card verify number.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(buy_borrow.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();
                }
            }
        });
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }
}