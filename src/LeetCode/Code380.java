package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;

public class Code380 {
    static class RandomizedSet {

        HashMap<Integer, Integer> indexMap;
        ArrayList<Integer> valueList;

        public RandomizedSet() {
            indexMap = new HashMap();
            valueList = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (indexMap.containsKey(val)) {
                return false;
            }
            valueList.addLast(val);
            indexMap.put(val, valueList.indexOf(val));
            return true;
        }

        public boolean remove(int val) {
            if (!indexMap.containsKey(val)) {
                return false;
            }

            Integer i = indexMap.get(val);
            int lastValue = valueList.getLast();

            valueList.set(i, lastValue);
            valueList.removeLast();

            indexMap.put(lastValue, i);
            indexMap.remove(val);
            return true;
        }

        public int getRandom() {
            int index = (int) (Math.random() * (valueList.size()));
            return valueList.get(index);
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.remove(2);
        randomizedSet.insert(2);
        System.out.println(randomizedSet.getRandom());
        randomizedSet.remove(1);
        randomizedSet.insert(2);
        System.out.println(randomizedSet.getRandom());
    }
}
