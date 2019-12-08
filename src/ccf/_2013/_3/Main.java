package ccf._2013._3;

/**
 * 最大的矩形
 * http://118.190.20.162/view.page?gpid=T3
 */

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        solve_1();
    }

    public static void solve_1() {
        Scanner s = new Scanner(System.in);

        String nStr = s.nextLine();
        int n = Integer.valueOf(nStr);

        String str = s.nextLine();
        String[] arrStr = str.split(" +");

        int[] arr = new int[n];

        for (int i = 0; i < arrStr.length; i++) {
            if (i < n) {
                arr[i] = Integer.valueOf(arrStr[i]);
            }
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int minHight = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (arr[j] < minHight) {
                    minHight = arr[j];
                }
                int currentArea = (j - i + 1) * minHight;
                if (currentArea > maxArea) {
                    maxArea = currentArea;
                }
            }
        }
        System.out.println(maxArea);
    }


    public static void solve_2() {
        Scanner s = new Scanner(System.in);

        //String nStr=s.nextLine();
        int n = s.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int minHight = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (arr[j] < minHight) {
                    minHight = arr[j];
                }
                int currentArea = (j - i + 1) * minHight;
                if (currentArea > maxArea) {
                    maxArea = currentArea;
                }
            }
        }
        System.out.println(maxArea);
    }

}
