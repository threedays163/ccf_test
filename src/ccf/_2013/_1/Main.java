package ccf._2013._1;


/**
 * 题目:
 *  给定n个正整数，找出它们中出现次数最多的数。如果这样的数有多个，请输出其中最小的一个。
 * 输入:
 *
 *  输入的第一行只有一个正整数n(1 ≤ n ≤ 1000)，表示数字的个数。
 *  输入的第二行有n个整数s1, s2, …, sn (1 ≤ si ≤ 10000, 1 ≤ i ≤ n)。相邻的数用空格分隔。
 *
 * 输出:
 *
 *  输出这n个次数中出现次数最多的数。如果这样的数有多个，输出其中最小的一个。
 *
 * 输入样例:
 *  6
 * 10 1 10 20 30 20
 *
 * 输出样例:
 *  10
 */
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //187ms	22.91MB  使用Java集合省内存,但是耗时要多一些
        //solve1();
        //156ms	24.51MB  使用推荐算法耗内存,快一点
        solve2();
    }

    /**
     * 使用Java集合类库及stream, 排序  计算过程中保留了全部的输入结果
     *
     *
     *
     */
    public static void solve1(){
        Scanner scanner=new Scanner(System.in);
        String n=scanner.nextLine();

        String string = scanner.nextLine();

        String[] arr=string.split(" +");

        Map<Integer,Integer> num2Count=new HashMap<>();

        int max=0;
        Set<Integer> maxNums=new HashSet<>();
        for (String s : arr) {
            Integer num=Integer.valueOf(s);
            int value;
            if(num2Count.containsKey(num)){
                value=num2Count.get(num)+1;
            }else{
                value=1;
            }
            num2Count.put(num,value);
            if(value>max){
                max=value;
                maxNums.clear();
                maxNums.add(num);
            }else if(value==max){
                maxNums.add(num);
            }
        }
        int result=Collections.min(maxNums);
        System.out.println(result);
    }

    //官方推荐解法 直接申请单个元素可能的最大限制的数组, 通过输入的值直接索引
    public static void solve2(){

        int n;
        Scanner s=new Scanner(System.in);

        n=s.nextInt();

        int[] nums=new int[10001];

        int max=Integer.MIN_VALUE;
        int maxAppearMinValue=0;

        while(n-->0){
            int a=s.nextInt();
            nums[a]+=1;
            if(nums[a]>max){
                max=nums[a];
                maxAppearMinValue=a;
            }else if(nums[a]==max&&a<maxAppearMinValue){
                maxAppearMinValue=a;
            }
        }
        System.out.println(maxAppearMinValue);
    }
}
