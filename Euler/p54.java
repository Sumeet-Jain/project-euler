// Determine the winners of poker games. 
// Read line from poker.txt
// First five words are the first five cards of the first hand. 
// Next words are the next cards
//
// Output how many games winner 1  wins
//
//
import java.io.*;
public class p54{

    static int p1Hand = 0;
    static final int p2Hand = 1;
    static final int number = 0;
    static final int suit = 1;
    static final int hearts = 0, diamonds = 1, clubs = 15, spades = 16, ace = 14;
    
    

    //Reads both hands. Inserts them into an array. 
    //Returns hands as an array of [player][cards]
    //Used some data abstraction as above.
    //Index of cards represents cards value. The value of the index is the occurences.
    public static int[][] readHands(String line){
        int index = 0; 
        int count = 0;
        int[][] hands = new int[2][17]; 
        int player = 0;
        
        //Set each slot to 0;
        for(int i = 0; i < hands.length; i++)
            for (int j = 0; j < hands[0].length; j++)
                hands[i][j] = 0;

        //Read each card. Increase slot appropriately.
        while(index< line.length()){
            String card = line.substring(index, index + 2); 
            char num = card.charAt(number);
            char s = card.charAt(suit);
            if(count > 4)
                 player = 1; 
            hands[player][readNum(num)] = hands[player][readNum(num)] + 1;
            hands[player][readSuit(s)]++;
            count++;
            index = index + 3;
        }
        return hands;
    }

    //Translates a character into its corresponding number
    public static int readNum(char n){
        if(n == 'A')
            return 14;
        else if(n == 'K')
            return 13;
        else if(n == 'Q')
            return 12;
        else if(n == 'J')
            return 11;
        else if(n == 'T')
            return 10;
        else return ((int) n -48);
    }
       
    //Translates the suit into corresponding value
    public static int readSuit(char s){
        if(s == 'H')
            return hearts;
        if(s == 'D')
            return diamonds;
        if(s == 'C')
            return clubs;
        else return spades;
    }
   
    //Returns -1 if no pair, else returns 2xy where xy is the num of pair
    public static int hasPair(int[] hand){
        for (int i = ace; i >= 2; i--)
            if (hand[i] == 2)
                return (200 + i);
        return -1;
    }

    //Returns -1 if no trips, else returns 4xy
    public static int hasTrips(int[] hand){
        for(int i = ace; i >= 2; i--)
            if (hand[i] == 3)
                return (400 + i);
        return -1;
    }

    //Returns -1 if no two pair, else returns 3xy
    public static int hasTwoPair(int[] hand){
        for(int i = 2; i <= ace; i++)
            if(hand[i] == 2)
                for(int j = i + 1; j <= ace; j++)
                    if(hand[j] == 2)
                        return (300 + j);
        return -1;
    }
    
    //Returns 5xy if straight, else -1
    public static int hasStraight(int[] hand){
        for(int i = 2; i <= 10; i++)
            for(int j = i; j < i + 5; j++){
                if(hand[j] > 1)
                    return -1;
                else if(hand[j] == 0)
                    break;
                else if(hand[j] == 1 && j == i + 4)
                    return (500 + j);
            }
        return -1;
    }
    
    //Returns 6xy if flush, else -1
    public static int hasFlush(int[] hand){
        if(hand[clubs] == 5 || hand[spades] == 5 || hand[hearts] == 5 || hand[diamonds] == 5){
            for(int i = ace; i >= 2; i--)
                if(hand[i] == 1)
                    return (600 + i);
        }
         return -1;
    }

    //Returns 7xy if full house. If it doesnt, then it checks if it has pair, two pair, or trips 
    //and returns corresponding scores. Does this because it checks if it has a pair and a trip 
    //to determine Full house. It reduces the amount of times I have to check for pair or trips
    public static int hasFullHouse(int[] hand){
        int pair = hasPair(hand);
        int trips = hasTrips(hand);
        if(pair != -1 && trips != -1)
            return (700 - 300 + trips);
        else if(pair != -1){
            int twoPair = hasTwoPair(hand);
            if(twoPair == -1)
                return pair;
            else return twoPair;
        }
        else if(trips != -1)
            return trips;
        else return -1;
    }
    
    //Returns 8xy if has fours
    public static int hasFours(int[] hand){
        for(int i = ace; i >= 2; i--)
            if(hand[i] == 4)
                return (800 + i);
        return -1;
    }

    //Returns 900 if straight flush, or returns flush or straight or -1 score
    public static int hasStraightFlush(int[] hand){
        int flush = hasFlush(hand);
        int straight = hasStraight(hand);
        if (flush > -1 && straight > -1)
            return (900 - 500 + straight);
        else if(flush > -1)
            return flush;
        else if (straight > -1)
            return straight;
        else 
            return -1;
    }
    
    // Consolidates all checks. 
    // Goes top down. From straight flush to high card
    // Returns -100 if something goes very wrong.
    public static int determineScore(int[] hand){
        int straightAndOrFlush = hasStraightFlush(hand);
        if (straightAndOrFlush > 900)
            return straightAndOrFlush;
        int four = hasFours(hand);
        if (four != -1)
            return four;
        int fullOrTripsOrPair = hasFullHouse(hand);
        if (fullOrTripsOrPair > 700)
            return fullOrTripsOrPair;
        if (straightAndOrFlush != -1)
            return straightAndOrFlush;
        if(fullOrTripsOrPair != -1)
            return fullOrTripsOrPair;
        
        for(int i = ace; i >= 7; i--)
            if(hand[i] == 1)
                return i;
        return -100;
    }


    public static void main(String[] args){
        try{
            int winner1 = 0;
            int line = 1;
            long start = System.currentTimeMillis();
            BufferedReader br = new BufferedReader(new FileReader("poker.txt"));
            while(br.ready()){
               String strLine = br.readLine();
               int[][] hands = readHands(strLine);
               int[] p1 = hands[0];
               int[] p2 = hands[1];
               int score1 = determineScore(p1);
               int score2 = determineScore(p2);
               if(score1 > score2)
                   winner1++;
               else if(score1 == score2){
                   highCard:
                   for(int i = ace; i >= 2; i--){
                       if(p1[i] != 0 && p2[i] == 0)
                           winner1++;
                        else if(p1[i] == 0 && p2[i] != 0)
                            break highCard;
                   }
                }
               line++;
            }
            System.out.println(winner1);
            long end = System.currentTimeMillis();
            System.out.println(end-start);
            br.close();
        }
        catch(Exception e){
            System.err.println("ERROR" + e.toString());
        }
    }
}
    
// Outputs: 376
// Takes: 73 milliseconds
//
// Im fairly happy with this. Got to learn about try/catch in java and then about bufferedReader, which was cool. 
// Other than that, nothing was too difficult. It just took time coding. 
// Basic method was to score each hand in a xyz fashion. X indicated pair, two pair ...
// yz indicated highest card.
