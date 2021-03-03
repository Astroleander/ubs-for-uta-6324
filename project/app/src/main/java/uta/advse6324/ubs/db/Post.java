package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

public class Post extends DBHelper {
    public static String TABLE_POST = "tbl_Post";
    public Post(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "create TABLE");
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.POST);
        onCreate(db);
    }

    private static final String CREATE =
            "create table "+ TABLE_POST + " (" +
                    EnumTable.Post.ID          + " varchar(10) primary key, " +
                    EnumTable.Post.TITLE       + " varchar(50) not null," +
                    EnumTable.Post.CONTENT     + " text, " +
                    EnumTable.Post.LIKED       + " integer," +
                    EnumTable.Post.OWNER       + " varcahr(30) not null," +
                    EnumTable.Post.POST_DATE   + " timestamp default CURRENT_TIMESTAMP not null" +
            ")";


    public void insert(User user){
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

}
