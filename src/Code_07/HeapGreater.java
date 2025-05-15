package Code_07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater<T> {

    private final ArrayList<T> heap;
    private final HashMap<T, Integer> indexMap;
    private int heapSize;
    private final Comparator<? super T> comparator;

    public HeapGreater(Comparator<T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    public T peek() {
        return heap.getFirst();
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T result = heap.getFirst();
        swap(0, --heapSize);
        heapify(0);

        indexMap.remove(result);
        heap.remove(result);

        return result;
    }

    public void remove(T object) {
        if (isEmpty()) {
            return;
        }

        if (heapSize < 3) {
            heap.remove(object);
            indexMap.remove(object);
            heapSize--;
            return;
        }

        Integer removeIndex = indexMap.get(object);
        T needModify = heap.get(heapSize - 1);
        swap(removeIndex, --heapSize);
        resign(needModify);


        heap.remove(object);
        indexMap.remove(object);
    }


    public void resign(T object) {
        int index = indexMap.get(object);
        heapInsert(index);
        heapify(index);
    }

    public List<T> getAllElements() {
        List<T> elements = new ArrayList<>();
        for (int i = 0; i < heapSize; i++) {
            elements.add(heap.get(i));
        }
        return elements;
    }

    private void heapInsert(int index) {
        int parentIndex = (index - 1) / 2;
        T current = heap.get(index);
        T partent = heap.get(parentIndex);

        while (partent != null && comparator.compare(current, partent) > 0) {
            swap(index, parentIndex);
            index = parentIndex;

            current = heap.get(index);
            parentIndex = (index - 1) / 2;
            partent = heap.get(parentIndex);
        }

    }

    private void heapify(int index) {
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        int largestChildIndex = rightChildIndex < heapSize &&
                comparator.compare(heap.get(leftChildIndex), heap.get(rightChildIndex)) < 0 ?
                rightChildIndex : leftChildIndex;

        while (largestChildIndex < heapSize && comparator.compare(heap.get(index), heap.get(largestChildIndex)) < 0) {
            swap(index, largestChildIndex);
            index = largestChildIndex;

            leftChildIndex = index * 2 + 1;
            rightChildIndex = index * 2 + 2;
            largestChildIndex = rightChildIndex < heapSize &&
                    comparator.compare(heap.get(leftChildIndex), heap.get(rightChildIndex)) < 0 ?
                    rightChildIndex : leftChildIndex;
        }
    }

    //swap the indexMap and heap index
    private void swap(int i, int j) {
        T object_i = heap.get(i);
        T object_j = heap.get(j);

        heap.set(i, object_j);
        heap.set(j, object_i);

        indexMap.put(object_i, j);
        indexMap.put(object_j, i);
    }

    public void print() {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }

    public void getIndexMap() {
        indexMap.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
    }
}
