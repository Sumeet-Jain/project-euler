import java.util.ArrayList;

public class p50{

    public static  ArrayList getPrimes()
    {
        ArrayList p = new ArrayList();
        int num = 3; 
        p.add(2);
        boolean add = true;
        while(num < 1000000){
            for(int i = 0; i < p.size(); i++){
                if (num % (int) p.get(i) == 0){
                    add = false;
                    break;
                }
            }
            if(add)
                p.add(num);
            num++;
            add = true;
        }
        return p;
    }

    public static void main(String[] args){
        ArrayList p = getPrimes();
        int sum = 0;
        int index = 0;
        int start = 0;
        System.out.println(p.size());
        do{
            sum = 0;
            while(sum < 1000000 && index < p.size()){
                sum += (int) p.get(index);
                if (sum > 1000000)
                    sum-= (int) p.get(index);
                index++;
            }
            start++;
            index = start;
        }while(!(p.contains(sum)));
        System.out.println(sum);
    }
}

                
