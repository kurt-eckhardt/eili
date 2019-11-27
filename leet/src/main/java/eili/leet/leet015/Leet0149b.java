package eili.leet.leet015;

import java.util.HashMap;
import java.util.Map;

public class Leet0149b {

    public static void main(String[] args) {
//        System.out.println(maxPoints(new int[][]{}));
//        System.out.println(maxPoints(new int[][]{{1,1}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{3,2}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{3,2},{3,2}}));
        System.out.println(maxPoints(new int[][]{{1,1},{2,2},{3,3}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}}));
//        System.out.println(maxPoints(new int[][]{{0,0},{1,1},{1,-1}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{2,1},{2,2},{1,4},{3,3}}));
//        System.out.println(maxPoints(new int[][]{{0,0},{94911151,94911150},{94911152,94911151}}));
//
//        System.out.println(maxPoints(new int[][]{{3,10},{0,2},{3,10}}));
//        System.out.println(maxPoints(new int[][]{{0,10},{0,2}}));
//        System.out.println(maxPoints(new int[][]{{10,0},{2,0},{12,7},{4,7}}));
    }

    // Learnings:
    // 1) We don't need to worry about overcounting (i.e. adding points we've already counted)
    //    since we are going to take the maximum we've seen across all iterations.
    //    For example: (0,0) (1,0) (2,0) (3,0)
    //        Starting at (0,0), we would count 4 points on a line.
    //        Starting at (1,0), we would count 3 points on a line since the inner loop starts at i+1 (avoiding 0,0)
    //
    // 2) The original idea to use a Double to track slope *almost* worked, but it was too imprecise for some use
    //    cases {{0,0},{94911151,94911150},{94911152,94911151}}.
    //    To solve this, we can use a stringified version of the slope: key="x/GCD" + "y/GCD".
    //    The GCD works because it reduces all points down to the closest whole number rise/run from the start point.
    //    The GCD even works for x=0 y=INF use cases.
    //
    // 3) Don't remember all points across all iterations.  We only need to comprare max to the current iteration.
    //    This also solves the problem of incorrectly counting points that have the same slope, but are on different
    //    yIntercepts (because we don't remember them across iterations.)
    //
    // 4) GCD will swap the values so that the larger one is always being devided by the smaller one.
    //
    public static int maxPoints(int[][] points) {

        if (points.length < 3) {
            return points.length;
        }

        int max = 0;
        for (int i=0; i < points.length; i++) {

            int dupPointCnt = 0;
            int maxForPoint = 0;
            int startX = points[i][0];
            int startY = points[i][1];
            Map<String, Integer> slopeMap = new HashMap<>();

            for (int j=i+1; j < points.length; j++) {

                int endX = points[j][0];
                int endY = points[j][1];
                int rise = endY - startY;
                int run  = endX - startX;

                if (rise == 0 && run == 0) {
                    dupPointCnt++;
                    continue;
                }

                String slopeAsString = "";
                int gcd  = gcd(rise, run);
                slopeAsString = "Start:("+startX + ","+ startY + ") rise="+(rise/gcd)+",run="+(run/gcd);
                System.out.println(slopeAsString);

                int countForSlope = slopeMap.getOrDefault(slopeAsString, 0) + 1;
                slopeMap.put(slopeAsString, countForSlope);
                maxForPoint = Math.max(maxForPoint, countForSlope);
            }
            max = Math.max(max, maxForPoint + dupPointCnt);
        }

        return max + 1;
    }


    // 25 / 15 = 1  r 10
    // 15 / 10 = 1  r 5
    // 10 /  5 = 2  r 0
    // Note GCD will swap x and y when y > x
    // This is great since GCD requires x > y
    public static int gcd(int x, int y) {

        while (y != 0) {
            int mod = x % y;
            x = y;
            y = mod;
        }

        return x;
    }


    public static int maxPoints2(int[][] points) {
        int res = 0;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> map = new HashMap<>();

            int cnt = 0, dup = 1;

            for (int j = 0; j < i; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                if (x == 0 && y == 0) {
                    dup++;
                    continue;
                }

                int d = gcd(x, y);
                String key = x/d + "," + y/d;
                map.put(key, 1 + map.getOrDefault(key, 0));

                cnt = Math.max(cnt, map.get(key));
            }

            res = Math.max(res, cnt + dup);
        }

        return res;
    }

}
