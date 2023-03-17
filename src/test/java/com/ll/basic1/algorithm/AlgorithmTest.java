package com.ll.basic1.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;


class AlgorithmTest {

    @Test
    void t1() {
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        int answer =0;
        //--------------------------------//

        int[] a = new int[99999];
        for (int i = 0; i < a.length; i++) {
            double v = Math.random() * 99999;
            a[i] = (int) v;
        }

        long start = System.nanoTime();

//        Collections.sort(Arrays.asList(a));


        long end = System.nanoTime();
        System.out.println((end-start) / 1000000.0);


//        for (int i : answer)
//            System.out.println(answer);
    }

    @Test
    void t2() {
        int i = 12;
        double sqrt = Math.sqrt(999);
        int m = (int) sqrt;
        System.out.println(m);
    }
}