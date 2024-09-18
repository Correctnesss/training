package course1.lesson4;

import java.util.*;
import java.util.stream.Collectors;

public class TaskApp {
    public static void main(String[] args) {
        //Задача
        //Дан массив целых чисел. Требуется удалить все неуникальные значения, кроме последнего
        //[3, 4, 5, 3, 5, 6] -> [4, 3, 5, 6]
        //[3, 4, 5, 3, 5, 3] -> [4, 5, 3]

        System.out.println(Arrays.toString(getNewArray(new int[]{3, 4, 5, 3, 5, 6})));
        List<Integer> list= Arrays.asList(new Integer[]{3,4,5,3,5,6});
        Collections.reverse(list);
        System.out.println(list.stream().distinct().collect(Collectors.toList()));


    }

    public static void reverseArray(int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                array[i] = 0;
            } else {
                array[i] = 1;
            }

        }
    }

    public static int[] getNewArray(int[] array) {
        int[] result = new int[0];

        for (int i = array.length - 1; i >= 0; i--) {
            result = addValue(result, array[i]);
        }
        return result;
    }

    //[] 6 -> [6]
    //[5, 6] 5 -> [5, 6]
    //[5, 6] 3 -> [3, 5, 6]
    public static int[] addValue(int[] array, int value) {
        if (arrayContainsValue(array, value)) {
            return array;
        }
        int[] result = new int[array.length + 1];
        result[0] = value;
        for (int i = 0; i < array.length; i++) {
            result[i + 1] = array[i];
        }
        return result;

    }

    /**
     * Проверка, что массив содержит значение
     *
     * @param array
     * @param value
     * @return
     */

    public static boolean arrayContainsValue(int[] array, int value) {
        for (int arr : array) {
            if (arr == value) {
                return true;
            }
        }
        return false;
    }


}



