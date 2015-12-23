/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent.supportAPI;

import com.training.tiennguyen.examplestudent.constants.VariableConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmailAPI
 *
 * @author TienNguyen
 */
public class EmailAPI {

    /**
     * This function is used for checking valid email id format.
     *
     * @param email the information need to be check
     * @return boolean true for valid | false for invalid
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = VariableConstants.EMAIL_PATTERN;
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }
}
