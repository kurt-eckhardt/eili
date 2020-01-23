package eili.leet.leet300;

import java.util.*;

/**
 * Problem #:  0380
 * Name:       Insert Delete GetRandom O(1)
 * Tags:       Data structure, HashMap
 * BigO:       Time O(1), Space O(N)
 * Difficulty: Medium
 * Techniques: HashMap, Array
 * Learnings:
 * 1. No need to implement a modified HashMap, instead...
 * 2. Use two data structures.  A HashMap that allows O(1) add/remove and a List that
 *    allows selecting a random element in O(1) time.  Combine them by having the HashMap
 *    point to the index of the data value in the List.
 *
 * Problem Description:
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 *
 */
public class Leet0380 {

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    static class RandomizedSet {

        Map<Integer, Integer> valIdx;
        List<Integer> values;
        Random random;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            this.random = new Random();
            this.valIdx = new HashMap<>();
            this.values = new ArrayList<>();
        }

        public boolean insert(int... vals) {
            boolean all = true;
            for (int val : vals) all &= insert(val);
            return all;
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (valIdx.containsKey(val)) {
                return false;
            } else {
                valIdx.put(val, values.size());
                values.add(val);
                return true;
            }
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            // move last value into the place where valIdx was at
            // then drop the last element of the array to remove it
            if (valIdx.containsKey(val)) {
                int lastValue = values.get(values.size()-1);
//                System.out.println("Removing val=" + val + " by moving lastValue="+lastValue + " to index="+valIdx.get(val));
                values.set(valIdx.get(val), lastValue);
                values.remove(values.size()-1);
                valIdx.put(lastValue, valIdx.get(val));
                valIdx.remove(val);
                return true;
            } else {
                return false;
            }
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return values.get(random.nextInt(values.size()));
        }
    }


    public static void main(String[] args) {
        Random gen = new Random();
        RandomizedSet rs = new RandomizedSet();
        for (int i=0; i < 100; i++) {
            rs.insert(i);
        }
        for (int i=90; i < 100; i++) {
            rs.remove(i);
        }

        int[] counts = new int[100];
        for (int i=0; i < 10000; i++) {
            counts[rs.getRandom()]++;
        }
        for (int i=0; i < 100; i++) {
            if (counts[i] > 0) System.out.println("i="+i + " count="+counts[i]);
        }

        for (int valIdx : rs.valIdx.keySet()) {
            System.out.println("valIdx="+valIdx + " val at index=" +rs.values.get(valIdx));
        }
    }
}


/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//static class RandomizedSet {
//
//    int size = 0;
//    int capa = 10;
//    Node[] nodes;
//    Random rand;
//    List<Node> nList;
//
//    /**
//     * Initialize your data structure here.
//     */
//    public RandomizedSet() {
//        this.size  = 0;
//        this.capa  = 10;
//        this.nodes = new Node[capa];
//        this.rand  = new Random();
//        this.nList = new ArrayList<>();
//    }
//
//    public boolean insert(int... vals) {
//        boolean all = true;
//        for (int val : vals) all &= insert(val);
//        return all;
//    }
//
//    /**
//     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
//     */
//    public boolean insert(int val) {
//        System.out.println("Hashing val="+ val + " to bucket="+ hash(val));
//        Node bucket = nodes[hash(val)];
//        while (bucket != null && bucket.val != val) bucket = bucket.next;
//        if (bucket == null) {
//            ensureCapacity();
//            int hashVal  = hash(val);
//            Node newNode = new Node();
//            newNode.val  = val;
//            newNode.next = nodes[hashVal];
//            if (nodes[hashVal] != null) nodes[hashVal].prev = newNode;
//            nodes[hashVal] = newNode;
//            nList.add(newNode);
//            size++;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * Removes a value from the set. Returns true if the set contained the specified element.
//     */
//    public boolean remove(int val) {
//        int hashVal = hash(val);
//        Node bucket = nodes[hashVal];
//        while (bucket != null && bucket.val != val) bucket = bucket.next;
//
//        if (bucket != null) {
//            if (bucket == nodes[hashVal]) {
//                nodes[hashVal] = bucket.next;
//            }
//
//            if (bucket.prev != null) {
//                bucket.prev.next = bucket.next;
//            }
//
//            nList.remove(bucket);
//            size--;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * Get a random element from the set.
//     */
//    public int getRandom() {
//        if (size == 0) throw new NoSuchElementException();
//        return nList.get(rand.nextInt(size)).val;
//    }
//
//
//    private int hash(int val) {
//        return Math.abs(val % capa);
//    }
//
//    private void ensureCapacity() {
//        if (size >= 0.75*capa) {
//            Node[] oldNodes = nodes;
//            nodes = new Node[capa*2];
//            nList = new ArrayList<>();
//            this.size = 0;
//            this.capa = capa * 2;
//            for (Node oldNode : oldNodes) {
//                while (oldNode != null) {
//                    insert(oldNode.val);
//                    oldNode = oldNode.next;
//                }
//            }
//        }
//    }
//
//    class Node {
//        Integer val;
//        Node next;
//        Node prev;
//    }
//
//}