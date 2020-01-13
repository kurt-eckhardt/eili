package eili.leet.leet100;

import java.util.*;
import java.util.stream.Collectors;

public class Leet0140 {


    public static void main(String[] args) {
//      wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog", "icat", "icats"));
//      wordBreak("pineapplepenapple", List.of("apple", "pen", "applepen", "pine", "pineapple"));
//      wordBreak("catsandog", List.of("cats","dog","sand","and","cat"));
      wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        ,List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
    }

//    <Start Index, Sentences>
//    3 -> ((g))
//    2 -> ((og), (o,g))
//    1 -> ((dog), (do,g), (d,og), (d,o,g))
    public static Map<Integer, List<List<String>>> memos = new HashMap<>();

    public static List<String> wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);

        memos.clear();
        int maxWordLen = maxWordLen(wordDict);
        char[] chars   = s.toCharArray();
        for (int startIdx = chars.length-1; startIdx >= 0; startIdx--) {
            int maxEndIdx = Math.min(startIdx+maxWordLen, s.length());
            for (int endIdx = startIdx; endIdx < maxEndIdx; endIdx++) {
                String word = s.substring(startIdx, endIdx+1);
                if (dict.contains(word)) {
                    if (endIdx == chars.length-1 || memos.containsKey(endIdx+1)) {
                        //System.out.println("Adding word="+word+" to sentences="+memos.get(endIdx+1));
                        addWordToSentence(startIdx, word, memos.get(endIdx+1));
                    }
                }
            }
        }

        System.out.println(memos);
        List<String> fullSentences = new ArrayList<>();
        for (List<String> sentence : memos.getOrDefault(0, List.of())) {
            fullSentences.add(String.join(" ", sentence));
        }

        return fullSentences;
    }

    private static void addWordToSentence(int index, String word, List<List<String>> sentences) {
        List<List<String>> sentenceList = memos.getOrDefault(index, new ArrayList<>());

        if (sentences == null) {
            List<String> newSentence = new ArrayList<String>();
            newSentence.add(word);
            sentenceList.add(newSentence);
        } else {
            for (List<String> sentence : sentences) {
                List<String> newSentence = new ArrayList<>();
                newSentence.add(word);
                newSentence.addAll(sentence);
                sentenceList.add(newSentence);
            }
        }

        memos.put(index, sentenceList);
    }


    public static int maxWordLen(List<String> wordDict) {
        int max = 0;
        for (String word : wordDict) {
            max = Math.max(max, word.length());
        }
        return max;
    }
}
