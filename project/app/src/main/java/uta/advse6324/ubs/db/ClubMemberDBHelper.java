package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;


import uta.advse6324.ubs.pojo.ClubMember;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

public class ClubMemberDBHelper extends DBHelper {

    private static final String CREATE =
            "create table if not exists "+ EnumTable.TABLE_LIST.CLUBMEMBER + "(" +
                    EnumTable.ClubMember.CLUBNAME + " varchar(10) not null," +
                    EnumTable.ClubMember.USERNAME + " varchar(30) not null," +
                    "foreign key" + "(" + EnumTable.ClubMember.CLUBNAME + ")" + " references " + EnumTable.TABLE_LIST.CLUB + "(" + EnumTable.Club.CLUBNAME + ")," +
                    "foreign key" + "(" + EnumTable.ClubMember.USERNAME + ")" + " references " + EnumTable.TABLE_LIST.USER + "(" + EnumTable.User.USERNAME + ")," +
                    "primary key" + "(" + EnumTable.ClubMember.CLUBNAME + "," + EnumTable.ClubMember.USERNAME + ")" +
                    ")";
//    private static final String CREATE =
//            "create table if not exists "+ EnumTable.TABLE_LIST.CLUBMEMBER + "(" +
//                    EnumTable.ClubMember.CLUBNAME + " varchar(10) not null unique," +
//                    EnumTable.ClubMember.USERNAME + " varchar(30) not null unique" +
//                    ")";

    public ClubMemberDBHelper(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.CLUBMEMBER);
        onCreate(db);
    }

    public void insert(ClubMember member){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.ClubMember.CLUBNAME,        member.getClubname());
        cv.put(EnumTable.ClubMember.USERNAME,        member.getUsername());

        long res = db.insert(EnumTable.TABLE_LIST.CLUBMEMBER, null, cv);
    }

    public ClubMember[] queryAllMember(){
        ArrayList<ClubMember> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.CLUBMEMBER,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String club = cursor.getString(cursor.getColumnIndex(EnumTable.ClubMember.CLUBNAME));
            String user = cursor.getString(cursor.getColumnIndex(EnumTable.ClubMember.USERNAME));

            ClubMember member = new ClubMember(club, user);

            result.add(member);
        }
        cursor.close();
        ClubMember[] r = {};
        return result.toArray(r);
    }


    public String[] queryClubByUsername(String username) {
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.CLUBMEMBER,
                null,
                EnumTable.ClubMember.USERNAME + "=\"" + username + "\"" ,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(EnumTable.ClubMember.USERNAME));
            if (name.equals(username)) {
                String clubname = cursor.getString(cursor.getColumnIndex(EnumTable.ClubMember.CLUBNAME));
                result.add(clubname);
            }
        }
        cursor.close();
        String[] r = {};
        return result.toArray(r);
    }
}
