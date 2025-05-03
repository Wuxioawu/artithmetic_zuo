package Code_02;

import tools.Constants;
import tools.NumberOperation;

/**
 * ~num + 1 == -num
 * ToDo:search Original Code, Ones' complement Two's complement
 * example:
 * ~num: Invert all bits
 * → 11111010 (this is the ones' complement of 5)
 * ~num + 1: Add 1
 * → 11111010 + 1 = 11111011 (this is the two’s complement, i.e., -5)
 *
 *
 */
public class Code03_ExtractRightOne {

    public static int getRightMostOne(int number) {
        if(number == 1 || number == 0) return number;

        return number & (~number + 1);
    }

    public static int bitOneCounts(int N) {
        int count = 0;
        if(N == 0) return count;

        while (N != 0) {
            int rightMostOne = getRightMostOne(N);
            count++;
            N ^= rightMostOne;
        }
        return count;
    }

    public static void test() {

    }


    public static void main(String[] args) {
        test();
    }
}
