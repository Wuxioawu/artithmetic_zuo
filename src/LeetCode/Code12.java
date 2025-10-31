package LeetCode;

public class Code12 {

    public static String intToRoman(int num) {
        StringBuilder res = new StringBuilder();

        int firstBit = num / 1000;
        while (firstBit-- > 0) {
            res.append("M");
        }

        int secondBit = (num % 1000) / 100;
        if (secondBit == 9) {
            res.append("CM");
        } else if (secondBit >= 5) {
            res.append("D");
            secondBit -= 5;
            while (secondBit-- > 0) {
                res.append("C");
            }
        } else if (secondBit == 4) {
            res.append("CD");
        } else {
            while (secondBit-- > 0) {
                res.append("C");
            }
        }

        int thirdBit = (num % 100) / 10;
        if (thirdBit == 9) {
            res.append("XC");
        } else if (thirdBit >= 5) {
            res.append("L");
            thirdBit -= 5;
            while (thirdBit-- > 0) {
                res.append("X");
            }
        } else if (thirdBit == 4) {
            res.append("XL");
        } else {
            while (thirdBit-- > 0) {
                res.append("X");
            }
        }

        int fourthBit = num % 10;
        if (fourthBit == 9) {
            res.append("IX");
        } else if (fourthBit >= 5) {
            res.append("V");
            fourthBit -= 5;
            while (fourthBit-- > 0) {
                res.append("I");
            }
        } else if (fourthBit == 4) {
            res.append("IV");
        } else {
            while (fourthBit-- > 0) {
                res.append("I");
            }
        }
        return res.toString();
    }


    public static void main(String[] args) {
        int num = 58;
        String string = intToRoman(num);
        System.out.println(string);
    }
}
