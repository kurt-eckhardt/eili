package eili.leet.leet004;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Learnings:
 * 1) Sometimes we just have to use top-down approach.
 *
 * 2) Wow!!!
 * Set startIdx=0   to get permutations of candidates that sum to target
 * Set startIdx=i   to get combinations of candidates that sum to target
 * Set startIdx=i+1 to get combinations of candidates that sum to target without replacing candidate
 *
 * 3) We can use "if (i > startIdx && candidates[i] == candidates[i-1])" to prevent duplicates
 * The above code just skips over the current number if it is the same as the last one.
 */
public class Leet0040 {

    public static void main(String[] args) {
        System.out.println(dfs_solution(new int[] {1,2,2,2,5}, 5, 0, new ArrayList<>(), new ArrayList<>()));
        System.out.println(dfs_stack_solution(new int[] {1,2,2,2,5}, 5));
    }


    // Wow!!!
    // Set startIdx=0   to get permutations of candidates that sum to target
    // Set startIdx=i   to get combinations of candidates that sum to target
    // set startIdx=i+1 to get combinations of candidates that sum to target without replacing candidate
    public static List<List<Integer>> dfs_solution(int[] candidates, int target, int startIdx, List<Integer> path, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return ans;
        }

        for (int i=startIdx; i < candidates.length; i++) {
            if (candidates[i] > target) {
                return ans;
            } else if (i > startIdx && candidates[i] == candidates[i-1]) {
                // skip this candidate to avoid dupliates;
            } else {
                path.add(candidates[i]);
                dfs_solution(candidates, target-candidates[i], i+1, path, ans);
                path.remove(path.size()-1);
            }
        }

        return ans;
    }



    public static List<List<Integer>> dfs_stack_solution(int[] candidates, int realTarget) {

        List<List<Integer>> ans = new ArrayList<>();
        ArrayDeque<SearchState> stack = new ArrayDeque<>();
        stack.push(new SearchState(realTarget, 0, new ArrayList<>()));

        while (!stack.isEmpty()) {
            SearchState state = stack.pop();
            if (state.target == 0) {
                ans.add(state.path);
                continue;
            }

            for (int i=state.startIdx; i < candidates.length; i++) {
                if (candidates[i] > state.target) {
                    continue;
                } else if (i > state.startIdx && candidates[i] == candidates[i-1]) {
                    // skip this candidate to avoid dupliates;
                } else {
                    List<Integer> newStatePath = new ArrayList<>(state.path);
                    newStatePath.add(candidates[i]);
                    SearchState newSS = new SearchState(state.target-candidates[i], i+1, newStatePath);
                    System.out.println("Adding SearchState: " + newSS);
                    stack.push(newSS);
                }
            }
        }

        return ans;
    }

    static class SearchState {
        int target;
        int startIdx;
        List<Integer> path;
        public SearchState(int target, int startIdx, List<Integer> path) {
            this.target   = target;
            this.startIdx = startIdx;
            this.path     = path;
        }

        public String toString() {
            return "target="+ target + " startIdx=" + startIdx + " path="+ path;
        }
    }



    // DP Solution??
//    // [1,2,2,2,5], target = 5,
//    public static void comboSumsII(List<List<Integer>> target) {
//
//        //            0  1  2  3  4  5
//        // [1]        0  1  0  0  0  0
//        // [1,2]      0  1  1  1  0  0
//        // [1,2,2]    0  1  2  1  1  1
//        // [1,2,2,2]  0  1  3  1
//
//        // [1],
//        // [1], [2], [1,2]
//        // [1], [2], [1,2], [2], [1,2], [2,2], [1,2,2]
//        // [1], [2], [1,2], [2], [1,2], [2,2], [1,2,2], [2], [1,2], [2,2], [1,2,2], [2,2], [1,2,2], [2,2,2], [1,2,2,2]
//        // [1], [2], [1,2], [2,2], [1,2,2], [2,2,2], [1,2,2,2]
//
//        // [1], [2], [1,2], [2,2], [1,2,2], [2,2,2], [1,2,2,2], [5], [1,5], [2,5], [1,2,5], [2,2,5], [1,2,2,5], [2,2,2,5], [1,2,2,2,5]
//
//
//        //            0  1  2  3  4  5
//        // [1]        0  1  1  1  1  1
//        // [1,2]      0  1  2  2  3  3
//        // [1,2,5]    0  1  2  2  3  4
//
//
//    }
}
