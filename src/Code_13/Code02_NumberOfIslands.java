package Code_13;

import tools.Constants;
import tools.NumberOperation;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/description/">200. Number of Islands</a>
 */
public class Code02_NumberOfIslands {
    static class Dot {
    }

    private static int numIsLands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        ArrayList<Dot> dots = new ArrayList<>();
        Dot[][] dotArray = new Dot[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    Dot current = new Dot();
                    dots.add(current);
                    dotArray[i][j] = current;
                }
            }
        }

        UnionFind unionFind = new UnionFind(dots);
        for (int i = 1; i < grid.length; i++) {
            if (dotArray[i][0] != null && dotArray[i - 1][0] != null) {
                unionFind.union(dotArray[i][0], dotArray[i - 1][0]);
            }
        }

        for (int i = 1; i < grid[0].length; i++) {
            if (dotArray[0][i] != null && dotArray[0][i - 1] != null) {
                unionFind.union(dotArray[0][i], dotArray[0][i - 1]);
            }
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (dotArray[i][j] != null && dotArray[i - 1][j] != null) {
                    unionFind.union(dotArray[i][j], dotArray[i - 1][j]);
                }
                if (dotArray[i][j] != null && dotArray[i][j - 1] != null) {
                    unionFind.union(dotArray[i][j], dotArray[i][j - 1]);
                }
            }
        }
        return unionFind.sets;

    }

    private static class UnionFind {

        private final HashMap<Dot, Dot> parent;
        private final HashMap<Dot, Integer> size;
        private int sets;

        public UnionFind(List<Dot> list) {
            parent = new HashMap<Dot, Dot>();
            size = new HashMap<Dot, Integer>();
            for (Dot dot : list) {
                parent.put(dot, dot);
                size.put(dot, 1);
            }
            sets = list.size();
        }

        private Dot findFather(Dot currentNode) {
            Stack<Dot> stack = new Stack<Dot>();

            while (currentNode != parent.get(currentNode)) {
                stack.push(currentNode);
                currentNode = parent.get(currentNode);
            }

            while (!stack.isEmpty()) {
                parent.put(stack.pop(), currentNode);
            }

            return currentNode;
        }

        public void union(Dot a, Dot b) {
            Dot fatherA = findFather(a);
            Dot fatherB = findFather(b);

            if (fatherA == fatherB) return;

            int sizeA = size.get(fatherA);
            int sizeB = size.get(fatherB);

            Dot bigSizeNode = sizeA > sizeB ? fatherA : fatherB;
            Dot smallSizeNode = fatherA == bigSizeNode ? fatherB : fatherA;

            parent.put(smallSizeNode, bigSizeNode);
            size.put(bigSizeNode, sizeA + sizeB);
            sets--;
        }
    }

    private static int numIsLandsByBruteForce(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    infection(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void infection(char[][] grid, int i, int j) {
        if (i >= grid.length || i < 0 || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;

        grid[i][j] = 0;
        infection(grid, i + 1, j);
        infection(grid, i, j + 1);
        infection(grid, i - 1, j);
        infection(grid, i, j - 1);
    }

    private static int numIsLands3(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        UnionFindByArray unionFind = new UnionFindByArray(grid);

        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                unionFind.union(0, i, 0, i - 1);
            }
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && grid[i - 1][j] == '1') {
                    unionFind.union(i, j, i - 1, j);
                }
                if (grid[i][j] == '1' && grid[i][j - 1] == '1') {
                    unionFind.union(i, j, i, j - 1);
                }
            }
        }
        return unionFind.sets;
    }

    private static class UnionFindByArray {
        private int[] parent;
        private int[] size;
        private int sets;
        private int[] help;
        int clo;

        public UnionFindByArray(char[][] grid) {
            int length = grid.length * grid[0].length;
            clo = grid[0].length;
            parent = new int[length];
            size = new int[length];
            help = new int[length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        parent[getIndex(i, j)] = getIndex(i, j);
                        size[getIndex(i, j)]++;
                        sets++;
                    }
                }
            }
        }

        private int getIndex(int i, int j) {
            return i * clo + j;
        }

        private int getFather(int currrentNode) {
            int helpIndex = 0;

            while (parent[currrentNode] != currrentNode) {
                help[helpIndex] = currrentNode;
                currrentNode = parent[currrentNode];
            }

            while (helpIndex > 0) {
                parent[help[--helpIndex]] = currrentNode;
            }
            return currrentNode;
        }

        public void union(int i_1, int j_1, int i_2, int j_2) {
            int index_1 = getIndex(i_1, j_1);
            int index_2 = getIndex(i_2, j_2);

            int father_1 = getFather(index_1);
            int father_2 = getFather(index_2);

            if (father_1 == father_2) return;

            int size_1 = size[father_1];
            int size_2 = size[father_2];

            int bigSizeNode = size_1 > size_2 ? father_1 : father_2;
            int smallSizeNode = bigSizeNode == father_1 ? father_2 : father_1;

            parent[smallSizeNode] = bigSizeNode;
            size[bigSizeNode] = size_2 + size_1;
            sets--;
        }
    }

    private static char[][] getRandomInput(int maxM, int maxN) {
        int m = NumberOperation.getRandomNumber(maxM);
        int n = NumberOperation.getRandomNumber(maxN);
        char[][] result = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = NumberOperation.isRandomGreaterThanValue(0.5) ? '0' : '1';
            }
        }
        return result;
    }

    private static char[][] copyArray(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return grid;
        char[][] result = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                result[i][j] = grid[i][j];
            }
        }
        return result;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            char[][] input = getRandomInput(10, 10);
            char[][] copy = copyArray(input);
            int numIsLands = numIsLands3(input);
            int numIsLandsByBruteForce = numIsLandsByBruteForce(copy);

            if (numIsLands != numIsLandsByBruteForce) {
                System.out.println("numIsLands = " + numIsLands + ", numIsLandsByBruteForce = " + numIsLandsByBruteForce);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
