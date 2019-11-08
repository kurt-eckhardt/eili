package eili.gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Learnings:
 * 1) We can precompute sums up to a position in order to calculate the intermediate sums
 *    For example: sum a[1]..a[7]=28,  sum a[1]..a[4]=10  so, sum of subarray a[4]...a[7] = sum[7]-sum[4] (18)
 */
public class KthLargestSumSubArray {

    // Driver Code
    public static void main(String[] args) {
//        int a[] = new int[]{10, -10, 20, -40};
        int a[] = new int[] {1,2,3,4,5,6,7,8,9};
        int k = 6; //14152;

        // calls the function to find out the
        // k-th largest sum
        long started1 = System.currentTimeMillis();
        System.out.println(kthLargestSum(a, a.length, k));
        long elapsed1 = System.currentTimeMillis() - started1;
        long started2 = System.currentTimeMillis();
        System.out.println(kthLargestSum2(a, a.length, k));
        long elapsed2 = System.currentTimeMillis() - started2;
        System.out.println("Elapsed1="+elapsed1 + " Elapsed2="+elapsed2);
    }


    // function to calculate kth largest
    // element in contiguous subarray sum
    public static int kthLargestSum(int arr[], int n, int k) {

        // array to store sums of subarrays from element 0 up to i
        // used to compute the subs of all subarrays in O(N^2) time
        int sum[] = new int[n + 1];
        sum[0] = 0;
        sum[1] = arr[0];
        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        // priority_queue of min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

//        List<Integer> sums = new ArrayList<>();

        // loop to calculate the contigous subarray
        // sum position-wise
        for (int i = 1; i <= n; i++) {

            // loop to traverse all positions that
            // form contiguous subarray
            for (int j = i; j <= n; j++) {

                // calculates the contiguous subarray
                // sum from j to i index
                int aSubArraySum = sum[j] - sum[i - 1];
//                sums.add(aSubArraySum);

                // if queue has less then k elements,
                // then simply push it
                if (minHeap.size() < k) {
                    minHeap.add(aSubArraySum);

                } else {
                    // add x if it larger than our smallest element
                    // since the item on top of the heap can no longer
                    // be in our 'k' largest.
                    if (minHeap.peek() < aSubArraySum) {
                        minHeap.poll();
                        minHeap.add(aSubArraySum);
                    }
                }
            }
        }

//        Integer[] sorted = sums.toArray(new Integer[0]);
//        Arrays.sort(sorted);
//        for (int anInt : sorted) {
//            System.out.print(anInt+",");
//        }
//        System.out.println("Total Elements="+sums.size());


        // the top element will be then kth
        // largest element
        return minHeap.poll();
    }


    public static int kthLargestSum2(int arr[], int n, int k) {

        // priority_queue of min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//        List<Integer> sums = new ArrayList<>();

        // loop to calculate the contigous subarray
        // sum position-wise
        for (int sIdx=0; sIdx < arr.length; sIdx++) {
            for (int eIdx=sIdx; eIdx < arr.length; eIdx++) {
                int aSubArraySum = 0;
                for (int i=sIdx; i <= eIdx; i++) {
                    aSubArraySum = aSubArraySum + arr[i];
                }
//                sums.add(aSubArraySum);
                if (minHeap.size() < k) {
                    minHeap.add(aSubArraySum);
                } else {
                    // add x if it larger than our smallest element
                    // since the item on top of the heap can no longer
                    // be in our 'k' largest.
                    if (minHeap.peek() < aSubArraySum) {
                        minHeap.poll();
                        minHeap.add(aSubArraySum);
                    }
                }
            }
        }

//        Integer[] sorted = sums.toArray(new Integer[0]);
//        Arrays.sort(sorted);
//        for (int anInt : sorted) {
//            System.out.print(anInt+",");
//        }
//        System.out.println("Total Elements="+sums.size());

        // the top element will be then kth
        // largest element
        return minHeap.poll();
    }
}