package LeetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// todo: the monotonic stack solution same with the code1526
public class Code42 {

    public int trap(int[] height) {
        int[][] leftRightMax = new int[height.length][2];
        //use the monotonic stack to find the left max and right max for each index

        Stack<List<Integer>> stack = new Stack<>();

        for (int i = 0; i < height.length; i++) {


            if (stack.isEmpty() || false) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                stack.push(list);
            } else {

            }

        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
