package LeetCode;

import java.util.LinkedList;
import java.util.List;

public class Code228_SummaryRanges {

    public static List<String> summaryRanges(int[] nums) {
        List<String> resultList = new LinkedList<>();

        int startIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1 || i + 1 < nums.length && nums[i] + 1 != nums[i + 1]) {
                if (startIndex == i) {
                    resultList.add(nums[startIndex] + "");
                } else {
                    resultList.add(nums[startIndex] + "->" + nums[i]);
                }
                startIndex = i + 1;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,4,5,7};
        List<String> strings = summaryRanges(nums);
        System.out.println(strings);


    }
}
