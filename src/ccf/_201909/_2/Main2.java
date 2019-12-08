package ccf._201909._2;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = 0, D = 0, E = 0;
        int N = in.nextInt();
        int num[] = new int[N];
        int drop[] = new int[N];
        Arrays.fill(num, 0);
        Arrays.fill(drop, 0);
        for (int i = 0; i < N; i++) {
            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                int input = in.nextInt();
                if (input > 0) {
                    if (j != 0 && num[i] > input)
                        drop[i] = 1;
                    num[i] = input;
                } else
                    num[i] += input;
            }
        }
        for (int i = 0; i < N; i++) {
            T += num[i];
            if (drop[i] == 1)
                D++;
            if (drop[i - 1 >= 0 ? i - 1 : N - i - 1] == 1 && drop[i] == 1 && drop[i + 1 < N ? i + 1 : i + 1 - N] == 1) {
                E++;
            }
        }
        System.out.print(T + " " + D + " " + E);
    }
}
