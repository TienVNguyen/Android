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
        queryCreateTable.append(DatabaseConstants.CREATE_TABLE);
        queryCreateTable.append(DatabaseConstants.TABLE_STUDENTS);
        queryCreateTable.append(DatabaseConstants.OPEN_BRACKETS);
        queryCreateTable.append(DatabaseConstants.KEY_EMAIL);
        queryCreateTable.append(DatabaseConstants.PRIMARY_STRING_KEY);
        queryCreateTable.append(DatabaseConstants.COMMA);
        queryCreateTable.append(DatabaseConstants.KEY_NAME);
        queryCreateTable.append(DatabaseConstants.STRING_KEY);
        queryCreateTable.append(DatabaseConstants.COMMA);
        queryCreateTable.append(DatabaseConstants.KEY_GENDER);
        queryCreateTable.append(DatabaseConstants.INTEGER_KEY);
        queryCreateTable.append(DatabaseConstants.COMMA);
        queryCreateTable.append(DatabaseConstants.KEY_PHONE);
        queryCreateTable.append(DatabaseConstants.STRING_KEY);
        queryCreateTable.append(DatabaseConstants.COMMA);
        queryCreateTable.append(DatabaseConstants.KEY_MAJOR);
        queryCreateTable.append(DatabaseConstants.STRING_KEY);
        queryCreateTable.append(DatabaseConstants.COMMA);
        queryCreateTable.append(DatabaseConstants.KEY_AVATAR);
        queryCreateTable.append(DatabaseConstants.STRING_KEY);
        queryCreateTable.append(DatabaseConstants.CLOSE_BRACKETS);
        queryCreateTable.append(DatabaseConstants.SEMICOLON);
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
        queryDropTable.append(DatabaseConstants.DROP_TABLE_EXISTED);
        queryDropTable.append(DatabaseConstants.TABLE_STUDENTS);
        queryDropTable.append(DatabaseConstants.SEMICOLON);
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
        sqLiteDatabase.close();

        // Return insertFlag for notifying
        return insertFlag;
    }

    /**
     * This function will select count(*) of record(s) of table inside of database
     *
     * @return resultCount. If there is no record, it will return -1
     */
    public int selectCountStudent() {
        // Get the lock
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Select table query
        StringBuilder querySelect = new StringBuilder();
        querySelect.append(DatabaseConstants.SELECT_COUNT);
        querySelect.append(DatabaseConstants.TABLE_STUDENTS);
        querySelect.append(DatabaseConstants.SEMICOLON);

        // Get the result
        Cursor cursor = sqLiteDatabase.rawQuery(querySelect.toString(), null);
        int resultCount = 0;
        if (cursor != null && cursor.moveToFirst()) {
            resultCount = cursor.getInt(0);
        } else {
            resultCount = -1;
        }

        // Close connection
        cursor.close();
        sqLiteDatabase.close();

        // Return resultCount for notifying
        return resultCount;
    }

    /**
     * This function will check if that student's existed or already registered
     *
     * @param student the model
     * @return existedFlag. If there is a record, it will return true.
     */
    public boolean checkStudentExists(Student student) {
        // Get the lock
        SQLiteDatabase db = this.getReadableDatabase();

        // Select table columns
        String[] columns = new String[]{
                DatabaseConstants.KEY_EMAIL,
                DatabaseConstants.KEY_NAME
        };

        // Generate selection
        StringBuilder selection = new StringBuilder();
        selection.append(DatabaseConstants.KEY_EMAIL);
        selection.append(DatabaseConstants.SELECTION_IS);
        selection.append(DatabaseConstants.SELECTION_AND);
        selection.append(DatabaseConstants.KEY_NAME);
        selection.append(DatabaseConstants.SELECTION_IS);

        // Set value to selection
        String[] selectionArgs = new String[]{
                student.getEmail(),
                student.getName()
        };

        // Get the result
        Cursor cursor = db.query(DatabaseConstants.TABLE_STUDENTS, columns, selection.toString(), selectionArgs, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function will select all record(s) of table inside of database
     *
     * @return A list of Student in the database
     */
    public List<Student> selectAllStudent() {
        // Get the lock
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Select table columns
        String[] columns = new String[]{
                DatabaseConstants.KEY_EMAIL, DatabaseConstants.KEY_NAME, DatabaseConstants.KEY_GENDER,
                DatabaseConstants.KEY_PHONE, DatabaseConstants.KEY_MAJOR, DatabaseConstants.KEY_AVATAR
        };

        // Get the result
        Cursor cursor = sqLiteDatabase.query(DatabaseConstants.TABLE_STUDENTS, columns, null, null, null, null, null);
        List<Student> resultSelect = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
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
