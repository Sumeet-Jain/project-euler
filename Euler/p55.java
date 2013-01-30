import eulerTools.Tools;
import java.lang.Math;
//
//Outputs 249
//Problem was this one is that lyrchels go above the value of longs.
//I kept reducing the value of iterations until it didnt overflow, and still recieved the 
//same answer. I did this because of the quest, 10667 is the first one over 50 iters. 
//I guess i got lucky. Otherwise, I would have had to use the BigInteger class, which
//would have slowed down the program. Also, I would have had to rewrite a lot of it, 
//and really didnt want to so it could run for 50 iters. 
//
//Runs in 271 milliseconds. 
//Updated Tools for this excerise.
public class p55{
    
    public static void main(String[] args){
        int count = 0; 
        int iter =  0;
        long num = 1L; 
        long newNum = num;
               
        long start = System.currentTimeMillis();
        while(num < 10000){
            boolean add = true;
             do{
                 newNum = newNum + Long.parseLong(Tools.reverse("" + newNum));
                 if(Tools.isPalindrome("" + newNum)){
                     add = false;
                     break;
                 }
                iter++;
             }while(iter < 30);
             if(add)
                 count++;
             iter = 0;
             num++;
             newNum = num;
        }

        System.out.println(count);
        System.out.println(System.currentTimeMillis() - start);
    }
}
