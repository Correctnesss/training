package course1.lesson3;

import java.util.Arrays;

public class TwoDimArray {
    public static void main(String[] args) {
        int[][]array = new int[5][5];

        array[array.length-1][array.length-1] = 100;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();

        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i])); // приходит []
        }


        System.out.println(Arrays.toString(array)); // приходит [][]



        int[] subArray = array[2];


        System.out.println();
        System.out.println();
        System.out.println();


        int[][] newArray = new int [5][];

        newArray[0] = new int[]{1, 2, 3};
        newArray[1] = new int[]{1, 2, 3, 4 ,5};
        newArray[2] = new int[]{1};
        newArray[3] = new int[]{1, 2};

        System.out.println(Arrays.toString(newArray));


        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(newArray[i])); // приходит []
        }


        int[][] arr = {{1, 2},{3, 4}, {5, 6, 7}};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i])); // приходит []
        }

        //int[][][][][] многомерный массив


        char[] chars = "Java".toCharArray();
        System.out.println(chars[2]);

        System.out.println("Java".charAt(2));


    }

    public static int getAvg(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }


        return sum / array.length;

    }

}
