package uta.advse6324.ubs.ui.main.message;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;

import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.db.MesDBHelper;
import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Message;
import uta.advse6324.ubs.pojo.User;

import static uta.advse6324.ubs.ui.login.LoginActivity.LOGIN_USER_INFO;

public class MessageViewModel extends AndroidViewModel {
    private MesDBHelper dbhelper;
    private MutableLiveData<ArrayList<Message>> mMesList;

    public MessageViewModel(Application application) {
        super(application);
        ArrayList<Message> arr = queryList();
        mMesList.setValue(arr);
    }

    private ArrayList<Message> queryList() {

        mMesList = new MutableLiveData<>();
        dbhelper = new MesDBHelper(getApplication().getApplicationContext());
        dbhelper.onCreate(dbhelper.getReadableDatabase());
//        dbhelper.insert(new Message(" ","cyf1","1001889072","This is a introduction of club1",true));

//        dbhelper.insert(new Message(" ","1001889076","1001889074","This is a introduction of club2",true));
//        dbhelper.insert(new Message(" ","cyf3","1001889074","This is a introduction of club3",true));
//        dbhelper.insert(new Message(" ","cyf4","1001889074","This is a introduction of club4",true));


        Message[] list = dbhelper.queryAllMessage();
        dbhelper.close();

//        for(int i =0;i<list.length;i++){
//
//        }
        ArrayList<Message> arr = new ArrayList<>(Arrays.asList(list));
        return arr;
    }

    public LiveData<ArrayList<Message>> getList() {
        ArrayList<Message> arr = queryList();
        mMesList.setValue(arr);
        return mMesList;
    }

}
