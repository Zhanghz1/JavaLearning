package StreamTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataFilter {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3,4,21,6,1251);
        List<Integer> collect = list.stream().filter(s -> s % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(collect);

    }
}
