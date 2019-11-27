package eili.leet.leet015;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The core of the solution is sound, but there are too many edge cases
 * that would be good to discuss during an interview.  Not really realistic
 * to solve all of them in a 45 minute interview.
 */
public class Leet0149 {

    public static void main(String[] args) {
//        System.out.println(maxPoints(new int[][]{}));
//        System.out.println(maxPoints(new int[][]{{1,1}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{3,2}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{3,2},{3,2}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}}));
//        System.out.println(maxPoints(new int[][]{{0,0},{1,1},{1,-1}}));
//        System.out.println(maxPoints(new int[][]{{1,1},{2,1},{2,2},{1,4},{3,3}}));
//        System.out.println(maxPoints(new int[][]{{0,0},{94911151,94911150},{94911152,94911151}}));
        System.out.println(maxPoints(new int[][]{{3,10},{0,2},{3,10}}));

        //BigDecimal.valueOf(a.y).subtract(slope.multiply(BigDecimal.valueOf(a.x)));
        // 10-2.666*3 =
    }


    public static int maxPoints(int[][] points) {

        if (points == null) {
            return 0;
        } else if (points.length <= 1) {
            return points.length;
        }

        // convert raw data to points
        int numPoints  = points.length;
        Point[] pointA = new Point[numPoints];
        for (int i=0; i < numPoints; i++) {
            pointA[i] = new Point(i, points[i][0],points[i][1]);
        }


        Map<Line, Set<Point>> lineMap = new HashMap<>();
        for (int i=0; i < pointA.length; i++) {
            Point start = pointA[i];
            for (int j=i+1; j < points.length; j++) {
                Point end = pointA[j];
                Line line = new Line(start, end);
                Set<Point> pointsOnLine = lineMap.getOrDefault(line, new HashSet<Point>());
                pointsOnLine.add(start);
                pointsOnLine.add(end);
                lineMap.put(line, pointsOnLine);
            }
        }

        int max = 0;
        for (Line line : lineMap.keySet()) {
            System.out.println("Line["+line+"] points="+lineMap.get(line));
            max = Math.max(max, lineMap.get(line).size());
        }

        return max;
    }



    static class Point {
        int i;
        int x;
        int y;
        public Point(int i, int x, int y) {
            this.i=i;
            this.x=x;
            this.y=y;
        }

        @Override
        public int hashCode() {
            return this.i;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point op = (Point)o;
                return this.i == op.i;
//                return op.x == this.x && op.y == this.y;
            } else {
                return false;
            }
        }

        public String toString() {
            return "("+x+","+y+")";
        }
    }


    static class Line {

        public static final BigDecimal INF_SLOPE = BigDecimal.valueOf(Integer.MAX_VALUE);

        protected BigDecimal slope;
        protected BigDecimal yInter;
        protected Integer    xInter;

        public Line(Point a, Point b) {
            this.slope  = calcSlope(a,b);
            this.yInter = calcYInter(a,slope);
            this.xInter = slope.equals(INF_SLOPE) ? a.x : Integer.MIN_VALUE;
        }

        public boolean isOnLine(Point a) {
            return slope.multiply(BigDecimal.valueOf(a.x)).add(yInter).equals(BigDecimal.valueOf(a.y));
        }

        public static BigDecimal calcSlope(Point a, Point b) {
            if (a.x == b.x) {
                return INF_SLOPE;
            } else {
                return BigDecimal.valueOf(a.y-b.y).divide(BigDecimal.valueOf(a.x-b.x), new MathContext(15));
            }
        }

        public static BigDecimal calcYInter(Point a, BigDecimal slope) {
            if (slope.equals(INF_SLOPE)) {
                return INF_SLOPE;
            } else {
                return BigDecimal.valueOf(a.y).subtract(slope.multiply(BigDecimal.valueOf(a.x)), new MathContext(14, RoundingMode.HALF_UP));
            }
        }


        @Override
        public int hashCode() {
            return slope.multiply(yInter).toBigInteger().intValue() + xInter;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Line) {
                Line ol = (Line)o;
                return this.slope.equals(ol.slope) && this.yInter.equals(ol.yInter) && this.xInter.equals(ol.xInter);
            } else {
                return false;
            }
        }


        @Override
        public String toString() {
            return "slope="+slope+ " yInter="+yInter + " xInter="+xInter;
        }
    }
}
