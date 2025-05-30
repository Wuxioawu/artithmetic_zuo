package Code_13;


import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFindByMap<V> implements UnionInterface<V> {

    private final HashMap<V, V> parents;
    private HashMap<V, Integer> currentNodeSize;

    public UnionFindByMap(List<V> values) {
        parents = new HashMap<>();
        currentNodeSize = new HashMap<>();
        for (V v : values) {
            parents.put(v, v);
            currentNodeSize.put(v, 1);
        }
    }

    private V findAncestor(V current) {
        Stack<V> stack = new Stack<>();

        while (parents.get(current) != current) {
            stack.push(current);
            current = parents.get(current);
        }

        while (!stack.isEmpty()) {
            parents.put(stack.pop(), current);
        }
        return current;
    }

    @Override
    public boolean isSame(V a, V b) {
        return findAncestor(a) == findAncestor(b);
    }

    @Override
    public void union(V a, V b) {
        V aFather = findAncestor(a);
        V bFather = findAncestor(b);

        if (aFather == bFather) {
            return;
        }
        int sizeA = currentNodeSize.get(aFather);
        int sizeB = currentNodeSize.get(bFather);

        V bigSizeFather = sizeA > sizeB ? aFather : bFather;
        V smallSizeFather = bigSizeFather == aFather ? bFather : aFather;

        currentNodeSize.remove(smallSizeFather);
        currentNodeSize.put(bigSizeFather, sizeA + sizeB);
        parents.put(smallSizeFather, bigSizeFather);
    }
}




