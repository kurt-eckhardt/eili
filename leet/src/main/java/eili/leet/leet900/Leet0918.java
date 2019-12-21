package eili.leet.leet900;

/**
 * Problem #:  0918
 * Name:       Maximum Sum Circular Subarray
 * Tags:       Subarray, Kadane's, Array
 * BigO:       O(N) Time, (1) Space
 * Difficulty: Medium (Hard?)
 * Techniques: Dynamic Programming
 * Learnings:
 * 1) When max is split across the circular boundary, we take the total sum and subtract
 * out the largest minimum value to get the remaining largest max!
 *
 * Case #1: Max is somewhere in the single interval (Kadane's)
 * ----------------------------------
 * |            Max Sum             |
 * ----------------------------------
 *
 * Case #2: Max is split across the circular boundary.  In this case, we take the total
 * sum and subtract out the largest minimum.
 * ----------------------------------
 * |-Sum-->|<----Min Sum--->|<--Max-|
 * ----------------------------------
 *
 *
 * Given a circular array C of integers represented by A, find the maximum
 * possible sum of a non-empty subarray of C.
 *
 * Here, a circular array means the end of the array connects to the beginning of the array.
 * Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.
 *
 * Also, a subarray may only include each element of the fixed buffer A at most
 * once.  Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist
 * i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
 *
 * Example 1:
 * Input: [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 *
 * Example 2:
 * Input: [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 *
 * Example 3:
 * Input: [3,-1,2,-1]
 * Output: 4
 * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
 *
 * Example 4:
 * Input: [3,-2,2,-3]
 * Output: 3
 * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
 *
 * Example 5:
 * Input: [-2,-3,-1]
 * Output: -1
 * Explanation: Subarray [-1] has maximum sum -1
 *
 *
 * Approach:
 * The Maxium subarray sum is either in the single one interval array, or it spans across the circuluar boundary.
 * 1) In case #1, we can just use Kadane's algorithm to find the maximum subarray sum.
 * 2) In case #2, we need to realize #1 wasn't the max, because there must be a largest "MINIMUM" subarray somewhere in the middle.
 *    To get rid of this, we take the total of all the elements, and then subtract out the largest Minimum.
 *
 * So, our final answer is the Math.max(case #1 answer, case #2 answer):
 * For example:
 * Case #1: The largest single interval sum we can make is 5519 using Kadane's algorithm.
 * Case #2: The largest minimum value we can make is -2633.  Total of all sums=(-291+5519-2633+575)=3170
 *          If we remove this minimum, then the largest contiguous value we can make that spans across the circular boundary=3170+2633=5803
 *
 * So, the answer is Math.max(5519,5803) = 5803
 *
 *
 * 52+183+124+154-170-191-240+107-178+171+75+186-125+61-298+284+21-73-294+253+
 * 146+248-248+127+26+289+118-22-300+26-116-113-44+29+252-278+47+254-106+246
 * -275+42+257+15+96-298-69-104-239-95-4+76-202+156-14-178+188-84+78-195-125+   //-291
 *
 * 28+109+125-25-53+58+287+55-296+198+281+53-160+146+298+25-41-3+27-242+169+287-281+19+91+213+115+211-218+124-25-272+278+296-177-166-192+97-49-25+168-81+6-94+267+293+146-1-258+256+283-156+197+28+78+267-151-230-66+100-94-66-123+121-214-182+187+65-186+215+273+243-99-76+178+59+190+279+300+217+67-117+170+163+153-37-147-251+296-176+117+68+258-159-300-36-91-60+195-293-116+208+175-100-97+188+79-270+80+100+211+112+264-217-142+5+105+171-264-247+138+275+227-86+30-219+153+10-66+267+22-56-70-234-66+89+182+110-146+162
 * -48-201-240-225-15-275+129-117+28+150+84-264+249-85+70-140-259+26+162+5-203+
 * 143+184+101+140+207+131+177+274-178-79+14-36+104+52+31+257+273-52+74+276+104  //5519
 *
 * -133-255+188-252+229+200-74-39-250+142-201-196-43-40+255-149-299-197-175-96-155-196-24+12+79+71-144-59-120+227-256-163-297+116+286-283-31-221-41+121-170+   //-2633
 *
 * 160+205+8+88+25-272-107+292-180+299+94-97-81-134+37+238   //+575
 */
public class Leet0918 {

    public static int maxSumCircularSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int totSum  = nums[0];
        int maxSum  = nums[0];
        int minSum  = nums[0];
        int currMax = nums[0];
        int currMin = nums[0];
        for (int i=1; i < nums.length; i++) {
            currMax = Math.max(currMax+nums[i], nums[i]);
            currMin = Math.min(currMin+nums[i], nums[i]);
            maxSum  = Math.max(maxSum, currMax);
            minSum  = Math.min(minSum, currMin);
            totSum += nums[i];
        }

