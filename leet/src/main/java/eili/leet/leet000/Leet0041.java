package eili.leet.leet000;

/**
 * Problem #:  41
 * Name:       First Missing Positive
 * Tags:       Array, Logic
 * BigO:       O(N) Time, O(1) Space (Extra)
 * Difficulty: Hard
 * Techniques: Count Sort
 * Learnings:
 * Approach:
 * The insight here is that each number in the array must fall within the size of the array.
 * For example, if the size of the array is 20, then the first missing positive integer has
 * to be [1..21] (note 21 is the case where all the given numbers fall within the size of
 * the array).
 *
 * So, to figure out which number is missing, we place each number into the index that cooresponds
 * to the numbers's value+1 (similar to a modified count sort).  If the number falls outside the
 * range of the array then it can't be a missing value.
 *
 * Finally, we run through the array to find which index didn't have a corresponding value.
 * In the case all values are present then the first one "missing" is the length of the array+1.
 */
public class Leet0041 {

    public static int firstMissingPositive(int[] nums) {

        for (int i=0; i < nums.length; i++) {
            int insertIdx = nums[i]-1;
            if (insertIdx >= 0 && insertIdx < nums.length && nums[insertIdx] != nums[i]) {
                int temp = nums[insertIdx];
                nums[insertIdx] = nums[i];
                nums[i] = temp;
                i--;
            }
        }

        for (int i=0; i < nums.length; i++) {
            if (nums[i]-1 != i) return i+1;
        }

        return nums.length+1;
    }


    public static int firstMissingPositive_orig(int[] nums) {

        int min=Integer.MAX_VALUE;
        for (int i=0; i < nums.length; i++) {
            min = Math.max(1, Math.min(min, nums[i]));
        }


        for (int i=0; i < nums.length; i++) {
            int insertIdx = nums[i] - min;
            if (insertIdx >= 0 && insertIdx < nums.length) {
                if (nums[insertIdx] != nums[i]) {
                    int temp = nums[insertIdx];
                    nums[insertIdx] = nums[i];
                    nums[i] = temp;
                    i--;
                }
            }
        }

//        for (int num : nums) System.out.print(num + " ");
//        System.out.println();
//
        for (int i=0; i < nums.length; i++) {
            if (nums[i]-1 != i) return i+1;
        }

        return nums.length+1;
    }


    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[] {6}));
        System.out.println(firstMissingPositive(new int[] {-1}));
        System.out.println(firstMissingPositive(new int[] {0}));
        System.out.println(firstMissingPositive(new int[] {0, 2, 3}));
        System.out.println(firstMissingPositive(new int[] {1, 2, 0}));
        System.out.println(firstMissingPositive(new int[] {3,4,-1,1}));
        System.out.println(firstMissingPositive(new int[] {7,8,9,11,12}));
        System.out.println(firstMissingPositive(new int[] {1,2,3,4}));
        System.out.println(firstMissingPositive(new int[] {0,1,2,3}));
    }

}
