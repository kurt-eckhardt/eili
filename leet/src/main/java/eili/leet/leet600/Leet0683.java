package eili.leet.leet600;

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
    // Since k=3, we know for any number "n" we are looking for either:
    // 1. n-4 exists and the range [n-3,n-2,n-1] does not exist or,
    // 2. n+4 exists and the range [n+1,n+2,n+3] does not exist.
    // Its easy to check if n-4 or n+4 exist, but how do we quickly determine if the ranges
    // in between do not?
    public int kEmptySlots(int[] flowers, int k) {

    }
}
