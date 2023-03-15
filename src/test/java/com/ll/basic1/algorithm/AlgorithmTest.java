package com.ll.basic1.algorithm;

import org.junit.jupiter.api.Test;


class AlgorithmTest {

    @Test
    void t1() {
        int brown = 24;
        int yellow = 24;
        int[] answer = new int[2];
        //--------------------------------//

        int sum = brown + yellow;
        double sqrt = Math.sqrt(sum);

        int num = (int) sqrt;

        answer[1] = num;
        if ((num * num) == sum) answer[0] = num;
        else {
            for (int i = 1; i < yellow; i++)
                if (sum == (num * (num + i))) answer[0] = num + i;
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