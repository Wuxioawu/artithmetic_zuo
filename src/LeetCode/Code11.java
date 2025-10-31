package LeetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Code11 {

    public int maxArea(int[] height) {

        Stack<List<Integer>> stack = new Stack<>();
        int[][] res = new int[height.length][2];
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek().getLast()]) {
                List<Integer> pop = stack.pop();
                for (Integer index : pop) {
                    res[index][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                    res[index][1] = i;
                }
            }

            if (stack.isEmpty() || height[i] < height[stack.peek().getLast()]) {
                List<Integer> list = new LinkedList<>();
                list.add(i);
                stack.push(list);
            } else {
                //pop

            }
        }
        return Math.max(res[0][0], res[0][1]);
    }

    public static int maxArea2(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int maxArea = 0;

        while (l < r) {
            int width = r - l;
            int currentHeight;
            if (height[l] < height[r]) {
                l++;
                currentHeight = height[l];
            } else {
                r--;
                currentHeight = height[r];
            }
            int currentArea = currentHeight * width;
            maxArea = Math.max(maxArea, currentArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int i1 = maxArea2(height);
        System.out.println(i1);
    }
}
