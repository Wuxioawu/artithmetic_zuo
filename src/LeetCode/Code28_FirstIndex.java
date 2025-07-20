package LeetCode;

//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/?envType=problem-list-v2&envId=string
public class Code28_FirstIndex {

    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return 0;
        if (haystack.contains(needle)) {
            char[] haystackCharArray = haystack.toCharArray();
            char[] needleCharArray = needle.toCharArray();
            int indexNeedle = 0;
            int indexHaystack = 0;
            while (indexNeedle < needleCharArray.length) {
                int currentIndex = indexHaystack;
                while (haystackCharArray[indexHaystack] == needleCharArray[indexNeedle]) {
                    indexNeedle++;
                    indexHaystack++;
                    if (indexNeedle == needleCharArray.length) {
                        return indexHaystack - needleCharArray.length;
                    }
                }
                indexNeedle = 0;
                indexHaystack = currentIndex + 1;
            }
        }
        return -1;
    }

    private static int method2(String haystack, String needle) {
        if (haystack == null || needle == null) return 0;
        for (int i = 0, j = needle.length() + 1; i < haystack.length(); i++, j++) {
            if (haystack.subSequence(i, j).equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issip";

        int i = strStr(haystack, needle);
        System.out.println(i);
    }
}
