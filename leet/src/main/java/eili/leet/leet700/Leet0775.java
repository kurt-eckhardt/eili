package eili.leet.leet700;


/**
 * Problem #:  0775
 * Name:       Global and Local Inversions
 * Tags:       Array, Math
 * BigO:       Time O(N), Space O(1)
 * Difficulty: Medium
 * Techniques: Define and Conquer
 * Learnings:  Think about how to define the critical aspect of the problem.  Build an
 *             algorithm around this definition.
 *
 *             For example, in this case a "Global Inversion" means a number A[i] > A[j]
 *             and they are separated by *at least* 1 array element.  In cases where it is
 *             exactly 1 element, we also have a local inversion.  So, if we can find any
 *             global inversion where the elements are more than 1 index apart, then we
 *             will have more gloal inversions than local ones.
 *
 * Problem Description:
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 *
 * Example 1:
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 *
 * Example 2:
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 */
public class Leet0775 {

    public boolean isIdealPermutation(int[] A) {
        for (int i=0; i < A.length; i++) {
            if (Math.abs(i-A[i])-1 > 0) return false;
        }
        return true;
    }
}
