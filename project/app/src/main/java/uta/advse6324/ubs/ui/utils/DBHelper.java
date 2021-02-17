package uta.advse6324.ubs.ui.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;

import uta.advse6324.ubs.ui.pojo.User;

public class DBHelper extends SQLiteOpenHelper {

    private static class TABLE_LIST {
        public static String USER = "tbl_user";
        public static String CAR = "tbl_car";
        public static String Reservation = "tbl_reservation";
    }

    private ArrayList userFields = new ArrayList<String>();

    public static final String DB_NAME = "test3";
    public static final int DB_VERSION = 1;


    private static final String USER_CREATE =
            "create table "+ TABLE_LIST.USER + " (" +
                    EnumTable.User.ID           + " integer primary key autoincrement, " +
                    EnumTable.User.USERNAME     + " varchar(30) not null, " +
                    EnumTable.User.PASSWORD     + " varchar(30) not null," +
                    EnumTable.User.ROLE         + " varchar(10) not null," +
                    EnumTable.User.UTAID        + " varchar(10) not null," +
                    EnumTable.User.LASTNAME     + " varchar(30) not null," +
                    EnumTable.User.FIRSTNAME    + " varchar(30) not null," +
                    EnumTable.User.PHONE        + " varchar(20) not null," +
                    EnumTable.User.EMAIL        + " varchar(30) not null," +
                    EnumTable.User.ADDRESS      + " varchar(20)," +
                    EnumTable.User.CITY         + " varchar(20)," +
                    EnumTable.User.STATE        + " varchar(20)," +
                    EnumTable.User.ZIPCODE      + " varchar(20)," +
                    EnumTable.User.MEMBER       + " varchar(20)," +
                    EnumTable.User.STATUS       + " varchar(20) not null" +
                    ")";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        Field[] fields = User.class.getDeclaredFields();
//        for (Field field: fields) {
//            String field_name = field.toString().split(" ")[2];
//            String[] group = field_name.split("\\.");
//            userFields.add(group[group.length - 1]);
//        }
//        Log.d("DBHelper", "constructor");
//        Log.d("DBHelper", userFields.toString());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "create TABLE");
        db.execSQL(USER_CREATE);
        addauser(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST.USER);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

//  init a user
    private void addauser(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(EnumTable.User.USERNAME,"chen");
        cv.put(EnumTable.User.PASSWORD, "123456");
        cv.put(EnumTable.User.ROLE,     "123456");
        cv.put(EnumTable.User.UTAID,    "");
        cv.put(EnumTable.User.LASTNAME, "");
        cv.put(EnumTable.User.FIRSTNAME,"");
        cv.put(EnumTable.User.PHONE,    "15600128299");
        cv.put(EnumTable.User.EMAIL,    "");
        cv.put(EnumTable.User.ADDRESS,  "");
        cv.put(EnumTable.User.CITY,     "");
        cv.put(EnumTable.User.STATE,    "");
        cv.put(EnumTable.User.ZIPCODE,  "");
        cv.put(EnumTable.User.MEMBER,   "");
        cv.put(EnumTable.User.STATUS,  "");
        long res = db.insert(TABLE_LIST.USER, null, cv);
        Log.e("[inittable]", "init_reservation_tbl: " + res);
    }
    // get a user
    public User queryUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                EnumTable.User.USERNAME + "=\"" + username+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
//            String username = cursor.getString(cursor.getColumnIndex(EnumTable.User.USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(EnumTable.User.PASSWORD));
            String role = cursor.getString(cursor.getColumnIndex(EnumTable.User.ROLE));
            String uta_id = cursor.getString(cursor.getColumnIndex(EnumTable.User.UTAID));
            String firstname = cursor.getString(cursor.getColumnIndex(EnumTable.User.FIRSTNAME));
            String lastname = cursor.getString(cursor.getColumnIndex(EnumTable.User.LASTNAME));
            String phone = cursor.getString(cursor.getColumnIndex(EnumTable.User.PHONE));
            String email = cursor.getString(cursor.getColumnIndex(EnumTable.User.EMAIL));
            String address = cursor.getString(cursor.getColumnIndex(EnumTable.User.ADDRESS));
            String city = cursor.getString(cursor.getColumnIndex(EnumTable.User.CITY));
            String state = cursor.getString(cursor.getColumnIndex(EnumTable.User.STATE));
            String zipcode = cursor.getString(cursor.getColumnIndex(EnumTable.User.ZIPCODE));
            String member = cursor.getString(cursor.getColumnIndex(EnumTable.User.MEMBER));
            String status = cursor.getString(cursor.getColumnIndex(EnumTable.User.STATUS));

            User user1 = new User(
                    username,
                    password,
                    role,
                    uta_id,
                    lastname,
                    firstname,
                    phone,
                    email,
                    address,
                    city,
                    state,
                    zipcode,
                    member,
                    status
            );
            return  user1;
        }else{
            User user1 = new User("null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null");
            return user1;
        }

    }


    // query user's phonenumber
    public String queryUserPhonenumber(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                EnumTable.User.USERNAME + "=\"" + username+"\"" ,
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
        cv.put(EnumTable.User.USERNAME, user.getUsername());
        cv.put(EnumTable.User.PASSWORD, user.getPassword());
        cv.put(EnumTable.User.ROLE,     user.getRole());
        cv.put(EnumTable.User.UTAID,    user.getUta_id());
        cv.put(EnumTable.User.LASTNAME, user.getLastname());
        cv.put(EnumTable.User.FIRSTNAME,user.getFirstname());
        cv.put(EnumTable.User.PHONE,    user.getPhone());
        cv.put(EnumTable.User.EMAIL,    user.getEmail());
        cv.put(EnumTable.User.ADDRESS,  user.getAddress());
        cv.put(EnumTable.User.CITY,     user.getCity());
        cv.put(EnumTable.User.STATE,    user.getState());
        cv.put(EnumTable.User.ZIPCODE,  user.getZipcode());
        cv.put(EnumTable.User.MEMBER,   user.getMember());
        cv.put(EnumTable.User.STATUS,   user.getStatus());

        long res = db.update(TABLE_LIST.USER, cv, "USERNAME=?", new String[]{user.getUsername()});
        if(res == -1)
            return "failed";
        else
            return "Change Successfully";

    }











}
