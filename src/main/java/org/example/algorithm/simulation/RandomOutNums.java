package org.example.algorithm.simulation;

import java.util.Random;

public class RandomOutNums {
    private static final int range = 10;
    public static void main(String[] args) {
        int[] result = getNumber(range);
        for (int item : result) {
            System.out.print(item + ", ");
        }
    }
    private static int[] getNumber(int total) {
        int[] numberBox = new int[total];
        int[] rtnNumber = new int[total];
        for (int i = 0; i < total; i++) {
            numberBox[i] = i;
        }
        for (int i = 0, end = total - 1; i < total; i++, end--) {
            int randomIndex = new Random().nextInt(end + 1);
            rtnNumber[i] = numberBox[randomIndex];
            numberBox[randomIndex] = numberBox[end];
        }
        return rtnNumber;
    }
}
