package Code_13;

import tools.Constants;
import tools.NumberOperation;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/number-of-islands-ii/">收费题目</a>
 */
public class Code03_NumberOfIslands {

    private static List<Integer> numIslands(int m, int n, int[][] position) {
        UnionFindByArray unionFindByArray = new UnionFindByArray(m, n);

        List<Integer> res = new ArrayList<>();
        for (int[] pos : position) {
            unionFindByArray.connected(pos[0], pos[1]);
            res.add(unionFindByArray.sets);
        }

        return res;
    }

    private static class UnionFindByArray {
        private final int[] parent;
        private final int[] size;
        private final int[] help;

        private final int row, col;
        private int sets;

        public UnionFindByArray(int m, int n) {
            row = m;
            col = n;

            int length = m * n;
            parent = new int[length];
            Arrays.fill(parent, -1);
            size = new int[length];
            Arrays.fill(size, -1);
            help = new int[length];
            Arrays.fill(help, -1);
            sets = 0;
        }

        // must use getIndex
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

        private int getIndex(int i, int j) {
            return i * col + j;
        }

        private boolean checkValid(int i, int j) {
            return i >= 0 && j >= 0 && i < row && j < col;
        }

        private void union(int i1, int j1, int i2, int j2) {
            if (!checkValid(i1, j1) || !checkValid(i2, j2)) {
                return;
            }

            int index_1 = getIndex(i1, j1);
            int index_2 = getIndex(i2, j2);
            if (size[index_1] == -1 || size[index_2] == -1) return;

            int parent_1 = findFather(index_1);
            int parent_2 = findFather(index_2);

            if (parent_1 == parent_2) return;

            int size_1 = size[parent_1];
            int size_2 = size[parent_2];

            int bigSizeNode = size_1 > size_2 ? parent_1 : parent_2;
            int smallSizeNode = bigSizeNode == parent_1 ? parent_2 : parent_1;
            parent[smallSizeNode] = bigSizeNode;
            size[bigSizeNode] = size_1 + size_2;
            sets--;
        }

        public void connected(int i, int j) {
            int index_1 = getIndex(i, j);

            if (size[index_1] == -1) {
                parent[index_1] = index_1;
                size[index_1] = 1;
                sets++;

                union(i - 1, j, i, j);
                union(i + 1, j, i, j);
                union(i, j - 1, i, j);
                union(i, j + 1, i, j);
            }
        }

    }

    private static List<Integer> numIslands2(int m, int n, int[][] position) {
        UnionFindByMap unionFindByMap = new UnionFindByMap(m, n);
        List<Integer> res = new ArrayList<>();

        HashMap<Integer, List<Integer>> repeat = new HashMap<>();

        for (int[] pos : position) {
            // deleted repeated elements
            if (!repeat.containsKey(pos[0])) {
                ArrayList list = new ArrayList<Integer>();
                list.add(pos[1]);
                repeat.put(pos[0], list);
            } else {
                if (repeat.get(pos[0]).contains(pos[1])) {
                    res.add(unionFindByMap.sets);
                    continue;
                } else {
                    repeat.get(pos[0]).add(pos[1]);
                }
            }
            unionFindByMap.connect(pos[0], pos[1]);
            res.add(unionFindByMap.sets);
        }
        return res;
    }

    private static class UnionFindByMap {

        private HashMap<Integer, Integer> parent;
        private HashMap<Integer, Integer> size;
        private int sets;
        private int clo, row;

        public UnionFindByMap(int m, int n) {
            clo = n;
            row = m;
            parent = new HashMap<>();
            size = new HashMap<>();
            sets = 0;
        }

        private int getIndex(int i, int j) {
            if (i < 0 || j < 0 || i >= row || j >= clo)
                return -1;
            else
                return i * clo + j;
        }

        private Integer getFather(Integer currentNode) {
            Stack<Integer> stack = new Stack<>();
            while (currentNode != parent.get(currentNode)) {
                stack.push(currentNode);
                currentNode = parent.get(currentNode);
            }

            while (!stack.isEmpty()) {
                parent.put(stack.pop(), currentNode);
            }

            return currentNode;
        }

        private void union(int currentNode1, int currentNode2) {

            Integer father_1 = getFather(currentNode1);
            Integer father_2 = getFather(currentNode2);

            if (father_1 == null || father_2 == null || father_2 == father_1) return;

            Integer size_1 = size.get(father_1);
            Integer size_2 = size.get(father_2);
            if (size_1 == null || size_2 == null) return;

            Integer bigSizeNode = size_1 > size_2 ? father_1 : father_2;
            Integer smallSizeNode = bigSizeNode == father_1 ? father_2 : father_1;

            parent.put(smallSizeNode, bigSizeNode);
            size.put(bigSizeNode, size_1 + size_2);

            sets--;
        }

        public void connect(int i, int j) {
            int currentNode = getIndex(i, j);

            if (size.containsKey(currentNode)) {
                return;
            }

            parent.put(currentNode, currentNode);
            size.put(currentNode, 1);
            sets++;

            union(getIndex(i - 1, j), getIndex(i, j));
            union(getIndex(i + 1, j), getIndex(i, j));
            union(getIndex(i, j - 1), getIndex(i, j));
            union(getIndex(i, j + 1), getIndex(i, j));
        }
    }

    // for test
    private static int[][] getRandomPosition(int m, int n) {
        int size = NumberOperation.getRandomNumber(m * n);

        int[][] position = new int[size][2];
        for (int i = 0; i < size; i++) {
            position[i][0] = NumberOperation.getRandomNumber(m);
            position[i][1] = NumberOperation.getRandomNumber(n);
        }
        return position;
    }

    private static boolean isSameTwoList(List<Integer> list1, List<Integer> list2) {
        if (list1 == null && list2 == null) return true;
        if (list1 == null || list2 == null) return false;

        if (list1.size() != list2.size()) return false;

        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) return false;
        }
        return true;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int m = NumberOperation.getRandomNumber(5);
            int n = NumberOperation.getRandomNumber(5);
            int[][] position = getRandomPosition(m, n);

            List<Integer> num1 = numIslands(m, n, position);
            List<Integer> num2 = numIslands2(m, n, position);
            if (!isSameTwoList(num1, num2)) {
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
