package eili.leet.leet000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Problem #: 0090
 */
public class Leet0090 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        if (nums == null) return new ArrayList<>();
        Arrays.sort(nums);

        List<List<Integer>> powerset = new ArrayList<>();
        powerset.add(new ArrayList<>());

        HashSet<List<Integer>> seen = new HashSet<>();
        for (int aNum : nums) {
            List<List<Integer>> newsets = new ArrayList<>();
            int numpowersets = powerset.size();
            for (int i=0; i < numpowersets; i++) {
                List<Integer> newset = new ArrayList<>(powerset.get(i));
                newset.add(aNum);
                if (!seen.contains(newset)) {
                    seen.add(newset);
                    powerset.add(newset);
                }
            }
        }

        return powerset;
    }


    public List<List<Integer>> subsetsWithDup2(int[] nums) {

        if (nums == null) return new ArrayList<>();
        Arrays.sort(nums);

        List<List<Integer>> powerset = new ArrayList<>();
        powerset.add(new ArrayList<>());

        HashSet<List<Integer>> seen = new HashSet<>();
        for (int aNum : nums) {
            List<List<Integer>> newsets = new ArrayList<>();
            int numpowersets = powerset.size();
            for (int i=0; i < numpowersets; i++) {
                List<Integer> newset = new ArrayList<>(powerset.get(i));
                newset.add(aNum);
                if (!seen.contains(newset)) {
                    seen.add(newset);
                    powerset.add(newset);
                }
            }
        }

        return powerset;
    }
}
