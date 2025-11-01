package LeetCode;

public class Code74 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] < target) {
                left = mid + 1;
            } else if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        int row = matrix[left][0] > target ? Math.max(left - 1, 0) : left; // right
        left = 0;
        right = matrix[0].length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[row][mid] < target) {
                left = mid + 1;
            } else if (matrix[row][mid] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

    // convert to 1D array and do binary search
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int left = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int right = N * M - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / M][mid % M];
            if (midValue < target) {
                left = mid + 1;
            } else if (midValue > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int[][] matrix2 = {
                {1}
        };
        boolean result = searchMatrix(matrix, 11);
        System.out.println(result);  // true
    }
}
