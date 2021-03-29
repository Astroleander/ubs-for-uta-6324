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
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

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
                    EnumTable.Merchandise.ID   + "var primary key, " +
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
            String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.DATE));
            String owner_id = cursor.getString(cursor.getColumnIndex(EnumTable.Merchandise.OWNER_ID));
            Merchandise merchandise = new Merchandise(
                    id,
                    name,
                    description,
                    picture,
                    price,
                    Boolean.valueOf(availabe_status),
                    Boolean.valueOf(sell_lend),
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
