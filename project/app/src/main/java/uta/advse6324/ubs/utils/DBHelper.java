package uta.advse6324.ubs.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;

import uta.advse6324.ubs.pojo.User;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "test13";
    public static final int DB_VERSION = 1;

    private static final String USER_CREATE =
            "create table "+ EnumTable.TABLE_LIST.USER + " (" +
                    EnumTable.User.ID           + " varchar(10) primary key, " +
                    EnumTable.User.USERNAME     + " varchar(30) not null, " +
                    EnumTable.User.PASSWORD     + " varchar(30) not null," +
                    EnumTable.User.LASTNAME     + " varchar(30) not null," +
                    EnumTable.User.FIRSTNAME    + " varchar(30) not null," +
                    EnumTable.User.PHONE        + " varchar(20) not null," +
                    EnumTable.User.EMAIL        + " varchar(30) not null" +
                    ")";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("DBHelper", "Constructor is called");
        getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "create User TABLE");
        db.execSQL(USER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.USER);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.User.ID, user.getId());
        cv.put(EnumTable.User.USERNAME, user.getUsername());
        cv.put(EnumTable.User.PASSWORD, user.getPassword());
        cv.put(EnumTable.User.LASTNAME, user.getLastname());
        cv.put(EnumTable.User.FIRSTNAME,user.getFirstname());
        cv.put(EnumTable.User.PHONE,    user.getPhone());
        cv.put(EnumTable.User.EMAIL,    user.getEmail());
        long res = db.insert(EnumTable.TABLE_LIST.USER, null, cv);
        Log.e("[inittable]", "init_reservation_tbl: " + res);
    }
    // get a user
    public User queryUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.USER,
                null,
                EnumTable.User.ID + "=\"" + id+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex(EnumTable.User.USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(EnumTable.User.PASSWORD));
            String firstname = cursor.getString(cursor.getColumnIndex(EnumTable.User.FIRSTNAME));
            String lastname = cursor.getString(cursor.getColumnIndex(EnumTable.User.LASTNAME));
            String phone = cursor.getString(cursor.getColumnIndex(EnumTable.User.PHONE));
            String email = cursor.getString(cursor.getColumnIndex(EnumTable.User.EMAIL));

            User user1 = new User(
                    id,
                    username,
                    password,
                    lastname,
                    firstname,
                    phone,
                    email
            );
            return  user1;
        }else{
            User user1 = new User("null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null");
            return user1;
        }

    }

    public User queryUserByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.USER,
                null,
                EnumTable.User.USERNAME + "=\"" + username+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(EnumTable.User.ID));
            String password = cursor.getString(cursor.getColumnIndex(EnumTable.User.PASSWORD));
            String firstname = cursor.getString(cursor.getColumnIndex(EnumTable.User.FIRSTNAME));
            String lastname = cursor.getString(cursor.getColumnIndex(EnumTable.User.LASTNAME));
            String phone = cursor.getString(cursor.getColumnIndex(EnumTable.User.PHONE));
            String email = cursor.getString(cursor.getColumnIndex(EnumTable.User.EMAIL));

            User user1 = new User(
                    id,
                    username,
                    password,
                    lastname,
                    firstname,
                    phone,
                    email
            );
            return  user1;
        }else{
            User user1 = new User("null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null");
            return user1;
        }

    }

    public boolean queryIdUniqe(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.USER,
                null,
                EnumTable.User.ID + "=\"" + id+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()){
            String r = cursor.getString(cursor.getColumnIndex(EnumTable.User.ID));
            cursor.close();
            if (r == null){
                return true;
            }
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    // query user's phonenumber
    public String queryUserPhonenumber(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.USER,
                null,
                EnumTable.User.ID + "=\"" + id+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String r = cursor.getString(cursor.getColumnIndex(EnumTable.User.PHONE));
            cursor.close();
            if(r==null){
                return "None";
            }
            return  r;
        } else {
            cursor.close();
            return "None";
        }
    }
    // Changepw
    public String changePW(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.User.ID, user.getId());
        cv.put(EnumTable.User.USERNAME, user.getUsername());
        cv.put(EnumTable.User.PASSWORD, user.getPassword());
        cv.put(EnumTable.User.LASTNAME, user.getLastname());
        cv.put(EnumTable.User.FIRSTNAME,user.getFirstname());
        cv.put(EnumTable.User.PHONE,    user.getPhone());
        cv.put(EnumTable.User.EMAIL,    user.getEmail());

        long res = db.update(EnumTable.TABLE_LIST.USER, cv, "ID=?", new String[]{user.getId()});
        if(res == -1)
            return "failed";
        else
            return "Change Successfully";

    }

    public User queryLogin(String qid, String qpw) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.USER,
                null,
                EnumTable.User.ID + "=\"" + qid + "\" AND " + EnumTable.User.PASSWORD + "=\"" + qpw +"\"",
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(EnumTable.User.ID));
            String username = cursor.getString(cursor.getColumnIndex(EnumTable.User.USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(EnumTable.User.PASSWORD));
            String firstname = cursor.getString(cursor.getColumnIndex(EnumTable.User.FIRSTNAME));
            String lastname = cursor.getString(cursor.getColumnIndex(EnumTable.User.LASTNAME));
            String phone = cursor.getString(cursor.getColumnIndex(EnumTable.User.PHONE));
            String email = cursor.getString(cursor.getColumnIndex(EnumTable.User.EMAIL));

            User user = new User(
                    id,
                    username,
                    password,
                    lastname,
                    firstname,
                    phone,
                    email
            );
            cursor.close();
            return user;
        } else {
            cursor.close();
            return null;
        }
    }

    public String editUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.User.ID,       user.getId());
        cv.put(EnumTable.User.USERNAME, user.getUsername());
        cv.put(EnumTable.User.PASSWORD, user.getPassword());
        cv.put(EnumTable.User.LASTNAME, user.getLastname());
        cv.put(EnumTable.User.FIRSTNAME, user.getFirstname());
        cv.put(EnumTable.User.PHONE,    user.getPhone());
        cv.put(EnumTable.User.EMAIL,    user.getEmail());

        long res = db.update(EnumTable.TABLE_LIST.USER, cv, "ID=?", new String[]{user.getId()});
        if (res == -1)
            return "failed";
        else
            return "User Profile Update Successfully";
    }








}
