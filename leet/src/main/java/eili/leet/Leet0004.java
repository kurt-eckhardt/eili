package eili.leet;

/**
 * Problem #:  0004
 * Name:       Median of Two Sorted Arrays
 * Tags:       Binary Search
 * Difficulty: Hard
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * Approach:
 * For any sorted array, the median is defined as:
 *
 * if (size is odd)  median = element at (size/2)+1;
 * if (size is even) median = average of elements at (size/2) and (size/2)+1
 *
 * Eg1: [1,2,3,4,5,6,7,8,9] size=9, median = (size/2)+1 = 5
 * Eg2: [1,2,3,4,5,6,7,8]   size=8, median = avg(4,5) = 4.5
 *
 * Now, we can extend this to two sorted arrays A1, A2 by partitioning them
 * into left and right buckets whose total size = (sizeA+sizeB+1)/2.
 *
 * For example:
 * A: [1,3,5,7,9]     size=5
 * B: [2,4,6,8,10,12] size=6
 *                                 M
 * Combined, these are: [1,2,3,4,5,6,7,8,9,10,12]
 *
 * Note the median (M) is the 6th element (5+6+1)/2.  Also note there are
 * exactly 5 elements on the left of M and 5 on the right.
 *
 *    L   P   H
 * A: 1,3,5,7,9       partitionA Index = (LoIdx + HiIdx)/2 = 2
 * B: 2,4,6,8,10,12   partitionB Index = (sizeA+sizeB+1)/2 - partitionA = 4
 *
 *                Lower     Upper
 * A partitions = [1,3]     [5,7,9]
 * B partitions = [2,4,6,8] [10,12]
 *
 * Notice that "Lower" and "Upper" partitions add up to 6 and 5 elements
 * respectively.
 *
 * We have found our central numbers when:
 * max(lowerA) <= min(upperB) AND
 * max(lowerB) <= min(upperA)
 *
 * if (foundCentralNumbers()) {
 *     median for even = max(lowerA,lowerB) for odd
 *     median for odd  = average(max(lowerA,lowerB), min(upperA,upperB))
 * } else if (max(lowerA) > min(upperA)) {
 *     // central numbers are too large so move A partition to the left
 *     hiIdx = partitionA - 1;
 * } else {
 *     // central numbers are too large so move A partition to the right
 *     loIdx = partitionA + 1;
 * }
 *
 */
public class Leet0004 {

    public static void main(String[] args) {
        System.out.println(solution(new int[] {1,3,5,7,9}, new int[] {2,4,6,8,10,12}));
        System.out.println(solution(new int[] {1,3,5,7,9,11}, new int[] {2,4,6,8,10,12}));
    }

    public static double solution(int[] nums1, int[] nums2) {

        int totalElements = nums1.length + nums2.length;
        int lowerPartSize = (totalElements+1) / 2;

        // Ensure numsA is always the smaller of the two arrays
        int[] numsA = nums1.length <= nums2.length ? nums1 : nums2;
        int[] numsB = nums1.length <= nums2.length ? nums2 : nums1;

        // Setup binary search
        int loIdx = 0;
        int hiIdx = numsA.length;
        while (loIdx <= hiIdx) {
            int partitionAIdx = (loIdx + hiIdx) / 2;
            int partitionBIdx = lowerPartSize - partitionAIdx;

            int partitionALowerMax = partitionAIdx > 0 ? numsA[partitionAIdx-1] : Integer.MIN_VALUE;
            int partitionBLowerMax = partitionBIdx > 0 ? numsB[partitionBIdx-1] : Integer.MIN_VALUE;
            int partitionAUpperMin = partitionAIdx < numsA.length ? numsA[partitionAIdx] : Integer.MAX_VALUE;
            int partitionBUpperMin = partitionBIdx < numsB.length ? numsB[partitionBIdx] : Integer.MAX_VALUE;

            if (partitionALowerMax <= partitionBUpperMin &&
                partitionBLowerMax <= partitionAUpperMin) {

                if (totalElements % 2 == 0) {
                    return (double)(Math.max(partitionALowerMax, partitionBLowerMax) +
                                    Math.min(partitionAUpperMin, partitionBUpperMin)) / 2;
                } else {
                    return Math.max(partitionALowerMax, partitionBLowerMax);
                }
            } else if (partitionALowerMax > partitionBUpperMin) {
                hiIdx = partitionAIdx - 1;
            } else {
                loIdx = partitionAIdx + 1;
            }
        }

        return -1;
    }

}
