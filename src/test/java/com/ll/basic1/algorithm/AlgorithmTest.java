package com.ll.basic1.algorithm;

import org.junit.jupiter.api.Test;

class AlgorithmTest {

    @Test
    void t1() {
        int n = 15;
        int answer = 0;
        //--------------------------------//
        String num = Integer.toString(n, 2);
        int length = num.replace("0", "").length();

        for (int i = n + 1; i > n; i++) {
            String s = Integer.toString(i, 2);
            int iLength = s.replace("0", "").length();

            if (length == iLength) {
                answer = i;
                break;
            }
        }


//        for (int i : answer)
            System.out.println(answer);
    }
}