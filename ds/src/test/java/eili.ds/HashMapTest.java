package eili.ds;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>(2);

        System.out.println("hm.size()=" + hm.size() + " cap=" + hm.capacity());
        hm.put("5", 5);
        System.out.println("hm.size()=" + hm.size() + " cap=" + hm.capacity());
        hm.put("6", 6);
        System.out.println("hm.size()=" + hm.size() + " cap=" + hm.capacity());
        System.out.println("Removing 5: " + hm.remove("5") + " size is now: " + hm.size() + " cap=" + hm.capacity());
        System.out.println("Removing 6: " + hm.remove("6") + " size is now: " + hm.size() + " cap=" + hm.capacity());

        HashMap<Integer, Integer> hm2 = new HashMap<>(2000000);
        long start1 = System.currentTimeMillis();
        for (int i=0; i < 1000000; i++) {
            hm2.put(i,i);
        }
        long elapsed1 = System.currentTimeMillis() - start1;
        System.out.println("Elapsed="+elapsed1);

        java.util.HashMap<Integer, Integer> jhm = new java.util.HashMap<>(2000000);
        long start2 = System.currentTimeMillis();
        for (int i=0; i < 1000000; i++) {
            jhm.put(i,i);
        }
        long elapsed2 = System.currentTimeMillis() - start2;
        System.out.println("Elapsed="+elapsed2);


    }

}