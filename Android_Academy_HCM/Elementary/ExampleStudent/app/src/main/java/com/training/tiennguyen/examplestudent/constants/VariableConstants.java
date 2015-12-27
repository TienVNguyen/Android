/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent.constants;

/**
 * VariableConstants
 *
 * @author TienNguyen
 */
public class VariableConstants {
    // For email
    public static final String EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

    // For email
    public static final String IMAGE_JPG = "jpg";
    public static final String IMAGE_JPG_CAP = "JPG";
    public static final String IMAGE_GIF = "gif";
    public static final String IMAGE_GIF_CAP = "GIF";
    public static final String IMAGE_PNG = "png";
    public static final String IMAGE_PNG_CAP = "PNG";
    public static final String IMAGE_BMP = "bmp";
    public static final String IMAGE_BMP_CAP = "BMP";

    // For transfer intent
    public static final String STUDENT_NAME = "STUDENT_NAME";
    public static final String STUDENT_EMAIL = "STUDENT_EMAIL";
    public static final String STUDENT_GENDER = "STUDENT_GENDER";
    public static final String STUDENT_PHONE = "STUDENT_PHONE";
    public static final String STUDENT_MAJOR = "STUDENT_MAJOR";
    public static final String STUDENT_AVATAR = "STUDENT_AVATAR";

    // For messages
    public static final String REGISTER_MESSAGE = "Đăng ký sinh viên";
    public static final String REGISTER_MESSAGE_SUCCESS_TITLE = "Thông báo thành công";
    public static final String REGISTER_MESSAGE_FAIL_TITLE = "Thông báo thất bại";
    public static final String REGISTER_MESSAGE_YES = "Chấp nhận";
    public static final String REGISTER_MESSAGE_NO = "Từ chối";
    public static final String REGISTER_MESSAGE_SUCCESS = "Thành công!";
    public static final String REGISTER_MESSAGE_FAIL = "không thành công!";
    public static final String REGISTER_MESSAGE_AGAIN = "Thử lại";

    // For error messages in Checking
    public static final String NAME_ERROR_EMPTY = "Tên không được trống!";
    public static final String NAME_ERROR_OVER_30_CHARACTERS = "Tên không quá 30 ký tự!";
    public static final String EMAIL_ERROR_EMPTY = "Email không được trống!";
    public static final String EMAIL_ERROR_OVER_30_CHARACTERS = "Email không quá 30 ký tự!";
    public static final String EMAIL_ERROR_WRONG_FORMAT = "Email sai định dạng!";
    public static final String GENDER_ERROR_EMPTY = "Chưa lựa chọn giới tính!";
    public static final String PHONE_ERROR_EMPTY = "Điện thoại không được trống!";
    public static final String PHONE_ERROR_UNDER_6_OVER_30_CHARACTERS = "Điện thoại không ít hơn 6 hoặc quá 13 ký tự!";
    public static final String PHONE_ERROR_WRONG_FORMAT = "Điện thoại sai định dạng";
    public static final String MAJOR_ERROR_EMPTY = "Ngành học không được trống!";
    public static final String MAJOR_ERROR_OVER_20_CHARACTERS = "Ngành học không quá 20 ký tự!";
    public static final String AVATAR_ERROR_EMPTY = "URL không được trống!";
    public static final String AVATAR_ERROR_OVER_200_CHARACTERS = "URL không quá 200 ký tự!";
    public static final String AVATAR_ERROR_WRONG_FORMAT = "URI không hợp lệ!";

}
