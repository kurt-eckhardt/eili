package eili.leet.leet095;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  0948
 * Name:       Bag of Tokens
 * Tags:       Top-Down
 * BigO:       O(N)  O(N)
 * Difficulty: Medium
 * Techniques: Recursion, Dynamic Programming, Memoization, Two Pointers
 * Learnings:  This problem can be iteratively instead of using recursion
 *
 *
 * You have an initial power P, an initial score of 0 points, and a bag of tokens.
 * Each token can be used at most once, has a value token[i], and has potentially two ways
 * to use it.
 *
 * 1. If we have at least token[i] power, we may play the token face up, losing token[i] power, and
 * gaining 1 point.
 *
 * 2. If we have at least 1 point, we may play the token face down, gaining token[i]
 * power, and losing 1 point.
 *
 * Return the largest number of points we can have after playing any number of tokens.
 *
 * Note:
 * tokens.length <= 1000
 * 0 <= tokens[i] < 10000
 * 0 <= P < 10000
 *
 * Example 1:
 * Input: tokens = [100], P = 50
 * Output: 0
 *
 * Example 2:
 * Input: tokens = [100,200], P = 150
 * Output: 1
 *
 * Example 3:
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 */
public class Leet0948 {

    public static void main(String[] args) {
//        System.out.println(maximizePoints(new int[] {100,200,300,400}, 200));
//        System.out.println(maximizePoints(new int[] {2897,6861,2070,5292,3402,9784,9718,2089,5660,3294,9685,9245,5861,7200,6813,3533,9163,8994,3306,7473,90,8163,5648,9523,3631,6257,3230,7827,6007,9874,10,1407,436,1258,9293,9486,4804,9466,8183,7786,7472,1876,5488,4238,9497,1738,1698,6588,1574,1100}, 5039));
        System.out.println(maximizePoints(new int[] {6180,6178,7955,9549,9853,2081,8156,5459,499,1773,2760,343,177,7661,8534,9432,7986,4963,5210,2895,489,9983,9190,7595,2703,3619,7316,9494,4538,8494,4884,6944,9039,7421,6840,7656,8514,809,5800,5023,6550,1761,3236,5964,7697,2380,849,3996,5244,4349,2848,121,6883,7127,466,6887,2389,1036,7981,7472,5446,4753,3892,1446,2983,851,842,1226,1833,990,9248,5620,3451,9952,5090,2335,5305,7992,8780,8184,3425,1559,7150,7358,5960,6674,6254,4996,1590,9772,3207,2063,6742,712,826,3333,9538,3876,2395,2496,4362,571,6154,43,909,5813,9966,4244,4524,9520,6739,9500,1532,4369,5658,2777,1090,7786,4794,6320,1019,275,9034,9613,8993,8008,3917,7039,6902,2227,5939,3967,6803,8945,1288,6107,5298,1871,5586,630,7736,1676,3894,8329,3046,6841,1262,3985,5597,8177,3756,1062,8221,292,5642,5557,3387,4336,6003,7526,5078,2017,67,297,6139,9877,4246,6146,5755,2015,266,1337,1194,2424,8706,5297,7026,668,9490,286,8912,215,5401,5347,95,2920,8605,2863,2453,5189,2086,9069,2233,8405,3046,6183,1208,3875,6420,1034,7207,2069,6191,1884,8829,7352,2812,7879,9019,7410,6099,8177,5890,9981,4469,292,8234,4670,9097,1557,9433,3719,7450,4638,408,8973,7168,6156,5323,9825,2755,3133,8443,6776,7749,4935,1086,2166,3061,3101,1903,4485,363,3483,8879,3868,9895,9987,8475,255,5992,7127,1595,5014,671,5362,895,8499,855,9684,1311,5315,593,2656,8406,1580,2352,9251,8178,2476,1366,9174,3475,3308,7368,4847,7848,2356,8988,5099,1702,2077,118,6679,8321,603,8860,7763,7443,1908,7307,3687,3104,8921,8335,124,9032,5127,2306,3999,7867,8876,30,5809,8750,1700,5401,1660,8620,7339,747,9561,6524,8052,8243,3790,1565,8807,2236,8955,1533,9181,3712,4734,7422,542,406,5586,710,6659,208,1099,4325,7216,5938,4210,8298,7074,1674,871,693,4579,8986,8502,3957,3731,477,2505,8890,2328,7964,5148,5676,9713,536,234,4320,9398,9990,3926,7981,3259,2739,9816,2916,5171,2944,8095,4998,2658,1161,7502,2238,336,8276,3137,3907,3049,4488,2364,9807,1924,8086,7,4253,4863,5206,3794,3792,209,8533,9704,8274,455,9157,4212,2137,9776,6975,3572,5637,2704,2311,1735,3062,3497,315,5081,121,4594,1317,1268,9059,4146,9824,6304,1742,1294,4201,9434,7647,2079,1582,1194,1651,4188,3168,7271,7733,4812,2233,7689,3256,1622,3851,8193,3304,5953,2136,9067,5761,537,8275,3859,9323,7603,7882,3269,6479,3801,5038,8466,33,1452,757,4333,6638,7630,5325,1273,1969,7079,9808,4541,4844,2699,8715,3340,6281,893,8691,1722,3837,1830,9076,1603,2659,3673,7061,7767,8701,2164,600,3530,2845,7118,981,5711,2148,2760,4697,9009,5161,411,2508,1055,9622,3443,8502,6462,9037,3893,8749,2246,6230,9806,242,959,3676,2262,9257,5586,2010,1412,771,6489,8608,409,7780,4178,329,1840,7945,7281,4013,6887,7491,3240,4993,9496,8513,7231,6002,4700,7687,1932,2922,6935,242,589,7853,2356,7139,6713,46,8602,7626,51,217,8616,8979,4531,1537,1722,4340,6877,5577,7508,639,7646,2733,8084,8217,2792,8257,77,9573,7858,5144,8297,7755,3956,5351,6282,1331,8513,579,534,9270,9877,6211,2789,52,9996,1796,6390,5172,1360,9559,2900,5220,7466,9383,7907,4791,5452,4569,4787,2644,3487,2591,3567,8164,3119,2776,2367,9664,705,5133,1225,4776,2151,2740,6023,3870,868,3478,9424,4752,8356,2738,9977,9715,9815,9681,5129,1827,9875,585,8244,7600,592,8055,9890,9747,7203,4659,6478,9989,9551,9435,6966,6896,1845,7810,2826,1233,1388,3033,8450,872,3931,879,1727,4936,5889,519,3577,7763,5271,3605,5337,6595,9182,1280,5580,6253,6931,5036,2573,1688,9603,7189,2636,4588,4780,9867,829,9061,2096,7560,7047,7533,1127,162,8227,7002,5827,8102,9419,5497,9794,9489,9728,5781,510,1487,6302,1758,4074,9395,8575,1118,7699,9049,8158,9469,9457,7310,3002,3997,4371,7056,849,2614,9328,9386,2952,1938,5373,2235,2287,3523,8288,465,592,5595,6551,7578,9529,5894,6839,3379,3523,195,9748,5089,960,1059,1409,5885,7219,1482,3754,6354,2354,5991,3331,6407,5796,5477,1207,8621,3503,1600,7581,8460,2185,4515,5833,3422,3957,6542,9349,6254,8785,221,5391,5159,8702,4462,7520,7396,4784,3706,1359,4043,5067,8752,7384,2873,648,1856,7571,1629,8190,7780,1779,3305,4551,5111,7657,894,1182,1058,7054,4037,1091,8089,1212,8240,8180,2090,6066,3634,3229,7927,2970,2774,5700,3696,5719,7417,6823,9295,505,5053,9512,9083,5154,1857,3303,5502,7362,3173,8240,8286,9715,6316,5419,9580,8881,1583,3970,9755,947,2564,5335,6897,5219,3396,8966,1926,2498,502,9222,7774,2580,3355,5592,2471,8274,6732,1847,3270,1649,2492,829,9100,4338,8298,8133,1355,8397,585,9372,7559,9598,6863,2263,1870,7989,7707,5535,1263,2164,4616,2117,8404,8714,3237,6757,2104,6677,7529,1647,2437,712,9394,9666,4486,6772,7024,969,9507,2376,657,2741,9391,9772,4711,3380,6967,7389,2425,9200,3998,1723,2235,8429,9054,6268,1692,2019,8293,7349,9423,5021,1766,1476,4417,5480,9899,6245,9710,9456,3653,3473,5177,6410,2593,4154,8637,2830,9965,811,2874,8314,8945,1031,8960,6148,9283,9292,6831,9409,293,519,545,8867,5516,1318,2085,559,4201,2048,5507,2987,7797,5734,5400,5405,4687,2210,503,4178,1196,106,1905,9682,1603,5027,6693,2161,2230,7302,3115,9915,6050,358,472,6216,4203,920,9012,9665,9237,7891,2573,178,4285,550,6033,4729,8860,9807,1268,4298,908,6740}, 5507));
    }


