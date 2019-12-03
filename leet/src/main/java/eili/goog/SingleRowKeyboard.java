package eili.goog;

public class SingleRowKeyboard {

    public static void main(String[] args) {
        System.out.println(timeTaken("abcdefghijklmnopqrstuvwxyz", "z"));
    }


    public static int timeTaken(String keyboard, String text) {

        int[] charmap = new int[26];
        for (int i=0; i < keyboard.length(); i++) {
            charmap[keyboard.charAt(i)-'a'] = i;
        }

        int last = 0;
        int time = 0;
        char[] chars = text.toCharArray();
        for (char c : chars) {
            int cPos = charmap[c-'a'];
            time += Math.abs(cPos-last);
            last = cPos;
        }

        return time;
    }
}
