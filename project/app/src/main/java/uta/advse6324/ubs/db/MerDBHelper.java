package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;

import uta.advse6324.ubs.pojo.Merchandise;

import androidx.annotation.Nullable;

import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

import static android.content.ContentValues.TAG;

public class MerDBHelper extends DBHelper {

    public MerDBHelper(@Nullable Context context) {
        super(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.d("DBHelper", "create User TABLE");
        db.execSQL(MER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.MERCHANDISE);
        onCreate(db);
    }



    private static final String MER_CREATE =
            "create table if not exists "+ EnumTable.TABLE_LIST.MERCHANDISE + " (" +
                    EnumTable.Merchandise.ID   + " timestamp default CURRENT_TIMESTAMP primary key, " +
                    EnumTable.Merchandise.NAME + " varchar(30) not null," +
                    EnumTable.Merchandise.DESCRIPTION +" varchar(30) not null, "+
                    EnumTable.Merchandise.PICTURE + " blob not null, " +
                    EnumTable.Merchandise.PRICE + " varchar(10)," +
                    EnumTable.Merchandise.AVAILABLE + " bollean not null,"+
                    EnumTable.Merchandise.SELL_LEND + " bollean not null," +
                    EnumTable.Merchandise.DATE + " timestamp default CURRENT_TIMESTAMP not null," +
                    EnumTable.Merchandise.OWNER_ID +" varchar(10)" +

                    ")";

    public void insert(Merchandise mer){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "insert: " + mer.getSell_lend());
        ContentValues cv = new ContentValues();
//        cv.put(EnumTable.Merchandise.ID,mer.getId());
        cv.put(EnumTable.Merchandise.NAME,mer.getName());
        cv.put(EnumTable.Merchandise.DESCRIPTION,mer.getDescription());
        cv.put(EnumTable.Merchandise.PICTURE,mer.getPicture());
        cv.put(EnumTable.Merchandise.PRICE,mer.getPrice());
        cv.put(EnumTable.Merchandise.AVAILABLE,mer.getAvailable_status());
        cv.put(EnumTable.Merchandise.SELL_LEND,mer.getSell_lend());
        cv.put(EnumTable.Merchandise.OWNER_ID ,mer.getOwner_id());

        long res = db.insert(EnumTable.TABLE_LIST.MERCHANDISE, null, cv);
    }

    public boolean delete(Merchandise merchandise){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(EnumTable.TABLE_LIST.MERCHANDISE, "id=?", new String[]{merchandise.getId()}) > 0;
    }

    public Merchandise[] queryAllMerchandise() {
        ArrayList<Merchandise> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.MERCHANDISE,
                null,
                EnumTable.Merchandise.AVAILABLE + "=\"1\"" ,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.ID));
            String name = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.NAME));
            String description = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.DESCRIPTION));

            byte[] picture = cursor.getBlob(cursor.getColumnIndex(EnumTable.Merchandise.PICTURE));
            String price = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.PRICE));
            String availabe_status = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.AVAILABLE));
            String sell_lend = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.SELL_LEND));
            Log.d(TAG, "queryAllMerchandise: "+ sell_lend + (Integer.parseInt(sell_lend) == 1));
            String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.DATE));
            String owner_id = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.OWNER_ID));
            Merchandise merchandise = new Merchandise(
                    id,
                    name,
                    description,
                    picture,
                    price,
                    Integer.parseInt(availabe_status) == 0,
                    Integer.parseInt(sell_lend) == 0,
                    post_date,
                    owner_id
            );
            result.add(merchandise);
        }
        cursor.close();
        Merchandise[] r = {};
        return result.toArray(r);
    }

    public Merchandise[] queryAllMerchandiseContainsUNAVAILABLE() {
        ArrayList<Merchandise> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.MERCHANDISE,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.ID));
            String name = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.NAME));
            String description = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.DESCRIPTION));

            byte[] picture = cursor.getBlob(cursor.getColumnIndex(EnumTable.Merchandise.PICTURE));
            String price = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.PRICE));
            String availabe_status = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.AVAILABLE));
            String sell_lend = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.SELL_LEND));
            Log.d(TAG, "queryAllMerchandise: "+ sell_lend + (Integer.parseInt(sell_lend) == 1));
            String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.DATE));
            String owner_id = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.OWNER_ID));
            Merchandise merchandise = new Merchandise(
                    id,
                    name,
                    description,
                    picture,
                    price,
                    Integer.parseInt(availabe_status) == 0,
                    Integer.parseInt(sell_lend) == 0,
                    post_date,
                    owner_id
            );
            result.add(merchandise);
        }
        cursor.close();
        Merchandise[] r = {};
        return result.toArray(r);
    }

    public String changeA(Merchandise mer){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Merchandise.ID, mer.getId());
        cv.put(EnumTable.Merchandise.NAME,mer.getName());
        cv.put(EnumTable.Merchandise.DESCRIPTION,mer.getDescription());
        cv.put(EnumTable.Merchandise.PICTURE,mer.getPicture());
        cv.put(EnumTable.Merchandise.PRICE,mer.getPrice());
        cv.put(EnumTable.Merchandise.AVAILABLE,mer.getAvailable_status());
        cv.put(EnumTable.Merchandise.SELL_LEND,mer.getSell_lend());
        cv.put(EnumTable.Merchandise.OWNER_ID ,mer.getOwner_id());
        cv.put(EnumTable.Merchandise.DATE,mer.getTimestamp());

        long res = db.update(EnumTable.TABLE_LIST.MERCHANDISE, cv, "ID=?", new String[]{mer.getId()});
        if(res == -1)
            return "failed";
        else
            return "Change Successfully";

    }

    public Merchandise[] queryMerchandiseByUserID(String uid) {
        ArrayList<Merchandise> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.MERCHANDISE,
                null,
                EnumTable.Merchandise.OWNER_ID + "=\"" + uid +"\"" ,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.ID));
            String name = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.NAME));
            String description = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.DESCRIPTION));

            byte[] picture = cursor.getBlob(cursor.getColumnIndex(EnumTable.Merchandise.PICTURE));
            String price = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.PRICE));
            String availabe_status = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.AVAILABLE));
            String sell_lend = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.SELL_LEND));
            String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.DATE));
            String owner_id = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.OWNER_ID));
            Merchandise merchandise = new Merchandise(
                    id,
                    name,
                    description,
                    picture,
                    price,
                    Integer.parseInt(availabe_status) == 0,
                    Integer.parseInt(sell_lend) == 0,
                    post_date,
                    owner_id
            );
            result.add(merchandise);
        }
        cursor.close();
        Merchandise[] r = {};
        return result.toArray(r);
    }
}
