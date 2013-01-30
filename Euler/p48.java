public class p48{

    public static long square(long base, long exp){
        long bae = 1;
        long div = 10000000000L ;
        while(exp > 0){
            if(bae > 99999999)
                bae = bae % div;
            bae = base * bae;
            exp--;
        }
        return bae;
    }


    public static void main(String[] args){
        long sum = 0;
        for (long i = 1; i <= 1000; i++)
            sum +=  square(i,i);
       System.out.println(sum);
    }
}
