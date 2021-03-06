package eili.scratch;

public class MaxKSubArray {


    public static void main(String[] args) {
        System.out.println(maxKSubArray2(new int[] {5,-1,-2, 6,-30}, 3));
        System.out.println(maxKSubArray2(new int[] {52,183,124,154,-170,-191,-240,107,-178,171,75,186,-125,61,-298,284,21,-73,-294,253,146,248,-248,127,26,289,118,-22,-300,26,-116,-113,-44,29,252,-278,47,254,-106,246,-275,42,257,15,96,-298,-69,-104,-239,-95,-4,76,-202,156,-14,-178,188,-84,78,-195,-125,28,109,125,-25,-53,58,287,55,-296,198,281,53,-160,146,298,25,-41,-3,27,-242,169,287,-281,19,91,213,115,211,-218,124,-25,-272,278,296,-177,-166,-192,97,-49,-25,168,-81,6,-94,267,293,146,-1,-258,256,283,-156,197,28,78,267,-151,-230,-66,100,-94,-66,-123,121,-214,-182,187,65,-186,215,273,243,-99,-76,178,59,190,279,300,217,67,-117,170,163,153,-37,-147,-251,296,-176,117,68,258,-159,-300,-36,-91,-60,195,-293,-116,208,175,-100,-97,188,79,-270,80,100,211,112,264,-217,-142,5,105,171,-264,-247,138,275,227,-86,30,-219,153,10,-66,267,22,-56,-70,-234,-66,89,182,110,-146,162,-48,-201,-240,-225,-15,-275,129,-117,28,150,84,-264,249,-85,70,-140,-259,26,162,5,-203,143,184,101,140,207,131,177,274,-178,-79,14,-36,104,52,31,257,273,-52,74,276,104,-133,-255,188,-252,229,200,-74,-39,-250,142,-201,-196,-43,-40,255,-149,-299,-197,-175,-96,-155,-196,-24,12,79,71,-144,-59,-120,227,-256,-163,-297,116,286,-283,-31,-221,-41,121,-170,160,205,8,88,25,-272,-107,292,-180,299,94,-97,-81,-134,37,238,52,183,124,154,-170,-191,-240,107,-178,171,75,186,-125,61,-298,284,21,-73,-294,253,146,248,-248,127,26,289,118,-22,-300,26,-116,-113,-44,29,252,-278,47,254,-106,246,-275,42,257,15,96,-298,-69,-104,-239,-95,-4,76,-202,156,-14,-178,188,-84,78,-195,-125,28,109,125,-25,-53,58,287,55,-296,198,281,53,-160,146,298,25,-41,-3,27,-242,169,287,-281,19,91,213,115,211,-218,124,-25,-272,278,296,-177,-166,-192,97,-49,-25,168,-81,6,-94,267,293,146,-1,-258,256,283,-156,197,28,78,267,-151,-230,-66,100,-94,-66,-123,121,-214,-182,187,65,-186,215,273,243,-99,-76,178,59,190,279,300,217,67,-117,170,163,153,-37,-147,-251,296,-176,117,68,258,-159,-300,-36,-91,-60,195,-293,-116,208,175,-100,-97,188,79,-270,80,100,211,112,264,-217,-142,5,105,171,-264,-247,138,275,227,-86,30,-219,153,10,-66,267,22,-56,-70,-234,-66,89,182,110,-146,162,-48,-201,-240,-225,-15,-275,129,-117,28,150,84,-264,249,-85,70,-140,-259,26,162,5,-203,143,184,101,140,207,131,177,274,-178,-79,14,-36,104,52,31,257,273,-52,74,276,104,-133,-255,188,-252,229,200,-74,-39,-250,142,-201,-196,-43,-40,255,-149,-299,-197,-175,-96,-155,-196,-24,12,79,71,-144,-59,-120,227,-256,-163,-297,116,286,-283,-31,-221,-41,121,-170,160,205,8,88,25,-272,-107,292,-180,299,94,-97,-81,-134,37,238}, 300));
    }


    /**
     * BigO: O(N*K) Time, O(K) Space
     * Idea is to slide a window of size 'k' forward and keep track of the previous 'k'
     * sums so that we can pick the maximum along the way.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maxKSubArray2(int[] nums, int k) {
        int max = nums[0];
        int[] sumPrev = new int[k+1];
        for (int i=0; i < nums.length; i++) {
            for (int j=k; j > 0; j--) {
                sumPrev[j] = sumPrev[j-1]+nums[i];
                max = Math.max(max, sumPrev[j]);
            }
        }

        return max;
    }

    // What is the best number to replace in the previous 'k' numbers to maximize the sum?
    // we decide to take or not take an element based on the length of our current subarray.
    // If length is < k, then we either move the left or the right pointer to maximize our value.
    // Otherwise, we are forced to move the left pointer.
    // 5  2  4  0
    // 5 -3  2  1

    //  5  2  7
    //  0  1  2  3  4  5
    //  5,-3, 5, 5,-3, 5
    //
    // sum=2
    //  l      r
    //  1, -2, 3, -2, 1, -2, 3, -2
    //
    //                  l   r sum=-4
    // -2, -3, -1, -2, -3, -1
    public static int maxKSubArray(int[] nums, int k) {

        int lIdx = 0;
        int rIdx = 0;
        int sum  = nums[0];
        int max  = nums[0];
        while (lIdx < nums.length-1) {
            int subarrayLen = rIdx-lIdx+1;
            System.out.println("lIdx="+lIdx + " rIdx="+rIdx + " sum="+sum + " max="+max+ " saLen="+subarrayLen);
            if (rIdx == nums.length-1) {
                sum = sum - nums[lIdx++];
                max = Math.max(max, sum);
            } else if (subarrayLen >= k && k > 1) {
                sum = sum - nums[lIdx++];
                max = Math.max(max, sum);
            } else if (subarrayLen >= k && k == 1) {
                sum = sum - nums[lIdx++];
                sum = sum + nums[++rIdx];
                max = Math.max(max, sum);
            } else if (lIdx == rIdx) {
                sum = sum + nums[++rIdx];
                max = Math.max(max, sum);
            } else if (nums[rIdx] > sum) { //1, -2, 3, -2, 1, -2, 3, -2  [1,-2,3]
                sum = sum - nums[lIdx++];
                max = Math.max(max, sum);
            } else if (subarrayLen < k) {
                sum = sum + nums[++rIdx];
                max = Math.max(max, sum);
            } else {
                System.out.println("Unexpected scenario[lIdx="+lIdx + " rIdx="+rIdx + " sum="+sum + " max="+max+"]");
            }
        }

        return max;
    }
}
