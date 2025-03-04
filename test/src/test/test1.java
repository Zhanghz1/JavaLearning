package test;

import java.util.Random;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        int[] redBall = generator();
        /*for (int i = 0; i < 7; i++) {
            System.out.println(redBall[i]);
        }*/
        guessAndResult();
    }
    //生成中奖号码
    public static int[] generator(){
        int[] arr = new int[7];
        Random r = new Random();
        for (int i = 0; i < arr.length-1; i++) {
            arr[i] = r.nextInt(33) + 1;
            for (int j = 0; j < i; j++) {
                if( arr[i]  ==  arr[j] ){
                    i--;
                    break;
                }
            }
        }
        arr[6] = r.nextInt(16) + 1;
        return arr;
    }
    //输入猜测并对比
    public static void guessAndResult(){
        int[] guess = new int[7];
        Scanner sc = new Scanner(System.in);

    }

}
