package eili.goog;

import java.util.*;

public class BisectPoints {

    public static void main(String[] args) {
        //System.out.println(bisect(new int[][]{{0,0},{2,0},{1,1},{-2,2},{-2,0}}));
        System.out.println(bisect(new int[][]{{-2,0},{-1,0},{0,0},{1,0},{2,0}}));
        System.out.println(bisect(new int[][]{{0,-2},{0,-1},{0,0},{0,1},{0,2}}));
    }


    public static double bisect(int[][] points) {

        HashSet<Double> anglesSet = new HashSet<>();
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            if (x != 0 || y != 0) {
                double degrees = Math.atan2(y, x) * 180.0 / Math.PI;
                System.out.println("x="+x + " y="+y + " degrees="+degrees);
                anglesSet.add(degrees);
            }
        }

        ArrayList<Double> angles = new ArrayList<>(anglesSet);
        Collections.sort(angles);
        int numAngles = angles.size();
        if (numAngles % 2 == 1) {
            return angles.get(numAngles/2);
        } else {
            return (angles.get(numAngles/2) + angles.get(numAngles/2-1)) / 2;
        }
    }
}
