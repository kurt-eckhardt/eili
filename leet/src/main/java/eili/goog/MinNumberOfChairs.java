package eili.goog;

import java.util.Arrays;

public class MinNumberOfChairs {

    public static void main(String[] args) {
        System.out.println(maxOverlaps(new int[] {1,2,4,5,3}, new int[] {5,5,7,6,8}));
    }

    public static int maxOverlaps(int[] arrivTime, int[] leaveTime) {

        Arrays.sort(arrivTime);
        Arrays.sort(leaveTime);

        int max  = 0;
        int curr = 0;
        int aIdx = 0;
        int lIdx = 0;
        while (aIdx < arrivTime.length) {
            if (arrivTime[aIdx] < leaveTime[lIdx]) {
                aIdx++;
                curr++;
                max = Math.max(max, curr);
            } else {
                lIdx++;
                curr--;
            }
        }

        return max;
    }
}
