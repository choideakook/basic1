package com.ll.basic1.algorithm;

import org.junit.jupiter.api.Test;


class AlgorithmTest {

    @Test
    void t1() {
        String s = "banana";
        int[] answer = new int[s.length()];
        //--------------------------------//

        String copy = s;

        for (int i = s.length() -1; i >= 0; i--) {

        }


        for (int i : answer)
            System.out.println(i);
    }

    @Test
    void t2() {
        int i = 12;
        double sqrt = Math.sqrt(999);
        int m = (int) sqrt;
        System.out.println(m);
    }
}