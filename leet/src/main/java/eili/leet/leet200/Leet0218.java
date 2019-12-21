package eili.leet.leet200;

import java.util.*;

public class Leet0218 {

    public static void main(String[] args) {
        System.out.println(skyline1(new Building[]{new Building(0,2147483647,2147483647)}));
//        System.out.println(skyline(new int[][]{{1,5,1},{2,3,1},{2,4,2}}));
//        System.out.println(skyline(new int[][]{{0,2147483647,2147483647}}));
//        System.out.println(skyline(new int[][]{{2,4,7},{2,4,5},{2,4,6}}));
    }


    public static List<List<Integer>> skyline(int[][] bArray) {

        List<List<Integer>> solution = new ArrayList<>();

        Wall[] walls = new Wall[bArray.length*2];
        for (int i=0; i < bArray.length; i++) {
            walls[i*2+0] = new Wall(bArray[i][0], bArray[i][2], 0);
            walls[i*2+1] = new Wall(bArray[i][1], bArray[i][2], 1);
        }

        Arrays.sort(walls, new WallXComparator());

        int curH = 0;
        PriorityQueue<Integer> heightQ = new PriorityQueue<>(Comparator.reverseOrder());
        for (int wallIdx=0; wallIdx < walls.length; ) {

            int curX = walls[wallIdx].x;
            while (walls[wallIdx].x == curX && walls[wallIdx].type == 0) {
//                System.out.println("At x="+ curX + " Adding: " + walls[wallIdx].height);
                heightQ.add(walls[wallIdx++].height);
            }

            while (wallIdx < walls.length && walls[wallIdx].x == curX && walls[wallIdx].type == 1) {
//                System.out.println("At x="+ curX + " Removing: " + walls[wallIdx].height);
                heightQ.remove(walls[wallIdx++].height);
            }

            int tallestAtX = heightQ.isEmpty() ? 0 : heightQ.peek();
//            System.out.println("At x=" + curX + " Tallest=" + tallestAtX);
            if (curH != tallestAtX) {
                solution.add(List.of(curX, tallestAtX));
                curH = tallestAtX;
            }
        }

        return solution;
    }


    /**
     * @param buildings
     * @return
     */
    public static List<Point> skyline1(Building[] buildings) {

        List<Point> solution = new ArrayList<>();
        Arrays.sort(buildings, new BuildingXComparator());

        int begX = buildings[0].sX;
        int endX = Integer.MIN_VALUE;
        for (Building b : buildings) {
            endX = Math.max(endX, b.eX);
        }

        int curH = 0;
        int bIdx = 0;
        PriorityQueue<Building> heightQ = new PriorityQueue<>(new BuildingHComparator());

        for (int x=begX; x <= endX; x++) {

//            // Add buildings starting at this X index
//            while (bIdx < buildings.length && buildings[bIdx].sX == x) {
//                System.out.println("adding");
//
//                heightQ.add(buildings[bIdx++]);
//            }
//
//            // remove buildings ending at this X index
//            while (!heightQ.isEmpty() && heightQ.peek().eX <= x) {
//                System.out.println("removing");
//                heightQ.remove();
//            }
//
//            int tallestAtX = heightQ.isEmpty() ? 0 : heightQ.peek().height;
//            if (curH != tallestAtX) {
//                System.out.println("evaling");
//                solution.add(new Point(x, tallestAtX));
//                curH = tallestAtX;
//            }
        }

        return solution;
    }



    public static class Wall {
        int x;
        int height;
        int type; // 0 = start, 1 = end

        public Wall(int x, int h, int t) {
            this.x      = x;
            this.height = h;
            this.type   = t;
        }

        public String toString() {
            return "x="+x + " h="+ height + " t="+type;
        }
    }

    public static class WallXComparator implements Comparator<Wall> {
        @Override
        public int compare(Wall w1, Wall w2) {
            if (w1.x > w2.x) {
                return 1;
            } else if (w1.x == w2.x) {
                if (w1.type == 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
        }
    }


    public static class Building {
        int sX;
        int eX;
        int height;

        public Building(int sX, int eX, int h) {
            this.sX = sX;
            this.eX = eX;
            this.height = h;
        }
    }

    public static class BuildingXComparator implements Comparator<Building> {
        @Override
        public int compare(Building b1, Building b2) {
            if (b1.sX > b2.sX) {
                return 1;
            } else if (b1.sX == b2.sX) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static class BuildingHComparator implements Comparator<Building> {
        @Override
        public int compare(Building b1, Building b2) {
            if (b1.height < b2.height) {
                return 1;
            } else if (b1.height == b2.height) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
    }
}
