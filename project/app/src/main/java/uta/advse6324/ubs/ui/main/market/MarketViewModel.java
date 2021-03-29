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

        Drawable d = getApplication().getDrawable(R.drawable.adv_02);
        Bitmap bm = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] blob= stream.toByteArray();

        Merchandise[] list = dbhelper.queryAllMerchandise();
        dbhelper.close();
        ArrayList<Merchandise> arr = new ArrayList<>(Arrays.asList(list));
        arr.add(new Merchandise("Sell My Soul", "100$ for broken", blob, "", true, true, "1"));
        arr.add(new Merchandise("Sell My Apple", "100$ for broken", blob, "", true, true, "1"));
        arr.add(new Merchandise("Sell My Pen", "100$ for broken", blob, "", true, true, "1"));
        mMerList.setValue(arr);
    }

    public LiveData<ArrayList<Merchandise>> getList() {
        return mMerList;
    }
}