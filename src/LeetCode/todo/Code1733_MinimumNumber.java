package LeetCode.todo;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Code1733_MinimumNumber {

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        HashMap<Integer, HashSet<Integer>> langMap = new HashMap<>();
        // build the table to reflect the uses and languages
        for (int i = 0; i < languages.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < languages[i].length; j++) {
                set.add(languages[i][j]);
            }
            langMap.put(i + 1, set);
        }

        // who need to learn language
        boolean[] canTalk = new boolean[friendships.length];
        for (int i = 0; i <= n; i++) {
            for (int f = 0; f < friendships.length; f++) {
                int f1 = friendships[f][0];
                int f2 = friendships[f][1];
                if (langMap.get(f1).contains(i) && langMap.get(f2).contains(i)) {
                    canTalk[i] = true;
                }
            }
        }

        int minTeach = friendships.length;
        for (int i = 1; i <= n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int f = 0; f < friendships.length; f++) {
                if (!canTalk[f]) {
                    int f1 = friendships[f][0];
                    int f2 = friendships[f][1];

                    if(!langMap.get(f1).contains(i)) {
                        set.add(f1);
                    }
                    if(!langMap.get(f2).contains(i)) {
                        set.add(f1);
                    }
                }
            }
            minTeach = Math.min(minTeach, set.size());
        }
        return minTeach;
    }


    public static void main(String[] args) {

    }
}
