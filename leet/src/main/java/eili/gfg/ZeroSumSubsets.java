package eili.gfg;

/**
 * https://www.geeksforgeeks.org/number-of-subsets-with-zero-sum/
 *
 * Given an array ‘arr’ consisting of integers, the task is to find the number of subsets such
 * that their sum is equal to zero. Empty subset should also be considered.
 *
 * Example #1:
 * Input : arr[] = {2, 2, -4}
 * Output : 2
 *
 * All possible subsets:
 * {} = 0
 * {2} = 2
 * {2} = 2
 * {-4} = -4
 * {2, 2} = 4
 * {2, -4} = -2
 * {2, -4} = -4
 * {2, 2, -4} = 0
 *
 * Since, {} and {2, 2, -4} are only possible subsets
 * with sum 0, ans will be 2.
 *
 * Example #2:
 * Input : arr[] = {1, 1, 1, 1}
 * Output : 1
 * {} is the only possible subset with
 * sum 0, thus ans equals 1.
 */
public class ZeroSumSubsets {

// base10 => base3
//  125/3 => 41 r2
//   41/3 => 13 r2
//   13/3 => 4  r1
//    4/3 => 1  r1
//    1/3 => 0  r1
//        =  11122
//
//  base2 => base3
//    000 => 0,0
//    001 => 0,1
//    010 => 0,2
//    011 => 1,0
//    100 => 1,1
//    101 => 1,2   1*3+2*1
//    110 => 2,0
//    111 => 2,1

    // {2, 2, -4}
    //x  0  1  2
    //0  0 -4  2
    //1 -2  2 -2
    //2  4  0  x

    // {2, 2, -4, -2}
    public static void main(String[] args) {
        // Sum of Values up to index i (sumAtI)
        //  i: 0  1  2  3  4
        //sum: 0  2  4  0 -2

        // number of subsets from index [0..i-1] that add up to -sumAtI
        // dp[i][S] = # of subsets over [0..i-1] that add up to -sumAtI (S)
    }
}
