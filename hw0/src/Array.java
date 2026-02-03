public class Array{
    public static void windowPosSum(int[] a, int n){
        int len=a.length;
        for(int i=0;i<len;++i){
            if(a[i]<0) continue;
            int tmp=0;
            for(int j=i;j<len && j<=i+n;++j){
                tmp+=a[j];
            }
            a[i]=tmp;
        }
    }

    static void main(String[] args){
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        System.out.println(java.util.Arrays.toString(a));
    }
}