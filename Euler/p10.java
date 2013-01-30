import eulerTools.Tools;

public class p10{
    static public void main(String[] args){
        boolean[] primes = Tools.getPrimes(2000000);
        long sum = 0;
        int count = 0;
        for(int i = 2; i < primes.length; i++)
            if(primes[i]){
                count++;
                sum = sum + i;
            }
        System.out.println(count);
        System.out.println(sum);
    }
}
