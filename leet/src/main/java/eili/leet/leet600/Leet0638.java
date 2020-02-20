package eili.leet.leet600;

import java.util.*;

/**
 * Problem #:  0638
 * Name:       Shopping Offers
 * Tags:
 * BigO:       O(Items^[MaxBuy+1]) Time, O(I^B) Space
 * Difficulty: Medium (Hard?)
 * Techniques: Dynamic Programming, Memoization
 * Learnings:  Base Conversions
 * 1. To convert from base7 to base10, remember each b7 digit should be multiplied by its base7 value.
 *    For example, 846(b7) = 49*8 + 7*4 + 1*6 = 426(b10)
 *
 * 2. To convert from base10 to base7, mod base10 by base7 and multiply by 10 to shift the value over.
 *    base7 = (b10 % 7) * power + base7;
 *
 *
 * Problem Description:
 * In LeetCode Store, there are some kinds of items to sell. Each item has a price.
 * However, there are some special offers, and a special offer consists of one or more
 * different kinds of items with a sale price. You are given the each item's price, a
 * set of special offers, and the number we need to buy for each item. The job is to output
 * the lowest price you have to pay for exactly certain items as given, where you could
 * make optimal use of the special offers.
 *
 * Each special offer is represented in the form of an array, the last number represents
 * the price you need to pay for this special offer, other numbers represents how many
 * specific items you could get if you buy this offer.
 *
 * You could use any of special offers as many times as you want.
 *
 * Note:
 * There are at most 6 kinds of items, 100 special offers.
 * For each item, you need to buy at most 6 of them.
 * You are not allowed to buy more items than you want, even if that would lower the overall price.
 *
 *
 * Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * Output: 14
 * Explanation:
 * There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 * In special offer 1, you can pay $5 for 3A and 0B
 * In special offer 2, you can pay $10 for 1A and 2B.
 * You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 *
 */
public class Leet0638 {

