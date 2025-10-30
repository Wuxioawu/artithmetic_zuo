package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code381 {
    static class RandomizedCollection {
        HashMap<Integer, HashSet<Integer>> indexMap;
        ArrayList<Integer> valueList;

        public RandomizedCollection() {
            indexMap = new HashMap<>();
            valueList = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (indexMap.containsKey(val)) {
                var indexSet = indexMap.get(val);
                valueList.addLast(val);
                indexSet.add(valueList.size() - 1);
                indexMap.put(val, indexSet);
                return false;
            }

            HashSet<Integer> indexSet = new HashSet<>();
            valueList.addLast(val);
            indexSet.add(valueList.size() - 1);
            indexMap.put(val, indexSet);
            return true;
        }

        public boolean remove(int val) {
            if (!indexMap.containsKey(val)) {
                return false;
            }

            int lasValue = valueList.getLast();
            int lastIndex = valueList.size() - 1;

            var indexSet = indexMap.get(val);
            int index = indexSet.iterator().next();

            valueList.set(index, lasValue);
            if (indexSet.size() == 1) {
                indexMap.remove(val);
            } else {
                indexSet.remove(index);
                indexMap.put(val, indexSet);
            }


            HashSet<Integer> lastNodeIndexSet = indexMap.get(lasValue);
            if (lastNodeIndexSet != null) {
                lastNodeIndexSet.add(index);
                lastNodeIndexSet.remove(lastIndex);
                indexMap.put(lasValue, lastNodeIndexSet);
            }
            valueList.removeLast();
            return true;
        }

        public int getRandom() {
            int index = (int) (Math.random() * (valueList.size()));
            return valueList.get(index);
        }
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        randomizedCollection.insert(1);
        randomizedCollection.insert(1);
        randomizedCollection.insert(2);
        randomizedCollection.insert(1);
        randomizedCollection.insert(2);
        randomizedCollection.insert(2);
        randomizedCollection.remove(1);
        randomizedCollection.remove(2);
        randomizedCollection.remove(2);
        randomizedCollection.remove(2);
        for (int i = 0; i < 11; i++) {
            System.out.println(randomizedCollection.getRandom());
        }
    }
}
