package FileCount;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileCount {
    public static void main(String[] args) {
        /*需求:统计一个文件夹中每种文件的个数并打印。(考虑子文件夹
        打印格式如下:
        txt:3个
        doc:4个
        jpg:6个*/
        File file = new File("D:\\JavaLearning\\JavaLearning\\test\\src");
        Map<String, Integer> map = getFileCount(file);
        /*map.entrySet().forEach(entry -> {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ", " + value);
        });*/
        System.out.println(map);

    }
    public static Map<String,Integer> getFileCount(File file){
        //统计一个文件夹中每种文件的个数并打印
        //1. Map集合存放<文件类型，个数>
        Map<String,Integer> map = new HashMap<>();


        //2. 遍历文件，遇到有后缀的文件存入Map，对应count++；
        if (!file.exists()) return map;

        for (File f : file.listFiles()) {
            //将文件“放入”map集合
            if (f.isFile()){
                //得到文件后缀
                String[] split = f.getName().split("\\.");
                if (split.length >= 2){
                   String expendName = split[split.length-1];
                    //判断map是否已有该key
                    if (map.containsKey(expendName)){
                        int value = map.get(expendName);
                        map.put(expendName, ++value);
                    }else {
                        map.put(expendName, 1);
                    }
                }
            }else if (f.isDirectory()){
                Map<String,Integer> sonMap = getFileCount(f);
                for (Map.Entry<String, Integer> entry : sonMap.entrySet()) {
                    String key = entry.getKey();
                    Integer sonValue = entry.getValue();
                    map.put(key, map.getOrDefault(key, 0) + sonValue);
                }
            }

        }

        //   文件夹递归调用，将返回的Map集合与原Map合并
        return map;

    }



}
