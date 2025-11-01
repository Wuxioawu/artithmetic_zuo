package LeetCode;

public class Code240 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rowLeft = 0;
        int rowRight = matrix[0].length - 1;
        int colLeft = 0;
        int colRight = matrix.length - 1;
        while (rowLeft <= rowRight || colLeft <= colRight) {
            int rowMid = rowLeft + (rowRight - rowLeft) / 2;
            int colMid = colLeft + (colRight - colLeft) / 2;
            int[][] checkOutOfBound = checkOutOfBound(matrix, rowMid, colMid);
            rowMid = checkOutOfBound[0][1];
            colMid = checkOutOfBound[0][0];

            if (matrix[colMid][rowMid] < target) {
                rowLeft = rowMid + 1;

                if (matrix[colMid][rowMid] < target) {
                    colLeft = colMid + 1;
                } else if (matrix[colMid][rowMid] > target) {
                    colRight = colRight - 1;
                } else {
                    return true;
                }

            } else if (matrix[colMid][rowMid] > target) {
                rowRight = rowRight - 1;
                if (matrix[colMid][rowMid] < target) {
                    colLeft = colMid + 1;
                } else if (matrix[colMid][rowMid] > target) {
                    colRight = colRight - 1;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }

        return false;
    }

    private static int[][] checkOutOfBound(int[][] matrix, int row, int col) {
        int[][] lastIndex = new int[1][2];
        lastIndex[0][0] = col < 0 ? 0 : Math.min(col, matrix.length - 1);
        lastIndex[0][1] = row < 0 ? 0 : Math.min(row, matrix[0].length - 1);
        return lastIndex;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int[][] matrix2 = {{1, 4}, {2, 5}};
        boolean b = searchMatrix(matrix2, 2);
        System.out.println(b);
    }
}
