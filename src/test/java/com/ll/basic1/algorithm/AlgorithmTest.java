package com.ll.basic1.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


class AlgorithmTest {

    @Test
    void t1() {
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        int answer = 0;
        //--------------------------------//

        int max = 0;
        Arrays.sort(people);

        for (int i = 0; i < people.length; i++) {

            max += people[i];

            if (limit > max) continue;
            else if (limit == max)  max = 0;
            else max = people[i];

            answer++;
        }
        answer += max == 0 ? 0 : 1;

//        for (int[] i : ints)
            System.out.println(answer);
    }

    @Test
    void t2() {
        int i = 12;
        double sqrt = Math.sqrt(999);
        int m = (int) sqrt;
        System.out.println(m);
    }
}