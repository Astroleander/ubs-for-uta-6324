package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import uta.advse6324.ubs.pojo.ClubMember;
import uta.advse6324.ubs.pojo.Merchandise;
import uta.advse6324.ubs.pojo.Message;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

import static android.content.ContentValues.TAG;

public class MesDBHelper extends DBHelper {
    public MesDBHelper(@Nullable Context context) {
        super(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.d("DBHelper", "create User TABLE");
        db.execSQL(MES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.MESSAGE);
        onCreate(db);
    }

    private static final String MES_CREATE =
            "create table if not exists "+ EnumTable.TABLE_LIST.MESSAGE + " (" +
                    EnumTable.Message.TIME + " timestamp default CURRENT_TIMESTAMP primary key, " +
                    EnumTable.Message.SEND + " varchar(30) not null, " +
                    EnumTable.Message.RECEIVE + " varchar(30) not null, " +
                    EnumTable.Message.CONTENT + " varchar(100), " +
                    EnumTable.Message.READSTATUS + " bollean not null" +
                    ")";

    public Message[] queryAllMessage() {
        ArrayList<Message> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.MESSAGE,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String time = cursor.getString(cursor.getColumnIndex(EnumTable.Message.TIME));
            String send = cursor.getString(cursor.getColumnIndex(EnumTable.Message.SEND));
            String receive = cursor.getString(cursor.getColumnIndex(EnumTable.Message.RECEIVE));
            String content = cursor.getString(cursor.getColumnIndex(EnumTable.Message.CONTENT));
            String read_status = cursor.getString(cursor.getColumnIndex(EnumTable.Message.READSTATUS));

            Log.d(TAG, "queryAllMessage: "+ read_status + (Integer.parseInt(read_status) == 1));
//          
            Message message = new Message(
                    time,
                    send,
                    receive,
                    content,
                    Integer.parseInt(read_status) == 0
            );
            result.add(message);
        }
        cursor.close();
        Message[] r = {};
        return result.toArray(r);
    }

    public void insert(Message mes){
        SQLiteDatabase db = this.getWritableDatabase();
//        Log.d(TAG, "insert: " + mes.getSell_lend());
        ContentValues cv = new ContentValues();
//        cv.put(EnumTable.Merchandise.ID,mer.getId());
//        cv.put(EnumTable.Message.TIME,mes.getTime());
        cv.put(EnumTable.Message.SEND,mes.getSend());
        cv.put(EnumTable.Message.RECEIVE,mes.getReceive());
        cv.put(EnumTable.Message.CONTENT,mes.getContent());
        cv.put(EnumTable.Message.READSTATUS,mes.isRead_status());
        long res = db.insert(EnumTable.TABLE_LIST.MESSAGE, null, cv);
    }
    public boolean delete(Message mes){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(EnumTable.TABLE_LIST.MESSAGE, "RECEIVE=? AND TIME=? AND SEND=?", new String[]{mes.getReceive(),mes.getTime(),mes.getSend()}) > 0;
    }
}
