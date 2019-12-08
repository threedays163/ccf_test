package ccf._201909._2;

import java.util.Scanner;

/**
 * 小明种苹果（续）
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        //n:苹果数棵树
        int n=scanner.nextInt();

        //输出结果
        //T:表示最后剩余的苹果个数
        int T=0;
        //D:表示发生苹果掉落的棵树
        int D=0;
        //E:连续3颗发生苹果掉落的组数
        int E=0;

        //剩余数量
        int sum=0;
        //标记是否掉落
        int arr[]=new int[n];

        //发生掉落的棵树
        int countDown=0;
        //发生连续掉落的组数
        int groutCount=0;

        //n行
        for(int i=0;i<n;i++){
            //后面有几个元素
            int m = scanner.nextInt();
            int temSum=0;

            for(int j=0;j<m;j++){
                int tem=scanner.nextInt();
                if(tem>0){
                    //重新计数
                    if(j>0&&tem!=temSum){
                        arr[i]=1;
                    }
                    temSum=tem;
                }else{
                    //掉落
                    temSum+=tem;
                }
            }
            sum+=temSum;
            if(arr[i]==1){
                countDown++;
            }
            if(i>=2){
                groutCount+=fun(arr,i,n);
            }
        }
        groutCount+=fun(arr, 0, n);
        groutCount+=fun(arr, 1, n);

        T=sum;
        D=countDown;
        E=groutCount;
        System.out.printf("%d %d %d",T,D,E);
    }

    public static int fun(int[] arr,int i,int n){
        int countCurrentDown=0;
        countCurrentDown+=arr[(i-2+n)%n];
        countCurrentDown+=arr[(i-1+n)%n];
        countCurrentDown+=arr[i];
        if(countCurrentDown==3){
            return 1;
        }else{
            return 0;
        }
    }
}
