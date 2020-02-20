package eili.leet.leet600;

import java.util.HashSet;
import java.util.Set;

public class Leet0683 {

    // k=4
    // 3 6 9 1 4 5 7 2 8
    //      3
    //  1       6
    //    2  4     9
    //         5 7
    //            8
    //
    // k=3
    // 3 7 4 1 5 7 2 8
    // 1 2 3 4 5 6 7 8 9
    // 3 3 3 3 3 3 3
    //
    // 3 6 4 1 5 7 2 8
    // 1 2 3 4 5 6 7 8 9
    // 3 3 3 3 3 6 6 6 6
    //
    // 3 5 1 9 ...
    // 1 2 3 4 5 6 7 8 9
    // 3 3 3 3 3 3 3
    //         5
    //
    // 3 8 6
    // 1 2 3 4 5 6 7 8 9 A
    //     3 3 3 3 3
    //               8 8 8
    //           6 3 8 8 8  // don't tell 'A' that 6 exists because 8 is in the way
    //
    // 3 8 6 A
    // 1 2 3 4 5 6 7 8 9 A
    // 3 3 3 3 3 3 3
    //       8 8 8 8 8 8 8
    //       6 6 6

    // Sets of k=3: 1-5, 2-6, 3-7, 4-8, 5-9, 6-A
    // 3 1 8 2 4
    // 1 2 3 4 5 6 7 8 9 A
    // Blooming Set     Start Candidate Set
    //                  1,2,3,4,5,6
    // 3                3,4,5,6
    // 3,1,8            3,4
    // 3,1,8,2          3,4
    // 4,8 match!
    // stop since the start candidate set is empty and we haven't found a matching pair
    //
    // Sets of k=3: 1-5, 2-6, 3-7, 4-8, 5-9, 6-A
    // 3 1 6 8 A
    // 1 2 3 4 5 6 7 8 9 A
    // Blooming Set     Start Candidate Set
    //                  1,2,3,4,5,6
    // 3                3,4,5,6
    // 3,1              3,4,5,6
    // 3,1,6            6
    // 3,1,6,8          []
    // stop since the start candidate set is empty and we haven't found a matching pair

    // Since k=3, we know for any number "n" we are looking for either:
    // 1. n-4 exists and the range [n-3,n-2,n-1] does not exist or,
    // 2. n+4 exists and the range [n+1,n+2,n+3] does not exist.
    // Its easy to check if n-4 or n+4 exist, but how do we quickly determine if the ranges
    // in between do not?
    public static int kEmptySlots(int[] flowers, int k) {
        Set<Integer> blooming = new HashSet<>();
        Set<Integer> canStart = new HashSet<>();

        for (int i=0; i < flowers.length-k; i++) canStart.add(i);

        for (int i=0; i < flowers.length; i++) {
            blooming.add(flowers[i]);

            // Can we start at flowers[i] and end at flowers[i]+k?
            if (canStart.contains(flowers[i]) && blooming.contains(flowers[i]+k+1)) {
                System.out.print("found empty slots from " + flowers[i] + " to " + (flowers[i]+k+1) + " on day=");
                return i;
            }

            // Can we start at flowers[i]-k end at flowers[i]?
            if (canStart.contains(flowers[i]-k-1) && blooming.contains(flowers[i]-k-1)) {
                System.out.print("found empty slots from " + (flowers[i]-k-1) + " to " + flowers[i] + " on day=");
                return i;
            }

            // Remove remaining eligible start positions
            int blockedByI = Math.max(0, flowers[i]-k);
            while (!canStart.isEmpty() && blockedByI < flowers[i]) {
                canStart.remove(blockedByI++);
            }

            if (canStart.isEmpty()) {
                System.out.print("no more valid starting positions.  stopping on day=" + i + " ");
                return -1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        System.out.println(kEmptySlots(new int[] {0,2,1}, 1));
        System.out.println(kEmptySlots(new int[] {0,1,2}, 1));
        System.out.println(kEmptySlots(new int[] {6,10,0,1,2,3,4,5,7,8,9}, 3));
        System.out.println(kEmptySlots(new int[] {3,1,8,2,4,0,5,6,7,8,9}, 3));
        System.out.println(kEmptySlots(new int[] {3,1,6,8,10,0,2,4,5,7,9}, 3));
        System.out.println(kEmptySlots(new int[] {3,1,6,10,8,0,2,4,5,7,9}, 3));
    }
}
