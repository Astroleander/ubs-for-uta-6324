package uta.advse6324.ubs.ui.ReleaseNewInfo;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import uta.advse6324.ubs.ui.main.NavigationActivity;
import uta.advse6324.ubs.R;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;
import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;



public class ReleaseNewInformation extends AppCompatActivity {
    private Button release_button;
    private PostDBHelper dbHelper;
    private User user;
    private Post post;
    private EditText text_topic;
    private EditText text_content;
    private EditText text_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_new_information);
        this.getSupportActionBar().setTitle(R.string.title_release_new_information);
        user = (User) this.getIntent().getSerializableExtra(LOGIN_USER_INFO);//这个
        initView();
        this. Release();
    }


    private void Release() {
        release_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getRandomNickname(10);//Without considering the repetition, a random number of 10 digits and letters.
                String owner = user.getUsername();
                Boolean advertisement = false;
                String title = getStringFromEditText(text_topic);
                String content = getStringFromEditText(text_content);
                String contact = getStringFromEditText(text_contact);
                Integer liked = null;
                if (title.length() > 10){
                    Toast.makeText(ReleaseNewInformation.this, "Topic need to be less than 12 characters.", Toast.LENGTH_LONG).show();
                } else if (content.length() > 60){
                    Toast.makeText(ReleaseNewInformation.this, "Content need to be less than 60 characters.", Toast.LENGTH_LONG).show();
                } else if (contact.length() > 16){
                    Toast.makeText(ReleaseNewInformation.this, "Contact need to be less than 16 characters.", Toast.LENGTH_LONG).show();
                } else if (content.length() == 0 || contact.length() == 0 || title.length() == 0) {
                    Toast.makeText(ReleaseNewInformation.this, "Please fill in all the information.", Toast.LENGTH_LONG).show();
                }else {
                    post = new Post(
                            id,
                            title,
                            content,
                            owner,
                            contact,
                            advertisement,
                            liked
                    );
                    Log.d("initSubmit", post.toString());
                    dbHelper.insert(post);
                    Toast.makeText(ReleaseNewInformation.this, "Released successfully.", Toast.LENGTH_LONG).show();


                    finish();
                }
            }
        });
    }
    private void initView() {
        this.dbHelper = new PostDBHelper(this);
        this.dbHelper.getReadableDatabase();
        release_button = findViewById(R.id.release_button);
        text_topic = findViewById(R.id.text_topic);
        text_contact = findViewById(R.id.text_contact);
        text_content = findViewById(R.id.text_content);

    }
    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    /**
     * java generates 10 digits of random numbers and letter combinations
     * @param length: the length of the random number
     * @return
     */
    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

            if ("char".equalsIgnoreCase(charOrNum)) {

                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }





}