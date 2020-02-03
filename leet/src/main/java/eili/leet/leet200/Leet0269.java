package eili.leet.leet200;

import java.util.*;

/**
 * Problem #:  0315
 * Name:       Count of Smaller Numbers After Self
 * Tags:       Khan's Algorithm, Topological Sort, Graph
 * BigO:       Time O(|V|+|E|), Space O(|V|)
 * Difficulty: Hard
 * Techniques: Topological Sort
 *
 * Learnings:
 * 1) Topology sort can be written using either Kahn's Algorithm or DFS (with all dependencies completed check)
 *    Khan's algoritgm is clean:
 *    1. Build Bi-Directional Graph using two Adjacency Lists
 *    2. Find top level parents and add them to a queue
 *    3. Visit next node in the list and add it to the topological sorted result list.
 *    4. Remove child->parent link for each child of the visiting node
 *    5. If child has no parents, then add it to the nodes to visit list
 *
 * 2) DFS may be more intuitive than Khan's algorithm.  This algorithm works by visiting a node only when all
 *    of it's children have been visited.
 *    1. Build Bi-Directional Graph using two Adjacency Lists
 *    2. Find top level parents and add them to a queue
 *    3. Visit next node in the list
 *    4. If all children are visited, then add the visiting node to the topological sorted result list.
 *    5. Else, add all unvisited node to the queue and add the parent back into the queue
 *
 * 3) Adjacency Matrix was more difficult to traverse than Adjacency List (using Node->Kids Map)
 */
public class Leet0269 {

    public static void main(String[] args) {
        System.out.println(alienDictionary(new String[] { "wrt", "wrf", "er", "ett", "rftt" }));
//        System.out.println(alienDictionary(new String[] { "ace", "bdf", "ab" }));
//        System.out.println(alienDictionary(new String[] { "ab", "adf", "cb", "cdf", "ef", "gf", "hb", "ib", "if" }));
    }


    // Annoying
    public static String alienDictionary(String[] words) {

        // Build Bi-Directional Graph using two Adjacency Lists
        Map<Character, Set<Character>> parentToKids = new HashMap<>();
        Map<Character, Set<Character>> kidToParents = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length() - 1; i++) {
                char fromChar = word.charAt(i);
                char toChar   = word.charAt(i + 1);
                if (fromChar != toChar) {
                    Set<Character> kids = parentToKids.getOrDefault(fromChar, new HashSet<>());
                    kids.add(toChar);
                    parentToKids.put(fromChar, kids);

                    Set<Character> parents = kidToParents.getOrDefault(toChar, new HashSet<>());
                    parents.add(fromChar);
                    kidToParents.put(toChar, parents);
                }
            }
        }

        // Add top level parents toVisit
        ArrayDeque<Character> toVisit = new ArrayDeque<>();
        for (char parent : parentToKids.keySet()) {
            if (kidToParents.get(parent) == null) toVisit.add(parent);
        }

        // Kahn's Algorithm.  Visit nodes when they have no parent nodes
        List<Character> sorted = new ArrayList<>();
        while (!toVisit.isEmpty()) {
            char visiting = toVisit.remove();
            sorted.add(visiting);
            for (char child : parentToKids.getOrDefault(visiting, Set.of())) {
                Set<Character> parents = kidToParents.getOrDefault(child, Set.of());
                parents.remove(visiting);
                if (parents.isEmpty()) toVisit.add(child);
            }
        }

        return sorted.toString();
    }






//    public static String alienDictionary(String[] words) {
//
//        Map<Character, Set<Character>> parentToKids = new HashMap<>();
//        Map<Character, Set<Character>> kidToParents = new HashMap<>();
//
//        // Build Bi-Directional Graph using two Adjacency Lists
//        for (String word : words) {
//            for (int i = 0; i < word.length() - 1; i++) {
//                char fromChar = word.charAt(i);
//                char toChar   = word.charAt(i + 1);
//                Set<Character> kids = parentToKids.getOrDefault(fromChar, new HashSet<>());
//                kids.add(toChar);
//                parentToKids.put(fromChar, kids);
//
//                Set<Character> parents = kidToParents.getOrDefault(toChar, new HashSet<>());
//                parents.add(fromChar);
//                kidToParents.put(toChar, parents);
//            }
//        }
//
//        int[] level = new int[26];
//        for (int i=0; i < 26; i++) {
//            level[i] = -1;
//        }
//
//        ArrayDeque<Character> queue = new ArrayDeque<>();
//        for (char aChar : parentToKids.keySet()) {
//            boolean isTopLevelParent = kidToParents.get(aChar) == null;
//            if (isTopLevelParent) {
//                level[aChar-'a'] = 0;
//                for (char child : parentToKids.get(aChar)) {
//                    if (!queue.contains(child)) queue.add(child);
//                }
//            }
//        }
//
//        while (!queue.isEmpty()) {
//            char visiting = queue.removeFirst();
//            if (level[visiting-'a'] == -1) {
//                int maxOfAllParents = 0;
//                boolean allParentsVisited = true;
//                for (char parent : kidToParents.get(visiting)) {
//                    if (level[parent-'a'] == -1) {
//                        allParentsVisited = false;
//                        if (!queue.contains(parent)) queue.add(parent);
//                    }
//                    maxOfAllParents = Math.max(maxOfAllParents, level[parent-'a']);
//                }
//
//                if (allParentsVisited) {
//                    level[visiting-'a'] = maxOfAllParents + 1;
//                    Set<Character> children = parentToKids.get(visiting);
//                    if (children != null) {
//                        for (char child : children) {
//                            if (level[child - 'a'] == -1 && !queue.contains(child)) {
//                                queue.add(child);
//                            }
//                        }
//                    }
//                } else {
//                    queue.add(visiting);
//                }
//            }
//        }
//
//        StringBuffer priority = new StringBuffer();
//        for (int i=0; i < 26; i++) {
//            if (level[i] != -1) {
//                System.out.println("char["+('a'+i)+"]="+level[i]);
//            }
//        }
//
//        return priority.toString();
//    }



