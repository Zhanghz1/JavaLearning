package LeetCode;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TransMatrix {
    public static void main(String[] args) {
        /*给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。

        矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。*/
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9},{7,8,9}};


    }
    public int[][] transpose(int[][] matrix){
        int rowL = matrix.length;
        int columnL = matrix[0].length;
        int[][] transM = new int[columnL][rowL];
        for (int i = 0; i < columnL; i++) {
            for (int j = 0; j < rowL; j++) {
                transM[i][j] = matrix[j][i];
            }
        }
        return transM;
    }
    public int maxScore(String s) {
        int score = 0;
        for (int i = 1; i < s.length(); i++) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == '0'){
                    hashMap.put('0', hashMap.getOrDefault('0', 0) + 1);
                }
            }
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1'){
                    hashMap.put('1', hashMap.getOrDefault('1', 0) + 1);
                }
            }
            score = Math.max(score, hashMap.getOrDefault('0', 0) + hashMap.getOrDefault('1', 0));
        }
        return score;
    }

    public int vowelStrings(String[] words, int left, int right) {
        int count = 0;
        for (int i = left; i < right; i++) {
            if (judge(words[i])) count++;
        }
        return count;
    }
    public boolean judge(String str){
        char[] chars = {'a','e','i','o','u'};
        boolean val1 = false;
        boolean val2 = false;
        for (int i = 0; i < chars.length; i++) {
            if (str.charAt(0) == chars[i]) val1 = true;
        }
        for (int i = 0; i < chars.length; i++) {
            if (str.charAt(str.length()-1) == chars[i]) val2 = true;
        }
        return val1&&val2;
    }
    public int peakIndexInMountainArray(int[] arr) {
        int l = arr.length;
        int left = 0;
        int right = l-1;
        int index = 0;//记录左侧值在数组中的位置
        while(true){
            int n = (right - left)/2 + index;
            //峰顶在n右侧
            if((arr[n] > arr[n-1]) && (arr[n] < arr[n+1])){
                left = n;
                index += left;
            }
            //峰顶在左侧
            if((arr[n] < arr[n-1]) && (arr[n] > arr[n+1])){
                right = n;
            }
            if((arr[n] > arr[n-1]) && (arr[n] > arr[n+1])){
                index = n;
                break;
            }
        }
        return index;
    }


}
