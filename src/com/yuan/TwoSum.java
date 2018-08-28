package com.yuan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * 示例：
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @auhtor yuanshuwei
 * @create 2018-08-25 下午4:00
 */
public class TwoSum {

    public int[] solution1(int[] nums, int target) {

        int res[] = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 指针移动
     * @param nums
     * @param target
     * @return
     */
    public int[] solution2(int[] nums, int target) {

        int res[] = new int[2];

        int[] copyArr = new int[nums.length];
        System.arraycopy(nums, 0, copyArr, 0, nums.length);
        Arrays.parallelSort(copyArr);

        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int sum = copyArr[low] + copyArr[high];
            if (sum < target) {
                low++;
            }else if (sum > target) {
                high--;
            }else {
                break;
            }
        }

        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == copyArr[low] && index1 == -1) {
                res[0] = i;
                index1 = 1;
            } else if (nums[i] == copyArr[high] && index2 == -1) {
                res[1] = i;
                index2 = 1;
            }
        }
        return res;
    }

    /**
     *保持数组中的元素与索引对应的最好方式就是哈希表
     *
     * 两遍哈希表
     * @param nums
     * @param target
     * @return
     */
    public int[] solution3(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (map.containsKey(remain) && map.get(remain) != i) {
                return new int[]{i, map.get(remain)};
            }
        }
        throw new IllegalArgumentException("not exists");
    }

    /**
     * 一遍哈希表
     * @param nums
     * @param target
     * @return
     */
    public int[] solution4(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (map.containsKey(remain) && map.get(remain) != i) {
                return new int[]{map.get(remain), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("not exists");
    }

    public static void main(String[] args) {


        // 3 2 4 - 6
        // 3 3 - 6
        int[] numbers = new int[]{3, 2, 4};
        int target = 6;

        long start = System.currentTimeMillis();
        int[] resArr = new TwoSum().solution4(numbers, target);
        long end = System.currentTimeMillis();

        System.out.println("用时:" + (end-start));

        System.out.println(Arrays.toString(resArr));
    }
}