//    public static String alienDictionary(String[] words) {
//
//        Set<Integer> dict = new HashSet<>();
//
//        // build topological graph
//        int[][] graph = new int[26][26];
//        for (String word : words) {
//            for (int i=0; i < word.length()-1; i++) {
//                int fromChar = word.charAt(i)   - 'a';
//                int toChar   = word.charAt(i+1) - 'a';
//                if (fromChar != toChar) {
//                    graph[fromChar][toChar] = 1;
//                }
//                dict.add(fromChar);
//                dict.add(toChar);
//            }
//        }
//
//        // find starting nodes
//        int min = Integer.MAX_VALUE;
//        int[] charToCounts = new int[26];
//        for (int from=0; from < 26; from++) {
//            for (int to=0; to < 26; to++) {
//                charToCounts[from] += graph[to][from];
//            }
//            min = Math.min(min, charToCounts[from]);
//        }
//
//        // Use minHeap to perform BFS traversal from letters that have fewest inbound to most
//        int visited[] = new int[26];
////        PriorityQueue<int[]> bfsPQ = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
//        ArrayDeque<int[]> bfsPQ = new ArrayDeque<>();
//        for (int i=0; i < 26; i++) {
//            if (dict.contains(i) && charToCounts[i] == min) {
//                bfsPQ.add(new int[]{i, charToCounts[i]});
//                visited[i] = 1;
//            }
//        }
//
//        StringBuffer priority = new StringBuffer();
//        while (!bfsPQ.isEmpty()) {
//            int[] charToCount = bfsPQ.remove();
//            int from = charToCount[0];
//            priority.append((char)(from+'a'));
//            for (int to=0; to < 26; to++) {
//                if (graph[from][to] == 1 && visited[to] == 0) {
//                    bfsPQ.add(new int[] {to, charToCounts[to]});
//                    visited[to] = 1;
//                }
//            }
//        }
//
//        return priority.toString();
//    }

    static class TopSortChars {
        static String topSort(String[] words) {
            HashMap<Character, List<Character>> pToC = new HashMap<>();
            HashMap<Character, List<Character>> cToP = new HashMap<>();
            HashSet<Character> alphabet = new HashSet<>();
            HashSet<Character> visited = new HashSet<>();
            boolean done = false;
            int charIndex=0;
            while(!done) {
                done=true;
                char lastChar = '-';
                String lastWord = null;
                for(String word: words) {
                    if(word.length()<(charIndex+1))
                        continue;
                    char newChar = word.charAt(charIndex);
                    if(word.length()>(charIndex+1))
                        done = false;
                    if(charIndex==0 || (lastWord!=null && lastWord.charAt(charIndex-1)== word.charAt(charIndex-1))) {
                        if(lastChar!=newChar && lastChar!='-') {
                            List<Character> parentChars = cToP.getOrDefault(newChar, new LinkedList<Character>());
                            parentChars.add(lastChar);
                            cToP.put(newChar, parentChars);
                            List<Character> childChars = pToC.getOrDefault(lastChar, new LinkedList<Character>());
                            childChars.add(newChar);
                            pToC.put(lastChar, childChars);
                        }
                    }
                    lastChar = word.charAt(charIndex);
                    lastWord = word;
                    alphabet.add(lastChar);
                }
                charIndex++;
            }
            StringBuffer sb = new StringBuffer();
            while(sb.length()!=alphabet.size()) {
                for(Character c: alphabet) {
                    if(visited.contains(c)==false && (!cToP.containsKey(c) || cToP.get(c).isEmpty())) {
                        sb.append(c);
                        visited.add(c);
                        updateRelationships(c, pToC, cToP);
                    }
                }
            }
            return sb.toString();
        }
        static void updateRelationships(char parent, HashMap<Character, List<Character>> pToC, HashMap<Character, List<Character>> cToP) {
            if(pToC.get(parent)==null)
                return;
            for(Character child: pToC.get(parent)) {
                List<Character> parentList = cToP.get(child);
                int index = parentList.indexOf(parent);
                if(index>=0)
                    parentList.remove(index);
            }
        }
        public static void main(String[] args) {
            String[] input = new String[] { "acedgih", "acedgib", "acedgb", "acedf", "acef", "acf", "ad", "d"};
            String result = topSort(input);
            System.out.println("Top Sort:"+result);
        }
    }
}
