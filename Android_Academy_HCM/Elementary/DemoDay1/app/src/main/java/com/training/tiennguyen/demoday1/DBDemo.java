package com.training.tiennguyen.demoday1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Win 8.1 VS8 X64 on 19/12/2015.
 */
public class DBDemo extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "google";
    private static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    public DBDemo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBDemo(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBDemo(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EMAIL + " KEY,"
                + KEY_PASSWORD + " KEY" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());

        long flag = db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUserExists(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                new String[]{
                        KEY_ID,
                        KEY_EMAIL,
                        KEY_PASSWORD
                }, KEY_EMAIL + "= ? AND " + KEY_PASSWORD + "= ?",
                new String[]{String.valueOf(user.getEmail()), String.valueOf(user.getPassword())},
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
