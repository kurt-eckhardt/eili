import java.util.HashMap;

/**
 * Problem #:  0001
 * Name:       Two Sum
 * Tags:       HashMap
 * Difficulty: Easy
 *
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * Approach #1: HashMap
 * [-3, 2, 3, 4, 5, 10, 13, 14, -8, 20, 21, 22]  target=14
 *
 * Approach #2: walk two pointers inward until they sum to target (works for sorted list only)
 * [-3, 2, 3, 4, 5, 10, 13, 14, 16, 20, 21, 22]  target=-3 // -3+needed=-3 needed=target-num
 * needed = 5-(-3) = 8
 */
public class Leet0001 {

    public static int[] solution(int[] nums, int target) {

        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int atIndex=0; atIndex < nums.length; atIndex++) {
            int valAtIndex = nums[atIndex];
            int difference = target - valAtIndex;
            if (seen.containsKey(difference)) {
                return new int[] {seen.get(difference), atIndex};
            } else {
                seen.put(valAtIndex, atIndex);
            }
        }

        return null;
    }
}
