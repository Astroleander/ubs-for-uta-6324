package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uta.advse6324.ubs.pojo.Billing;
import uta.advse6324.ubs.pojo.Merchandise;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

public class BillingDBHelper extends DBHelper {

    public BillingDBHelper(@Nullable Context context) {
        super(context);
    }



    private static final String Billing_CREATE =
            "create table if not exists "+ EnumTable.TABLE_LIST.Billing + " (" +
                    EnumTable.Billing.ID           + " varchar(10) primary key, " +
                    EnumTable.Billing.NAME + " varchar(30) not null," +
                    EnumTable.Billing.ADDRESS +" varchar(30) not null, " +
                    EnumTable.Billing.USERID +" varchar(10)" +
                    ")";

    public void insert(Billing billing){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Billing.ID,billing.getId());
        cv.put(EnumTable.Billing.NAME,billing.getName());
        cv.put(EnumTable.Billing.ADDRESS, billing.getAddress());
        cv.put(EnumTable.Billing.USERID, billing.getAddress());


        long res = db.insert(EnumTable.TABLE_LIST.Billing, null, cv);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.d("DBHelper", "create User TABLE");
        db.execSQL(Billing_CREATE);
        String qwe="123";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.Billing);
        onCreate(db);
    }

    public Billing[] queryBillingByOwner(String owner_username){
        ArrayList<Billing> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.Billing,
                null,
                EnumTable.Billing.NAME + "=\"" + owner_username + "\"" ,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String owner = cursor.getString(cursor.getColumnIndex(EnumTable.Billing.NAME));
            if (owner.equals(owner_username)) {
                String id = cursor.getString(cursor.getColumnIndex(EnumTable.Billing.ID));
                String name = cursor.getString(cursor.getColumnIndex(EnumTable.Billing.NAME));
                String address = cursor.getString(cursor.getColumnIndex(EnumTable.Billing.ADDRESS));
                String userId = cursor.getString(cursor.getColumnIndex(EnumTable.Billing.USERID));
                Billing billing = new Billing(
                        id, name,
                        address,
                        userId
                );
                result.add(billing);
            }
        }
        cursor.close();
        Billing[] r = {};
        return result.toArray(r);
    }
    public boolean delete(Billing billing){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(EnumTable.TABLE_LIST.Billing, "id=?", new String[]{billing.getId()}) > 0;
    }



}