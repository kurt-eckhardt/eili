package eili.foo;

public class Test {

    public static void main(String[] args) {
        String s = "skunk";
        int sLength  = s.length();
        int numPerms = 1 << sLength; // 2^n perms
        for (int i=0; i < numPerms; i++) {
            String aPerm = "";
            for (int j=0; j < sLength; j++) {
                if (((i >> j) & 0x1) != 0) {
                    aPerm += s.charAt(j);
                }
            }
            System.out.println("A Permutation:"+aPerm);
        }
    }
}
