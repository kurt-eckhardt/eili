package eili.math;

public class EggDrop {

    static int eggs      = 12;
    static int maxHeight = 1000;
    static Integer[][] dropsRequiredForHeightN = new Integer[eggs+1][maxHeight+1];
    static Integer[][] startingFloorForHeightN = new Integer[eggs+1][maxHeight+1];


    public static void main(String[] args) {
        for (int height=0; height <= maxHeight; height++) {
            Integer minDrops = numDropsReqWithNEggs(height, eggs);
            Integer maxFloor = startingFloorForHeightN[eggs][height];
            System.out.println("For building height="+height + " and eggs="+eggs + " minDrops=" + minDrops + " starting from floor="+ maxFloor);
        }
    }

    public static int numDropsReqWithNEggs(int height, int eggs) {
        if (height < 1) {
            return 0;
        } else if (eggs == 1) {
            return height; // base case
        } else if (dropsRequiredForHeightN[eggs][height] != null) {
            return dropsRequiredForHeightN[eggs][height];
        } else {
            int minSoFar = Integer.MAX_VALUE;
            int minFloor = -1;
            for (int startFloor = 1; startFloor <= height; startFloor++) {
                int dropsIfEggBroke = numDropsReqWithNEggs(startFloor-1, eggs-1);
                int dropsIfNotBroke = numDropsReqWithNEggs(height-startFloor, eggs); // test all floors above
                int worstCaseDrops  = Math.max(dropsIfEggBroke, dropsIfNotBroke) +1; // +1 is for this drop
                if (worstCaseDrops <= minSoFar) {
                    minSoFar = worstCaseDrops;
                    minFloor = startFloor;
                }
            }
            dropsRequiredForHeightN[eggs][height] = minSoFar;
            startingFloorForHeightN[eggs][height] = minFloor;
            return minSoFar;
        }
    }



    public static Integer[] dropsRequiredForHeight = new Integer[1001];
    public static Integer[] startingFloorForHeight = new Integer[1001];

    // height   startFloor   ifBroke     noBreak      return
    // 1        -            -           -            0
    // 2        1            1egg(0)=0   2eggs(1)=0   max(B,NB)+1=1
    // 2        2            1egg(1)=0   2eggs(1)=0   max(B,NB)+1=1
    // 3        1            1egg(0)=0   2eggs(2)=1   max(B,NB)+1=2
    // 3        2            1egg(1)=0   2eggs(1)=0   max(B,NB)+1=1
    public static int numDropsReqWithTwoEggs(int height) {
        if (height < 1) {
            return 0;
        } else if (dropsRequiredForHeight[height] != null) {
            return dropsRequiredForHeight[height];
        } else {
            int minSoFar = Integer.MAX_VALUE;
            int minFloor = -1;
            for (int startFloor = 1; startFloor <= height; startFloor++) {
                int dropsIfEggBroke = numDropsReqWithOneEggo(startFloor-1);
                int dropsIfNotBroke = numDropsReqWithTwoEggs(height-startFloor); // test all floors above
                int worstCaseDrops  = Math.max(dropsIfEggBroke, dropsIfNotBroke)+1;
                if (worstCaseDrops <= minSoFar) {
                    minSoFar = worstCaseDrops;
                    minFloor = startFloor;
                }
            }
            dropsRequiredForHeight[height] = minSoFar;
            startingFloorForHeight[height] = minFloor;
            return minSoFar;
        }
    }

    public static int numDropsReqWithOneEggo(int height) {
        return height;
//        if (height < 1) {
//            return 0;
//        } else {
//            return height / 2 + (height % 2);
//        }
    }

//
//
//    public static Integer[] dropsRequiredForHeight3 = new Integer[1001];
//    public static Integer[] startingFloorForHeight3 = new Integer[1001];
//    public static int numDropsReqWithThreeEggs(int height) {
//        if (height <= 1) {
//            return 0;
//        } else if (dropsRequiredForHeight3[height] != null) {
//            return dropsRequiredForHeight3[height];
//        } else {
//            int minSoFar = Integer.MAX_VALUE;
//            int minFloor = -1;
//            for (int startFloor = 1; startFloor <= height; startFloor++) {
//                int dropsIfEggBroke = numDropsReqWithTwoEggs(startFloor-1);
//                int dropsIfNotBroke = numDropsReqWithThreeEggs(height-startFloor); // test all floors above
//                int worstCaseDrops  = Math.max(dropsIfEggBroke, dropsIfNotBroke)+1;
//                if (worstCaseDrops <= minSoFar) {
//                    minSoFar = worstCaseDrops;
//                    minFloor = startFloor;
//                }
//            }
//            dropsRequiredForHeight3[height] = minSoFar;
//            startingFloorForHeight3[height] = minFloor;
//            return minSoFar;
//        }
//    }
}
