package uta.advse6324.ubs.ui.main.profile;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.utils.DBHelper;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<User> mUser;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mUser = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<User> getUserinfo(Context context, User user){
        System.out.println(user.getId());
        mUser.setValue(user);
        return mUser;
    }
}