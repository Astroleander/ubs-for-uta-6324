package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import uta.advse6324.ubs.pojo.Club;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

public class ClubDBHelper  extends DBHelper {

    public ClubDBHelper(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CLUB_CREATE);
    }

    private static final String CLUB_CREATE =
            "create table if not exists "+ EnumTable.TABLE_LIST.CLUB+ " (" +
                    EnumTable.Club.CLUBNAME          + " varchar(10) primary key," +
                    EnumTable.Club.CLUBMANAGERID     + " varchar(10) NOT NULL," +
                    EnumTable.Club.CLUBCATEGORY      + " varchar(10) NOT NULL," +
                    EnumTable.Club.CLUBINTRODUCTION  + " varchar(10) NOT NULL" +
                    ")";


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.CLUB);
        onCreate(db);
    }

    public void insert(Club club){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Club.CLUBNAME,             club.getName());
        cv.put(EnumTable.Club.CLUBMANAGERID,        club.getManagerid());
        cv.put(EnumTable.Club.CLUBCATEGORY,         club.getCategory());
        cv.put(EnumTable.Club.CLUBINTRODUCTION,     club.getIntroduction());

        long res = db.insert(EnumTable.TABLE_LIST.CLUB, null, cv);
    }

    public Club[] queryAllClub(){
        ArrayList<Club> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.CLUB,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBNAME));
            String manager = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBMANAGERID));
            String category = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBCATEGORY));
            String introduction = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBINTRODUCTION));

            Club club = new Club(name, manager, category, introduction);

            result.add(club);

        }
        cursor.close();
        Club[] r = {};
        return result.toArray(r);
    }

    public Club[] queryClubByName(String name){
        ArrayList<Club> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.CLUB,
                null,
                EnumTable.Club.CLUBNAME + "=\"" + name + "\"",
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String clubname = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBNAME));
            String manager = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBMANAGERID));
            String category = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBCATEGORY));
            String introduction = cursor.getString(cursor.getColumnIndex(EnumTable.Club.CLUBINTRODUCTION));

            Club club = new Club(clubname, manager, category, introduction);

            result.add(club);
        }
        cursor.close();
        Club[] r = {};
        return result.toArray(r);
    }


}
