package ccf._2013._4;

import java.util.Scanner;
import java.util.Set;

/**
 * 问题描述
 * 　　我们把一个数称为有趣的，当且仅当：
 * 　　1. 它的数字只包含0, 1, 2, 3，且这四个数字都出现过至少一次。
 * 　　2. 所有的0都出现在所有的1之前，而所有的2都出现在所有的3之前。
 * 　　3. 最高位数字不为0。
 * 　　因此，符合我们定义的最小的有趣的数是2013。除此以外，4位的有趣的数还有两个：2031和2301。
 * 　　请计算恰好有n位的有趣的数的个数。由于答案可能非常大，只需要输出答案除以1000000007的余数。
 * 输入格式
 * 　　输入只有一行，包括恰好一个正整数n (4 ≤ n ≤ 1000)。
 * 输出格式
 * 　　输出只有一行，包括恰好n 位的整数中有趣的数的个数除以1000000007的余数。
 * 样例输入
 * 4
 * 样例输出
 * 3
 */


public class Main {

    public static void main(String[] args) {
        solve_2();
    }

    public static void solve_1(){
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();

        int count=0;

        int[] arr=new int[n];

        int i=1;
        while(i++<=n){

        }

    }

    public static void solve_2(){
        Scanner s=new Scanner(System.in);
        int N=s.nextInt();

        long[] count = new long[8];
        count[6] = 0;
        count[7] = 1;
        long mod = 1000000007;
        for (int i = 2; i <= N; ++i) {
            long[] newCount = new long[8];
            newCount[0] = (count[0] * 2 + count[1] + count[3]) % mod;
            newCount[1] = (count[1] * 2 + count[2] + count[5]) % mod;
            newCount[2] = (count[2] + count[6]) % mod;
            newCount[3] = (count[3] * 2 + count[4] + count[5]) % mod;
            newCount[4] = (count[4] + count[7]) % mod;
            newCount[5] = (count[5] * 2 + count[6] + count[7]) % mod;
            newCount[6] = 0;
            newCount[7] = 1;
            count = newCount;
        }

        System.out.println(count[0]);
    }
}
