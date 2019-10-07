import java.util.Random;


//1111110101010111101011110010100100000010101110111010111001110100111000010101100101000001101101001000

// next overflow = 0
//
// map(7) -> ?
// map(6) -> ?
// overflow = 2

public class Rand {


    public static void main(String[] args) {
        int max = 7;
        int[] histog = new int[max+1];

        for (int i=0; i < 1000000; i++) {
            int randval = rand7_take2();
            histog[randval]++;
        }

        for (int i=0; i < max+1; i++) {
            System.out.println(i + " count="+histog[i]);
        }
    }

    // given rand5, return rand7
    public static int rand7() {
        Random rand5 = new Random();
        // 001
        // 010
        // 011
        // 100 (treat as 00)
        // 101 => throw away


        // how many permutations can be made choosing 2 numbers from [1-7] (with replacement)
        // how many ways to make 4 by adding two numbers in range [1-7] ?
        // 1+3,3+1,2+2 (3)
        int loBits=5, hiBits=5;
        while (loBits == 5) loBits = rand5.nextInt(5)+1;
        while (hiBits == 5) hiBits = rand5.nextInt(5)+1;
        return ((hiBits & 0x3) << 1) | (loBits & 0x1);
    }


//    int[] results = new int[] {
//             0,  1,  2,  3,  4,
//             5,  6,  7,  8,  9,
//            10, 11, 12, 13, 14,
//            15, 16, 17, 18, 19,
//            20,  0,  1,  2,  3
//    };
    // there are 25 combiations using 2 rolls of 0-4 (5^2)
    public static int rand7_take2() {
        Random rand5 = new Random();
        while (true) {
            // keep rolling while roll1 == 4 && roll2 > 0 (or calculated value is > 21)
            // while (roll1 == 4 && roll2 > 0) {
            int roll1 = rand5.nextInt(5); // 0-4
            int roll2 = rand5.nextInt(5); // 0-4
            int value = ((roll1 * 5 + roll2) + 1);
            if (value <= 21) return (value % 7);
        }
    }

    // Generates numbers from 0 to max (inclusive)
    public static int rand(int max) {
        Random rand7 = new Random();
        while (true) {
            int rand = Math.abs(rand7.nextInt(7));
            if (rand <= max) return rand;
        }
    }

    private static void test1() {
        Random rand = new Random();
        int[] histo = new int[36];
        for (int i=0; i < 1000000; i++) {
            int sum = 0;
            for (int j=0; j < 5; j++) {
                // random number between 1-7
                sum = sum + (rand.nextInt(7) + 1);
            }
            histo[sum]++;
        }

        for (int i=5; i <= 35; i++) {
            System.out.println(i + "  count: " + histo[i] + "  pct: "+ (histo[i]*100/1000000) + "%");
        }
    }


    private static void test2() {
        Random rand7 = new Random();
        int[] bits   = new int[6];
        int[] histo  = new int[4];
        int bit00=0,bit01=0,bit10=0,bit11=0;
        int numTimesSeen1 = 0;
        for (int iters=0; iters < 1000000; iters++) {

            // load buffer
            for (int i=0; i < 2; i++) {
                int nextInt = rand7.nextInt(7)+1;
                for (int j=0; j < 3; j++) {
                    int bitIdx = (i*3) + j;
                    int bitVal = (nextInt >> j) & 0x1;
                    numTimesSeen1 += bitVal;
                    if (numTimesSeen1 == 8) {
                        numTimesSeen1 = 0;
                        bitVal = 0;
                    }
                    bits[bitIdx] = bitVal;
                }
            }

            // read in groups of 2
            for (int i=0; i < 6; i+=2) {
                int bit0 = bits[i+0];
                int bit1 = bits[i+1];
                if (bit0 == 0) bit00++; else bit01++;
                if (bit1 == 0) bit10++; else bit11++;
                int val  = (bit1 << 1) | bit0;
                histo[val]++;
            }
        }

        System.out.println("bit00="+bit00);
        System.out.println("bit01="+bit01);
        System.out.println("bit10="+bit10);
        System.out.println("bit11="+bit11);
        System.out.println("Histogram");
        for (int i=0; i < 4; i++) {
            System.out.println(i + " count=" + histo[i] + " pct="+(histo[i]*100/3000000));
        }
    }

}
