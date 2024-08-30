package LeetCode;

import java.time.chrono.HijrahEra;
import java.util.*;

public class LeetCode_hot100_2 {




    //283 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    //
    //请注意 ，必须在不复制数组的情况下原地对数组进行操作。
    public void moveZeroes(int[] nums) {
        //从头遍历，将所有非零元素前移，最后填充末尾为0
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //11 盛最多水的容器
    // 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
    //
    //找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    //
    //返回容器可以储存的最大水量。
    public int maxArea(int[] height) {
        //双指针，存水量=较短边*索引之差，记录一个最大值并保持更新，移动较短边，直到大于长边。。。
        int indexL = 0, indexR = height.length-1;
        int maxArea = 0;
        while (indexL <= indexR){
            if (height[indexL] <= height[indexR]){
                maxArea = Math.max(maxArea, height[indexL]*(indexR-indexL));
                indexL++;
            }else {
                maxArea = Math.max(maxArea, height[indexR]*(indexR-indexL));
                indexR--;
            }
        }
        return maxArea;
    }

    //15 三数之和
    //给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
    // 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
    public List<List<Integer>> threeSum(int[] nums) {
        //先排序，设置第一个指针i，放在最小值处，然后双指针left，right，分别从左右两边逼近，如果二者之和大于-nums[i]，则right左移直到小于-nums[i]
        //然后移动left直到二者相遇或满足-nums[i]
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        if (n < 3) return null;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int right = nums.length-1;
            int target = -nums[i];
            for (int left = i+1; left < n; left++) {
                if (left > i+1 && nums[left] == nums[left-1]) continue;
                while (left < right && nums[left] + nums[right] > target){
                    right--;
                }
                if (left == right) break;
                if (target == nums[left] + nums[right]){
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[left]);
                    list1.add(nums[right]);
                    list.add(list1);
                }
            }
        }
        return list;
    }

    //42 接雨水
    //给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    public int trap(int[] height) {
        //解法1：动态规划。分别遍历出每个下标对应的，左右两边的最大值；最后计算出该下标的村水量=min(左右最大值)-下标对应值
        //解法2：双指针。以leftMax和RightMax作为左右最大值，从两边向中间遍历，优先移动较小侧的指针，如果遇到小于Max的值，则取Max-height[i]加入结果值。
        int n = height.length;
        int left = 0, right = n-1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while (left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax-height[left];
                left++;
            }else{
                ans += rightMax-height[right];
                right--;
            }
        }
        return ans;
    }

    //3 无重复字符的最长子串
    //给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
    public int lengthOfLongestSubstring(String s) {
        //设置滑动窗口，从左侧开始，移动右指针，将值放入set，直到遇到重复字符；此时移动左指针
        Set<Character> set = new HashSet<>();
        int right = 0;
        int subLength = 0;
        for (int left = 0; left < s.length(); left++) {
            if (left != 0){
                set.remove(s.charAt(left-1));
            }
            while (right < s.length() && !set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
            }
            subLength = Math.max(right-left, subLength);
        }
        return subLength;
    }

    //438 找到字符串中所有字母异位词
    // 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
    public List<Integer> findAnagrams(String s, String p) {
        //创建两个数组存放各字母出现的次数，先把p和s的前p.length()个放入，作为滑动窗口，依次迭代比较数组值
        int[] pc = new int[26];
        int[] sc = new int[26];
        List<Integer> list = new ArrayList<>();
        if (p.length() > s.length()) return list;
        for (int i = 0; i < p.length(); i++) {
            ++pc[p.charAt(i) - 'a'];
            ++sc[s.charAt(i) - 'a'];
        }
        if (Arrays.equals(pc, sc)) list.add(0);
        for (int i = p.length(); i < s.length(); i++) {
            ++sc[s.charAt(i) - 'a'];
            --sc[s.charAt(i-p.length()) - 'a'];
            if (Arrays.equals(pc, sc)) list.add(i-p.length()+1);
        }
        return list;
    }










}
