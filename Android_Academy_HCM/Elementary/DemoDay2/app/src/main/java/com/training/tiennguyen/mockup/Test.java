/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.mockup;

import com.training.tiennguyen.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win 8.1 VS8 X64 on 20/12/2015.
 */
public class Test {
    public static List<Student> getStudentList() {
        Student s = new Student();
        s.setName(" Roman Nurik ");
        s.setAvatar("http://androidweekly.net/assets/landingpage/testimonials/nurik-ef032225a9cfce1822d4058faa9fd8be.jpg");
        s.setWebsite("http://roman.nurik.net/");

        Student s1 = new Student();
        s1.setName(" Lars Vogel ");
        s1.setAvatar("http://androidweekly.net/assets/landingpage/testimonials/vogel-da91ed7332cec928853044a3817c25a2.jpg");
        s1.setWebsite("http://vogella.com/");

        Student s2 = new Student();
        s2.setName(" Mark Allison ");
        s2.setAvatar("http://androidweekly.net/assets/landingpage/testimonials/allison-cbd43c874eaec8b48ba3e3cd5a287177.jpg");
        s2.setWebsite("http://blog.stylingandroid.com/");

        Student s3 = new Student();
        s3.setName(" Smashing Magazine ");
        s3.setAvatar("http://androidweekly.net/assets/landingpage/testimonials/smashingmag-ccdefa49acc17538a4014c9220d9fbab.jpg");
        s3.setWebsite("http://www.smashingmagazine.com/");

        Student s4 = new Student();
        s4.setName(" Chris Banes ");
        s4.setAvatar("http://androidweekly.net/assets/landingpage/testimonials/banes-4ce86cf28cceed1968f364b69c34e1e8.jpg");
        s4.setWebsite("http://chris.banes.me/");

        Student s5 = new Student();
        s5.setName(" Joaquim Vergès ");
        s5.setAvatar("http://androidweekly.net/assets/landingpage/testimonials/joaquim-26a6d33c5d5bf10d79b156f9b2c3cb1b.jpg");
        s5.setWebsite("https://twitter.com/joenrv");

        List<Student> studentList = new ArrayList<>();
        studentList.add(s);
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);

        return studentList;
    }
}
