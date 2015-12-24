/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent.supportAPI;

import com.training.tiennguyen.examplestudent.constants.VariableConstants;

/**
 * ImageAPI
 *
 * @author TienNguyen
 */
public class ImageAPI {
    // This contains the valid image type
    private static final String[] VALID_IMAGE_TYPE = {
            VariableConstants.IMAGE_JPG,
            VariableConstants.IMAGE_JPG_CAP,
            VariableConstants.IMAGE_GIF,
            VariableConstants.IMAGE_GIF_CAP,
            VariableConstants.IMAGE_PNG,
            VariableConstants.IMAGE_PNG_CAP,
            VariableConstants.IMAGE_BMP,
            VariableConstants.IMAGE_BMP_CAP
    };

    /**
     * This function is used for checking valid Image URL.
     *
     * @param imageURL the information need to be check
     * @return boolean true for valid | false for invalid
     */
    public static boolean isImageURLValid(String imageURL) {
        for (int i = 0; i < VALID_IMAGE_TYPE.length; i++) {
            if (imageURL.contains(VALID_IMAGE_TYPE[i])) {
                return true;
            }
        }

        return false;
    }
}
