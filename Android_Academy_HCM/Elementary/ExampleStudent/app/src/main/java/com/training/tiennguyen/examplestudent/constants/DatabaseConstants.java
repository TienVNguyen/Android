/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent.constants;

/**
 * DatabaseConstants
 *
 * @author TienNguyen
 */
public class DatabaseConstants {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GOOGLE";
    public static final String TABLE_STUDENTS = "STUDENTS";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_GENDER = "GENDER";
    public static final String KEY_PHONE = "PHONE";
    public static final String KEY_MAJOR = "MAJOR";
    public static final String KEY_AVATAR = "AVATAR";

    public static final String PRIMARY_STRING_KEY = " PRIMARY KEY ";
    public static final String INTEGER_KEY = " INTEGER KEY ";
    public static final String STRING_KEY = " KEY ";


    public static final String CREATE_TABLE = " CREATE TABLE ";
    public static final String DROP_TABLE_EXISTED = " DROP TABLE IF EXISTS ";
    public static final String SELECT_COUNT = " SELECT COUNT(*) FROM ";

    public static final String SELECTION_IS = " = ? ";
    public static final String SELECTION_AND = " AND ";
    public static final String OPEN_BRACKETS = " ( ";
    public static final String CLOSE_BRACKETS = " ) ";
    public static final String COMMA = ",";
    public static final String SEMICOLON = ";";
}
