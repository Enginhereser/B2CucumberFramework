package _temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Temp1 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        write1(list1);

        List<Integer> list2 = new LinkedList<>(Arrays.asList(6,7,8,9));
        write1(list2);
    }

    public static void write1(List<Integer> list){
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}
