package LeetCode;

public class SumOfTwoNum {
    /*给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

    你可以按任意顺序返回答案。*/
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int val;
            val = target-nums[i];
            result[0] = i;
            for (int j = i+1; j < nums.length; j++) {
                if (val == nums[j]){
                    //找到了和为target的整数
                    result[1] = j;
                    break;
                }
            }
            if (result[1] != 0) break;
        }
        return result;
    }
}
