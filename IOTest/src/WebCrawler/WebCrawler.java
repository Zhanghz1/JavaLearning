package WebCrawler;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
    public static void main(String[] args) throws IOException {
        String familyNameNet = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String boyNameNet = "http://www.haoming8.cn/baobao/10881.html";
        String girlNameNet = "http://www.haoming8.cn/baobao/7641.html";
        //2.爬取数据,把网址上所有的数据拼接成一个字符串
        String familyNameStr = webCrawler(familyNameNet);
        String boyNameStr = webCrawler(boyNameNet);
        String girlNameStr = webCrawler(girlNameNet);
        //3. 取得数据
        ArrayList<String> familyNameTempList = getData(familyNameStr, "([\\u4e00-\\u9fa5]{4})(，|。)",1);
        ArrayList<String> boyNameTempList = getData(boyNameStr, "([\\u4e00-\\u9fa5]{2})(、|。)",1);
        ArrayList<String> girlsNameTempList = getData(girlNameStr, "(([\\u4e00-\\u9fa5]{2} ){4}[\\u4e00-\\u9fa5]{2})",1);
        //4. 处理数据
        //姓氏
        ArrayList<String> familyNameList = new ArrayList<>();
        for (String s : familyNameTempList) {
            for (int i = 0; i < s.length(); i++) {
                familyNameList.add(s.charAt(i) + "");
            }
        }
        //男生名字
        ArrayList<String> boyNameList = new ArrayList<>();
        for (String s : boyNameTempList) {
            if(!boyNameList.contains(s)){
                boyNameList.add(s);
            }
        }
        //女生名字
        ArrayList<String> girlsNameList = new ArrayList<>();
        for (String s : girlsNameTempList) {
            String[] arr = s.split(" ");
            for (int i = 0; i < arr.length; i++) {
                girlsNameList.add(arr[i]);
            }
        }
        //5. 生成数据：姓名-性别-年龄
        ArrayList<String> list = getInfos(familyNameList, boyNameList, girlsNameList, 50, 50);
        Collections.shuffle(list);
        //6. 写出数据
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\JavaLearning\\JavaLearning\\IOTest\\names.txt"));
        for (String s : list) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();

    }
    //生成数据：姓名-性别-年龄
    public static ArrayList<String> getInfos(ArrayList<String> familyNameList,
                                             ArrayList<String> boyNameList,
                                             ArrayList<String> girlsNameList,
                                             int boysCount, int girlsCount){
        //生成不重复的名字
        HashSet<String> boyhs = new HashSet<>();
        while (true){
            if (boyhs.size() == boysCount)  break;
            Collections.shuffle(familyNameList);
            Collections.shuffle(boyNameList);
            boyhs.add(familyNameList.get(0) + boyNameList.get(0));
        }
        HashSet<String> girlhs = new HashSet<>();
        while (true){
            if (girlhs.size() == girlsCount)  break;
            Collections.shuffle(familyNameList);
            Collections.shuffle(girlsNameList);
            girlhs.add(familyNameList.get(0) + girlsNameList.get(0));
        }
        //生成符合格式的信息
        ArrayList<String> list = new ArrayList<>();
        Random r = new Random();
        for (String boyName : boyhs) {
            int age = r.nextInt(10) + 18;
            list.add(boyName + "-男-" + age);
        }
        for (String girlName : girlhs) {
            int age = r.nextInt(10) + 18;
            list.add(girlName + "-女-" + age);
        }


        return list;
    }



    //通过Lambda表达式获取数据
    private static ArrayList<String> getData(String str,String regex, int index){
        ArrayList<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            list.add(matcher.group(index));
        }
        return list;

    }
    //爬取数据
    public static String webCrawler(String net) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(net);
        URLConnection conn = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        int ch;
        while ((ch = br.read()) != -1){
            sb.append((char) ch);
        }
        br.close();
        return sb.toString();
    }

}
