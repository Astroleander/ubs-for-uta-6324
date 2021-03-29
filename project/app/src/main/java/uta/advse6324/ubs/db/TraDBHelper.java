package uta.advse6324.ubs.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import androidx.annotation.Nullable;

import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.transaction;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

public class TraDBHelper extends DBHelper {

    public TraDBHelper(@Nullable Context context) {
        super(context);
    }


    private static final String TRA_CREATE =
            "create table if not exists "+ EnumTable.TABLE_LIST.TRANSCATION + " (" +
                    EnumTable.Transcation.USERID + " varchar(10) not null," +
                    EnumTable.Transcation.MERID + " varchar(10) not null," +
                    EnumTable.Transcation.DATE + " timestamp default CURRENT_TIMESTAMP not null," +
                    EnumTable.Transcation.BUY_BORROW + " boolean not null," +
                    EnumTable.Transcation.PAY_STATUS + " boolean not null" +

                    ")";

    public void onCreate(SQLiteDatabase db) {
//        Log.d("DBHelper", "create User TABLE");
        db.execSQL(TRA_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.TRANSCATION);
        onCreate(db);
    }

    public transaction[] queryTransactionByUserID(String uid) {
        ArrayList<transaction> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.TRANSCATION,
                null,
                EnumTable.Transcation.USERID + "=\"" + uid +"\"" ,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String userid = cursor.getString(cursor.getColumnIndex(EnumTable.Transcation.USERID));
            String merid = cursor.getString(cursor.getColumnIndex(EnumTable.Transcation.MERID));
            String date = cursor.getString(cursor.getColumnIndex(EnumTable.Transcation.DATE));
            String pay = cursor.getString(cursor.getColumnIndex(EnumTable.Transcation.PAY_STATUS));
            String bob = cursor.getString(cursor.getColumnIndex(EnumTable.Transcation.BUY_BORROW));
            transaction transaction = new transaction(
                    userid,
                    merid,
                    date,
                    Boolean.valueOf(bob),
                    Boolean.valueOf(pay)
            );
            result.add(transaction);
        }
        cursor.close();
        transaction[] r = {};
        return result.toArray(r);
    }
}
