package eili.leet.leet200;

/**
 * Problem #:  0215
 * Name:       Find Kth Largest Element
 * Tags:       Search, Partition, Array
 * BigO:       O(N) Time (best) O(N^2) (worst), O(1) Space
 * Difficulty: Medium
 * Techniques: Quickselect
 * Learnings:
 * 1) Knowing how to write Quicksort partitioning is cruicial.
 *
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest
 * element in the sorted order, not the kth distinct element.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 */
public class Leet0215 {


    public static void main(String[] args) {
        for (int i=1; i < 8; i++) {
            int[] nums = new int[] {1, 0, 2, 7, 2, 1, 4};
            System.out.println("The k=" + i + " largest value="+kthLargest(nums, i));
        }
    }

    public static int kthLargest(int[] nums, int kSmall) {

        int k  = nums.length - kSmall;
        int lo = 0;
        int hi = nums.length-1;
        int placedAt = -1;
        while (placedAt != k) {
            placedAt = partition(nums, lo, hi);
            if (placedAt < k) {
                lo = placedAt+1;
            } else {
                hi = placedAt-1;
            }
        }

        return nums[placedAt];
    }


    public static int partition(int[] nums, int lo, int hi) {

        int insertIdx = lo;
        int pivotVal  = nums[hi];
        for (int i=lo; i < hi; i++) {
            if (nums[i] < pivotVal) {
                swap(nums, i, insertIdx);
                insertIdx++;
            }
        }

        swap(nums, insertIdx, hi);
        return insertIdx;
    }

    public static void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a]  = nums[b];
        nums[b]  = temp;
    }

//    public static int partition(int[] nums, int pivotIdx, int lo, int hi) {
//
//        swap(nums, pivotIdx, hi);
//        int pivotVal  = nums[hi];
//        int insertIdx = lo;
//
//        System.out.println("PivotVal="+pivotVal);
//        for (int i=lo; i < hi; i++) {
//            if (nums[i] < pivotVal) {
//                swap(nums, i, insertIdx);
//                insertIdx++;
//            }
//        }
//
//        swap(nums, insertIdx, hi);
//        return insertIdx;
//    }
}
