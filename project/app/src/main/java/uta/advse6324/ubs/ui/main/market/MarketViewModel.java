package uta.advse6324.ubs.ui.main.market;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import uta.advse6324.ubs.R;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.db.PostDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Post;

public class MarketViewModel extends AndroidViewModel {
    private MerDBHelper dbhelper;
    private MutableLiveData<ArrayList<Merchandise>> mMerList;

    public MarketViewModel(Application application) {
        super(application);
        mMerList = new MutableLiveData<>();
        dbhelper = new MerDBHelper(getApplication().getApplicationContext());
        dbhelper.onCreate(dbhelper.getReadableDatabase());

        Merchandise[] list = dbhelper.queryAllMerchandise();
        dbhelper.close();
        ArrayList<Merchandise> arr = new ArrayList<>(Arrays.asList(list));
        mMerList.setValue(arr);
    }

    public LiveData<ArrayList<Merchandise>> getList() {
        dbhelper = new MerDBHelper(getApplication().getApplicationContext());
        dbhelper.onCreate(dbhelper.getReadableDatabase());

        Merchandise[] list = dbhelper.queryAllMerchandise();
        dbhelper.close();
        ArrayList<Merchandise> arr = new ArrayList<>(Arrays.asList(list));
        mMerList.setValue(arr);
        return mMerList;
    }
}