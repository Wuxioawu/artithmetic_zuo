package Code_13;

public class UnionFindByArray implements UnionInterface<Integer> {

    private final int[] parents = new int[100001];
    private final int[] size = new int[100001];

    private final int[] help = new int[100001];

    public UnionFindByArray(int n) {
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    private int findFather(int currentNode) {
        int helpIndex = 0;
        while (parents[currentNode] != currentNode) {
            help[helpIndex++] = currentNode;
            currentNode = parents[currentNode];
        }
        while (helpIndex > 0) {
            parents[help[helpIndex--]] = currentNode;
        }
        return currentNode;
    }


    @Override
    public boolean isSame(Integer a, Integer b) {
        return findFather(a) == findFather(b);
    }

    @Override
    public void union(Integer a, Integer b) {
        int fatherA = findFather(a);
        int fatherB = findFather(b);

        if (fatherA == fatherB) {
            return;
        }

        int sizeA = size[fatherA];
        int sizeB = size[fatherB];

        int bigSizeNode = sizeA > sizeB ? fatherA : fatherB;
        int smallSizeNode = bigSizeNode == fatherB ? fatherA : fatherB;
        parents[smallSizeNode] = bigSizeNode;
        size[bigSizeNode] += sizeA;
    }
}
