package MapIteration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapIteration {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        //Entry键值对遍历
        //1. 增强for
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ", " + value);
        }
        //2. 迭代器
        Iterator<Map.Entry<String, Integer>> entryInterator = map.entrySet().iterator();
        while (entryInterator.hasNext()){
            Map.Entry<String, Integer> entry = entryInterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ", " + value);
        }
        //3. Lambda表达式
        map.entrySet().forEach(entry -> {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ", " + value);
        });



        //*******************************************************
        //键找值遍历：
        //获取所有键，放到一个单列集合Set中，遍历Set获得每个键对应的值
        //1. 增强for
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Integer value = map.get(key);
            System.out.println(key + ", " + value);
        }
        //2. 迭代器
        //创建泛型为String的迭代器
        Iterator<String> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()){
            String key = keyIterator.next();
            Integer value = map.get(key);
            System.out.println(key + ", " + value);
        }
        //3. Lambda表达式
        keySet.forEach(key -> {                 //不简写：(String key) ->
            Integer value = map.get(key);
            System.out.println(key + ", " + value);
        });
//*******************************************************
    }
}
