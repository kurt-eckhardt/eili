package eili.leet.leet000;

/**
 * Problem #:  0091
 * Name:       Decode Ways
 * Tags:
 * BigO:       O(N) Time, O(T) Space
 * Difficulty: Medium
 * Techniques: Dynamic Programming, Memoization
 * Learnings:
 * Related:
 */
public class Leet0091 {


    public static void main(String[] args) {
        System.out.println(numDecodings("0"));
        System.out.println(numDecodings("10"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("1491311212311"));

        // 2,2,1,1  22,1,1  2,21,1  2,2,11  22,11
//        System.out.println(numDecodings_orig("0"));
//        System.out.println(numDecodings_orig("10"));
//        System.out.println(numDecodings_orig("226"));
//        System.out.println(numDecodings_orig("1491311212311"));

    }

    public static int numDecodings(String s) {

        char[] nums = s.toCharArray();

        // using integers as memos
        int ways_2 = 0;
        int ways_1 = 0;
        int ways   = 1;

        for (int i = nums.length-1; i >= 0; i--) {
            int numAtI   = nums[i]  -'0';
            int numAtI_1 = (i < nums.length-1) ? nums[i+1]-'0' : Integer.MAX_VALUE;

            ways_2 = ways_1;
            ways_1 = ways;
            if (numAtI == 0) {
                ways = 0;
            } else if (numAtI == 1 || (numAtI == 2 && numAtI_1 <= 6)) {
                ways = ways_1 + ways_2;
            }
        }

        return ways;
    }


    // (1)
    // (1,1)     (11)
    // (1,1,1)   (1,11),  (11, 1)  (treat as 11 + combos of n-2)
    // n=4
    // treat as '1'  combined with (n-1 combos) [(1,1,1), (1,11), (11,1)]
    // (1,1,1,1) (1,1,11) (1,11,1)
    // treat as '11' combined with (n-2 combos) [(1,1), (11)]
    // (11,1,1)  (11,11)
    public static int numDecodings_orig(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] nums = s.toCharArray();
        int[] memos = new int[nums.length];
        memos[nums.length-1] = nums[nums.length-1] != '0' ? 1 : 0;
        for (int i = nums.length-2; i >= 0; i--) {
            int numAtI   = nums[i+0]-'0';
            int numAtI_1 = nums[i+1]-'0';

            if (numAtI > 2 || (numAtI == 2 && numAtI_1 > 6)) {
                memos[i] = memos[i+1];
            } else if (numAtI == 1 || (numAtI == 2 && numAtI_1 <= 6)) {
                if (i < nums.length-2) {
                    memos[i] = memos[i+1] + memos[i+2];
                } else {
                    memos[i] = memos[i+1] + 1;
                }
            }
        }

        return memos[0];
    }
}
