package LeetCode;

import java.util.Arrays;

public class Code274 {
    public static int hIndex(int[] citations) {
        int N = citations.length;
        if (N == 1) return citations[0] >= 1 ? 1 : 0;
        int result = 0;
        Arrays.sort(citations);
        for (int i = N - 1; i >= 0; i--) {
            if (citations[i] <= N) {
                if (N - i >= citations[i]) {
                    result = Math.max(result, citations[i]);
                } else {
                    result = Math.max(result, N - i);
                }
            }
        }
        return result;
    }

    public static int hIndex2(int[] citations) {
        Arrays.sort(citations);

        int h = 0;
        int rest;
        int N = citations.length;
        for (int i = 0; i < N; i++) {
            rest = N - i;
            if (rest <= citations[i]) {
                h = rest;
                break;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        int[] citations = {0, 0, 2};
        int i1 = hIndex2(citations);
        System.out.println(i1);
    }
}
