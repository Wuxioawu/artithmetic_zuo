package Code_13;

import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/number-of-provinces/">LetCode547</a>
 */
public class Code01_FriendCircles {

    private static class UnionFind {
        private final int[] parent;
        private final int[] size;
        private final int[] help;
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            sets = n;
        }

        private int findFather(int currentNode) {
            int helpIndex = 0;
            while (currentNode != parent[currentNode]) {
                help[helpIndex++] = currentNode;
                currentNode = parent[currentNode];
            }
            while (helpIndex > 0) {
                parent[help[--helpIndex]] = currentNode;
            }
            return currentNode;
        }

        public void union(int p, int q) {
            int fatherP = findFather(p);
            int fatherQ = findFather(q);

            if (fatherP == fatherQ) {
                return;
            }

            int sizeP = size[fatherP];
            int sizeQ = size[fatherQ];

            int bigSizeNode = sizeP > sizeQ ? fatherP : fatherQ;
            int smallSizeNode = bigSizeNode == fatherP ? fatherQ : fatherP;

            parent[smallSizeNode] = bigSizeNode;
            size[bigSizeNode] = sizeQ + sizeP;
            sets--;
        }

        public int getSets() {
            return sets;
        }
    }

    private static int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSets();
    }

    private static int findCircleDFS(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] isConnected, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                dfs(isConnected, visited, j);
            }
        }
    }

    private static int findCircleBFS(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    visited[node] = true;
                    for (int j = 0; j < isConnected.length; j++) {
                        if (isConnected[node][j] == 1 && !visited[j]) {
                            queue.offer(j);
                        }
                    }
                }
                count++;
            }

        }
        return count;
    }


    // for test
    private static int[][] getRandomRect(int maxSize) {
        int n = NumberOperation.getRandomNumber(maxSize);
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = NumberOperation.isRandomGreaterThanValue(0.7) ? 1 : 0;
                    res[j][i] = res[i][j] == 1 ? 1 : 0;
                }
            }
        }
        return res;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[][] random = getRandomRect(10);
            int circleNum = findCircleNum(random);
            int circleDFS = findCircleDFS(random);
            int circleBFS = findCircleBFS(random);

            if (circleNum != circleDFS || circleNum != circleBFS) {
                System.out.println("circleNum: " + circleNum + " circleDFS: " + circleDFS);
                System.out.println(Constants.CODE_ERROR);
                return;
            }

        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
