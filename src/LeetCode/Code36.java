package LeetCode;

import java.util.Arrays;
import java.util.HashSet;

public class Code36 {

    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character> rowSet = new HashSet<>();
        HashSet<Character> colSet = new HashSet<>();
        HashSet<Character> boxSet1 = new HashSet<>();
        HashSet<Character> boxSet2 = new HashSet<>();
        HashSet<Character> boxSet3 = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (rowSet.contains(board[i][j])) return false;
                else if (board[i][j] != '.') {
                    rowSet.add(board[i][j]);
                }

                if (colSet.contains(board[j][i])) return false;
                else if (board[j][i] != '.') {
                    colSet.add(board[j][i]);
                }

                if (board[i][j] == '.') continue;

                if (j < 3) {
                    if (boxSet1.contains(board[i][j])) return false;
                    else boxSet1.add(board[i][j]);
                } else if (j < 6) {
                    if (boxSet2.contains(board[i][j])) return false;
                    else boxSet2.add(board[i][j]);
                } else {
                    if (boxSet3.contains(board[i][j])) return false;
                    else boxSet3.add(board[i][j]);
                }
            }
            //check the column
            rowSet.clear();
            if ((i + 1) % 3 == 0) {
                boxSet1.clear();
                boxSet2.clear();
                boxSet3.clear();
            }
            colSet.clear();
        }
        return true;
    }

    public static boolean isValidSudoku2(char[][] board) {
        boolean[][] visited = new boolean[5][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    if (visited[3][board[j][i] - '0']) return false;
                    else visited[3][board[j][i] - '0'] = true;
                }
                if (board[i][j] == '.') continue;
                int index = board[i][j] - '0';
                if (visited[4][index]) return false;
                else if (board[i][j] != '.') {
                    visited[4][index] = true;
                }
                if (visited[j / 3][index]) return false;
                else visited[j / 3][index] = true;
            }
            //check the column
            Arrays.fill(visited[4], false);
            if ((i + 1) % 3 == 0) {
                Arrays.fill(visited[0], false);
                Arrays.fill(visited[1], false);
                Arrays.fill(visited[2], false);
            }
            Arrays.fill(visited[3], false);
        }
        return true;
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
        };

        char[][] board2 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };

//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                System.out.print(board[i][j] + ' ');
//            }
//            System.out.println();
//        }

        boolean validSudoku = isValidSudoku2(board2);
        System.out.println(validSudoku);

    }
}
