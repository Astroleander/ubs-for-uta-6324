package uta.advse6324.ubs.ui.main.home;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Post;

public class HomeViewModel extends AndroidViewModel {
    private PostDBHelper dbhelper;
    private MutableLiveData<ArrayList<Post>> mPostList;

    public HomeViewModel(Application application) {
        super(application);
        mPostList = new MutableLiveData<>();
        dbhelper = new PostDBHelper(getApplication().getApplicationContext());
        dbhelper.onCreate(dbhelper.getReadableDatabase());
        ArrayList<Post> a = new ArrayList<>();
        Post[] list = dbhelper.queryAllPost();
        dbhelper.close();
        ArrayList<Post> arr = new ArrayList<>(Arrays.asList(list));
        for (int i = 0; i < arr.size(); i += 10) {
            arr.add(i, new Post("x","Advertisement","Welcome!", "x" , "x" , true, 12));
        }
        mPostList.setValue(arr);
    }

    public LiveData<ArrayList<Post>> getList() {
        return mPostList;
    }
}