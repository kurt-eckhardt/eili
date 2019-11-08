package eili.foo;

public class MaxKSubArray {

    // What is the best number to replace in the previous 'k' numbers to maximize the sum?
    // we decide to take or not take an element based on the length of our current subarray.
    // If length is < k, then we either move the left or the right pointer to maximize our value.
    // Otherwise, we are forced to move the left pointer.
    // 5  2  4  0
    // 5 -3  2  1

    //  5  2  7
    //  0  1  2  3  4  5
    //  5,-3, 5, 5,-3, 5
    //
    // sum=2
    //  l      r
    //  1, -2, 3, -2, 1, -2, 3, -2
    //
    //                  l   r sum=-4
    // -2, -3, -1, -2, -3, -1
    public static int maxKSubArray(int[] nums, int k) {

        int lIdx = 0;
        int rIdx = 0;
        int sum  = nums[0];
        int max  = nums[0];
        while (lIdx < nums.length-1) {
            int subarrayLen = rIdx-lIdx+1;
            System.out.println("lIdx="+lIdx + " rIdx="+rIdx + " sum="+sum + " max="+max+ " saLen="+subarrayLen);
            if (rIdx == nums.length-1) {
                sum = sum - nums[lIdx++];
                max = Math.max(max, sum);
            } else if (subarrayLen >= k && k > 1) {
                sum = sum - nums[lIdx++];
                max = Math.max(max, sum);
            } else if (subarrayLen >= k && k == 1) {
                sum = sum - nums[lIdx++];
                sum = sum + nums[++rIdx];
                max = Math.max(max, sum);
            } else if (lIdx == rIdx) {
                sum = sum + nums[++rIdx];
                max = Math.max(max, sum);
            } else if (nums[rIdx] > sum) { //1, -2, 3, -2, 1, -2, 3, -2  [1,-2,3]
                sum = sum - nums[lIdx++];
                max = Math.max(max, sum);
            } else if (subarrayLen < k) {
                sum = sum + nums[++rIdx];
                max = Math.max(max, sum);
            } else {
                System.out.println("Unexpected scenario[lIdx="+lIdx + " rIdx="+rIdx + " sum="+sum + " max="+max+"]");
            }
        }

        return max;
    }
}
