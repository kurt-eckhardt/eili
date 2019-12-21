package eili.leet.leet000;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Leet0030 {
    public static void main(String[] args) {
        List<String> patterns = makePermPatterns(3);
        System.out.println(patterns);
//        System.out.println(makeAllPerms(new String[]{"A", "B", "C"}));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        return null;
    }

    public static List<String> makePermPatterns(int n) {
        List<String> allPerms = new ArrayList<>();
        allPerms.add("");
        for (int i=0; i < n; i++) {
            List<String> perms = new ArrayList<>();
            int numPerms = allPerms.size();
            for (int j=0; j < numPerms; j++) {
                String aPerm = allPerms.get(j);
                for (int k=0; k <= aPerm.length(); k++) {
                    perms.add(aPerm.substring(0, k) + i + aPerm.substring(k));
                }
            }
            allPerms.clear();
            allPerms.addAll(perms);
        }
        return allPerms;
    }

}
