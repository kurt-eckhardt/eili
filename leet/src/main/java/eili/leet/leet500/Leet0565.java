package eili.leet.leet500;

/**
 * Problem #:  0565
 * Name:       Array Nesting
 * Tags:       Array
 * BigO:       O(N) Time, O(1) Space
 * Difficulty: Medium (Easy?)
 * Techniques: Traversal
 * Learnings:
 *
 * Suppose the first element in S starts with the selection of element A[i] of index = i, the next element
 * in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate
 * element occurs in S.
 *
 * Example 1:
 * Input: A = [5,4,0,3,1,6,2]
 * Output: 4
 * Explanation: A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * One of the longest S[K]:  S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 */
public class Leet0565 {

    public static void main(String[] args) {
        System.out.println(arrayNesting(new int[]{5,4,0,3,1,6,2}));
        System.out.println(arrayNesting(new int[]{0}));
    }

    public static int arrayNesting(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        int maxLen   = 0;
        int curLen   = 0;
        int curIdx   = 0;
        int startIdx = 0;

        while (startIdx < nums.length) {
            int nextIdx = nums[curIdx];
            if (nextIdx != -1) {
                nums[curIdx] = -1;
                maxLen = Math.max(maxLen, ++curLen);
                curIdx = nextIdx;
            } else {
                startIdx++;
                curLen = 0;
                curIdx = startIdx;
            }
        }

        return maxLen;
    }
}
