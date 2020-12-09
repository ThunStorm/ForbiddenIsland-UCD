package com.esr.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author William
 * @Date 2020/11/24
 * @Version 1.0
 **/
public abstract class Map {
//    public final static int[] layOut = new int[]{1, 2, 5, 6, 7, 12, 25, 30, 31, 32, 35, 36};
    public final static ArrayList<Integer> blankLayout = new ArrayList<Integer>(){{
        add(0);add(1);add(4);add(5);add(6);add(11);add(24);add(29);add(30);add(31);add(34);add(35);
    }};
    public final static int rows = 6;
    public final static int cols = 6;
    public final static HashMap<Integer, int[]> coordinatesMatcher = new HashMap<>();
    public final static HashMap<String, Integer> numberMatcher = new HashMap<>();
    public final static HashMap<Integer, String> adventurerMatcher = new HashMap<>();
    public static void setUpMatchers()
    {
        coordinatesMatcher.put(0, new int[]{0, 2});
        coordinatesMatcher.put(1, new int[]{0, 3});
        coordinatesMatcher.put(2, new int[]{1, 1});
        coordinatesMatcher.put(3, new int[]{1, 2});
        coordinatesMatcher.put(4, new int[]{1, 3});
        coordinatesMatcher.put(5, new int[]{1, 4});
        coordinatesMatcher.put(6, new int[]{2, 0});
        coordinatesMatcher.put(7, new int[]{2, 1});
        coordinatesMatcher.put(8, new int[]{2, 2});
        coordinatesMatcher.put(9, new int[]{2, 3});
        coordinatesMatcher.put(10, new int[]{2, 4});
        coordinatesMatcher.put(11, new int[]{2, 5});
        coordinatesMatcher.put(12, new int[]{3, 0});
        coordinatesMatcher.put(13, new int[]{3, 1});
        coordinatesMatcher.put(14, new int[]{3, 2});
        coordinatesMatcher.put(15, new int[]{3, 3});
        coordinatesMatcher.put(16, new int[]{3, 4});
        coordinatesMatcher.put(17, new int[]{3, 5});
        coordinatesMatcher.put(18, new int[]{4, 1});
        coordinatesMatcher.put(19, new int[]{4, 2});
        coordinatesMatcher.put(20, new int[]{4, 3});
        coordinatesMatcher.put(21, new int[]{4, 4});
        coordinatesMatcher.put(22, new int[]{5, 2});
        coordinatesMatcher.put(23, new int[]{5, 3});

        numberMatcher.put(Arrays.toString(new int[]{0, 2}), 0);
        numberMatcher.put(Arrays.toString(new int[]{0, 3}), 1);
        numberMatcher.put(Arrays.toString(new int[]{1, 1}), 2);
        numberMatcher.put(Arrays.toString(new int[]{1, 2}), 3);
        numberMatcher.put(Arrays.toString(new int[]{1, 3}), 4);
        numberMatcher.put(Arrays.toString(new int[]{1, 4}), 5);
        numberMatcher.put(Arrays.toString(new int[]{2, 0}), 6);
        numberMatcher.put(Arrays.toString(new int[]{2, 1}), 7);
        numberMatcher.put(Arrays.toString(new int[]{2, 2}), 8);
        numberMatcher.put(Arrays.toString(new int[]{2, 3}), 9);
        numberMatcher.put(Arrays.toString(new int[]{2, 4}), 10);
        numberMatcher.put(Arrays.toString(new int[]{2, 5}), 11);
        numberMatcher.put(Arrays.toString(new int[]{3, 0}), 12);
        numberMatcher.put(Arrays.toString(new int[]{3, 1}), 13);
        numberMatcher.put(Arrays.toString(new int[]{3, 2}), 14);
        numberMatcher.put(Arrays.toString(new int[]{3, 3}), 15);
        numberMatcher.put(Arrays.toString(new int[]{3, 4}), 16);
        numberMatcher.put(Arrays.toString(new int[]{3, 5}), 17);
        numberMatcher.put(Arrays.toString(new int[]{4, 1}), 18);
        numberMatcher.put(Arrays.toString(new int[]{4, 2}), 19);
        numberMatcher.put(Arrays.toString(new int[]{4, 3}), 20);
        numberMatcher.put(Arrays.toString(new int[]{4, 4}), 21);
        numberMatcher.put(Arrays.toString(new int[]{5, 2}), 22);
        numberMatcher.put(Arrays.toString(new int[]{5, 3}), 23);

        adventurerMatcher.put(1, "Diver");
        adventurerMatcher.put(2, "Engineer");
        adventurerMatcher.put(3, "Explorer");
        adventurerMatcher.put(4, "Messenger");
        adventurerMatcher.put(5, "Navigator");
        adventurerMatcher.put(6, "Pilot");


    }
}

