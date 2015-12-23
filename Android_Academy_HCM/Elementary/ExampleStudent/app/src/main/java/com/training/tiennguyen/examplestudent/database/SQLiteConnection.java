/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.training.tiennguyen.examplestudent.constants.DatabaseConstants;
import com.training.tiennguyen.examplestudent.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * SQLiteConnection
 *
 * @author TienNguyen
 */
public class SQLiteConnection extends SQLiteOpenHelper {
    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     */
    public SQLiteConnection(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public SQLiteConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Create a helper object to create, open, and/or manage a database.
     * The database is not actually created or opened until one of
     * {@link #getWritableDatabase} or {@link #getReadableDatabase} is called.
     * <p/>
     * <p>Accepts input param: a concrete instance of {@link DatabaseErrorHandler} to be
     * used to handle corruption when sqlite reports database corruption.</p>
     *
     * @param context      to use to open or create the database
     * @param name         of the database file, or null for an in-memory database
     * @param factory      to use for creating cursor objects, or null for the default
     * @param version      number of the database (starting at 1); if the database is older,
     *                     {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                     newer, {@link #onDowngrade} will be used to downgrade the database
     * @param errorHandler the {@link DatabaseErrorHandler} to be used when sqlite reports database
     */
    public SQLiteConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table query
        StringBuilder queryCreateTable = new StringBuilder();
        queryCreateTable.append("CREATE TABLE ");
        queryCreateTable.append(DatabaseConstants.TABLE_STUDENTS);
        queryCreateTable.append(" ( ");
        queryCreateTable.append(DatabaseConstants.KEY_EMAIL);
        queryCreateTable.append(" PRIMARY KEY, ");
        queryCreateTable.append(DatabaseConstants.KEY_NAME);
        queryCreateTable.append(" KEY, ");
        queryCreateTable.append(DatabaseConstants.KEY_GENDER);
        queryCreateTable.append(" INTEGER KEY, ");
        queryCreateTable.append(DatabaseConstants.KEY_PHONE);
        queryCreateTable.append(" KEY, ");
        queryCreateTable.append(DatabaseConstants.KEY_MAJOR);
        queryCreateTable.append(" KEY, ");
        queryCreateTable.append(DatabaseConstants.KEY_AVATAR);
        queryCreateTable.append(" KEY); ");
        db.execSQL(queryCreateTable.toString());
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table query
        StringBuilder queryDropTable = new StringBuilder();
        queryDropTable.append("DROP TABLE IF EXISTS ");
        queryDropTable.append(DatabaseConstants.TABLE_STUDENTS);
        queryDropTable.append(";");
        db.execSQL(queryDropTable.toString());

        // Create table
        onCreate(db);
    }

    /**
     * This function will insert a new record to table inside of database
     *
     * @param student the model
     * @return insertFlag long
     */
    public long addStudent(Student student) {
        // Get the lock
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.beginTransaction();

        // Prepare the value
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.KEY_EMAIL, student.getEmail());
        contentValues.put(DatabaseConstants.KEY_NAME, student.getName());
        contentValues.put(DatabaseConstants.KEY_GENDER, student.isGender());
        contentValues.put(DatabaseConstants.KEY_PHONE, student.getPhone());
        contentValues.put(DatabaseConstants.KEY_MAJOR, student.getMajor());
        contentValues.put(DatabaseConstants.KEY_AVATAR, student.getAvatar());

        // Execute insert
        long insertFlag = sqLiteDatabase.insert(DatabaseConstants.TABLE_STUDENTS, null, contentValues);

        // Close connection
        sqLiteDatabase.endTransaction();
        sqLiteDatabase.close();

        // Return insertFlag for notifying
        return insertFlag;
    }

    /**
     * This function will select count(*) of record(s) of table inside of database
     *
     * @param student the model
     * @return resultCount. If there is no record, it will return -1
     */
    public int selectCountStudent(Student student) {
        // Get the lock
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Select table query
        StringBuilder querySelect = new StringBuilder();
        querySelect.append("SELECT COUNT(*) FROM ");
        querySelect.append(DatabaseConstants.TABLE_STUDENTS);

        // Get the result
        Cursor cursor = sqLiteDatabase.rawQuery(querySelect.toString(), null);
        int resultCount = cursor.moveToFirst() ? cursor.getInt(0) : -1;

        // Close connection
        cursor.close();
        sqLiteDatabase.close();

        // Return resultCount for notifying
        return resultCount;
    }

    /**
     * This function will select all record(s) of table inside of database
     *
     * @return A list of Student in the database
     */
    public List<Student> selectAllStudent() {
        // Get the lock
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Select table query
        StringBuilder querySelect = new StringBuilder();
        querySelect.append("SELECT ");
        querySelect.append(DatabaseConstants.KEY_EMAIL);
        querySelect.append(",");
        querySelect.append(DatabaseConstants.KEY_NAME);
        querySelect.append(",");
        querySelect.append(DatabaseConstants.KEY_GENDER);
        querySelect.append(",");
        querySelect.append(DatabaseConstants.KEY_PHONE);
        querySelect.append(",");
        querySelect.append(DatabaseConstants.KEY_MAJOR);
        querySelect.append(",");
        querySelect.append(DatabaseConstants.KEY_AVATAR);
        querySelect.append(" FROM ");
        querySelect.append(DatabaseConstants.TABLE_STUDENTS);

        // Get the result
        Cursor cursor = sqLiteDatabase.rawQuery(querySelect.toString(), null);
        List<Student> resultSelect = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                // Assign value
                Student student = new Student();
                student.setEmail(cursor.getString(0));
                student.setName(cursor.getString(1));
                student.setGender(cursor.getInt(2) == 0 ? false : true);
                student.setPhone(cursor.getString(3));
                student.setMajor(cursor.getString(4));
                student.setAvatar(cursor.getString(5));
                resultSelect.add(student);
            } while (cursor.moveToNext());
        }

        // Close connection
        cursor.close();
        sqLiteDatabase.close();

        // Return resultSelect for notifying
        return resultSelect;
    }
}
