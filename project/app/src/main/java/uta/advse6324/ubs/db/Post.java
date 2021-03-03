package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

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


    public void insert(uta.advse6324.ubs.pojo.Post post){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Post.ID,      post.getId());
        cv.put(EnumTable.Post.TITLE,   post.getTitle());
        cv.put(EnumTable.Post.CONTENT, post.getContent());
        cv.put(EnumTable.Post.LIKED,   post.getLiked());
        cv.put(EnumTable.Post.OWNER,   post.getOwner());

        long res = db.insert(EnumTable.TABLE_LIST.POST, null, cv);
    }

    public uta.advse6324.ubs.pojo.Post[] queryPostByOwner(String owner_id){
        ArrayList<uta.advse6324.ubs.pojo.Post> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.POST,
                null,
                EnumTable.Post.OWNER + "=\"" + owner_id + "\"" ,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String owner = cursor.getString(cursor.getColumnIndex(EnumTable.Post.OWNER));
            if (owner.equals(owner_id)) {
                String id = cursor.getString(cursor.getColumnIndex(EnumTable.Post.ID));
                String title = cursor.getString(cursor.getColumnIndex(EnumTable.Post.TITLE));
                String content = cursor.getString(cursor.getColumnIndex(EnumTable.Post.CONTENT));
                int liked = cursor.getInt(cursor.getColumnIndex(EnumTable.Post.LIKED));
                String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Post.POST_DATE));

                uta.advse6324.ubs.pojo.Post post = new uta.advse6324.ubs.pojo.Post(
                        id,
                        title,
                        content,
                        liked,
                        owner,
                        post_date
                );
                result.add(post);
            }
        }
        cursor.close();
        uta.advse6324.ubs.pojo.Post[] r = {};
        return result.toArray(r);
    }
}
