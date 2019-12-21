package eili.leet.leet000;

import java.util.*;

/**
 *
 * Given a set of non-duplicate candidate numbers (candidates) and a target number,
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Note: All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class Leet0039 {


    // 8 = ways(7) * ways(1)
    //   + ways(6) * ways(2)
    //   + ways(5) * ways(3)
    //   + ways(4) * ways(4)
    // ways(1) = 0
    // ways(2) = ways(1) * ways(1)
    // ways(3) = ways(2) * ways(1) + ways(3)

    // denom, amount
    // waysToUseDenomToMakeAmount[2][0] = 0
    // waysToUseDenomToMakeAmount[2][2] = 1
    // waysToUseDenomToMakeAmount[2][4] = 1 // [2,2]
    // waysToUseDenomToMakeAmount[2][6] = 1 // [2,2,2]
    // waysToUseDenomToMakeAmount[2][8] = 1 // [2,2,2,2]

    // waysToUseDenomToMakeAmount[5][5] = 1 // [2,2,2,2]

    //          0 1 2 3 4 5 6 7 8
    // [2]      0 0 1 0 1 0 1 0 1
    // [2,3]    0 0 1 1 1 1 2 1 2
    // [2,3,5]  0 0 1 1 1 2 2 2 3

    //          0 1 2 3 4 5 6 7 8
    // [2]      0 0 1 0 1 0 1 0 1
    // [2,3]    0 0 1 1 1 1 2 1 2
    // [2,3,5]  0 0 1 1 1 2 2 2 3   // numberOfWaysToMake[7] += numberOfWaysToMake[7-5]


    // [2],[2,2],[2,2,2],[2,2,2,2]
    // [3],[3,3],[3,2],[3,2,2],[3,3,2]
    // [5],[5,2],[5,3]

    public static void main(String[] args) {
//        solution(new int[] {2,3,5}, 8);
        System.out.println(solution(new int[] {2,3,6,7}, 7));
        System.out.println(dfs_solution(new int[] {2,3,6,7}, 7, 0, new ArrayList<>(), new ArrayList<>()));
    }

    public static List<List<Integer>> solution(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0) return new ArrayList<>();

        Arrays.sort(candidates);
        HashMap<Integer, List<List<Integer>>> waysToMake = new HashMap<>();

        for (int num : candidates) {

            // add value to default list
            List<List<Integer>> waysToMakeNum = waysToMake.getOrDefault(num, new ArrayList<>());
            List<Integer> wayToMakeNum  = new ArrayList<>();
            wayToMakeNum.add(num);
            waysToMakeNum.add(wayToMakeNum);
            waysToMake.put(num, waysToMakeNum);

            for (int i=num+1; i <= target; i++) {
                List<List<Integer>> waysToMakeDeltaI = waysToMake.get(i - num);
                if (waysToMakeDeltaI != null) {
                    List<List<Integer>> waysToMakeI = waysToMake.getOrDefault(i, new ArrayList<>());
                    for (List<Integer> wayToMakeDeltaI : waysToMakeDeltaI) {
                        List<Integer> wayToMakeI = new ArrayList<>(wayToMakeDeltaI);
                        wayToMakeI.add(num);
                        waysToMakeI.add(wayToMakeI);
                    }

                    waysToMake.put(i, waysToMakeI);
                }
            }
        }

        return waysToMake.getOrDefault(target, new ArrayList<>());
    }


    public static List<List<Integer>> dfs_solution(int[] candidates, int target, int startIdx, List<Integer> path, List<List<Integer>> ans) {

        if (target == 0) {
            System.out.println("Adding: " + path);
            ans.add(new ArrayList<>(path));
            return ans;
        }

        for (int i=startIdx; i < candidates.length; i++) {
            System.out.println("Looking at candidate=" + candidates[i] + " startIdx=" + startIdx);
            if (candidates[i] > target) {
                return ans;
            } else {
                path.add(candidates[i]);
                dfs_solution(candidates, target-candidates[i], i, path, ans);
                path.remove(path.size()-1);
            }
        }

        return ans;
    }

}
