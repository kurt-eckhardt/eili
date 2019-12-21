package eili.leet.leet900;

import java.util.PriorityQueue;

/**
 * Problem #:  0973
 * Name:       K Closest Points to Origin
 * Tags:       Math, Heap, Stack, Quickselect
 * BigO:       O(N)
 * Difficulty: Medium
 * Techniques:
 * Learnings:
 * Approaches:
 * 1) Use two stacks to track closest & max elements.
 * 2) Use quickselect to find the k largest elements.
 *
 * Given a list of points on the plane, find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except
 * for the order that it is in.)
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 */
public class Leet0973 {

    public static void main(String[] args) {
        ;
    }

    // can also use two stacks one to track all values and one to keep max values
    public static int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || K == 0) {
            return new int[][] {{}};
        }

        PriorityQueue<PointDist> maxHeap = new PriorityQueue<PointDist>();
        for (int i=0; i < points.length; i++) {
            PointDist pd = new PointDist(points[i][0], points[i][1]);
            if (maxHeap.size() < K) {
                maxHeap.add(pd);
            } else if (pd.dist < maxHeap.peek().dist) {
                maxHeap.remove();
                maxHeap.add(pd);
            }
        }

        int[][] closest = new int[K][2];
        for (int i=0; i < K; i++) {
            PointDist pd = maxHeap.remove();
            closest[i][0] = pd.x;
            closest[i][1] = pd.y;
        }

        return closest;
    }


    static class PointDist implements Comparable<PointDist> {
        public int x;
        public int y;
        public Double dist;
        public PointDist(int x, int y) {
            this.x = x;
            this.y = y;
            this.dist = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        }

        @Override
        public int compareTo(PointDist o) {
            return -1 * this.dist.compareTo(o.dist);
        }
    }
}
