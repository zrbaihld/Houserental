package com.zrb.houserental.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class MainTestJava {
    private static int[] ints = {1, 5, 4, 8, 6, 3, 9, 7, 4, 8, 2, 9, 3};

    public static void main(String[] strings) {
        List<test> tests = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            test test = new test();
            test.setDays(ints[i]);
            test.setName("test" + i);
            tests.add(test);
        }
        Collections.sort(tests, new Comparator<test>() {
            @Override
            public int compare(test floorEntity, test t1) {
                if (floorEntity.getDays() > t1.getDays())
                    return 1;
                else if (floorEntity.getDays() == t1.getDays())
                    return 0;
                else
                    return -1;
            }
        });
        for (test test : tests) {
            System.out.println("test.getName()  " + test.getName() + "test.getDays()  " + test.getDays());
        }
    }

    private static class test {
        private int days;
        private String name;

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
