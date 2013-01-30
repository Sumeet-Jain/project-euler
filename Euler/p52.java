// Find a number such that x, 2x, 3x, 4x, 5x, and 6x all have the same digits.


import eulerTools.Tools;

public class p52{
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int num = 1;
        System.out.println(Tools.isPerm(1234, 4321));
loop :
        while(true){
            if(Tools.isPerm(num, num*2) && Tools.isPerm(num, num*3) && Tools.isPerm(num, num*4) && Tools.isPerm(num, num*5) && Tools.isPerm(num, num*6)){
                System.out.println(num);
                break loop;
            }
            else num++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
// outputs: 142857
// Nothing too new here. Just brute forced this. 
// There are optimizations, such as only computing thing with the first digit as one, 
// but there is no need to code these. For example, the number has to start with 1xxxxxx
// Otherwise, 2xxxxxx * 6 = 12xxxxxx, and thus has a different number of digits.
//
// Forum for this one is pretty interesting. 1/7 naturally has this property. 
//
