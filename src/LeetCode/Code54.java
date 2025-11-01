package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Code54 {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        int size = matrix.length * matrix[0].length;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right && size > 0; i++) {
                result.add(matrix[top][i]);
                size--;
            }
            top++;

            for (int i = top; i <= bottom && size > 0; i++) {
                result.add(matrix[i][right]);
                size--;
            }
            right--;

            for (int i = right; i >= left && size > 0; i--) {
                result.add(matrix[bottom][i]);
                size--;
            }

            bottom--;
            for (int i = bottom; i >= top && size > 0; i--) {
                result.add(matrix[i][left]);
                size--;
            }
            left++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
        };
        List<Integer> integers = spiralOrder(matrix);
        System.out.println(integers);
    }
}
