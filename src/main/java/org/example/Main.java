package org.example;

import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static String removeDuplicateLetters(String s) {
        char[] arr = s.toCharArray();
        Set<Character> set = new LinkedHashSet<>(); // 保持插入顺序

        for (char c : arr) {
            set.add(c);
        }

        // 使用 StringBuilder 来构建字符串
        StringBuilder sb = new StringBuilder();
        for (char c : set) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "bcabc";
        System.out.println(removeDuplicateLetters(s)); // 输出 "abc"
    }
}