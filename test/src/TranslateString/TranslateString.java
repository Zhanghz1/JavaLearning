package TranslateString;

import java.util.Scanner;
import java.util.StringJoiner;

public class TranslateString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入数字：");
            StringBuilder sb = new StringBuilder(sc.next());
            boolean checkNum = checkNum(sb);
            //检查是否合法
            if(!checkNum){
                System.out.println("输入的数字不合法，请重新输入。");
                continue;
            }
            StringJoiner sj = (referenceTable(sb));
            //System.out.println(joiner.toString())，默认调用对象的toString方法
            System.out.println(sj);
        }
    }
    public static boolean checkNum(StringBuilder sb){
        if(sb.length() > 9)
            return false;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(c < '0' || c > '9') return false;
        }
        return true;
    }
    public static StringJoiner referenceTable(StringBuilder sb){
        String[] referenceTable = {"0","I","II","III","IV","V","VI","VII","VIII","IX"};
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            sj.add(referenceTable[(int)(c - '0')]);
        }
        return sj;
    }
}
