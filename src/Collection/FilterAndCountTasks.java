package Collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FilterAndCountTasks {
    public interface Filter<T> {
        T apply(T o);
    }
    public static <T> T[] filterMethod(T[] array, Filter<T> filter) {
        T[] resultArray = (T[]) Array.newInstance(array.getClass().getComponentType(),array.length);
        for(int i = 0;i< array.length;i++) {
            resultArray[i] = filter.apply(array[i]);
        }
        return resultArray;
    }

    public static <K> Map<K,Integer> countMethod(K[]array) {
        Map<K,Integer> map = new HashMap<>();
        for(K k : array) {
            map.merge(k,1,Integer::sum);
        }
        return map;
    }

    public static void main(String[] args) {
        String[] arr = {"Abc","Def","Abc","Ghi","Def","Abc","Def","Abc","Jkl"};
        Filter<String> stringFilter = s->s.repeat(3);
        String[] newArr = filterMethod(arr,stringFilter);
        Arrays.stream(newArr).forEach(System.out::println);
        System.out.println(countMethod(arr));
    }
}
