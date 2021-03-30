package uta.advse6324.ubs.ui.main.market;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import uta.advse6324.ubs.db.MerDBHelper;
import uta.advse6324.ubs.pojo.Merchandise;

public class MarketViewModel extends AndroidViewModel {
    private MerDBHelper dbhelper;
    private MutableLiveData<ArrayList<Merchandise>> mMerList;

    public MarketViewModel(Application application) {
        super(application);
        ArrayList<Merchandise> arr = queryList();
        mMerList.setValue(arr);
    }

    private ArrayList<Merchandise> queryList() {
        mMerList = new MutableLiveData<>();
        dbhelper = new MerDBHelper(getApplication().getApplicationContext());
        dbhelper.onCreate(dbhelper.getReadableDatabase());

        Merchandise[] list = dbhelper.queryAllMerchandise();
        dbhelper.close();
        ArrayList<Merchandise> arr = new ArrayList<>(Arrays.asList(list));
        return arr;
    }

    public LiveData<ArrayList<Merchandise>> getList() {
        ArrayList<Merchandise> arr = queryList();
        mMerList.setValue(arr);
        return mMerList;
    }
}