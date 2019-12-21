package eili.leet.leet700;

import java.util.ArrayDeque;

/**
 * Problem #:  0739
 * Name:       Daily Temperatures
 * Tags:       Monotonic Stack
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Medium
 * Techniques: Monotonic Stack, Memoization
 * Learnings:
 *
 * Given a list of daily temperatures T, return a list such that, for each day in the
 * input, tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature
 * will be an integer in the range [30, 100].
 *
 */
public class Leet0739 {

    public static void main(String[] args) {
        int[] temps = new int[] {73,74,75,71,69,72,76,73};
        int[] daysToWait = dailyTemperatures(temps);
        for (int i=0; i < temps.length; i++) {
            int nextHotter = daysToWait[i] == 0 ? -1 : temps[i+daysToWait[i]];
            System.out.println("day="+i + " temp="+temps[i] + " next hotter day="+nextHotter);
        }
    }


    /**
     * Approach is to keep a monotonic stack to track which temps have not seen a greater one.
     * Instead of tracking the temps directly, though, we store their indexes so that we can
     * calculate how many days have passed since the temp was recorded.
     *
     * @param temps
     * @return
     */
    public static int[] dailyTemperatures(int[] temps) {

        int[] daysToWait = new int[temps.length];
        ArrayDeque<Integer> tStack = new ArrayDeque<Integer>();
        for (int i=0; i < temps.length; i++) {
            while (!tStack.isEmpty() && temps[i] > temps[tStack.peek()]) {
                daysToWait[tStack.peek()] = i-tStack.pop();
            }
            tStack.push(i);
        }

        return daysToWait;
    }
}