    static Map<Integer, Integer> memo = new HashMap<>();


    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        memo.clear();
        return frugal(price, special, needs);
    }


    public static int frugal(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

        int memoKey = needs.stream().reduce(0, (a,n) -> a * 10 + n);
        if (memo.containsKey(memoKey)) {
            return memo.get(memoKey);
        }

        // start off with min cost equal to using no specials
        int minCost = 0;
        for (int i=0; i < needs.size(); i++) {
            minCost += needs.get(i) * price.get(i);
        }

        // now try to reduce the cost using one or more specials
        for (List<Integer> aSpecial : special) {
            if (canApplySpecial(needs, aSpecial)) {
                for (int j=0; j < aSpecial.size()-1; j++) {
                    needs.set(j, needs.get(j)-aSpecial.get(j));
                }

                int aSpecialPrice = aSpecial.get(aSpecial.size()-1);
                int minCostForRemainingNeeds = frugal(price, special, needs);
                minCost = Math.min(minCost, aSpecialPrice + minCostForRemainingNeeds);

                for (int j=0; j < aSpecial.size()-1;j ++) {
                    needs.set(j, needs.get(j)+aSpecial.get(j));
                }
            }
        }

        memo.put(memoKey, minCost);
        return minCost;
    }

    private static boolean canApplySpecial(List<Integer> needs, List<Integer> special) {
        for (int i=0; i < special.size()-1; i++) {
            if (special.get(i) > needs.get(i)) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        System.out.println(shoppingOffers(List.of(3,4), List.of(List.of(3,0,5),List.of(1,2,10)), new ArrayList(List.of(5,5))));
        System.out.println(shoppingOffers(List.of(2,3,4), List.of(List.of(1,1,0,4),List.of(2,2,1,9)), new ArrayList(List.of(1,2,1))));
        System.out.println(shoppingOffers(List.of(4,3,2,9,8,8), List.of(List.of(1,5,5,1,4,0,18),List.of(3,3,6,6,4,2,32)), new ArrayList(List.of(6,5,5,6,4,1))));
        System.out.println(shoppingOffers(
                List.of(1,2,3,1,9,9),
                List.of(List.of(6,3,5,6,1,0,17),List.of(6,6,6,5,2,2,2),List.of(5,2,2,1,4,1,1),List.of(6,4,4,4,0,4,19),List.of(4,6,0,1,0,4,3),List.of(1,2,0,5,0,4,13),List.of(2,5,1,0,0,3,8),List.of(4,3,1,3,5,3,11),List.of(6,1,0,1,5,6,23),List.of(5,3,1,0,3,6,7),List.of(3,4,0,6,2,1,6),List.of(0,3,6,3,4,0,2),List.of(2,2,3,6,3,2,1),List.of(6,1,1,4,2,0,2),List.of(5,6,6,2,1,4,20),List.of(1,4,5,2,5,4,9),List.of(2,3,2,2,5,4,4),List.of(6,6,0,3,0,6,23),List.of(0,6,1,5,6,5,2),List.of(0,0,6,0,4,5,17),List.of(0,0,5,2,3,5,7),List.of(6,0,5,3,6,3,2),List.of(4,3,5,4,0,6,15),List.of(6,2,1,5,2,1,15)),
                new ArrayList(List.of(5,6,5,5,6,1)))
        );
        System.out.println(shoppingOffers(
                List.of(4,7,9,9,3,2),
                List.of(List.of(0,0,4,6,2,0,22),List.of(1,4,3,5,5,3,10),List.of(4,5,6,3,4,1,29),List.of(0,3,2,2,4,2,4),List.of(4,6,3,4,4,6,21),List.of(5,6,3,6,3,4,23),List.of(6,1,3,4,6,2,9),List.of(3,3,6,1,5,1,16),List.of(0,3,6,4,0,2,5),List.of(5,1,2,3,5,5,7),List.of(0,1,1,6,2,4,24),List.of(1,5,2,2,6,1,3),List.of(4,2,2,4,3,1,8),List.of(3,1,0,6,1,2,30),List.of(4,6,1,4,0,0,2),List.of(0,4,5,6,2,5,1),List.of(2,6,0,6,6,2,21),List.of(2,1,3,4,0,2,2),List.of(6,4,4,4,1,3,24),List.of(6,3,1,6,5,5,12),List.of(1,3,2,1,3,2,32),List.of(2,2,0,3,1,2,16),List.of(2,4,3,6,6,5,26),List.of(1,6,3,5,0,4,2),List.of(6,2,1,5,6,2,9),List.of(0,4,2,2,5,3,3),List.of(6,3,3,6,0,5,9),List.of(4,3,2,5,3,3,29),List.of(1,6,0,0,1,6,31),List.of(5,6,0,5,4,3,31),List.of(0,4,2,6,0,6,28),List.of(5,4,3,2,5,3,32),List.of(6,5,1,1,4,6,18),List.of(3,3,3,2,3,3,2),List.of(5,6,2,5,3,3,7),List.of(1,2,6,4,4,0,18),List.of(0,4,4,0,0,3,18),List.of(4,2,0,0,3,3,19),List.of(6,0,4,4,4,6,15),List.of(6,2,3,0,2,2,4),List.of(4,1,1,5,5,5,14),List.of(3,6,4,0,6,2,27),List.of(2,4,6,2,2,3,24),List.of(6,0,5,3,1,6,7),List.of(3,1,5,1,2,6,28),List.of(5,2,2,1,1,4,25),List.of(5,6,5,0,3,4,19),List.of(3,5,3,3,5,1,31),List.of(6,0,1,1,6,4,14),List.of(0,3,4,3,3,4,10),List.of(4,1,2,2,0,0,27),List.of(2,2,1,3,5,2,24),List.of(2,3,2,6,1,1,21),List.of(6,6,5,6,2,2,12),List.of(6,6,3,1,0,6,28),List.of(6,4,1,6,5,0,8),List.of(3,3,0,5,4,2,7),List.of(4,3,3,3,0,2,25),List.of(1,2,0,5,2,4,8),List.of(0,1,6,6,5,5,27),List.of(3,6,4,5,2,2,4),List.of(4,4,6,1,5,3,30),List.of(4,3,4,5,5,5,19),List.of(0,0,0,6,1,0,27),List.of(6,5,0,1,2,4,10),List.of(2,6,0,0,1,0,13),List.of(4,1,6,1,4,1,24),List.of(2,4,0,1,4,1,25),List.of(5,1,3,3,4,1,8),List.of(5,5,1,0,2,1,25),List.of(1,6,2,4,0,6,27),List.of(4,0,3,0,5,3,30),List.of(2,4,6,6,3,2,4),List.of(6,4,2,2,0,3,27),List.of(1,2,1,2,2,1,2),List.of(2,0,3,0,5,4,4),List.of(3,5,4,4,1,1,25),List.of(2,1,1,6,3,3,28),List.of(4,4,4,3,6,3,21),List.of(1,4,1,4,2,2,27),List.of(0,6,0,2,2,2,33),List.of(3,3,5,6,4,6,9),List.of(1,0,0,3,4,2,11),List.of(1,3,0,3,0,1,16),List.of(2,3,0,0,0,5,1),List.of(3,5,6,4,1,4,3),List.of(3,1,0,2,6,0,19),List.of(3,0,0,5,3,1,6),List.of(1,0,4,1,2,2,18),List.of(0,0,4,3,5,1,31),List.of(4,4,2,5,5,2,2),List.of(5,0,2,6,5,3,4),List.of(6,2,1,0,2,3,11),List.of(4,5,1,5,3,3,23),List.of(6,2,5,1,6,6,4),List.of(5,6,6,1,5,6,6),List.of(3,2,6,1,4,5,19),List.of(0,2,6,2,5,0,26),List.of(0,1,3,6,3,6,18),List.of(3,5,4,6,5,3,6)),
                new ArrayList(List.of(6,6,6,6,6,6)))
        );
    }


//
////    A,B	00 01 02 03 04 05 10 11 12 13 14 15 20 21 22 23 24 25 30 31 32 33 34 35 40 41 42 43 44 45 50 51 52 53 54 55
////    A,B	0  3  6  9  12 15 4  7  10 13 16 19 8  11 14 17 20 23 12 15 18 21 24 27 16 19 22 25 28 31 20 23 26 29 32 35
////    S1    u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  10 13 u  u  u  u  14 17 u  u  u  u  18 21
////    S2    u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  u  1  4  7  10 u  u  5  8  11 14 u  u  9  12 15 18
//    public static int frugal_dp(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs) {
//
//        // count by base 7
//        // 000 001 002 003 004 005 006 010 011 012 013
//        int nsize = needs.size();
//        int memos = (int)Math.pow(7,nsize);
//        int[] dp  = new int[memos];
//
//        // Calculate worst-case cost
//        for (int i=0; i < memos; i++) {
//            int needsAtI = base10ToBase7(i);
//            dp[i] = getPriceForNeeds(needsAtI, prices);
//        }
//
//        // Now Apply specials
//        for (List<Integer> sp : specials) {
//
//            int spPrice   = sp.get(sp.size()-1);
//            int[] special = listToArray(sp, true);
//
//            for (int i=0; i < memos; i++) {
//                int[] needsAtI  = base10ToBase7_a(i);
//                int[] remaining = getRemainingNeeds(needsAtI, special);
////                System.out.print("i="+i);
////                System.out.print(" needs="); for (int n : needsAtI)  System.out.print(n + " ");
////                System.out.print(" specs="); for (int n : special)   System.out.print(n + " ");
////                System.out.print(" remai=");
////                if (remaining != null) {
////                    for (int n : remaining) System.out.print(n + " ");
////                } else {
////                    System.out.print("x");
////                }
//                if (remaining != null) {
//                    int price = lookupPriceForNeeds(remaining, dp) + spPrice;
//                    dp[i] = Math.min(dp[i], price);
////                    System.out.println(" price using special="+ price + " new dp[i]="+dp[i]);
//                } else {
////                    System.out.println(" using existing dp[i]="+dp[i]);
//                }
//            }
//        }
//
//        return lookupPriceForNeeds(listToArray(needs, false), dp);
//    }
//
//
//    private static int[] listToArray(List<Integer> list, boolean isSpeical) {
//        int[] arr  = new int[6];
//        int maxLen = isSpeical ? list.size() - 1 : list.size(); // igonre price
//        for (int i=0; i < 6; i++) {
//            arr[i] = i < maxLen ? list.get(i) : 0;
//        }
//        return arr;
//    }
//
//    private static int arrayToReverseInt(int[] arr) {
//        int val = 0;
//        for (int i=arr.length-1; i >= 0; i--) {
//            val = val * 10 + arr[i];
//        }
//        return val;
//    }
//
//
//
//    private static int lookupPriceForNeeds(int[] needs, int[] dp) {
////        System.out.print("Looking up price for needs=");
////        for (int n: needs) System.out.print(n);
//        int needsB7  = arrayToReverseInt(needs);
//        int needsB10 = base7ToBase10(needsB7);
//        int dpVal    = dp[needsB10];
////        System.out.println(" needsB7="+needsB7 + " needsB10="+needsB10 + " dpVal="+dpVal);
//        return dpVal;
//    }
//
//
//
//    private static int[] getRemainingNeeds(int[] needs1, int[] needs2) {
//        int[] rem = new int[6];
//        for (int i=0; i < 6; i++) {
//            rem[i] = needs1[i] - needs2[i];
//            if (rem[i] < 0) return null;
//        }
//        return rem;
//    }
//
//
//    // Needs=CBA so, prices must also be reversed
//    private static int getPriceForNeeds(int needs, List<Integer> prices) {
//        int total = 0;
//        int i=0;
//        while (needs != 0) {
//            total += prices.get(i) * (needs % 10);
//            needs = needs / 10;
//            i++;
//        }
//        return total;
//    }
//
//    // base7=10  base10=7
//    private static int base7ToBase10(int digits) {
//        int base10 = 0;
//        int power  = 1;
//        while (digits != 0) {
//            int digit = digits % 10;
//            base10 = digit * power + base10;
//            power  = power * 7;
//            digits = digits / 10;
//        }
//        return base10;
//    }
//
//
//    private static int base10ToBase7(int b10) {
//        int power = 1;
//        int base7 = 0;
//        do {
//            base7 = (b10 % 7) * power + base7;
//            power = power * 10;
//            b10 = b10 / 7;
//        } while (b10 != 0);
//
//        return base7;
//    }
//
//    private static int[] base10ToBase7_a(int b10) {
//        int i = 0;
//        int[] b7 = new int[6];
//        do {
//            b7[i] = b10 % 7;
//            b10   = b10 / 7;
//            i++;
//        } while (b10 != 0);
//
//        return b7;
//    }
}
