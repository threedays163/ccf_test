package ccf._201909._1;

import java.util.Scanner;

/**
 * 小明种苹果
 */
public class Main {

    public static void main(String[] arg){
        Scanner scanner=new Scanner(System.in);
        //n:苹果数棵树
        int n=scanner.nextInt();
        //m:轮数
        int m=scanner.nextInt();

        int [][] arr=new int[n][m+1];

        //输出结果
        //T:表示最后剩余的苹果个数
        int T=0;
        //k:表示去掉苹果数最多的树编号,相等取编号最小的
        int k=0;
        //P:掉果最多的个数
        int P=0;

        //剩余数量
        int sum=0;
        int max=Integer.MIN_VALUE;
        int maxIndex=-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m+1;j++){
                int tem=scanner.nextInt();

                //先不校验
                sum+=tem;
                arr[i][j]=tem;
            }
            int temSum=0;
            for(int a =1;a<m+1;a++){
                temSum+=-arr[i][a];
            }
            if(temSum>max){
                max=temSum;
                maxIndex=i;
            }
        }

        T=sum;
        k=maxIndex+1;
        P=max;
        System.out.printf("%d %d %d",T,k,P);
    }
}