    /**
     *  Key insights:
     *  Power: 0 - Power is useless.  We want to maximize points at the expense of power
     *  UP: Points++, Power -= TokenVal
     *  DN: Points--, Power += TokenVal
     *
     *  Maximize power by trading 1 point for the highest cost token (maximize buying power)
     *  Maximize points by trading the lowest token value for 1 point
     *
     *  Idea: Sort tokens by value (can use O(N) count sort since token val is < 10000)
     */
    public static Map<String, Integer> memos = new HashMap<>();

    public static int maximizePoints(int[] tokens, int power) {
        if (tokens == null || tokens.length == 0) return 0;
        Arrays.sort(tokens);
        return maximizePoints(tokens, power, 0, 0, tokens.length-1);
    }

    public static int maximizePoints(int[] tokens, int power, int points, int lIdx, int rIdx) {

        String memoKey = power + "_" + points + "_" + lIdx + "_" + rIdx;
        if (memos.containsKey(memoKey)) {
            return memos.get(memoKey);
        }

        System.out.println("power="+power + " points=" + points + " lIdx="+ lIdx + " rIdx="+rIdx);
        int max = points;
        if (lIdx > rIdx) {
            max = points;
        }
        // can use power or points
        else if (power >= tokens[lIdx] && points > 0) {
            int usePoint = maximizePoints(tokens, power+tokens[rIdx], points-1, lIdx, rIdx-1);
            int usePower = maximizePoints(tokens, power-tokens[lIdx], points+1, lIdx+1, rIdx);
            max = Math.max(points, Math.max(usePoint, usePower));
        }
        // no points, must use power
        else if (power >= tokens[lIdx]) {
            int usePoint = maximizePoints(tokens, power-tokens[lIdx], points+1, lIdx+1, rIdx);
            max = Math.max(points, usePoint);
        }
        // no power, so must try using points
        else if (points > 0) {
            int usePower = maximizePoints(tokens, power+tokens[rIdx], points-1, lIdx, rIdx-1);
            max = Math.max(points, usePower);
        }
        // no power or points so we are done
        else {
            max = points;
        }

        memos.put(memoKey, max);
        return max;
    }
}
