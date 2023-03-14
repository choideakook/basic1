package com.ll.basic1.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Stack;

class AlgorithmTest {

    @Test
    void t1() {
        String s = "bbaa";
        int answer = 0;
        //--------------------------------//
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            try {
                if (s.charAt(i) == stack.peek()) {
                    stack.pop();
                    continue;
                }
            }catch (Exception e){}
            stack.add(s.charAt(i));
        }
        answer = stack.empty() ? 1 : 0;


//        for (int i : answer)
        System.out.println(answer);
    }

    @Test
    void t2() {
        Stack<Integer> s = new Stack<>();
        s.peek();
        System.out.println("dd");
    }
}