        return maxSum < 0 ? maxSum : Math.max(totSum-minSum, maxSum);
    }



    public static void main(String[] args) {
        System.out.println(maxSumCircularSubArray(new int[] {-5,3,5}));
        System.out.println(maxSumCircularSubArray(new int[] {-5,3,5}));
        System.out.println(maxSumCircularSubArray(new int[] {5,-3,5}));
        System.out.println(maxSumCircularSubArray(new int[] {5,-3,2,1}));
        System.out.println(maxSumCircularSubArray(new int[] {17}));
        System.out.println(maxSumCircularSubArray(new int[] {1,-2,3,-2}));
        System.out.println(maxSumCircularSubArray(new int[] {-2,-3,-1}));
        System.out.println(maxSumCircularSubArray(new int[] {52,183,124,154,-170,-191,-240,107,-178,171,75,186,-125,61,-298,284,21,-73,-294,253,146,248,-248,127,26,289,118,-22,-300,26,-116,-113,-44,29,252,-278,47,254,-106,246,-275,42,257,15,96,-298,-69,-104,-239,-95,-4,76,-202,156,-14,-178,188,-84,78,-195,-125,28,109,125,-25,-53,58,287,55,-296,198,281,53,-160,146,298,25,-41,-3,27,-242,169,287,-281,19,91,213,115,211,-218,124,-25,-272,278,296,-177,-166,-192,97,-49,-25,168,-81,6,-94,267,293,146,-1,-258,256,283,-156,197,28,78,267,-151,-230,-66,100,-94,-66,-123,121,-214,-182,187,65,-186,215,273,243,-99,-76,178,59,190,279,300,217,67,-117,170,163,153,-37,-147,-251,296,-176,117,68,258,-159,-300,-36,-91,-60,195,-293,-116,208,175,-100,-97,188,79,-270,80,100,211,112,264,-217,-142,5,105,171,-264,-247,138,275,227,-86,30,-219,153,10,-66,267,22,-56,-70,-234,-66,89,182,110,-146,162,-48,-201,-240,-225,-15,-275,129,-117,28,150,84,-264,249,-85,70,-140,-259,26,162,5,-203,143,184,101,140,207,131,177,274,-178,-79,14,-36,104,52,31,257,273,-52,74,276,104,-133,-255,188,-252,229,200,-74,-39,-250,142,-201,-196,-43,-40,255,-149,-299,-197,-175,-96,-155,-196,-24,12,79,71,-144,-59,-120,227,-256,-163,-297,116,286,-283,-31,-221,-41,121,-170,160,205,8,88,25,-272,-107,292,-180,299,94,-97,-81,-134,37,238}));
    }



    /**
     * Only computes (after much headbanging), the single interval solution
     *
     * @param nums
     * @return
     */
//    public static int wrong_maxSumCircularSubArray(int[] nums) {
//
//        int lIdx = 0;
//        int rIdx = 0;
//        int sum  = nums[0];
//        int max  = nums[0];
//        int k    = nums.length;
//        while (lIdx < nums.length*2-1) {
//            int subarrayLen = rIdx-lIdx+1;
////            System.out.println("lIdx="+lIdx + " rIdx="+rIdx + " sum="+sum + " max="+max+ " saLen="+subarrayLen);
//            if (rIdx == nums.length*2-1) {
//                sum = sum - nums[lIdx++ % nums.length];
//                max = Math.max(max, sum);
//            } else if (subarrayLen >= k && k > 1) {
//                sum = sum - nums[lIdx++ % nums.length];
//                max = Math.max(max, sum);
//            } else if (subarrayLen >= k && k == 1) {
//                sum = sum - nums[lIdx++ % nums.length];
//                sum = sum + nums[++rIdx % nums.length];
//                max = Math.max(max, sum);
//            } else if (lIdx == rIdx) {
//                sum = sum + nums[++rIdx % nums.length];
//                max = Math.max(max, sum);
//            } else if (nums[lIdx % nums.length] < 0) {
//                sum = sum - nums[lIdx++ % nums.length];
//                max = Math.max(max, sum);
//            } else if (nums[rIdx % nums.length] > sum) { //1, -2, 3, -2, 1, -2, 3, -2  [1,-2,3]
//                sum = sum - nums[lIdx++ % nums.length];
//                max = Math.max(max, sum);
//            } else if (subarrayLen < k) {
//                sum = sum + nums[++rIdx % nums.length];
//                max = Math.max(max, sum);
//            } else {
//                System.out.println("Unexpected scenario[lIdx="+lIdx + " rIdx="+rIdx + " sum="+sum + " max="+max+"]");
//            }
//        }
//
//        return max;
//    }
}
