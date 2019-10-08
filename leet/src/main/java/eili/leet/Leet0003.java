package eili.leet;

public class Leet0003 {

    // curIdx  aChar  lastSeenAt  startIdx   curLen   maxLen
    // 0       a      a0          0          1        1
    // 1       b      a0,b1       0          2        2
    // 2       c      a0,b1,c2    0          3        3
    // 3       a      a3,b1,c2    1          3        3
    public static void main(String[] args) {
        System.out.println(Leet0003.solution("This abxaaci is a string with ! and punctuations."));
    }

    public static int solution(String s) {

        Integer[] lastSeenAt = new Integer[256]; // index where chars was last seen
        int startIdx = 0; // index where the substring starts
        int curLen   = 0;
        int maxLen   = 0;
        char[] chars = s.toCharArray();

        for (int curIdx=0; curIdx < chars.length; curIdx++) {
            char aChar = chars[curIdx];
            if (!isCharInPattern(aChar, startIdx, lastSeenAt)) {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            }
            // move startIdx 1 position past the last place we saw this character
            else {
                int lastSeenIdx = lastSeenAt[indexFor(aChar)];
                startIdx = lastSeenIdx+1;
                curLen   = curIdx - startIdx + 1;
            }
            lastSeenAt[indexFor(aChar)] = curIdx;
        }

        return maxLen;
    }


    public static boolean isCharInPattern(char aChar, int startIdx, Integer[] lastSeenAt) {
        Integer lastSeenIdx = lastSeenAt[indexFor(aChar)];
        if (lastSeenIdx != null && lastSeenIdx >= startIdx) {
            return true;
        } else {
            return false;
        }
    }


    public static int indexFor(char aChar) {
        return aChar-' ';
    }
}
