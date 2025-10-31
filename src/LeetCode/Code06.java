package LeetCode;

public class Code06 {

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int N = s.length();
        int basic = numRows * 2 - 2;
        StringBuilder res = new StringBuilder();

        // the first line
        int currentIndex = 0;
        while (currentIndex < N) {
            res.append(s.charAt(currentIndex));
            currentIndex += basic;
        }

        for (int row = 2; row < numRows; row++) {
            currentIndex = row - 1;
            int followIndex = 2 * numRows - (row + 1);
            while (currentIndex < N && followIndex < N) {
                res.append(s.charAt(currentIndex));
                res.append(s.charAt(followIndex));
                currentIndex += basic;
                followIndex += basic;
            }

            if (currentIndex < N) {
                res.append(s.charAt(currentIndex));
            }
        }

        // the last line
        currentIndex = numRows - 1;
        while (currentIndex < N) {
            res.append(s.charAt(currentIndex));
            currentIndex += basic;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "A";
        String convert = convert(s, 1);
        System.out.println(convert);


    }
}
