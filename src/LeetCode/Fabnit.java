package LeetCode;

import tools.NumberOperation;

public class Fabnit {

    private static int fab(int index) {
        if(index == 1) {
            return 0;
        }
        if(index == 2) {
            return 1;
        }
        return fab(index - 1) + fab(index - 2);
    }

    private static int process(int index) {
        int length = Math.max(index, 2);
        int[] arr = new int[length];
        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i < index; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[index - 1];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int num = NumberOperation.getRandomNumber(10) + 1;
            int fab1 = fab(num);
            int fab2 = process(num);
            if(fab1 != fab2) {
                System.out.println(fab1 + " " + fab2);
                System.out.println("it is wrong");
                return;
            }
        }

        System.out.println("success!!");
    }
}
