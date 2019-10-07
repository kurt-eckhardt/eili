import java.util.HashMap;
import java.util.Map;

public class BaseConverter {

    private static char[] chars = new char[64];
    private static Map<Character, Integer> charValMap = new HashMap<>();
    static {
        for (int i=0; i < 10; i++) chars[i]    = (char)('0'+i);
        for (int i=0; i < 27; i++) chars[i+10] = (char)('A'+i);
        for (int i=0; i < 27; i++) chars[i+36] = (char)('a'+i);

        // reverse lookup
        for (int i=0; i < 64; i++) {
            charValMap.put(chars[i], i);
        }

    }

    // Integer to ASCII (aka itoa)
    public static final String itoa(int value, int toBase) {
        String result = "";
        do {
            result = chars[(value % toBase)] + result;
            value  = value / toBase;
        } while (value != 0);

        return result;
    }


    // ASCII to Integer (aka atoi)
    public static final int atoi(String value, int fromBase) {
        int result = 0;
        int multiplier = 1;
        char[] chr = value.toCharArray();
        for (int i=1; i <= value.length(); i++) {
            result += charValMap.get(chr[value.length()-i]) * multiplier;
            multiplier = multiplier * fromBase;
        }

        return result;
    }
}
