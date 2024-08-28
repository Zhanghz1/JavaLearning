package StreamTest;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
//找出24岁以上的人
public class MapStream {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("zhangsan,23");
        arrayList.add("lisi,24");
        arrayList.add("wangwu,25");

        Map<String, String> collect = arrayList.stream().filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
                .collect(Collectors.toMap(
                        s -> s.split(",")[0]
                        ,
                        s -> s.split(",")[1]));
        System.out.println(collect);
    }
}
