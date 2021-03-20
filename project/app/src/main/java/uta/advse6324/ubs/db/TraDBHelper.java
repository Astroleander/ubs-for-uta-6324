package uta.advse6324.ubs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

public class TraDBHelper extends DBHelper {

    public TraDBHelper(@Nullable Context context) {
        super(context);
    }


    private static final String TRA_CREATE =
            "create table if not exists "+ EnumTable.TABLE_LIST.TRANSCATION + " (" +
                    EnumTable.Transcation.USERID + "varchar(10) not null," +
                    EnumTable.Transcation.MERID + "varchar(10) not null," +
                    EnumTable.Transcation.DATE + "timestamp default CURRENT_TIMESTAMP not null," +
                    EnumTable.Transcation.BUY_BORROW + "boolean not null," +
                    EnumTable.Transcation.PAY_STATUS + "boolean not null" +

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

}
