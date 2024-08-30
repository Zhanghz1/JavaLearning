package LeetCode;

public class Programmercarl {

    //27 移除元素， 类似：283 移动零
    public int removeElement(int[] nums, int val) {
        //双指针，一个按序指向下一个元素，一个指向非val元素
        int n = nums.length;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val) continue;
            nums[index++] = nums[i];
        }
        return index;
    }

    //209. 长度最小的子数组
    //给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其总和大于等于 target 的长度最小的子数组
    // [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    public int minSubArrayLen(int target, int[] nums) {
        //双指针，先移动右指针直到和大于等于target，（更新minLength），再移动左指针，直到和小于target
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target){
                minLength = Math.min(minLength, right-left+1);
                sum -= nums[left++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    //59 螺旋矩阵2
    //给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
    public int[][] generateMatrix(int n) {
        //设置填充边界top bottom left right， 依次填充
        int target = n*n;
        int num = 1;
        int top = 0, bottom = n-1, left = 0, right = n-1;
        int[][] result = new int[n][n];
        while (num <= target){
            //先从左到右
            for (int i = left; i <= right; i++) {
                result[top][i] = num++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result[i][right] = num++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                result[bottom][i] = num++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                result[i][left] = num++;
            }
            left++;
        }
        return result;
    }





}
