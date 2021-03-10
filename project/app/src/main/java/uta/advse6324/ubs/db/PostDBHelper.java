package uta.advse6324.ubs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.ETC1;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import uta.advse6324.ubs.pojo.Post;
import uta.advse6324.ubs.pojo.User;
import uta.advse6324.ubs.utils.DBHelper;
import uta.advse6324.ubs.utils.EnumTable;

public class PostDBHelper extends DBHelper {
    public static String TABLE_POST = "tbl_Post";
    public PostDBHelper(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EnumTable.TABLE_LIST.POST);
        onCreate(db);
    }

    private static final String CREATE =
            "create table if not exists "+ TABLE_POST + " (" +
                    EnumTable.Post.ID          + " varchar(10) primary key, " +
                    EnumTable.Post.TITLE       + " varchar(50) not null," +
                    EnumTable.Post.CONTENT     + " text, " +
                    EnumTable.Post.LIKED       + " integer," +
                    EnumTable.Post.OWNER       + " varcahr(30) not null," +
                    EnumTable.Post.POST_DATE   + " timestamp default CURRENT_TIMESTAMP not null," +
                    EnumTable.Post.CONTACT     + " text not null," +
                    EnumTable.Post.ADVERTISEMENT + " boolean not null" +
            ")";


    public void insert(Post post){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Post.ID,      post.getId());
        cv.put(EnumTable.Post.TITLE,   post.getTitle());
        cv.put(EnumTable.Post.CONTENT, post.getContent());
        cv.put(EnumTable.Post.LIKED,   post.getLiked());
        cv.put(EnumTable.Post.OWNER,   post.getOwner());
        cv.put(EnumTable.Post.CONTACT, post.getContact());
        cv.put(EnumTable.Post.ADVERTISEMENT, post.getAdvertisement());

        long res = db.insert(EnumTable.TABLE_LIST.POST, null, cv);
    }

    public boolean delete(Post post){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_POST, "id=?", new String[]{post.getId()}) > 0;
    }

    public Post[] queryPostByOwner(String owner_username){
        ArrayList<Post> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.POST,
                null,
                EnumTable.Post.OWNER + "=\"" + owner_username + "\"" ,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String owner = cursor.getString(cursor.getColumnIndex(EnumTable.Post.OWNER));
            if (owner.equals(owner_username)) {
                String id = cursor.getString(cursor.getColumnIndex(EnumTable.Post.ID));
                String title = cursor.getString(cursor.getColumnIndex(EnumTable.Post.TITLE));
                String content = cursor.getString(cursor.getColumnIndex(EnumTable.Post.CONTENT));
                int liked = cursor.getInt(cursor.getColumnIndex(EnumTable.Post.LIKED));
                String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Post.POST_DATE));
                String contact = cursor.getString(cursor.getColumnIndex(EnumTable.Post.CONTACT));
                String advertisemnt = cursor.getString(cursor.getColumnIndex(EnumTable.Post.ADVERTISEMENT));
                Post post = new Post(
                        id, title,
                        content,
                        owner,
                        contact,
                        Boolean.valueOf(advertisemnt),
                        liked,
                        post_date
                );
                result.add(post);
            }
        }
        cursor.close();
        Post[] r = {};
        return result.toArray(r);
    }

    public Post queryPostByID(String id){
        Post post = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.POST,
                null,
                EnumTable.Post.ID + "=\"" + id+"\"" ,
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String owner = cursor.getString(cursor.getColumnIndex(EnumTable.Post.OWNER));
            String title = cursor.getString(cursor.getColumnIndex(EnumTable.Post.TITLE));
            String content = cursor.getString(cursor.getColumnIndex(EnumTable.Post.CONTENT));
            int liked = cursor.getInt(cursor.getColumnIndex(EnumTable.Post.LIKED));
            String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Post.POST_DATE));
            String contact = cursor.getString(cursor.getColumnIndex(EnumTable.Post.CONTACT));
            String advertisement = cursor.getString(cursor.getColumnIndex(EnumTable.Post.ADVERTISEMENT));
            post = new Post(
                    id,
                    title,
                    content,
                    owner,
                    contact,
                    Boolean.valueOf(advertisement),
                    liked,
                    post_date
            );
        }
        System.out.println(post.getId());
        return post;
    }

    public Post[] queryAllPost() {
        ArrayList<Post> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                EnumTable.TABLE_LIST.POST,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String owner = cursor.getString(cursor.getColumnIndex(EnumTable.Post.OWNER));
            String id = cursor.getString(cursor.getColumnIndex(EnumTable.Post.ID));
            String title = cursor.getString(cursor.getColumnIndex(EnumTable.Post.TITLE));
            String content = cursor.getString(cursor.getColumnIndex(EnumTable.Post.CONTENT));
            int liked = cursor.getInt(cursor.getColumnIndex(EnumTable.Post.LIKED));
            String post_date = cursor.getString(cursor.getColumnIndex(EnumTable.Post.POST_DATE));
            String contact = cursor.getString(cursor.getColumnIndex(EnumTable.Post.CONTACT));
            String advertisemnt = cursor.getString(cursor.getColumnIndex(EnumTable.Post.ADVERTISEMENT));
            Post post = new Post(
                    id, title,
                    content,
                    owner,
                    contact,
                    Boolean.valueOf(advertisemnt),
                    liked,
                    post_date
            );
            result.add(post);
        }
        cursor.close();
        Post[] r = {};
        return result.toArray(r);
    }

    public String editPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.Post.ID,            post.getId());
        cv.put(EnumTable.Post.TITLE,         post.getTitle());
        cv.put(EnumTable.Post.CONTENT,       post.getContent());
        cv.put(EnumTable.Post.LIKED,         post.getLiked());
        cv.put(EnumTable.Post.OWNER,         post.getOwner());
        cv.put(EnumTable.Post.CONTACT,       post.getContact());
        cv.put(EnumTable.Post.ADVERTISEMENT, post.getAdvertisement());
        cv.put(EnumTable.Post.POST_DATE,     post.getTimestamp());

        long res = db.update(EnumTable.TABLE_LIST.POST, cv, "ID=?", new String[]{post.getId()});
        if (res == -1)
            return "failed";
        else
            return "Post Update Successfully";
    }
}
